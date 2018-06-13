package indi.smt.uno.crawler.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Photo implements Serializable {
    private Integer id;

    private Integer cateId;

    private String title;

    private Date insertTime;

    private Date createTime;

    private Integer isShow;
}