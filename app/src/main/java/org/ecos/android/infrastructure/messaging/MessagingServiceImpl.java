package org.ecos.android.infrastructure.messaging;

import org.greenrobot.eventbus.EventBus;

public class MessagingServiceImpl implements MessagingService {
    private EventBus mEventBus;

    public MessagingServiceImpl(EventBus eventBus) {
        mEventBus = eventBus;
    }

    @Override
    public <T> void send(T message) {
        mEventBus.postSticky(message);
    }

    @Override
    public void registerMe(Object object) {
        mEventBus.register(object);
    }

    @Override
    public void unRegisterMe(Object object) {
        if(mEventBus.isRegistered(object))
            mEventBus.unregister(object);
    }
}
