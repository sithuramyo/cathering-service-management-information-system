package com.CSMIS.CSMISKhaingFamily.Function;

import java.io.IOException;



import java.io.InputStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.CSMIS.CSMISKhaingFamily.DAO.DoorlogRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.Doorlog;
import com.CSMIS.CSMISKhaingFamily.Entity.DoorlogKey;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;

@Service
public class DoorLogExcelReader {

    @Autowired
    DoorlogRepository doorlogRepository;
    
  

    public ValidationResult validateDoorlog(MultipartFile doorlogFile) throws IOException {
        ValidationResult validationResult = new ValidationResult();
        if (doorlogFile == null || doorlogFile.isEmpty()) {
            validationResult.addError("File is empty.");
            return validationResult;
        }

        if (!doorlogFile.getOriginalFilename().endsWith(".xlsx")) {
            validationResult.addError("Invalid file format. Only .xlsx files are allowed.");
            return validationResult;
        }

        try (InputStream inputStream = doorlogFile.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rowIterator = sheet.iterator();

                if (rowIterator.hasNext()) {
                    Row headerRow = rowIterator.next();
                    int numberOfColumns = headerRow.getPhysicalNumberOfCells();
                    if (numberOfColumns != 3) {
                        validationResult.addError("Wrong file.");
                        return validationResult;
                    }
                }

                Set<String> uniqueDates = new HashSet<>();
                Set<String> uniqueDoorLogNos = new HashSet<>();
                Set<String> uniqueCompositeKeys = new HashSet<>();

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    String date;
                    Date dateCellValue = row.getCell(1).getDateCellValue();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    date = dateFormat.format(dateCellValue);
                    Cell doorlogCell = row.getCell(2);
                    String doorlog;
                    if (doorlogCell.getCellType() == CellType.NUMERIC) {
                        doorlog = String.valueOf((int) doorlogCell.getNumericCellValue());
                    } else {
                        doorlog = doorlogCell.getStringCellValue();
                    }
                    String compositeKey = date + "_" + doorlog;

                    if (!uniqueCompositeKeys.add(compositeKey)) {
                        String[] parts = compositeKey.split("_");
                        String existingDate = parts[0];
                        String existingDoorlog = parts[1];
                        validationResult.addError("Duplicate values for Doorlog And Date: " + existingDate + " and " + existingDoorlog);
                    }

                    uniqueDates.add(date);
                    uniqueDoorLogNos.add(doorlog);
                }
            }
        }

        return validationResult;
    }


    public void importDoorlogExcel(MultipartFile doorlogFile) throws IOException {
    	ValidationResult validationResult = validateDoorlog(doorlogFile);

        if (validationResult.hasErrors()) {
            // Handle validation errors, e.g., return the errors or display them to the user
            return;
        }
    	try (InputStream inputStream = doorlogFile.getInputStream();
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                if (row.getCell(0) != null && row.getCell(1) != null &&  row.getCell(2) != null) {
                    String dept = row.getCell(0).getStringCellValue();
                    Cell dateCell = row.getCell(1);
                    String date;
                    if (dateCell.getCellType() == CellType.NUMERIC) {
                        Date dateValue = DateUtil.getJavaDate(dateCell.getNumericCellValue());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        date = dateFormat.format(dateValue);
                    } else {
                        date = dateCell.getStringCellValue();
                    }
                    Cell doorlogCell = row.getCell(2);
                    String doorlog;
                    if (doorlogCell.getCellType() == CellType.NUMERIC) {
                        doorlog = String.valueOf((int) doorlogCell.getNumericCellValue());
                    } else {
                        doorlog = doorlogCell.getStringCellValue();
                    }

                  
                    Doorlog door = new Doorlog();
                    DoorlogKey doorlogKey = new DoorlogKey();
                    doorlogKey.setDate(date);
                    doorlogKey.setDoorlogno(doorlog);
                    door.setId(doorlogKey);
                    door.setDepartment(dept);
                    doorlogRepository.save(door);
                }
            }
        }
    } catch (Exception e) {
        throw new WrongFileException("Wrong file");
    }
    }

}
