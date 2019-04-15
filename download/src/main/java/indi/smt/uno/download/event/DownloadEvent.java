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

	public DownloadEvent(Object source, List<String> segmentationList) {
		super(source);
		this.segmentationList = segmentationList;
	}
}
