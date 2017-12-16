package org.ecos.core.tests.service.messaging;

import org.ecos.core.infrastructure.messaging.BroadcastingEmitter;
import org.ecos.core.infrastructure.messaging.BroadcastingReceiver;
import org.ecos.core.infrastructure.messaging.BroadcastingServiceImpl;
import org.ecos.core.infrastructure.messaging.Dial;
import org.ecos.core.tests.service.messaging.samples.VolumeContainer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.cthul.matchers.CthulMatchers.matchesPattern;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class BroadcastServiceImplTests {
    private static final String UUID_STRING_PATTERN = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

    private BroadcastingEmitter mEmitter;
    private BroadcastingReceiver mListener;

    @BeforeMethod
    public void setUp() throws Exception {
        mEmitter = new BroadcastingServiceImpl();
        mListener = new BroadcastingServiceImpl();
    }

    @Test
    public void howToInitABroadcaster() {
        mEmitter.initANewBroadcasting();
    }

    @Test
    public void whatReceivesWhenABroadcasterIsInitialized() {
        Dial dial = mEmitter.initANewBroadcasting();

        assertThat(dial.asString(), matchesPattern(UUID_STRING_PATTERN));
    }

    @Test
    public void howToInitAListener() {
        Dial dial = mEmitter.initANewBroadcasting();

        mListener.
            setMe().
            asReceiverOf(dial);
    }

    @Test
    public void hello_from_the_other_side(){

    }

    @Test
    public void howThisCrazyThingWorksJustASimpleExampleSendingText() {
        Dial dial = mEmitter.initANewBroadcasting();

        String theMessage = "HEEEELOOOO!!!!";

        mListener.
            setMe().
            asReceiverOf(dial);

        mEmitter.
            sendThis(theMessage).
            toReceiversOf(dial).
            emit();


        List<Object> messages = new ArrayList<>();

        Iterable<Object> iterator = mListener.onReceivingAll();

        for (Object item : iterator) {
            messages.add(item);
        }

        assertThat(messages,hasSize(equalTo(1)));
        assertThat(messages.get(0),is(equalTo(theMessage)));

    }

    @Test
    public void beCarefulTheOrderReallyMatters() {
        Dial dial = mEmitter.initANewBroadcasting();

        mEmitter.
            sendThis("HEEEELOOOO!!!!").
            toReceiversOf(dial).
            emit();

        mListener.
            setMe().
            asReceiverOf(dial);

        Collection<Object> messages = new ArrayList<>();

        Iterable<Object> iterator = mListener.onReceivingAll();

        for (Object item : iterator) {
            messages.add(item);
        }

        assertThat(messages,hasSize(equalTo(0)));

    }

    @Test
    public void nowWeWillSendAMoreComplexStuff(){
        Dial broadcasterDial =
            mEmitter.
                initANewBroadcasting();

        mListener.
            setMe().
            asReceiverOf(broadcasterDial);


        VolumeContainer moreComplexObject = VolumeContainer.newOne();
        VolumeContainer.Volume volume = moreComplexObject.volumes.get(0);
        VolumeContainer.Volume volume2= moreComplexObject.volumes.get(1);

        assertThat(moreComplexObject.volumes,hasSize(equalTo(3)));

        assertThat(volume.getName(),is(equalTo("va_85621143-1133-412f-83b4-57a01a552638_")));

        assertThat(volume2.getMapped_wwpns(),hasItem("2101001B32BD4280"));
        assertThat(volume2.getMapped_wwpns(),hasItem("2100001B329D4280"));
        assertThat(volume2.getMapped_wwpns(),hasItem("2101001B32BD637E"));

        mEmitter.
            sendThis(moreComplexObject).
            toReceiversOf(broadcasterDial).
            emit();


        List<Object> messages = new ArrayList<>();

        Iterable<Object> iterator = mListener.onReceivingAll();

        for (Object item : iterator) {
            messages.add(item);
        }

        assertThat(messages,hasSize(equalTo(1)));

        VolumeContainer messageReceived = (VolumeContainer) messages.get(0);

        VolumeContainer.Volume volumeReceived = messageReceived.volumes.get(0);
        VolumeContainer.Volume volumeReceived2 = messageReceived.volumes.get(1);

        assertThat(messageReceived.volumes,hasSize(equalTo(3)));

        assertThat(volumeReceived.getName(),is(equalTo("va_85621143-1133-412f-83b4-57a01a552638_")));

        assertThat(volumeReceived2.getMapped_wwpns(),hasItem("2101001B32BD4280"));
        assertThat(volumeReceived2.getMapped_wwpns(),hasItem("2100001B329D4280"));
        assertThat(volumeReceived2.getMapped_wwpns(),hasItem("2101001B32BD637E"));

    }

}