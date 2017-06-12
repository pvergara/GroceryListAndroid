package org.ecos.groceryList.views;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.ecos.android.infrastructure.messaging.MessagingService;
import org.ecos.groceryList.R;
import org.ecos.groceryList.dtos.items.Item;
import org.ecos.groceryList.dtos.items.Name;
import org.ecos.groceryList.dtos.items.Quantity;
import org.ecos.groceryList.events.ItemSentToUpdateEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressWarnings("ALL")
public class ItemViewHolder extends RecyclerView.ViewHolder {
    public static final String BACKGROUND_COLOR = "backgroundColor";
    private MessagingService mMessagingService;

    @BindView(R.id.item_as_text)
    TextView mNameText;

    @BindView(R.id.quantity_as_text)
    TextView mQuantityText;

    @BindView(R.id.item_container)
    View mItemContainer;


    private Item mItem;
    private Name mName;

    public void bind(Item item, Name name) {
        mItem = item;
        mName = name;


        updateViews();
    }

    private void updateViews() {
        if(mName!=null && mName.equals(mItem.getName())) {
            showsSelectionAnimation(mItemContainer);
            mName=null;
        }
        mNameText.setText(mItem.getName().toString());
        if(mItem.getQuantity().equals(Quantity.fromDefault()))
            mQuantityText.setVisibility(View.GONE);
        else {
            mQuantityText.setVisibility(View.VISIBLE);
            mQuantityText.setText(mItem.getQuantity().asString());
        }
    }


    public ItemViewHolder(View view, MessagingService messagingService) {
        super(view);
        initDependencies(view, messagingService);
    }

    private void initDependencies(View view, MessagingService messagingService) {
        mMessagingService = messagingService;
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.item_container)
    public void onClick() {
        sendBackToUpdate();
    }

    private void showsSelectionAnimation(View myView) {
        ObjectAnimator fadeIn = ObjectAnimator.ofArgb(myView, BACKGROUND_COLOR, Color.argb(128, 170, 245, 208), Color.argb(0, 170, 245, 208));
        fadeIn.setDuration(500);


        final AnimatorSet mAnimationSet = new AnimatorSet();
        mAnimationSet.play(fadeIn);
        mAnimationSet.start();
    }

    private void sendBackToUpdate() {
        mMessagingService.send(new ItemSentToUpdateEvent(mItem));
    }
}
