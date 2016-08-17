package org.ecos.android.infrastructure.ui;

import android.support.design.widget.Snackbar;
import android.view.View;

public class UserCommunicationServiceImpl implements UserCommunicationService {
    public UserCommunicationServiceImpl() {
    }

    @Override
    public void show(CharSequence message, View view) {
        Snackbar.
            make(view, message, Snackbar.LENGTH_LONG).
            setAction("Action", null).
            show();
    }
}
