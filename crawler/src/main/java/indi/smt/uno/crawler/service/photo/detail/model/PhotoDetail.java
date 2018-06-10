package indi.smt.uno.crawler.service.photo.detail.model;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;
import indi.smt.uno.crawler.common.CommonConstacts;
import lombok.Data;

import java.util.List;

/**
 * @author SwordNoTrace
 * @date 2018/6/7 0:05
 */
@Data
@Gecco(matchUrl = CommonConstacts.BASE_URL + "{type}/{path}/", pipelines = "photoDetailHandlerPipeline")
public class PhotoDetail implements HtmlBean {

    @RequestParameter("type")
    private String type;

    @RequestParameter("path")
    private String path;

    @Text
    @HtmlField(cssPath = "#main h1")
	private String title;

    @HtmlField(cssPath = "#main .news .n_bd img")
    private List<PhotoImage> imageSrcList;
}
