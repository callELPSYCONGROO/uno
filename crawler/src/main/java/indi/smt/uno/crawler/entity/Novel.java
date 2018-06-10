package indi.smt.uno.crawler.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SwordNoTrace
 * @date 2018/6/10 21:58
 */
@Data
public class Novel implements Serializable {
	private Integer id;
	private String title;
	private String content;
	private String author;
	private String cls;
	private Integer isShow;
	private Date insertTime;
	private Date createTime;
}
