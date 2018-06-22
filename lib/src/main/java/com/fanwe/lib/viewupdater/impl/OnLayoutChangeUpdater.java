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

import android.view.View;

import com.fanwe.lib.viewupdater.BaseViewUpdater;

/**
 * 设置监听对象到view中来实现实时刷新
 * <p>
 * 监听对象：{@link View.OnLayoutChangeListener}
 */
public class OnLayoutChangeUpdater extends BaseViewUpdater
{
    @Override
    protected boolean startImplemention()
    {
        final View view = getView();
        if (view == null)
            return false;

        view.removeOnLayoutChangeListener(mListener);
        view.addOnLayoutChangeListener(mListener);
        return true;
    }

    @Override
    protected void stopImplemention()
    {
        final View view = getView();
        if (view == null)
            return;

        view.removeOnLayoutChangeListener(mListener);
    }

    private final View.OnLayoutChangeListener mListener = new View.OnLayoutChangeListener()
    {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom)
        {
            notifyUpdatable();
        }
    };
}
