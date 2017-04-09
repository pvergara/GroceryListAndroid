package org.ecos.groceryList.wireUp.modules;

import org.ecos.android.infrastructure.messaging.MessagingService;
import org.ecos.groceryList.viewModels.ItemViewModel;
import org.ecos.groceryList.viewModels.ItemViewModelImpl;
import org.ecos.groceryList.viewModels.ListCreationViewModel;
import org.ecos.groceryList.viewModels.ListCreationViewModelImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelsModule {
    @Provides
    ListCreationViewModel providesListCreationViewModel(MessagingService messagingService){
        return new ListCreationViewModelImpl(messagingService);
    }

    @Provides
    ItemViewModel providesItemViewModel(MessagingService messagingService){
        return new ItemViewModelImpl(messagingService);
    }

}
