package com.hustascii.probook.activity;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hustascii.probook.R;
import com.hustascii.probook.view.FlowLayout;

import java.util.ArrayList;

public class SearchActivity extends ActionBarActivity {
    private ArrayList<String> keyword;
    private ArrayList<String> history;
    private TextView tvMore,tvClear;
    private FlowLayout KeyFlowLayout,HisFlowLayout;
    private int[] his_res=new int[]{R.drawable.tv_blue,R.drawable.tv_green,R.drawable.tv_purple};
    private int[] key_res=new int[]{R.drawable.tv_blue,R.drawable.tv_orange,R.drawable.tv_green};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        KeyFlowLayout= (FlowLayout) findViewById(R.id.keyword);
        HisFlowLayout= (FlowLayout) findViewById(R.id.his);
        tvMore= (TextView) findViewById(R.id.tv_more);
        tvClear= (TextView) findViewById(R.id.tv_clear);
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchActivity.this,"添加页面跳转",Toast.LENGTH_SHORT).show();
            }
        });
        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchActivity.this,"删除子控件",Toast.LENGTH_SHORT).show();
                HisFlowLayout.removeAllViews();
            }
        });
        initData();
        initKeyFlowLayout();
        initHisFlowLayout();
    }



    private void initData() {
        keyword=new ArrayList<String>();
        history=new ArrayList<String>();
        keyword.add("理财");
        keyword.add("易中天");
        keyword.add("励志");
        keyword.add("投资");
        keyword.add("悬疑");
        keyword.add("总裁");
        keyword.add("小说");
        keyword.add("道士");
        history.add("浪潮之巅");
        history.add("设计模式");
        history.add("C语言");
        history.add("乔布斯传");
        history.add("算法");

    }
    private void initHisFlowLayout() {
        LayoutInflater inflater=LayoutInflater.from(this);
        if(history.isEmpty()){
            return;
        }
        for(int i=0;i<history.size();i++){
            TextView btn=(TextView)inflater.inflate(R.layout.view_search_tv,HisFlowLayout,false);
            btn.setText(history.get(i).toString());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SearchActivity.this, "添加点击事件", Toast.LENGTH_SHORT).show();

                }
            });
            btn.setBackgroundResource(his_res[i%3]);
            HisFlowLayout.addView(btn);
        }

    }
    private void initKeyFlowLayout() {
        LayoutInflater inflater= LayoutInflater.from(this);
        if(keyword.isEmpty()){
            return;
        }
        for(int i=0;i<keyword.size();i++){
            TextView btn= (TextView) inflater.inflate(R.layout.view_search_tv,KeyFlowLayout,false);
            btn.setText(keyword.get(i).toString());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SearchActivity.this,"添加点击事件",Toast.LENGTH_SHORT).show();

                }
            });
            btn.setBackgroundResource(key_res[i%3]);
            KeyFlowLayout.addView(btn);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
