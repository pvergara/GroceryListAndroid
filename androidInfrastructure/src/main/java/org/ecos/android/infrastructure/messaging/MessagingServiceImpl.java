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
    public void registerMe(Object me) {

        if(!mEventBus.isRegistered(me))
            mEventBus.register(me);
    }

    @Override
    public void unRegisterMe(Object me) {
        if(mEventBus.isRegistered(me))
            mEventBus.unregister(me);
    }
}
