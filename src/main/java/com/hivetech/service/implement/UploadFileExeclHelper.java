package com.hivetech.service.implement;

import com.hivetech.model.Employee;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UploadFileExeclHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public String[] HEADERs = {"employeeId", "employeeAge", "employeeName", "employeeCity"};
    static String SHEET = "Sheet1";

    public static boolean HasExeclFormat(MultipartFile file){
        if(!TYPE.equals(file.getContentType())){
            return false;
        }
        return false;
    }
    public static List<Employee> excelEmployees(InputStream is){
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator <Row> rows = sheet.iterator();

            List<Employee> employees = new ArrayList<Employee>();

            int rowNumber = 0;
            while(rows.hasNext()){
                Row currentRow = rows.next();

                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }
                Iterator <Cell> cellsInRow =currentRow.iterator();

                Employee employee = new Employee();

                int cellIdx = 0;
                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx){
                        case 1:
                            employee.setEmployeeAge(currentCell.getNumericCellValue());
                            break;
                        case 2:
                            employee.setEmployeeName(currentCell.getStringCellValue());
                            break;
                        case 3:
                            employee.setEmployeeCity(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }
                employees.add(employee);
                }
            workbook.close();
            return employees;
            } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }

    }
}
