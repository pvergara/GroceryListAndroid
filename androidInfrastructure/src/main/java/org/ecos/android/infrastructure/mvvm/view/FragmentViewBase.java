package org.ecos.android.infrastructure.mvvm.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.view.View;

public abstract class FragmentViewBase extends Fragment implements org.ecos.android.infrastructure.mvvm.view.View {

    protected FragmentActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void runOnUiThread(Runnable runnable) {
        mActivity.runOnUiThread(runnable);
    }
}
