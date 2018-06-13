package indi.smt.uno.crawler.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Novel implements Serializable {
    private Integer id;

    private Integer cateId;

    private String title;

    private Date insertTime;

    private String author;

    private Integer isShow;

    private Date createTime;

    private String content;
}