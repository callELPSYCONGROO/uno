package indi.smt.uno.crawler.novel.detail.model;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.spider.HtmlBean;
import indi.smt.uno.crawler.common.CommonConstacts;
import lombok.Data;

/**
 * @author SwordNoTrace
 * @date 2018/6/7 0:05
 */
@Data
@Gecco(matchUrl = CommonConstacts.BASE_URL + "{type}/{path}/", pipelines = "novelDetailHandlerPipeline")
public class NovelDetail implements HtmlBean {

    @RequestParameter("type")
    private String type;

    @RequestParameter("path")
    private String path;

    @Text
    @HtmlField(cssPath = "#main h1")
	private String title;

    @Html
    @HtmlField(cssPath = "#main .news .n_bd")
    private String context;
}
