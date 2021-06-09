package com.sd.lib.viewupdater;

import android.view.View;

public interface ViewUpdater {
    /**
     * 设置更新对象
     */
    void setUpdatable(Updatable updatable);

    /**
     * 通知更新对象{@link #setUpdatable(Updatable)}
     */
    void notifyUpdatable();

    /**
     * 返回设置的view
     */
    View getView();

    /**
     * 设置view
     */
    void setView(View view);

    /**
     * 是否已经开始监听
     *
     * @return true-已经开始
     */
    boolean isStarted();

    /**
     * 开始监听
     *
     * @return true-已经开始
     */
    boolean start();

    /**
     * 停止监听
     */
    void stop();

    interface Updatable {
        /**
         * 更新回调
         */
        void update();
    }
}