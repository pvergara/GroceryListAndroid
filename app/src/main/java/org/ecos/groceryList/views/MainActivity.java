package org.ecos.groceryList.views;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.ecos.groceryList.R;
import org.ecos.groceryList.application.GroceryListApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements org.ecos.android.infrastructure.mvvm.view.View {

    private FragmentManager mFragmentManager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(mToolbar);

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

            mFragmentManager.
                beginTransaction().
                replace(R.id.list_creation_fragment_container, listCreationView).
                replace(R.id.item_fragment_container, itemView).
                commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}