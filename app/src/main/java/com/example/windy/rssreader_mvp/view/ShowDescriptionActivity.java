package com.example.windy.rssreader_mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.windy.rssreader_mvp.presenter.*;
import com.example.windy.rssreader_mvp.R;
/**
 * Created by windy on 2017/3/15.
 */

public class ShowDescriptionActivity extends AppCompatActivity{
    Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_description);

        Intent intent = getIntent();

        presenter = new Presenter(this);
        String content = presenter.getContent(intent);

        TextView contentText = (TextView) this.findViewById(R.id.content);
        contentText.setText(content);

        Button backButton = (Button) this.findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
