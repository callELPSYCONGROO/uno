package indi.smt.uno.crawler.qq_nab_img;

import com.geccocrawler.gecco.GeccoEngine;

/**
 * Hello world!
 *
 */
public class UnoCrawlerApplicationTest {

    public static void main( String[] args ) {
        GeccoEngine.create()
                .classpath("indi.smt.uno.crawler.qq_nab_img.model")
                .start("http://sports.qq.com/nba/")
                .interval(3000)
//                .loop(true)
                .start();
    }
}
