package com.CSMIS.CSMISKhaingFamily.Function;

import java.io.IOException;


import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;
import javax.mail.MessagingException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.CSMIS.CSMISKhaingFamily.DAO.HolidayRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.HolidayData;

@Service
public class HolidayExcelReader {
	@Autowired
	HolidayRepository holidayRepository;
	
	public ValidationResult validateHoliday(MultipartFile holidayFile) throws IOException {
	    ValidationResult validationResult = new ValidationResult();

	    if (holidayFile == null || holidayFile.isEmpty()) {
	        validationResult.addError("File is empty.");
	        return validationResult;
	    }

	    if (!holidayFile.getOriginalFilename().endsWith(".xlsx")) {
	        validationResult.addError("Invalid file format. Only .xlsx files are allowed.");
	        return validationResult;
	    }
	    InputStream inputStream = holidayFile.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        int numberOfSheets = workbook.getNumberOfSheets();

        for (int i = 0; i < numberOfSheets; i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                Row headerRow = rowIterator.next();
                if (headerRow.getCell(0) == null && headerRow.getCell(1) == null) {
                    validationResult.addError("Wrong File.");
                    return validationResult;
                }
            }
        
	    Set<String> uniqueDate = new HashSet<>();
	    while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
	    String holidayDate;
    	Date dateCellValue = row.getCell(0).getDateCellValue();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	holidayDate = dateFormat.format(dateCellValue);
    	if (uniqueDate.contains(holidayDate)) {
            validationResult.addError("Duplicate email found: " + holidayDate);
        }else {
        	uniqueDate.add(holidayDate);
        }
        }
        }
	    return validationResult;
	}

	public void holidayimportExcel(MultipartFile holidayFile) throws IOException, MessagingException{
		ValidationResult validationResult = validateHoliday(holidayFile);

        if (validationResult.hasErrors()) {
            // Handle validation errors, e.g., return the errors or display them to the user
            return;
        }
		List<HolidayData> existingDataList = holidayRepository.findAll();
		Map<String, HolidayData> existingDataMap = existingDataList.stream()
		        .collect(Collectors.toMap(HolidayData::getHolidayDate, Function.identity()));

		try(InputStream inputStream = holidayFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream)){
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();

		if (rowIterator.hasNext()) {
		    rowIterator.next();
		}

		while (rowIterator.hasNext()) {
		    Row row = rowIterator.next();
		    if (row.getCell(0) != null && row.getCell(1) != null) {

		    	String holidayDate;
		    	Date dateCellValue = row.getCell(0).getDateCellValue();
		    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    	holidayDate = dateFormat.format(dateCellValue);
		        String holidayName = row.getCell(1).getStringCellValue();

		        HolidayData existingData = existingDataMap.get(holidayDate);
		        if (existingData != null) {
		            existingData.setHolidayName(holidayName);
		            holidayRepository.save(existingData);
		        } else {
		            HolidayData newHoliday = new HolidayData(holidayDate, holidayName);
		            holidayRepository.save(newHoliday);
		        }
		    }
		}
		}catch(Exception e) {
			 throw new WrongFileException("Wrong file");	
	}
		
	}
}
