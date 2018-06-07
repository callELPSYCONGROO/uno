package indi.smt.uno.crawler.photo.detail.model;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

/**
 * PhotoImage
 * @author: Overload
 * @date: 2018/6/7 21:55
 * @version: 1.0
 */
@Data
public class PhotoImage implements HtmlBean {

    @Image(value = "src")
    @HtmlField(cssPath = "img")
    private String img;
}
