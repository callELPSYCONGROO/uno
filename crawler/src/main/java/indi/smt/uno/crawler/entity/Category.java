package indi.smt.uno.crawler.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Category implements Serializable {
    private Integer id;

    private String domain;

    private String cateName;

    private String parentName;
}