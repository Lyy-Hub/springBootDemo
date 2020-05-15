package com.lyy.service.api;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liyueyang on 2019/6/4.
 */
public interface ExcelOperationService {

    public Boolean exportE(HttpServletResponse response, String fileName);

    public Boolean importE(String fileName);

    public XSSFWorkbook exportExcelByTemplate(HttpServletRequest request, String paraMapJson);
}
