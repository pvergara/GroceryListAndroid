package org.ecos.groceryList.viewModels;

import org.ecos.android.infrastructure.mvvm.viewModel.ViewModel;

import java.util.ArrayList;

public interface ListCreationViewModel extends ViewModel {
    void init();

    ArrayList<String> getCollection();
}
