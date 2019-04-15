package indi.smt.uno.download.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * @author 無痕剑
 * @date 2019/4/16 0:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DownloadEvent extends ApplicationEvent {

	private List<String> segmentationList;

	private String title;

	private String category;

	public DownloadEvent(Object source, String title, String category, List<String> segmentationList) {
		super(source);
		this.title = title;
		this.category = category;
		this.segmentationList = segmentationList;
	}
}
