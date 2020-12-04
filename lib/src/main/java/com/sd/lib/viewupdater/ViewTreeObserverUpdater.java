package com.sd.lib.viewupdater;

import android.view.View;
import android.view.ViewTreeObserver;

import java.lang.ref.WeakReference;

public abstract class ViewTreeObserverUpdater extends BaseViewUpdater
{
    private WeakReference<ViewTreeObserver> mGlobalViewTreeObserver;

    private ViewTreeObserver getGlobalViewTreeObserver()
    {
        return mGlobalViewTreeObserver == null ? null : mGlobalViewTreeObserver.get();
    }

    private final View.OnAttachStateChangeListener mOnAttachStateChangeListener = new View.OnAttachStateChangeListener()
    {
        @Override
        public void onViewAttachedToWindow(View v)
        {
            mGlobalViewTreeObserver = new WeakReference<>(v.getViewTreeObserver());
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
    protected final boolean startImpl(View view)
    {
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
    protected final void stopImpl(View view)
    {
        if (view == null)
            return;

        final ViewTreeObserver observer = view.getViewTreeObserver();
        if (observer.isAlive())
            unregister(observer);

        final ViewTreeObserver globalObserver = getGlobalViewTreeObserver();
        if (globalObserver != null && globalObserver.isAlive())
            unregister(globalObserver);
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
