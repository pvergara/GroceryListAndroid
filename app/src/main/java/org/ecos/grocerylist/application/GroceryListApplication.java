package org.ecos.grocerylist.application;

import android.app.Application;

import org.ecos.grocerylist.wireUp.AndroidInfrastructureComponent;
import org.ecos.grocerylist.wireUp.AndroidInfrastructureModule;
import org.ecos.grocerylist.wireUp.DaggerAndroidInfrastructureComponent;

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