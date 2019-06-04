package com.lyy.pojo;

/**
 * Created by liyueyang on 2019/6/4.
 */
public class PageRequest<T> {
    private int num=0;
    private int size;
    private T paramContent;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public T getParamContent() {
        return paramContent;
    }

    public void setParamContent(T paramContent) {
        this.paramContent = paramContent;
    }
}
