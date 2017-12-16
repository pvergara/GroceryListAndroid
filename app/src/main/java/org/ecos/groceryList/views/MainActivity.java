package org.ecos.groceryList.views;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import org.ecos.groceryList.R;
import org.ecos.groceryList.application.GroceryListApplication;

import butterknife.ButterKnife;

public class MainActivity
    extends
    AppCompatActivity
    implements
    org.ecos.android.infrastructure.mvvm.view.View {
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadDependencies();

        addTheFragments(savedInstanceState);
    }

    private void loadDependencies() {
        mFragmentManager = getSupportFragmentManager();

        GroceryListApplication application = (GroceryListApplication) getApplication();
        application.getGeneralComponent().inject(this);

        ButterKnife.bind(this);
    }

    private void addTheFragments(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            ListCreationView listCreationView = new ListCreationView();
            ItemView itemView = new ItemView();

            //@formatter:off
            mFragmentManager.
                beginTransaction().
                    replace(R.id.list_creation_fragment_container, listCreationView).
                    replace(R.id.item_fragment_container, itemView).
                commit();
            //@formatter:on
        }
    }
}