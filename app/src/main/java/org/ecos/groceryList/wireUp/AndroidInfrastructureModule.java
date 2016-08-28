package org.ecos.groceryList.wireUp;

import org.ecos.android.infrastructure.messaging.MessagingService;
import org.ecos.android.infrastructure.messaging.MessagingServiceImpl;
import org.ecos.android.infrastructure.mvvm.binding.BindingManager;
import org.ecos.android.infrastructure.mvvm.binding.BindingManagerImpl;
import org.ecos.android.infrastructure.ui.UserCommunicationService;
import org.ecos.android.infrastructure.ui.UserCommunicationServiceImpl;
import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;

@Module
class AndroidInfrastructureModule {

    @Provides
    UserCommunicationService providesUserCommunicationService(){
        return new UserCommunicationServiceImpl();
    }

    @Provides
    BindingManager providesBindingManager(){
        return new BindingManagerImpl();
    }

    @Provides
    MessagingService providesMessagingService(){
        return new MessagingServiceImpl(EventBus.getDefault());
    }
}
