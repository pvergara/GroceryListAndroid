package org.ecos.groceryList.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.ecos.android.infrastructure.ui.UserCommunicationService;
import org.ecos.groceryList.R;
import org.ecos.groceryList.application.GroceryListApplication;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements org.ecos.android.infrastructure.mvvm.view.View {

    private FragmentManager mFragmentManager;
    private GroceryListApplication mApplication;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Inject
    UserCommunicationService mUserCommunicationService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(mToolbar);

        loadDependencies();

        addFragmentIfNotPreviouslyAdded(ListCreationFragment.class, R.id.fragment3);
        addFragmentIfNotPreviouslyAdded(ItemFragment.class, R.id.fragment4);
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    private <T extends Fragment> void addFragmentIfNotPreviouslyAdded(Class<T> fragmentClass, int fragmentContainerId){
        if (mFragmentManager.findFragmentById(fragmentContainerId) == null) {
            try {
                T fragment = fragmentClass.newInstance();
                getSupportFragmentManager().beginTransaction().add(fragmentContainerId,fragment).commit();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadDependencies() {
        mFragmentManager = getSupportFragmentManager();
        mApplication = (GroceryListApplication) getApplication();

        mApplication.getAndroidInfrastructureComponent().inject(this);

        ButterKnife.bind(this);
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