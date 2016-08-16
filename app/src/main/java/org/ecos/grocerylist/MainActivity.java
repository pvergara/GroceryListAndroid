package org.ecos.grocerylist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.ecos.android.infrastructure.ui.UserCommunicationService;
import org.ecos.android.infrastructure.ui.UserCommunicationServiceImpl;
import org.ecos.grocerylist.application.GroceryListApplication;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements org.ecos.android.infrastructure.mvvm.view.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    @Inject
    UserCommunicationService mUserCommunicationService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(mToolbar);

        loadDependencies();

    }

    private void loadDependencies() {
        ((GroceryListApplication) getApplication()).getAndroidInfrastructureComponent().inject(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab)
    public void floatingActionBarOnClick(View view) {
        mUserCommunicationService = new UserCommunicationServiceImpl();
        mUserCommunicationService.show("Replace with your own action", view);
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