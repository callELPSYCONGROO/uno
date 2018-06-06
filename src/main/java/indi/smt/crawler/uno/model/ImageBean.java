package indi.smt.crawler.uno.model;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

/**
 * ImageBean
 * @author: Overload
 * @date: 2018/6/6 18:18
 * @version: 1.0
 */
@Data
public class ImageBean implements HtmlBean {

    @Image(value = "alt")
    @HtmlField(cssPath = "img")
    private String alt;

    @Image(value = "src", download = "/qq_nba_img")
    @HtmlField(cssPath = "img")
    private String url;
}
