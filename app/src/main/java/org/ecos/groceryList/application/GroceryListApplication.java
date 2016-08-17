package org.ecos.groceryList.application;

import android.app.Application;

import org.ecos.groceryList.wireUp.AndroidInfrastructureComponent;
import org.ecos.groceryList.wireUp.AndroidInfrastructureModule;
import org.ecos.groceryList.wireUp.DaggerAndroidInfrastructureComponent;

public class GroceryListApplication extends Application {

    private AndroidInfrastructureComponent mAndroidInfrastructureComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAndroidInfrastructureComponent = DaggerAndroidInfrastructureComponent.
            builder().
            androidInfrastructureModule(new AndroidInfrastructureModule()).
            build();
    }

    public AndroidInfrastructureComponent getAndroidInfrastructureComponent() {
        return mAndroidInfrastructureComponent;
    }    
}