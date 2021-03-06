package org.ecos.groceryList.wireUp.components;

import org.ecos.groceryList.views.ItemView;
import org.ecos.groceryList.views.ListCreationView;
import org.ecos.groceryList.views.MainActivity;
import org.ecos.groceryList.wireUp.modules.AndroidInfrastructureModule;
import org.ecos.groceryList.wireUp.modules.ViewModelsModule;

import dagger.Component;

@Component(modules = {AndroidInfrastructureModule.class,ViewModelsModule.class})
public interface GeneralComponent {
    void inject(MainActivity activity);
    void inject(ListCreationView view);
    void inject(ItemView itemView);
}