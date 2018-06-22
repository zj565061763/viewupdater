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
import android.view.ViewTreeObserver;

public abstract class ViewTreeObserverUpdater extends BaseViewUpdater
{
    @Override
    protected void onViewChanged(View newView, View oldView)
    {
        super.onViewChanged(newView, oldView);
        if (oldView != null)
            oldView.removeOnAttachStateChangeListener(mOnAttachStateChangeListener);
    }

    private final View.OnAttachStateChangeListener mOnAttachStateChangeListener = new View.OnAttachStateChangeListener()
    {
        @Override
        public void onViewAttachedToWindow(View v)
        {
            if (isStarted())
                startImplemention();
        }

        @Override
        public void onViewDetachedFromWindow(View v)
        {
        }
    };

    @Override
    protected void onStateChanged(boolean started)
    {
        super.onStateChanged(started);

        final View view = getView();
        if (view != null)
        {
            view.removeOnAttachStateChangeListener(mOnAttachStateChangeListener);
            if (started)
                view.addOnAttachStateChangeListener(mOnAttachStateChangeListener);
        }
    }

    @Override
    protected boolean startImplemention()
    {
        final View view = getView();
        if (view == null)
            return false;

        final ViewTreeObserver observer = view.getViewTreeObserver();
        if (!observer.isAlive())
            return false;

        unregister(observer);
        register(observer);
        return true;
    }

    @Override
    protected void stopImplemention()
    {
        final View view = getView();
        if (view == null)
            return;

        final ViewTreeObserver observer = view.getViewTreeObserver();
        if (!observer.isAlive())
            return;

        unregister(observer);
    }

    /**
     * 注册监听
     *
     * @param observer
     */
    protected abstract void register(ViewTreeObserver observer);

    /**
     * 取消注册监听
     *
     * @param observer
     */
    protected abstract void unregister(ViewTreeObserver observer);
}
