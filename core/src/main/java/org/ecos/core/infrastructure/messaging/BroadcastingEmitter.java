package org.ecos.core.infrastructure.messaging;

public interface BroadcastingEmitter {
    Dial initANewBroadcasting();

    <TMessage> BroadcastingEmitter sendThis(TMessage anythingToEmit);
    BroadcastingEmitter toReceiversOf(Dial dial);
    void emit();
}
