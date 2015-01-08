package com.hustascii.probook.activity;

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
    private int[] color=new int[]{R.color.purple,R.color.orange,R.color.blue};
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
        keyword.add("Test");
        keyword.add("Test3");
        keyword.add("Te23st");
        keyword.add("Te");
        keyword.add("算法");
        keyword.add("Te112st");
        keyword.add("Test");
        keyword.add("Tes12313213t");
        keyword.add("T");
        history.add("2");
        history.add("3");
        history.add("4");
        history.add("5");
        history.add("6");
        history.add("7");
        history.add("8");
        keyword.add("T");
        history.add("2");
        history.add("3");
        history.add("4");
        history.add("5");
        history.add("6");
        history.add("7");
        history.add("8");
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
                    Toast.makeText(SearchActivity.this,"添加点击事件",Toast.LENGTH_SHORT).show();

                }
            });
            btn.setBackgroundResource(color[i%3]);
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
            btn.setBackgroundResource(color[i%3]);
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
