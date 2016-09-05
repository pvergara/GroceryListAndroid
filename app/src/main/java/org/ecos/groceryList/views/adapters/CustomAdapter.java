package org.ecos.groceryList.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ecos.android.infrastructure.messaging.MessagingService;
import org.ecos.groceryList.R;
import org.ecos.groceryList.dtos.Items;
import org.ecos.groceryList.dtos.items.Item;
import org.ecos.groceryList.events.ItemSendToUpdateEvent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>  {

    private MessagingService mMessagingService;

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final MessagingService mMessagingService;
        @BindView(R.id.textView) TextView textView;

        private Item mItem;

        TextView getTextView() {
            return textView;
        }

        ViewHolder(View view,MessagingService messagingService) {
            super(view);
            mMessagingService = messagingService;
            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            sendBackToUpdate();
        }

        private void sendBackToUpdate() {
            mMessagingService.send(new ItemSendToUpdateEvent(mItem));
        }

        void bind(Item item) {
            mItem = item;
        }
    }

    private Items mCollection;
    public void setCollection(Items collection) {
        mCollection = collection;
    }

    public CustomAdapter(MessagingService messagingService) {
        mMessagingService = messagingService;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup rootViewGroup, int viewType) {
        View view = LayoutInflater.
            from(rootViewGroup.getContext()).
            inflate(R.layout.fragment_list_creation_item, rootViewGroup, false);

        return new ViewHolder(view,mMessagingService);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = mCollection.get(position);

        holder.bind(item);

        holder.getTextView().setText(item.getName().toString());
    }

    @Override
    public int getItemCount() {
        return mCollection.size();
    }
}