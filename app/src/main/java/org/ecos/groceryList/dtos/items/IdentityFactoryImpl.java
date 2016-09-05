package org.ecos.groceryList.dtos.items;

import java.util.UUID;

public class IdentityFactoryImpl implements IdentityFactory {
    @Override
    public Identity create() {
        return Identity.from(UUID.randomUUID());
    }
}
