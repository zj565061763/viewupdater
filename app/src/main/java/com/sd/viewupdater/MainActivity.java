package com.sd.viewupdater;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sd.lib.viewupdater.ViewUpdater;
import com.sd.lib.viewupdater.impl.OnPreDrawUpdater;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mButton;
    private final ViewUpdater mUpdater = new OnPreDrawUpdater() {
        @Override
        protected void onStateChanged(boolean started) {
            super.onStateChanged(started);
            Log.i(TAG, "onStateChanged started:" + started);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换选中状态
                v.setSelected(!v.isSelected());
            }
        });

        // 设置更新回调对象
        mUpdater.setUpdatable(new ViewUpdater.Updatable() {
            @Override
            public void update() {
                // 获得选中状态
                final boolean isSelected = mButton.isSelected();
                mButton.setTextColor(isSelected ? Color.RED : Color.BLACK);
                Log.i(TAG, "update isSelected:" + isSelected);
            }
        });

        // 设置监听哪个view
        mUpdater.setView(mButton);
        // 开始监听
        mUpdater.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 停止监听
        mUpdater.stop();
    }
}