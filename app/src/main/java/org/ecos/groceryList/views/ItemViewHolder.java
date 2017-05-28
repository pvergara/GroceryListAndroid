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
import org.ecos.groceryList.events.ItemSendToUpdateEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressWarnings("ALL")
public class ItemViewHolder extends RecyclerView.ViewHolder {
    public static final String BACKGROUND_COLOR = "backgroundColor";
    private MessagingService mMessagingService;

    @BindView(R.id.item_as_text)
    TextView mItemText;

    @BindView(R.id.item_container)
    View mItemContainer;


    private Item mItem;

    public void bind(Item item) {
        mItem = item;

        updateViews();
    }

    private void updateViews() {
        mItemText.setText(mItem.getName().toString());
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
        showsSelectionAnimation(mItemContainer);
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
        mMessagingService.send(new ItemSendToUpdateEvent(mItem));
    }
}
