package org.ecos.groceryList.application;

import android.app.Application;

import org.ecos.groceryList.wireUp.components.DaggerGeneralComponent;
import org.ecos.groceryList.wireUp.components.GeneralComponent;
import org.ecos.groceryList.wireUp.modules.ViewModelsModule;

public class GroceryListApplication extends Application {

    private GeneralComponent mGeneralComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //@formatter:off
        mGeneralComponent = DaggerGeneralComponent.
            builder().
                viewModelsModule(new ViewModelsModule()).
            build();
        //@formatter:on

    }

    public GeneralComponent getGeneralComponent() {
        return mGeneralComponent;
    }

}