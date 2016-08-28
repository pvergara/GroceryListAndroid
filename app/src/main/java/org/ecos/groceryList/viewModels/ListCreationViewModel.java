package org.ecos.groceryList.viewModels;

import org.ecos.android.infrastructure.mvvm.binding.OnChangeListener;
import org.ecos.android.infrastructure.mvvm.binding.Property;
import org.ecos.android.infrastructure.mvvm.viewModel.ViewModel;
import org.ecos.groceryList.R;

import java.util.ArrayList;

public interface ListCreationViewModel extends ViewModel {
    class Properties{
        public static Property addItem = Property.from(R.id.listCreationGroceryList, "addItem");
    }
    void setOnchangeListener(OnChangeListener onChangeListener);

    void init();

    ArrayList<String> getCollection();

    void deInit();
}
