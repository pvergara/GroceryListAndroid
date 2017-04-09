package org.ecos.groceryList.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.ecos.android.infrastructure.messaging.MessagingService;
import org.ecos.groceryList.R;
import org.ecos.groceryList.dtos.items.Item;
import org.ecos.groceryList.events.ItemSendToUpdateEvent;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("ALL")
public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final MessagingService mMessagingService;
    @BindView(R.id.textView)
    TextView textView;

    private Item mItem;

    public TextView getTextView() {
        return textView;
    }

    public ItemViewHolder(View view, MessagingService messagingService) {
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

    public void bind(Item item) {
        mItem = item;
    }
}
