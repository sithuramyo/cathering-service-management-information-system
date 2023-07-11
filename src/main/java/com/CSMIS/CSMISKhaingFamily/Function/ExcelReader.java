package com.CSMIS.CSMISKhaingFamily.Function;

import java.io.IOException;





import java.io.InputStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;

@Service
public class ExcelReader {
    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private Configuration configuration;
    
    private String generateCompositeKey(Operator operator) {
        return generateCompositeKey(operator.getStaffId(), operator.getDoorLogNo(), operator.getEmployeeEmail());
    }

    private String generateCompositeKey(String staffId, String doorlog, String email) {
        return staffId + "_" + doorlog + "_" + email;
    }

    private String getCellStringValue(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        }
        return "";
    }
    public ValidationResult validateExcel(MultipartFile employeeFile) throws IOException {
        ValidationResult validationResult = new ValidationResult();

        if (employeeFile == null || employeeFile.isEmpty()) {
            validationResult.addError("File is empty.");
            return validationResult;
        }
 
        if (!employeeFile.getOriginalFilename().endsWith(".xlsx")) {
            validationResult.addError("Invalid file format. Only .xlsx files are allowed.");
            return validationResult;
        }

        InputStream inputStream = employeeFile.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        int numberOfSheets = workbook.getNumberOfSheets();

        for (int i = 0; i < numberOfSheets; i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                Row headerRow = rowIterator.next();
                // Check if the required columns exist
                if (headerRow.getCell(0) == null || headerRow.getCell(1) == null ||
                    headerRow.getCell(2) == null || headerRow.getCell(3) == null ||
                    headerRow.getCell(4) == null || headerRow.getCell(5) == null ||
                    headerRow.getCell(6) == null) {
                    validationResult.addError("Wrong File.");
                    return validationResult;
                }
            }

            Set<String> uniqueEmails = new HashSet<>();
            Set<String> uniqueDoorLogNos = new HashSet<>();
            Set<String> uniqueStaffIds = new HashSet<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell doorlogCell = row.getCell(3);
                String doorlog;
                if (doorlogCell.getCellType() == CellType.NUMERIC) {
                    doorlog = String.valueOf((int) doorlogCell.getNumericCellValue());
                } else {
                    doorlog = doorlogCell.getStringCellValue();
                }
                String email = row.getCell(6).getStringCellValue();
                String staffId = row.getCell(1).getStringCellValue();

                String compositeKey = staffId + "_" + doorlog + "_" + email;

                if (uniqueEmails.contains(email)) {
                    validationResult.addError("Duplicate email found: " + email);
                } else if (uniqueDoorLogNos.contains(doorlog)) {
                    validationResult.addError("Duplicate DoorLogNo found: " + doorlog);
                } else if (uniqueStaffIds.contains(staffId)) {
                    validationResult.addError("Duplicate Staff ID found: " + staffId);
                } else {
                    uniqueEmails.add(email);
                    uniqueDoorLogNos.add(doorlog);
                    uniqueStaffIds.add(staffId);
                }
            }

            // Check for duplicates across email, doorlogno, and staffid
            Set<String> uniqueCompositeKeys = new HashSet<>();
            for (String compositeKey : uniqueEmails) {
                if (!uniqueCompositeKeys.add(compositeKey)) {
                    String[] parts = compositeKey.split("_");
                    String staffId = parts[0];
                    String doorlog = parts[1];
                    String email = parts[2];
                    validationResult.addError("Duplicate values for Staff ID: " + staffId + ", DoorLogNo: " + doorlog + ", and Email: " + email);
                }
            }
        }

        return validationResult;
    }

    public void importOperator(MultipartFile employeeFile) throws IOException {
        ValidationResult validationResult = validateExcel(employeeFile);

        if (validationResult.hasErrors()) {
            // Handle validation errors, e.g., return the errors or display them to the user
            return;
        }

        List<Operator> existingOperatorList = operatorRepository.findAll();
        Map<String, Operator> existingOperatorMap = existingOperatorList.stream()
                .collect(Collectors.toMap(operator -> generateCompositeKey(operator),
                        Function.identity()));

        try (InputStream inputStream = employeeFile.getInputStream();
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
                    if (row.getCell(0) != null && row.getCell(1) != null && row.getCell(2) != null 
                    	&& row.getCell(3) != null && row.getCell(4) != null && row.getCell(5) != null 
                    	&& row.getCell(6) != null) {
                        String division = row.getCell(0).getStringCellValue();
                        String staffId = row.getCell(1).getStringCellValue();
                        String employeeName = row.getCell(2).getStringCellValue();
                        String doorlog = getCellStringValue(row.getCell(3));
                        String deptName = row.getCell(4).getStringCellValue();
                        String teamName = row.getCell(5).getStringCellValue();
                        String email = row.getCell(6).getStringCellValue();
                        String compositeKey = generateCompositeKey(staffId, doorlog, email);

                        if (existingOperatorMap.containsKey(compositeKey)) {
                            Operator operator = existingOperatorMap.get(compositeKey);
                            operator.setEmployeeEmail(email);
                            operator.setDivision(division);
                            operator.setDept(deptName);
                            operator.setTeam(teamName);
                            operatorRepository.save(operator);
                            existingOperatorMap.remove(compositeKey);
                        } else {
                            Operator operator = new Operator();
                            operator.setDivision(division);
                            operator.setStaffId(staffId);
                            operator.setEmployeeName(employeeName);
                            operator.setDoorLogNo(doorlog);
                            operator.setDept(deptName);
                            operator.setTeam(teamName);
                            operator.setEmployeeEmail(email);
                            operator.setEmployeePassword(passwordEncoder.encode("12345"));
                            operator.setRole("OPERATOR");
                            operator.setStatus("ACTIVE");
                            operator.setToggle("false");
                            operatorRepository.save(operator);
                            sendMail(employeeName,email,"12345");
                        }
                    }
                }
            }

            // Set status of remaining operators in map to INACTIVE
            for (Operator existingData : existingOperatorMap.values()) {
                if (!existingData.getStaffId().equals("default_admin")) {
                    existingData.setStatus("INACTIVE");
                    operatorRepository.save(existingData);
                }
            }
        } catch (Exception e) {
            throw new WrongFileException("Wrong file");
        }
    }
    public void sendMail(String name,String useremail, String password) throws MessagingException, IOException, TemplateException {
        final String emailToRecipient = useremail;
        final String passwordToRecipient = password;
        final String nameToRecipient = name;

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
        Template template = configuration.getTemplate("email-template.ftl");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("name", nameToRecipient);
        templateData.put("email", emailToRecipient);
        templateData.put("password", passwordToRecipient);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateData);

        helper.setTo(emailToRecipient);
        helper.setText(html, true);
        helper.setSubject("CSMIS-Catering System Infomation");
        javaMailSender.send(message);
    }
 

 
}
