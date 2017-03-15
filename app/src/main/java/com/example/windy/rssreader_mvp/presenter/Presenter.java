package com.example.windy.rssreader_mvp.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.windy.rssreader_mvp.model.*;
import com.example.windy.rssreader_mvp.presenter.utils.*;
import com.example.windy.rssreader_mvp.view.*;

import org.xml.sax.SAXException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by windy on 2017/3/15.
 */

public class Presenter implements IPresenter {
    private MainActivity viewActivity;
    private ShowDescriptionActivity descriptionActivity;

    public Presenter(MainActivity viewActivity) {
        this.viewActivity = viewActivity;
    }

    public Presenter(ShowDescriptionActivity descriptionActivity) {
        this.descriptionActivity = descriptionActivity;
    }

    @Override
    public RssFeed getRssFeed(RssFeed feed, String RSS_URL) {
        try{
            feed = new RssFeed_SAXParser().getFeed(RSS_URL);
        }catch (ParserConfigurationException ex){
            ex.printStackTrace();
        }catch (SAXException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return feed;
    }

    @Override
    public void pShowListView(RssFeed feed, ListView itemList) {
        if (feed == null){
            viewActivity.setTitle("Unavailable RSS address");
            return ;
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(viewActivity,
                feed.getAllItems(), android.R.layout.simple_list_item_2,
                new String[]{RssItem.TITLE, RssItem.PUBDATE},
                new int[]{android.R.id.text1, android.R.id.text2});
        itemList.setAdapter(simpleAdapter);
        itemList.setOnItemClickListener(viewActivity);
        itemList.setSelection(0);
    }

    @Override
    public void pOnItemClick(Intent intent, RssFeed feed, int position) {

        Bundle bundle = new Bundle();
        bundle.putString("title", feed.getItem(position).getTitle());
        bundle.putString("description",feed.getItem(position).getDescription().toString());
        bundle.putString("link", feed.getItem(position).getLink());
        bundle.putString("pubDate", feed.getItem(position).getPubDate());

        intent.putExtra("android.intent.extra.rssItem", bundle);
    }

    @Override
    public String getContent(Intent intent) {
        String content;
        if (intent != null){
            Bundle bundle = intent.getBundleExtra("android.intent.extra.rssItem");
            if(bundle==null){
                content = "不好意思程序出错啦";
            }else{
                content = bundle.getString("title")
                        + bundle.getString("description") + "\n"
                        + "发布时间: " + bundle.getString("pubDate") + "\n"
                        + "\n详细信息请访问以下网址:\n"
                        + bundle.getString("link");
            }
        }else{
            content = "不好意思程序出错啦";
        }

        return content;
    }
}
