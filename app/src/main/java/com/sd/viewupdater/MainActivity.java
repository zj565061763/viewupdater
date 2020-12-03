package com.sd.viewupdater;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sd.lib.viewupdater.ViewUpdater;
import com.sd.lib.viewupdater.impl.OnPreDrawUpdater;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    private Button mButton;
    private final ViewUpdater mUpdater = new OnPreDrawUpdater();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 切换选中状态
                v.setSelected(!v.isSelected());
            }
        });

        // 设置状态变更回调
        mUpdater.setOnStateChangeCallback(new ViewUpdater.OnStateChangeCallback()
        {
            @Override
            public void onStateChanged(boolean started, ViewUpdater updater)
            {
                Log.i(TAG, "onStateChanged:" + started);
            }
        });

        // 设置view变化回调
        mUpdater.setOnViewChangeCallback(new ViewUpdater.OnViewChangeCallback()
        {
            @Override
            public void onViewChanged(View oldView, View newView, ViewUpdater updater)
            {
                Log.i(TAG, "onViewChanged:" + oldView + "," + newView);
            }
        });

        // 设置更新回调对象
        mUpdater.setUpdatable(new ViewUpdater.Updatable()
        {
            @Override
            public void update()
            {
                // 获得选中状态
                Log.i(TAG, "update:" + mButton.isSelected());
            }
        });

        // 设置监听哪个view
        mUpdater.setView(mButton);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // 开始监听
        mUpdater.start();
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        // 停止监听
        mUpdater.stop();
    }
}