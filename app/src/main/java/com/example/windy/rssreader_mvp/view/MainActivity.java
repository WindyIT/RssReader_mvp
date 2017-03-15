package com.example.windy.rssreader_mvp.view;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.windy.rssreader_mvp.R;
import com.example.windy.rssreader_mvp.model.*;
import com.example.windy.rssreader_mvp.presenter.*;

import java.util.List;


public class MainActivity extends AppCompatActivity
                          implements AdapterView.OnItemClickListener, IMainActivity{

    private RssFeed rssFeed = null;
    private final String RSS_URL = "http://rss.sina.com.cn/tech/rollnews.xml";
    private ListView listView;

    private Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());

        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.mList);
        presenter = new Presenter(this);

        rssFeed = presenter.getRssFeed(rssFeed, RSS_URL);
        showListView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(this, ShowDescriptionActivity.class);

        //在presenter里对intent进行数据操作
        presenter.pOnItemClick(intent, rssFeed, position);
        startActivityForResult(intent, 0);
    }

    @Override
    public void showListView(){
        presenter.pShowListView(rssFeed, listView);
    }
}
