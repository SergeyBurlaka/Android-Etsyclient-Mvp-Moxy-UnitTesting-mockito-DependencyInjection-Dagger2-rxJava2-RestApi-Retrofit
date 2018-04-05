package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.CategoryRepository;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.search_fragment.SearchPresenter;

import dagger.Subcomponent;


@Subcomponent(modules = {CategoryRepositoryModule.class})
@RepositoryScope
public interface CategoryRepositoryComponent {

    CategoryRepository getCommentsRepository();

    void inject(SearchPresenter searchPresenter);
}
