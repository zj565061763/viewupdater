package com.sd.lib.viewupdater.impl;

import android.view.View;

import com.sd.lib.viewupdater.BaseViewUpdater;

/**
 * 通过{@link View.OnLayoutChangeListener}来实现更新
 */
public class OnLayoutChangeUpdater extends BaseViewUpdater {
    @Override
    protected boolean startImpl(View view) {
        if (view == null) {
            return false;
        }

        view.addOnLayoutChangeListener(mListener);
        return true;
    }

    @Override
    protected void stopImpl(View view) {
        if (view == null) {
            return;
        }

        view.removeOnLayoutChangeListener(mListener);
    }

    private final View.OnLayoutChangeListener mListener = new View.OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            notifyUpdatable();
        }
    };
}
