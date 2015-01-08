package com.hustascii.probook;

import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hustascii.probook.fragment.FragmentIndex;

import net.youmi.android.spot.SpotDialogListener;
import net.youmi.android.spot.SpotManager;

import it.neokree.materialnavigationdrawer.MaterialAccount;
import it.neokree.materialnavigationdrawer.MaterialAccountListener;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.MaterialSection;
import it.neokree.materialnavigationdrawer.MaterialSectionListener;


public class MainActivity extends MaterialNavigationDrawer implements MaterialAccountListener {


    MaterialSection section1, section2, recorder, night, last, settingsSection;

    @Override
    public void init(Bundle savedInstanceState) {

        // 加载插播资源
        SpotManager.getInstance(this).loadSpotAds();
        // 插屏出现动画效果，0:ANIM_NONE为无动画，1:ANIM_SIMPLE为简单动画效果，2:ANIM_ADVANCE为高级动画效果
        SpotManager.getInstance(this).setAnimationType(SpotManager.ANIM_ADVANCE);
        // 设置插屏动画的横竖屏展示方式，如果设置了横屏，则在有广告资源的情况下会是优先使用横屏图。
        SpotManager.getInstance(this).setSpotOrientation(
                SpotManager.ORIENTATION_PORTRAIT);



        // add first account
        MaterialAccount account = new MaterialAccount("魏鸿鑫","weihongxin@hustascii.com",this.getResources().getDrawable(R.drawable.photo),this.getResources().getDrawable(R.drawable.bamboo));
        this.addAccount(account);

        // set listener
        this.setAccountListener(this);

        // create sections
        section1 = this.newSection("首页",new FragmentIndex());
        section2 = this.newSection("收藏",new MaterialSectionListener() {
            @Override
            public void onClick(MaterialSection section) {
                Toast.makeText(MainActivity.this, "收藏", Toast.LENGTH_SHORT).show();

                // deselect section when is clicked
                section.unSelect();
            }
        });
        // recorder section with icon and 10 notifications
        recorder = this.newSection("蓝绿色",this.getResources().getDrawable(R.drawable.ic_mic_white_24dp),new FragmentIndex()).setNotifications(10);
        // night section with icon, section color and notifications
        night = this.newSection("天蓝色", this.getResources().getDrawable(R.drawable.ic_hotel_grey600_24dp), new FragmentIndex())
                .setSectionColor(Color.parseColor("#2196f3"),Color.parseColor("#1565c0")).setNotifications(150);
        // night section with section color
//        last = this.newSection("帮我赚钱", new FragmentButton()).setSectionColor(Color.parseColor("#ff9800"), Color.parseColor("#ef6c00"));
        last = this.newSection("帮我赚钱", new MaterialSectionListener() {
            @Override
            public void onClick(MaterialSection section) {
                // 展示插播广告，可以不调用loadSpot独立使用
                SpotManager.getInstance(MainActivity.this).showSpotAds(
                        MainActivity.this, new SpotDialogListener() {
                            @Override
                            public void onShowSuccess() {
                                Log.i("YoumiAdDemo", "展示成功");
                            }

                            @Override
                            public void onShowFailed() {
                                Log.i("YoumiAdDemo", "展示失败");
                            }

                            @Override
                            public void onSpotClosed() {
                                Log.i("YoumiAdDemo", "展示关闭");
                            }

                        }); // //
            }
        });

        Intent i = new Intent(this,ContactsContract.Profile.class);
        settingsSection = this.newSection("设置",this.getResources().getDrawable(R.drawable.ic_settings_black_24dp),i);

        // add your sections to the drawer
        this.addSection(section1);
        this.addSection(section2);
        this.addSubheader("其它");
        this.addSection(recorder);
        this.addSection(night);
        this.addDivisor();
        this.addSection(last);
        this.addBottomSection(settingsSection);

        this.setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_TO_FIRST);
    }


    @Override
    public void onAccountOpening(MaterialAccount account) {
        // open profile activity
        Intent i = new Intent(this,ContactsContract.Profile.class);
        startActivity(i);
    }

    @Override
    public void onChangeAccount(MaterialAccount newAccount) {

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
