package indi.smt.crawler.uno.model;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

import java.util.List;

/**
 * NbaBean
 * @author: Overload
 * @date: 2018/6/6 17:24
 * @version: 1.0
 */
@Data
@Gecco(matchUrl = "http://sports.qq.com/nba/", pipelines = "consolePipeline")
public class NbaBean implements HtmlBean {

    @HtmlField(cssPath = ".pic-item img")
    private List<ImageBean> imageBeans;

    @Text
    @HtmlField(cssPath = "title")
    private String title;
}
