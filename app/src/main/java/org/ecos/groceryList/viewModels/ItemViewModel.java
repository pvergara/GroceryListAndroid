package org.ecos.groceryList.viewModels;

import org.ecos.android.infrastructure.mvvm.binding.OnChangeListener;
import org.ecos.android.infrastructure.mvvm.binding.Property;
import org.ecos.groceryList.R;

public interface ItemViewModel {
    class Properties{
        public static Property changeItemText = Property.from(R.id.itemViewItemText);
        public static Property changeActionStatus = Property.from(R.id.itemViewActionButton);
    }
    void init();

    void setItemText(CharSequence itemText);

    void deInit();

    void setOnchangeListener(OnChangeListener onChangeListener);

    void sendItemText();
}
