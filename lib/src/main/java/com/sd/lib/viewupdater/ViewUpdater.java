package com.sd.lib.viewupdater;

import android.view.View;

public interface ViewUpdater {
    /**
     * 设置更新回调对象
     *
     * @param updatable
     */
    void setUpdatable(Updatable updatable);

    /**
     * 通知设置的回调对象{@link #setUpdatable(Updatable)}进行更新
     */
    void notifyUpdatable();

    /**
     * 返回设置的view
     *
     * @return
     */
    View getView();

    /**
     * 设置view
     *
     * @param view
     */
    void setView(View view);

    /**
     * 是否已经开始监听
     *
     * @return
     */
    boolean isStarted();

    /**
     * 开始监听
     *
     * @return true-成功开始
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
