package org.ecos.groceryList.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ecos.android.infrastructure.ui.ItemTouchHelperAdapter;
import org.ecos.core.infrastructure.messaging.BroadcastingService;
import org.ecos.groceryList.R;
import org.ecos.groceryList.dtos.items.Items;
import org.ecos.groceryList.dtos.items.Item;
import org.ecos.groceryList.dtos.items.Name;
import org.ecos.groceryList.views.ItemViewHolder;

import java.util.Collections;

public class CustomAdapter extends RecyclerView.Adapter<ItemViewHolder> implements ItemTouchHelperAdapter {

    private BroadcastingService mBroadcastingService;
    private Items mCollection;
    private Name mName;

    @Override
    public void onItemDismiss(int position) {
        mCollection.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mCollection, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mCollection, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public void setCollection(Items collection) {
        mCollection = collection;
    }

    public CustomAdapter(BroadcastingService broadcastingService) {
        mBroadcastingService = broadcastingService;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup rootViewGroup, int viewType) {
        View view = LayoutInflater.
        from(rootViewGroup.getContext()).
        inflate(R.layout.fragment_list_creation_item, rootViewGroup, false);

        return new ItemViewHolder(view, mBroadcastingService);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = mCollection.get(position);

        holder.bind(item, mName);
        if (position == mCollection.size() - 1)
            mName = null;
    }

    @Override
    public int getItemCount() {
        return mCollection.size();
    }

    public void prepareToShowUpdate(Name sentValue) {
        mName = sentValue;
    }
}