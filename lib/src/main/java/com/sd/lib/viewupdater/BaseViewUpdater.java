package com.sd.lib.viewupdater;

import android.view.View;

import java.lang.ref.WeakReference;

public abstract class BaseViewUpdater implements ViewUpdater {
    private boolean mIsStarted;
    private Updatable mUpdatable;
    private WeakReference<View> mView;

    @Override
    public final void setUpdatable(Updatable updatable) {
        mUpdatable = updatable;
    }

    @Override
    public final void notifyUpdatable() {
        if (isStarted()) {
            if (mUpdatable != null) {
                mUpdatable.update();
            }
        }
    }

    @Override
    public final View getView() {
        return mView == null ? null : mView.get();
    }

    @Override
    public final void setView(View view) {
        final View old = getView();
        if (old != view) {
            stop();
            mView = view == null ? null : new WeakReference<>(view);
            onViewChanged(old, view);
        }
    }

    @Override
    public final boolean isStarted() {
        if (getView() == null) {
            setStarted(false);
        }
        return mIsStarted;
    }

    @Override
    public final boolean start() {
        if (isStarted()) {
            return true;
        }

        final View view = getView();
        if (view == null) {
            return false;
        }

        final boolean startImpl = startImpl(view);
        setStarted(startImpl);
        return startImpl;
    }

    @Override
    public final void stop() {
        if (mIsStarted) {
            final View view = getView();
            if (view != null) {
                stopImpl(view);
            }

            setStarted(false);
        }
    }

    private final void setStarted(boolean started) {
        if (mIsStarted != started) {
            mIsStarted = started;
            onStateChanged(started);
        }
    }

    protected void onViewChanged(View oldView, View newView) {
    }

    protected void onStateChanged(boolean started) {
    }

    /**
     * 开始监听
     *
     * @param view
     * @return true-成功开始
     */
    protected abstract boolean startImpl(View view);

    /**
     * 停止监听
     *
     * @param view
     */
    protected abstract void stopImpl(View view);
}
