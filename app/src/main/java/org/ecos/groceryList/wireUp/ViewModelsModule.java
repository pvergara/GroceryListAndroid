package org.ecos.groceryList.wireUp;

import org.ecos.groceryList.viewModels.ListCreationViewModel;
import org.ecos.groceryList.viewModels.ListCreationViewModelImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelsModule {
    @Provides
    ListCreationViewModel providesListCreationViewModel(){
        return new ListCreationViewModelImpl();
    }

}
