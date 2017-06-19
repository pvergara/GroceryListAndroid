package org.ecos.core.infrastructure.messaging;

interface BroadcastingReceiver {
    <TReceiver> BroadcastingReceiver setMe(TReceiver me);
    void asReceiverOf(Dial someBroadcaster);

    <TMessage> void onReceivingOnly(TMessage thisMessage);

    void onReceiving(Object thisMessage);
}
