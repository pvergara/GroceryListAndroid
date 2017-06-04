package org.ecos.groceryList.dtos.items;

import org.ecos.groceryList.exceptions.FactoryException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemFactory {
    private final Collection<Item> mItems;
    private IdentitySecureCreator mSecureCreator;
    private Name mItemName;
    private Quantity mItemQuantity;

    private ItemFactory(Collection<Item> items) {
        mItems = items;
        IdentityFactory factory = new IdentityFactoryImpl();
        mSecureCreator = new IdentitySecureCreator(factory);
        mItemQuantity = Quantity.fromDefault();
    }

    public static ItemFactory using(Collection<Item> items) {
        return new ItemFactory(items);
    }

    Item createFrom(Name itemName) {
        Identity newIdentity = mSecureCreator.createANonRepeatedIdentity(getIdentitiesOf(mItems));

        Item item = new Item(newIdentity);
        item.setName(itemName);
        item.setQuantity(Quantity.fromDefault());

        return item;
    }

    private List<Identity> getIdentitiesOf(Collection<Item> items) {
        List<Identity> identities = new ArrayList<>();
        //noinspection Convert2streamapi
        for (Item item: items) {
            identities.add(item.getIdentity());
        }
        return identities;
    }

    public ItemFactory and(Name itemName) {
        mItemName = itemName;
        return this;
    }

    public ItemFactory and(Quantity quantity) {
        mItemQuantity = quantity;
        return this;
    }

    public Item create() throws FactoryException {
        if(!hasName())
            throw new FactoryException();

        Identity newIdentity = mSecureCreator.createANonRepeatedIdentity(getIdentitiesOf(mItems));

        Item item = new Item(newIdentity);
        item.setName(mItemName);
        item.setQuantity(mItemQuantity);

        return item;
    }

    private boolean hasName() {
        return mItemName!=null;
    }
}
