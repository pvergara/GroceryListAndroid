package org.ecos.android.infrastructure.messaging;

public interface MessagingService {
    <T> void send(T itemText);
}
