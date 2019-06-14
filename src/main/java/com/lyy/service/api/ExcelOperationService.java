package com.lyy.service.api;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by liyueyang on 2019/6/4.
 */
public interface ExcelOperationService {

    public Boolean exportE(HttpServletResponse response, String fileName);

    public Boolean importE(String fileName);
}
