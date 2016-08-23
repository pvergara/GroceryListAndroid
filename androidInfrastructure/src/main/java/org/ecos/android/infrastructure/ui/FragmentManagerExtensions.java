package org.ecos.android.infrastructure.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class FragmentManagerExtensions {
    @SuppressWarnings("TryWithIdenticalCatches")
    public static <T extends Fragment> void addFragmentIfNotPreviouslyAdded(FragmentManager fragmentManager, Class<T> fragmentClass, int fragmentContainerId){
        if (fragmentManager.findFragmentById(fragmentContainerId) == null) {
            try {
                T fragment = fragmentClass.newInstance();
                fragmentManager.beginTransaction().add(fragmentContainerId,fragment).commit();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

}
