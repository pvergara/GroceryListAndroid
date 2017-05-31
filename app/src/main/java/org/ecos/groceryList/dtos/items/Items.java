package org.ecos.groceryList.dtos.items;

import org.ecos.groceryList.exceptions.NotFoundException;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Items  extends AbstractList<Item> {
    private final IdentitySecureCreator mSecureCreator;
    private List<Item> mItems;

    public Items() {
        mItems = new ArrayList<>();

        IdentityFactory factory = new IdentityFactoryImpl();
        mSecureCreator = new IdentitySecureCreator(factory);
    }

    public void addAll(List<Name> names) {
        for (Name itemName :names) {
            add(itemName);
        }
    }

    private List<Identity> getIdentitiesOf(Collection<Item> items) {
        List<Identity> identities = new ArrayList<>();
        for (Item item: items) {
            identities.add(item.getIdentity());
        }
        return identities;
    }


    public void add(Name itemName) {
        Identity newIdentity = mSecureCreator.createANonRepeatedIdentity(getIdentitiesOf(mItems));

        Item item = new Item(newIdentity);
        item.setName(itemName);
        item.setQuantity(Quantity.fromDefault());

        mItems.add(item);
    }

    public int size() {
        return mItems.size();
    }

    public Item get(int position) {
        return mItems.get(position);
    }

    public Item lookFor(Item item) throws NotFoundException {
        for (Item itemToUpdate : mItems) {
            if(itemToUpdate.equals(item))
                return item;
        }
        throw new NotFoundException();
    }

    public Item lookFor(Name itemName) throws NotFoundException {
        for (Item item : mItems) {
            Name name = item.getName();
            if(name.equals(itemName))
                return item;
        }

        throw new NotFoundException();
    }

    @Override
    public Item remove(int index) {
        return mItems.remove(index);
    }

    @Override
    public Item set(int index, Item element) {
        return mItems.set(index, element);
    }
}
