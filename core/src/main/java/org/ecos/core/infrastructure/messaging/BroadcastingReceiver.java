package org.ecos.core.infrastructure.messaging;

public interface BroadcastingReceiver {
    BroadcastingReceiver setMe();
    void asReceiverOf(Dial someBroadcaster);

    <TMessage> TMessage onReceivingOnly(TMessage thisMessage);

    Iterable<Object> onReceivingAll();

    <TMessage> void addNewMessage(TMessage message);
}