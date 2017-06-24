package org.ecos.core.infrastructure.messaging;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BroadcastingServiceImpl implements BroadcastingService {

    private Object mMessage;

    private Dial mDialToSendANewMessage;
    private Map<Dial, Collection<BroadcastingReceiver>> mMapOfReceiversByDial;

    private List<Object> mMyBrandNewMessages;
    private BroadcastingReceiver mNewReceiverOfSomeUnknownDial;

    public BroadcastingServiceImpl() {
        mMapOfReceiversByDial = new HashMap<>();
        mMyBrandNewMessages = new ArrayList<>();
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
        mMessage = message;
    }

    @Override
    public BroadcastingEmitter toReceiversOf(Dial dial) {
        prepareReceiversOf(dial);
        return this;
    }

    private void prepareReceiversOf(Dial dial) {
        mDialToSendANewMessage = dial;
    }

    @Override
    public void emit() {
        startTheMagic();
    }

    private void startTheMagic() {
        Iterable<? extends BroadcastingReceiver> receivers = getReceiversOf(mDialToSendANewMessage);
        for (BroadcastingReceiver receiver : receivers) {
            receiver.addNewMessage(mMessage);
        }
    }

    private Collection<BroadcastingReceiver> getReceiversOf(Dial dialToSendANewMessage) {
        Collection<BroadcastingReceiver> broadcastingReceivers = mMapOfReceiversByDial.get(dialToSendANewMessage);
        if (broadcastingReceivers == null)
            broadcastingReceivers = new ArrayList<>();

        return broadcastingReceivers;
    }

    @Override
    public BroadcastingReceiver setMe() {
        mNewReceiverOfSomeUnknownDial = this;
        return this;
    }

    @Override
    public void asReceiverOf(Dial someBroadcaster) {
        addTheNewReceiverWithTheOthersListenersOf(someBroadcaster);
    }

    private void addTheNewReceiverWithTheOthersListenersOf(Dial someBroadcaster) {
        Collection<BroadcastingReceiver> receivers = getReceiversOf(someBroadcaster);
        receivers.add(mNewReceiverOfSomeUnknownDial);

        mMapOfReceiversByDial.put(someBroadcaster,receivers);
    }

    @Override
    public <TMessage> TMessage onReceivingOnly(TMessage thisMessage) {
        int index = mMyBrandNewMessages.indexOf(thisMessage);
        Object theMessage = mMyBrandNewMessages.get(index);
        mMyBrandNewMessages.remove(thisMessage);

        //YEP... I'll think about a better way!!!!!!!!!! (in the meantime ... I DON'T WANNA WARNING MESSAGES RECALLING ME MY FAIL)
        //noinspection unchecked
        return (TMessage) theMessage;
    }

    @Override
    public Iterable<Object> onReceivingAll() {
        Collection<Object> result = new ArrayList<>();
        for (Object someOfMyMessage:mMyBrandNewMessages) {
            Object theMessage = onReceivingOnly(someOfMyMessage);
            result.add(theMessage);
        }
        return result;
    }

    @Override
    public <TMessage> void addNewMessage(TMessage message) {
        mMyBrandNewMessages.add(message);
    }

}
