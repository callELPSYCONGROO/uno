package indi.smt.uno.save.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 無痕剑
 * @date 2019/4/15 0:11
 */
@Data
@Entity
@Table(name = "video")
public class Video {

	@Id
	private Long id;

	@Column
	private String title;

	@Column
	private String category;

	@Column
	private String download;

	@Column(name = "vDate")
	private String date;

	@Column
	private Integer isShow;

	@Column
	private Date createTime;
}
