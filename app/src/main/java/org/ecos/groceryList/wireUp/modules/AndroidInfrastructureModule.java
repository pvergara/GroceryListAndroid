package org.ecos.groceryList.wireUp.modules;

import org.ecos.android.infrastructure.mvvm.binding.BindingManager;
import org.ecos.android.infrastructure.mvvm.binding.BindingManagerImpl;
import org.ecos.android.infrastructure.ui.UserCommunicationService;
import org.ecos.android.infrastructure.ui.UserCommunicationServiceImpl;
import org.ecos.core.infrastructure.messaging.BroadcastingService;
import org.ecos.core.infrastructure.messaging.BroadcastingServiceImpl;
import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidInfrastructureModule {

    @Provides
    UserCommunicationService providesUserCommunicationService(){
        return new UserCommunicationServiceImpl();
    }

    @Provides
    BindingManager providesBindingManager(){
        return new BindingManagerImpl();
    }

    @Provides
    BroadcastingService providesMessagingService(){
        return new BroadcastingServiceImpl();
    }
}
