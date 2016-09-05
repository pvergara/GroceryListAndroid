package org.ecos.groceryList.dtos.items;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IdentitySecureCreatorTest {

    @Test
    public void createANonRepeatedIdentity() throws Exception {
        //Arrange
        IdentityFactory factory = new IdentityFactoryImpl();

        IdentitySecureCreator secureCreator = new IdentitySecureCreator(factory);

        List<Identity> existentIdentities = Collections.singletonList(Identity.from(UUID.fromString("00000000-0000-0000-0000-000000000001")));

        //Act (...and exception)
        secureCreator.createANonRepeatedIdentity(existentIdentities);
    }

    @Test(expected=IllegalArgumentException.class)
    public void createANonRepeatedIdentityWhenTheCandidateIsAlwaysEqualsAsOneOfTheExistentIdentities() throws Exception {
        //Arrange
        IdentityFactory factory = mock(IdentityFactory.class);
        Identity candidate = Identity.from(UUID.fromString("700dcc38-6f8f-11e6-8b77-86f30ca893d3"));
        when(factory.create()).thenReturn(candidate);

        IdentitySecureCreator secureCreator = new IdentitySecureCreator(factory);

        List<Identity> existentIdentities = Collections.singletonList(candidate);

        //Act (...and exception)
        secureCreator.createANonRepeatedIdentity(existentIdentities);
    }

}