package com.wzh.mdmainui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * @author wang
 */
public class MainActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private Toolbar toolbar_default, toolbar_edit;
    private ImageView iv_defalut_left, iv_defalut_right;
    private EditText editText;
    private ImageView iv_edit_right;
    private boolean flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //通过vertical的偏移量除以totalScrollRange来获取百分比
                float progress = Math.abs(verticalOffset) / appBarLayout.getTotalScrollRange();
                Log.e("tag", "verticalOffset===" + verticalOffset);
                Log.e("tag", "progress===" + progress);
                Log.e("tag", "getTotalScrollRange===" + appBarLayout.getTotalScrollRange());
                if (verticalOffset == 0) {
                    toolbar_default.setVisibility(View.VISIBLE);
                    toolbar_edit.setVisibility(View.GONE);
                    iv_defalut_left.getBackground().setAlpha(255);
                    iv_defalut_right.getBackground().setAlpha(255);

                    toolbar_edit.getBackground().setAlpha(255);

                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    toolbar_default.setVisibility(View.GONE);
                    toolbar_edit.setVisibility(View.VISIBLE);
                    toolbar_default.getBackground().setAlpha(255);

                    toolbar_edit.getBackground().setAlpha(255);
                    editText.getBackground().setAlpha(255);
                   iv_edit_right.getBackground().setAlpha(255);


                } else {
                    //   中间状态，两个toolbar是重叠在一起的，根据是从折叠到展开，还是从展开到折叠，
                    // 同时加上进度progress
                    if (toolbar_edit.getVisibility() == View.GONE) {
                        flag = true;
                    } else if (toolbar_default.getVisibility() == View.GONE) {
                        flag = false;
                    }
                    toolbar_default.setVisibility(View.VISIBLE);
                    toolbar_edit.setVisibility(View.VISIBLE);


                    if (flag) { // 0到1变换
                        toolbar_default.getBackground().setAlpha((int) ((1 - progress) * 255));
                        iv_defalut_left.getBackground().setAlpha((int) ((1 - progress) * 255));
                        iv_defalut_right.getBackground().setAlpha((int) ((1 - progress) * 255));

                        toolbar_edit.getBackground().setAlpha((int) (progress * 255));
                        editText.getBackground().setAlpha((int) (progress * 255));
                        iv_edit_right.getBackground().setAlpha((int) (progress * 255));

                    } else {

                        toolbar_default.getBackground().setAlpha((int) (progress * 255));
                        iv_defalut_left.getBackground().setAlpha((int) (progress * 255));
                        iv_defalut_right.getBackground().setAlpha((int) (progress * 255));

                        toolbar_edit.getBackground().setAlpha((int) ((1 - progress) * 255));
                        editText.getBackground().setAlpha((int) ((1 - progress) * 255));
                        iv_edit_right.getBackground().setAlpha((int) ((1 - progress) * 255));
                    }


                }

            }
        });

    }

    private void initView() {
        appBarLayout = (AppBarLayout) findViewById(R.id.abl);
        toolbar_default = (Toolbar) findViewById(R.id.toolbar_default);
        toolbar_edit = (Toolbar) findViewById(R.id.toolbar_nono);
        iv_defalut_left = (ImageView) findViewById(R.id.iv_default_left);
        iv_defalut_right = (ImageView) findViewById(R.id.iv_default_right);
        iv_edit_right = (ImageView) findViewById(R.id.iv_nono_right);
        editText = (EditText) findViewById(R.id.et_nono_left);
    }
}
