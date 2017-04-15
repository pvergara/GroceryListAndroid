package org.ecos.groceryList.viewModels;

import org.ecos.android.infrastructure.mvvm.binding.OnChangeListener;
import org.ecos.android.infrastructure.mvvm.binding.Property;
import org.ecos.android.infrastructure.mvvm.viewModel.ViewModel;
import org.ecos.groceryList.R;
import org.ecos.groceryList.dtos.items.Items;

public interface ListCreationViewModel extends ViewModel {
    class Properties{
        public static Property addItem = Property.from(R.id.listCreationGroceryList, "addItem");
        public static Property updateItem = Property.from(R.id.listCreationGroceryList, "updateItem");
    }
    void setOnchangeListener(OnChangeListener onChangeListener);

    void init();

    Items getCollection();

    void deInit();
}
