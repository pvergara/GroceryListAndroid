package org.ecos.core.tests.service.messaging;

import org.ecos.core.infrastructure.messaging.BroadcastingEmitter;
import org.ecos.core.infrastructure.messaging.BroadcastingServiceImpl;
import org.ecos.core.infrastructure.messaging.Dial;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class BroadcastServiceImplTests {
    @Test
    public void howToInitABroadcaster(){
        BroadcastingEmitter service = new BroadcastingServiceImpl();
        Dial dial = service.initANewBroadcasting();

        assertThat(dial.toString(),is(not(equalTo(""))));
    }
}
