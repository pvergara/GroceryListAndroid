package org.ecos.core.infrastructure.messaging;

public class BroadcastingServiceImpl implements BroadcastingService {

    public BroadcastingServiceImpl() {
    }

    @Override
    public Dial initANewBroadcasting() {
        return Dial.createANewDial();
    }

    @Override
    public <TMessage> BroadcastingEmitter sendThis(TMessage message) {
        prepareMessageToEmit(message);
        return this;
    }

    private <TMessage> void prepareMessageToEmit(TMessage message) {

    }

    @Override
    public BroadcastingEmitter toReceiversOf(Dial dial) {
        prepareReceiversOf(dial);
        return this;
    }

    private void prepareReceiversOf(Dial dial) {

    }

    @Override
    public void emit() {
        startTheMagic();
    }

    private void startTheMagic() {

    }

    @Override
    public <TReceiver> BroadcastingReceiver setMe(TReceiver me) {
        return null;
    }

    @Override
    public void asReceiverOf(Dial someBroadcaster) {
        addTheNewReceiverWithTheOtherThatListenTo(someBroadcaster);
    }

    private void addTheNewReceiverWithTheOtherThatListenTo(Dial someBroadcaster) {

    }

    @Override
    public <TMessage> void onReceivingOnly(TMessage thisMessage) {

    }

    @Override
    public void onReceiving(Object thisMessage) {

    }
}
