package org.ecos.android.infrastructure.messaging;

public interface MessagingService {
    <T> void send(T itemText);

    void registerMe(Object object);

    void unRegisterMe(Object object);
}
