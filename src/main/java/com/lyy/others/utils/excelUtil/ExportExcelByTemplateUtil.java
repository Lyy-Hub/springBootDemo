package com.lyy.others.utils.excelUtil;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by liYueYang on 2020/3/24.
 */
public class ExportExcelByTemplateUtil {

    public static XSSFWorkbook exportExcelByTemplateUtil(List dataList) {
        XSSFWorkbook cityExcel;
        XSSFWorkbook returnwb = new XSSFWorkbook();
        try {
            InputStream in = ExportExcelByTemplateUtil.class.getResourceAsStream("templet/道路日.xlsx");
            XSSFWorkbook cityExcelTemplet = new XSSFWorkbook(in);
            cityExcel = cityExcelTemplet;
            Sheet sheet1 = cityExcel.getSheetAt(0);
            for (int i = 0; i < dataList.size(); i++) {
                Map<String, Object> dataRow = (Map<String, Object>) dataList.get(i);
                int j = 0;
                Row row = getAndAppendRow(sheet1, 2 + i);
                getCell(row, j++).setCellValue(dataRow.get("orgno") == null ? "0" : (String) dataRow.get("orgno"));
                getCell(row, j++).setCellValue(dataRow.get("roadno") == null ? "0" : ((String) dataRow.get("roadno")));
                getCell(row, j++).setCellValue(dataRow.get("date") == null ? "0" : ((String) dataRow.get("date")));
                getCell(row, j++).setCellValue(dataRow.get("shouldincome") == null ? 0 : ((BigDecimal) dataRow.get("shouldincome")).doubleValue());
                getCell(row, j++).setCellValue(dataRow.get("realincome") == null ? 0 : ((BigDecimal) dataRow.get("realincome")).doubleValue());
                getCell(row, j++).setCellValue(dataRow.get("qz_times") == null ? 0 : ((BigDecimal) dataRow.get("qz_times")).doubleValue());
                getCell(row, j++).setCellValue(dataRow.get("qz_carsnum") == null ? 0 : ((BigDecimal) dataRow.get("qz_carsnum")).doubleValue());
                getCell(row, j++).setCellValue(dataRow.get("dt_operatingberth") == null ? 0 : ((BigDecimal) dataRow.get("dt_operatingberth")).doubleValue());
                getCell(row, j++).setCellValue(dataRow.get("dt_enableberth") == null ? 0 : ((BigDecimal) dataRow.get("dt_enableberth")).doubleValue());
                getCell(row, j++).setCellValue(dataRow.get("dy_pda_coverrate") == null ? 0 : ((BigDecimal) dataRow.get("dy_pda_coverrate")).doubleValue());
                getCell(row, j++).setCellValue(dataRow.get("payoutcount") == null ? 0 : ((BigDecimal) dataRow.get("payoutcount")).doubleValue());
                getCell(row, j++).setCellValue(dataRow.get("parkouttotal") == null ? 0 : ((BigDecimal) dataRow.get("parkouttotal")).doubleValue());
                getCell(row, j++).setCellValue(dataRow.get("dy_outpayrate") == null ? 0 : ((BigDecimal) dataRow.get("dy_outpayrate")).doubleValue());
            }
            returnwb = cityExcel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnwb;
    }

    public static Row getAndAppendRow(Sheet sheet, int row) {
        Row r = sheet.getRow(row);
        Row t = sheet.createRow(row + 1);
        t.setRowStyle(r.getRowStyle());
        for (int i = r.getFirstCellNum(); i < r.getLastCellNum(); i++) {
            t.createCell(i).setCellStyle(r.getCell(i).getCellStyle());
        }
        return r;
    }

    public static Cell getCell(Row row, int c) {
        Cell cell = row.getCell(c);
        if (cell == null) {
            cell = row.createCell(c);
        }
        return cell;
    }
}
