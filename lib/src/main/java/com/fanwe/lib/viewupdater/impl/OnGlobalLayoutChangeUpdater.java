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
package com.fanwe.lib.viewupdater.impl;

import android.os.Build;
import android.view.ViewTreeObserver;

import com.fanwe.lib.viewupdater.ViewTreeObserverUpdater;

/**
 * 设置监听对象到view中来实现实时刷新
 * <p>
 * 监听对象：{@link ViewTreeObserver.OnGlobalLayoutListener}
 */
public class OnGlobalLayoutChangeUpdater extends ViewTreeObserverUpdater
{
    @Override
    protected void register(ViewTreeObserver observer)
    {
        observer.addOnGlobalLayoutListener(mListener);
    }

    @Override
    protected void unregister(ViewTreeObserver observer)
    {
        if (Build.VERSION.SDK_INT >= 16)
            observer.removeOnGlobalLayoutListener(mListener);
        else
            observer.removeGlobalOnLayoutListener(mListener);
    }

    private final ViewTreeObserver.OnGlobalLayoutListener mListener = new ViewTreeObserver.OnGlobalLayoutListener()
    {
        @Override
        public void onGlobalLayout()
        {
            notifyUpdatable();
        }
    };
}
