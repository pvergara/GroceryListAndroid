package org.ecos.grocerylist.wireUp;

import org.ecos.android.infrastructure.ui.UserCommunicationService;
import org.ecos.android.infrastructure.ui.UserCommunicationServiceImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidInfrastructureModule {

    @Provides
    UserCommunicationService providesUserCommunicationService(){
        return new UserCommunicationServiceImpl();
    }
}
