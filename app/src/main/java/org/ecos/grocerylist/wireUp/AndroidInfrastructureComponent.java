package org.ecos.grocerylist.wireUp;

import org.ecos.grocerylist.MainActivity;

import dagger.Component;

@Component(modules = {AndroidInfrastructureModule.class})
public interface AndroidInfrastructureComponent {
    void inject(MainActivity activity);
}