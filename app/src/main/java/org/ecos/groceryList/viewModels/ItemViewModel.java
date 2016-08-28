package org.ecos.groceryList.viewModels;

import org.ecos.android.infrastructure.mvvm.binding.OnChangeListener;

public interface ItemViewModel {
    void init();

    void setItemText(CharSequence itemText);

    void setOnchangeListener(OnChangeListener onChangeListener);

    void sendItemText();
}
