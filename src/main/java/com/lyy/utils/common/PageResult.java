package com.lyy.utils.common;

import java.util.List;

/**
 * Created by liyueyang on 2019/6/3.
 */
/*  --- Json 格式自定义 ---
    策略	例子	备注
    LOWER_CAMEL_CASE	userName	默认策略
    KEBAB_CASE	user-name
    LOWER_CASE	username
    SNAKE_CASE	user_name
    UPPER_CAMEL_CASE	UserName
1、配置文件中加上：spring.jackson.property-naming-strategy=SNAKE_CASE
2、直接在需要自定义的对象上加注解：
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
3、直接在特定字段上加注解：@JsonProperty("user_name")
*/
public class PageResult<T>{
    private int num;
    private int size;
    private Long total;
    private int totalPages;
    private List<T> content;

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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
