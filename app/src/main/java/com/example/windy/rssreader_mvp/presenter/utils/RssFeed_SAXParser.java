package com.example.windy.rssreader_mvp.presenter.utils;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.example.windy.rssreader_mvp.model.*;
/**
 * Created by windy on 2017/3/15.
 */

public class RssFeed_SAXParser {
    public RssFeed getFeed(String urlStr) throws
            ParserConfigurationException, SAXException, IOException {
        URL url = new URL(urlStr);
        SAXParserFactory saxParserFactory =  SAXParserFactory.newInstance(); // Build SAXParserFactory
        SAXParser saxParser = saxParserFactory.newSAXParser(); // Factory generate the Parser
        XMLReader xmlReader = saxParser.getXMLReader(); //Get the XMLReader through the Parser

        RssHandler rssHandler = new RssHandler();
        xmlReader.setContentHandler(rssHandler);

        //is utf-8
        InputSource inputSource = null;
        inputSource = new InputSource(url.openStream());
        inputSource.setEncoding("UTF-8");
        xmlReader.parse(inputSource);

        return rssHandler.getRssFeed();
    }
}
