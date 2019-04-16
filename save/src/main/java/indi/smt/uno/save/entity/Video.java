package indi.smt.uno.save.entity;

import lombok.Data;

import javax.persistence.*;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column
	private String title;

	@Column
	private String category;

	@Column
	private String download;

	@Column
	private Date date;

	@Column
	private Integer isShow;

	@Column
	private Date createTime;
}
