package com.lyy.others.utils.excelUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelUtil {

    /**
     * 导出为 excel
     * @param response 响应头
     * @param data 文件名 + 表头 + 数据
     */
    public static void exportExcel(HttpServletResponse response, ExcelData data) {
        log.info("导出解析开始，fileName:{}",data.getFileName());
        try {
            //实例化HSSFWorkbook
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建一个Excel表单，参数为sheet的名字
            HSSFSheet sheet = workbook.createSheet("sheet");
            //设置表头
            setTitle(workbook, sheet, data.getHead());
            //设置单元格并赋值
            setData(sheet, data.getData());
            //设置浏览器下载
            setBrowser(response, workbook, URLEncoder.encode(data.getFileName(),"utf-8"));
            log.info("导出解析成功!");
        } catch (Exception e) {
            log.info("导出解析失败!");
            e.printStackTrace();
        }
    }

    /**
     * 方法名：setTitle
     * 功能：设置表头
     */
    private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, String[] str) {
        try {
            HSSFRow row = sheet.createRow(0);
            //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
            for (int i = 0; i <= str.length; i++) {
                sheet.setColumnWidth(i, 15 * 256);
            }
            //设置为居中加粗,格式化时间格式
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
            //创建表头名称
            HSSFCell cell;
            for (int j = 0; j < str.length; j++) {
                cell = row.createCell(j);
                cell.setCellValue(str[j]);
                cell.setCellStyle(style);
            }
        } catch (Exception e) {
            log.info("导出时设置表头失败！");
            e.printStackTrace();
        }
    }

    /**
     * 方法名：setData
     * 功能：表格赋值
     */
    private static void setData(HSSFSheet sheet, List<String[]> data) {
        try{
            int rowNum = 1;
            for (int i = 0; i < data.size(); i++) {
                HSSFRow row = sheet.createRow(rowNum);
                for (int j = 0; j < data.get(i).length; j++) {
                    row.createCell(j).setCellValue(data.get(i)[j]);
                }
                rowNum++;
            }
            log.info("表格赋值成功！");
        }catch (Exception e){
            log.info("表格赋值失败！");
            e.printStackTrace();
        }
    }

    /**
     * 方法名：setBrowser
     * 功能：使用浏览器下载
     */
    private static void setBrowser(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
        try {
            //清空response
            response.reset();
            //设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            //将excel写入到输出流中
            workbook.write(os);
            os.flush();
            os.close();
            log.info("设置浏览器下载成功！");
        } catch (Exception e) {
            log.info("设置浏览器下载失败！");
            e.printStackTrace();
        }

    }

    /**
     * 导入excel
     * @param fileName 文件路径 + 文件名
     * @return
     */
    public static List<Object[]> importExcel(String fileName) {
        log.info("导入解析开始，fileName:{}",fileName);
        try {
            List<Object[]> list = new ArrayList<>();
            InputStream inputStream = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            //获取sheet的行数
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rows; i++) {
                //过滤表头行
                if (i == 0) {
                    continue;
                }
                //获取当前行的数据
                Row row = sheet.getRow(i);
                Object[] objects = new Object[row.getPhysicalNumberOfCells()];
                int index = 0;
                //获取总列数
                int columnNum = row.getPhysicalNumberOfCells();
                for (int m = 0; m < columnNum; m++) {
                    Cell cell = row.getCell(m);
                    //设置单元格类型
                    cell.setCellType(CellType.STRING);
                    objects[index] = cell.getStringCellValue();//获取第i行的索引为0的单元格数据
                    index++;
                }
                list.add(objects);
            }
            log.info("导入文件解析成功！");
            return list;
        }catch (Exception e){
            log.info("导入文件解析失败！");
            e.printStackTrace();
        }
        return null;
    }

    //测试导入
    public static void main(String[] args) {
        /*HttpServletResponse httpServletResponse = null;
        ExcelData excelData = new ExcelData();
        excelData.setFileName("用户信息表");
        String[] head = {"姓名","地址","状态","时间"};
        excelData.setHead(head);

        List<String[]> userInfoList = new ArrayList<>();
        for(int i = 0;i < 10;i++){
            String[] qwe = {"姓名" + i,"地点" + i,"状态" + i,"时间" + i};
            userInfoList.add(qwe);
        }
        excelData.setData(userInfoList);
        exportExcel(httpServletResponse, excelData);*/

        try {
            String fileName = "G:/test.xlsx";
            List<Object[]> list = importExcel(fileName);
            for (int i = 0; i < list.size(); i++) {
                testExcelPojo testExcelPojo = new testExcelPojo();
                testExcelPojo.setUserName((String) list.get(i)[0]);
                testExcelPojo.setAddress((String) list.get(i)[1]);
                testExcelPojo.setStatus((String) list.get(i)[2]);
                testExcelPojo.setCreateTime((String) list.get(i)[3]);
                testExcelPojo.setOther((String) list.get(i)[4]);
                System.out.println(testExcelPojo.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
