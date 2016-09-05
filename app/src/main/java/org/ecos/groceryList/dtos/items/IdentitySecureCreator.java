package org.ecos.groceryList.dtos.items;

import java.util.List;

class IdentitySecureCreator {
    private IdentityFactory mFactory;

    IdentitySecureCreator(IdentityFactory factory) {
        mFactory = factory;
    }

    Identity createANonRepeatedIdentity(List<Identity> existentIdentities){
        Identity cadidate = mFactory.create();

        int maxLoops = 30;
        int counter = 0;
        while(existentIdentities.contains(cadidate)){
            if(counter>=maxLoops)
                throw new IllegalArgumentException("Can not create different identity.");
            cadidate = mFactory.create();
            counter++;
        }
        return cadidate;
    }
}
