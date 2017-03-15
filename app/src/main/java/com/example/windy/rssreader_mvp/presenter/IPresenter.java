package com.example.windy.rssreader_mvp.presenter;

/**
 * Created by windy on 2017/3/15.
 */

import android.content.Intent;
import android.widget.ListView;

import com.example.windy.rssreader_mvp.model.*;

public interface IPresenter {
    RssFeed getRssFeed(RssFeed feed, String RSS_URL);
    void pShowListView(RssFeed feed, ListView itemList);
    void pOnItemClick(Intent intent, RssFeed feed, int position);

    String getContent(Intent intent);
}
