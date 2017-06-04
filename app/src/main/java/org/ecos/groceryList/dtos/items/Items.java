package org.ecos.groceryList.dtos.items;

import org.ecos.groceryList.exceptions.NotFoundException;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Items  extends AbstractList<Item> {
    private List<Item> mItems;

    public Items() {
        mItems = new ArrayList<>();
    }

    public void addAll(List<Name> names) {
        //noinspection Convert2streamapi
        for (Name itemName :names) {
            add(itemName);
        }
    }

    public void add(Name itemName) {
        Item newItem = ItemFactory.
        using(mItems).
            createFrom(itemName);

        mItems.add(newItem);
    }

    @Override
    public boolean add(Item item) {
        mItems.add(item);
        return true;
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
