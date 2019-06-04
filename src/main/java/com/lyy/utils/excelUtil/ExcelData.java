package com.lyy.utils.excelUtil;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 路径：com.example.demo.entity
 * 类名：
 * 功能：导出导入实体
 * 备注：
 * 创建人：typ
 * 创建时间：2018/10/19 16:07
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@Data
public class ExcelData implements Serializable{

    //文件名称
    private String fileName;
    //表头数据
    private String[] head;
    //数据
    private List<String[]> data;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String[] getHead() {
        return head;
    }

    public void setHead(String[] head) {
        this.head = head;
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }
}
