package com.io.ms.utility;

import com.io.ms.entities.school.SchoolAdminReport1;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelGenerator {
    private List<SchoolAdminReport1> list;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List <SchoolAdminReport1> list) {
        this.list = list;
        workbook = new XSSFWorkbook();
    }
    private void writeHeader() {
        sheet = workbook.createSheet("SchoolReport1");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "state", style);
        createCell(row, 1, "city", style);
        createCell(row, 2, "id", style);
        createCell(row, 3, "name", style);
        createCell(row, 4, "contactNum", style);
        createCell(row, 5, "address", style);
        createCell(row, 6, "pincode", style);
        createCell(row, 7, "email", style);
        createCell(row, 8, "createdAt", style);
        createCell(row, 9, "Space", style);
        createCell(row, 10, "trainingPartCompleted", style);
        createCell(row, 11, "dateofCompletion", style);
        createCell(row, 12, "Space", style);
        createCell(row, 13, "dealClosed", style);
        createCell(row, 14, "discontinuedDate", style);
        createCell(row, 15, "schoolActive", style);
        createCell(row, 16, "schoolInterested", style);
        createCell(row, 17, "Space", style);
        createCell(row, 18, "agreementCompleted", style);
        createCell(row, 19, "agreementCompletedDate", style);
    }
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell == null) {
            // Handle null value as needed, e.g., set an empty string or default value
            cell.setCellValue("");
        } else if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if (valueOfCell instanceof LocalDate) {
            cell.setCellValue((LocalDate) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (SchoolAdminReport1 record: list) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getState(), style);
            createCell(row, columnCount++, record.getState(), style);
            createCell(row, columnCount++, record.getId(), style);
            createCell(row, columnCount++, record.getName(), style);
            createCell(row, columnCount++, record.getContactNum1(), style);
            createCell(row, columnCount++, record.getAddress1(), style);

            createCell(row, columnCount++, record.getPincode(), style);
            createCell(row, columnCount++, record.getEmail(), style);
            createCell(row, columnCount++, record.getCreatedAt(), style);
            createCell(row, columnCount++, record.getSpace1(), style);
            createCell(row, columnCount++, record.getTrainingPartCompleted(), style);
            createCell(row, columnCount++, record.getDateofCompletion(), style);

            createCell(row, columnCount++, record.getSpace2(), style);
            createCell(row, columnCount++, record.getDealClosed(), style);
            createCell(row, columnCount++, record.getDiscontinuedDate(), style);
            createCell(row, columnCount++, record.getSchoolActive(), style);
            createCell(row, columnCount++, record.getSchoolInterested(), style);
            createCell(row, columnCount++, record.getSpace3(), style);

            createCell(row, columnCount++, record.getAgreementCompleted(), style);
            createCell(row, columnCount++, record.getAgreementCompletedDate(), style);

        }
    }
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
