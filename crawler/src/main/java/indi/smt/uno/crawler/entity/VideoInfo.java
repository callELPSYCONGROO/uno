package indi.smt.uno.crawler.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 無痕剑
 * @date 2019/4/14 21:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoInfo implements Serializable {

	private static final long serialVersionUID = 9132012500467556077L;

	private String title;

	private String category;

	private String download;

	private String date;
}
