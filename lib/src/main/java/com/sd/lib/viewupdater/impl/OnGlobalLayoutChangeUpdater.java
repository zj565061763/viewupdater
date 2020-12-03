package com.sd.lib.viewupdater.impl;

import android.os.Build;
import android.view.ViewTreeObserver;

import com.sd.lib.viewupdater.ViewTreeObserverUpdater;

/**
 * 通过{@link ViewTreeObserver.OnGlobalLayoutListener}来实现更新
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
        observer.removeOnGlobalLayoutListener(mListener);
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
