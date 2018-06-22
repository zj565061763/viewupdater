/*
 * Copyright (C) 2017 zhengjun, fanwe (http://www.fanwe.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fanwe.lib.viewupdater;

import android.view.View;

import java.lang.ref.WeakReference;

public abstract class BaseViewUpdater implements ViewUpdater
{
    private boolean mIsStarted;
    private Updatable mUpdatable;
    private WeakReference<View> mView;

    private OnStateChangeCallback mOnStateChangeCallback;
    private OnViewChangeCallback mOnViewChangeCallback;

    @Override
    public final void setUpdatable(Updatable updatable)
    {
        mUpdatable = updatable;
    }

    @Override
    public final void notifyUpdatable()
    {
        if (mUpdatable != null)
            mUpdatable.update();
    }

    @Override
    public final void setOnStateChangeCallback(OnStateChangeCallback callback)
    {
        mOnStateChangeCallback = callback;
    }

    @Override
    public final void setOnViewChangeCallback(OnViewChangeCallback callback)
    {
        mOnViewChangeCallback = callback;
    }

    @Override
    public final View getView()
    {
        return mView == null ? null : mView.get();
    }

    @Override
    public final void setView(View view)
    {
        final View old = getView();
        if (old != view)
        {
            stop();

            mView = view == null ? null : new WeakReference<>(view);

            onViewChanged(old, view);
            if (mOnViewChangeCallback != null)
                mOnViewChangeCallback.onViewChanged(old, view, this);
        }
    }

    protected void onViewChanged(View oldView, View newView)
    {
    }

    @Override
    public final boolean start()
    {
        if (isStarted())
            return true;

        setStarted(startImplemention());

        return isStarted();
    }

    @Override
    public final void stop()
    {
        stopImplemention();
        setStarted(false);
    }

    private final void setStarted(boolean started)
    {
        if (mIsStarted != started)
        {
            mIsStarted = started;

            onStateChanged(started);
            if (mOnStateChangeCallback != null)
                mOnStateChangeCallback.onStateChanged(started, this);
        }
    }

    protected void onStateChanged(boolean started)
    {
    }

    @Override
    public boolean isStarted()
    {
        if (getView() == null)
            setStarted(false);

        return mIsStarted;
    }

    /**
     * 开始实时更新
     *
     * @return true-成功开始
     */
    protected abstract boolean startImplemention();

    /**
     * 停止实时更新
     */
    protected abstract void stopImplemention();
}
