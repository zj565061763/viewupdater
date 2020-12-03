package com.sd.lib.viewupdater;

import android.view.View;

public interface ViewUpdater
{
    /**
     * 设置要更新回调对象
     *
     * @param updatable
     */
    void setUpdatable(Updatable updatable);

    /**
     * 通知设置的回调对象{@link #setUpdatable(Updatable)}进行更新
     */
    void notifyUpdatable();

    /**
     * 设置状态变化回调
     *
     * @param callback
     */
    void setOnStateChangeCallback(OnStateChangeCallback callback);

    /**
     * 设置view变化回调
     *
     * @param callback
     */
    void setOnViewChangeCallback(OnViewChangeCallback callback);

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
     * 开始监听
     *
     * @return true-成功开始
     */
    boolean start();

    /**
     * 停止监听
     */
    void stop();

    /**
     * 是否已经开始监听
     *
     * @return
     */
    boolean isStarted();

    interface Updatable
    {
        /**
         * 更新回调
         */
        void update();
    }

    interface OnStateChangeCallback
    {
        /**
         * 是否已经开始监听状态变化
         *
         * @param started
         * @param updater
         */
        void onStateChanged(boolean started, ViewUpdater updater);
    }

    interface OnViewChangeCallback
    {
        /**
         * 设置的view变化回调
         *
         * @param oldView
         * @param newView
         * @param updater
         */
        void onViewChanged(View oldView, View newView, ViewUpdater updater);
    }
}
