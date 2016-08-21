package org.ecos.groceryList.wireUp;

import org.ecos.groceryList.views.MainActivity;

import dagger.Component;

@Component(modules = {AndroidInfrastructureModule.class})
public interface AndroidInfrastructureComponent {
    void inject(MainActivity activity);
}