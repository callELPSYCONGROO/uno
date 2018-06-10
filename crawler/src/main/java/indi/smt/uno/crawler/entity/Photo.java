package indi.smt.uno.crawler.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SwordNoTrace
 * @date 2018/6/10 22:08
 */
@Data
public class Photo implements Serializable {
	private Integer id;
	private String titel;
	private String uri;
	private String cls;
	private Integer isShow;
	private Date insertTime;
	private Date createTime;
}
