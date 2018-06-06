package indi.smt.crawler.uno;

import com.geccocrawler.gecco.GeccoEngine;

/**
 * Hello world!
 *
 */
public class UnoCrawlerApplication {

    public static void main( String[] args ) {
        GeccoEngine.create()
                .classpath("indi.smt.crawler.uno.model")
                .start("http://sports.qq.com/nba/")
                .interval(3000)
//                .loop(true)
                .start();
    }
}
