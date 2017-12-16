package org.ecos.groceryList.wireUp.modules;

import org.ecos.core.infrastructure.messaging.BroadcastingService;
import org.ecos.groceryList.viewModels.ItemViewModel;
import org.ecos.groceryList.viewModels.ItemViewModelImpl;
import org.ecos.groceryList.viewModels.ListCreationViewModel;
import org.ecos.groceryList.viewModels.ListCreationViewModelImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelsModule {

    @Provides
    ListCreationViewModel providesListCreationViewModel(BroadcastingService broadcastingService){
        return new ListCreationViewModelImpl(broadcastingService);
    }

    @Provides
    ItemViewModel providesItemViewModel(BroadcastingService broadcastingService){
        return new ItemViewModelImpl(broadcastingService);
    }
}