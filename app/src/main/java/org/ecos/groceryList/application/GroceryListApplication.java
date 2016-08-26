package org.ecos.groceryList.application;

import android.app.Application;

import org.ecos.groceryList.wireUp.DaggerGeneralComponent;
import org.ecos.groceryList.wireUp.GeneralComponent;
import org.ecos.groceryList.wireUp.ViewModelsModule;

public class GroceryListApplication extends Application {

    private GeneralComponent mGeneralComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mGeneralComponent = DaggerGeneralComponent.
            builder().
//            androidInfrastructureModule(new AndroidInfrastructureModule()).
            viewModelsModule(new ViewModelsModule()).
            build();

    }

    public GeneralComponent getGeneralComponent() {
        return mGeneralComponent;
    }

}