package org.ecos.groceryList.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ecos.groceryList.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>  {
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView) TextView textView;
        TextView getTextView() {
            return textView;
        }

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private final ArrayList<String> mCollection;

    public CustomAdapter(ArrayList<String> collection) {
        mCollection = collection;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup rootViewGroup, int viewType) {
        View v = LayoutInflater.
            from(rootViewGroup.getContext()).
            inflate(R.layout.fragment_list_creation_item, rootViewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTextView().setText(mCollection.get(position));
    }

    @Override
    public int getItemCount() {
        return mCollection.size();
    }
}