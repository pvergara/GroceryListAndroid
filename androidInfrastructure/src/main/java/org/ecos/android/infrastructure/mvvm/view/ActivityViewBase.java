package org.ecos.android.infrastructure.mvvm.view;

import android.app.Activity;

public abstract class ActivityViewBase implements View {
    private final Activity mActivity;

    public ActivityViewBase(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void runOnUiThread(Runnable runnable) {
        mActivity.runOnUiThread(runnable);
    }
}
