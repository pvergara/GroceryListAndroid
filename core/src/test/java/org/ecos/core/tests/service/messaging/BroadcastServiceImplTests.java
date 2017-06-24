package org.ecos.core.tests.service.messaging;

import org.ecos.core.infrastructure.messaging.BroadcastingEmitter;
import org.ecos.core.infrastructure.messaging.BroadcastingReceiver;
import org.ecos.core.infrastructure.messaging.BroadcastingServiceImpl;
import org.ecos.core.infrastructure.messaging.Dial;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.cthul.matchers.CthulMatchers.matchesPattern;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

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
    public void howToInitAListener() {
        Dial dial = mEmitter.initANewBroadcasting();

        mListener.
            setMe().
            asReceiverOf(dial);
    }

    @Test
    public void whatReceivesWhenABroadcasterIsInitialized() {
        Dial dial = mEmitter.initANewBroadcasting();

        assertThat(dial.asString(), matchesPattern(UUID_STRING_PATTERN));
    }


    @Test
    public void howThisCrazyThingWorksASimpleExampleSendingText() {
        Dial dial = mEmitter.initANewBroadcasting();

        mListener.
            setMe().
            asReceiverOf(dial);

        mEmitter.
            sendThis("HEEEELOOOO!!!!").
            toReceiversOf(dial).
            emit();

        Collection<Object> messages = new ArrayList<>();

        Iterable<Object> iterator = mListener.onReceivingAll();

        for (Object item : iterator) {
            messages.add(item);
        }

        assertThat(messages,hasSize(equalTo(1)));

    }
}