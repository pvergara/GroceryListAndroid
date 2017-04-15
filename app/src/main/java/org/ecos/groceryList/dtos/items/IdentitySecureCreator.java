package org.ecos.groceryList.dtos.items;

import java.util.List;

public class IdentitySecureCreator {
    private IdentityFactory mFactory;

    public IdentitySecureCreator(IdentityFactory factory) {
        mFactory = factory;
    }

    public Identity createANonRepeatedIdentity(List<Identity> existentIdentities){
        Identity candidate = mFactory.create();

        int maxLoops = 30;
        int counter = 0;
        while(existentIdentities.contains(candidate)){
            if(counter>=maxLoops)
                throw new IllegalArgumentException("Can not create different identity.");
            candidate = mFactory.create();
            counter++;
        }
        return candidate;
    }
}
