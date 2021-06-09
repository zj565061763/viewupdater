package com.sd.lib.viewupdater.impl;

import android.view.ViewTreeObserver;

import com.sd.lib.viewupdater.ViewTreeObserverUpdater;

/**
 * 通过{@link ViewTreeObserver.OnPreDrawListener}来实现更新
 */
public class OnPreDrawUpdater extends ViewTreeObserverUpdater {
    @Override
    protected void register(ViewTreeObserver observer) {
        observer.addOnPreDrawListener(mListener);
    }

    @Override
    protected void unregister(ViewTreeObserver observer) {
        observer.removeOnPreDrawListener(mListener);
    }

    private final ViewTreeObserver.OnPreDrawListener mListener = new ViewTreeObserver.OnPreDrawListener() {
        @Override
        public boolean onPreDraw() {
            notifyUpdatable();
            return true;
        }
    };
}
