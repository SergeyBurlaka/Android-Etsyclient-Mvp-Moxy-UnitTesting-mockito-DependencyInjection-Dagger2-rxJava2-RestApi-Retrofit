package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.search_fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.SearchParams;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Category;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BaseFragment;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchFragment extends BaseFragment<SearchPresenter> implements
        SearchView {

    @InjectPresenter
    SearchPresenter mSearchPresenter;
    @BindView(R.id.et_search_key_words)
    EditText etInputSearchWords;
    @BindView(R.id.s_category)
    NiceSpinner sCategory;
    @BindView(R.id.b_submit)
    Button bSubmit;
    Category mCurrentCategory;
    private SearchFragmentHost mMainView;
    private List<Category> mCategories = new ArrayList<>();

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SearchFragmentHost) {
            mMainView = (SearchFragmentHost) context;
        }
    }

    @Override
    public void onDetach() {
        mMainView = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_nope, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> categories = new ArrayList<>();
        sCategory.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.spinner_background));
        sCategory.setTextColor(ContextCompat.getColor(getActivity(), R.color.textWhite));
        sCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (mCategories != null) {
                    mCurrentCategory = mCategories.get(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        if (savedInstanceState == null) {
            //showProgress();
            categories.add(getString(R.string.spinner_catgory_init_message));
            sCategory.attachDataSource(categories);
            mSearchPresenter.loadCategories();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onLoadedCategoriesSuccess(List<Category> categories) {
        hideProgress();
        mCurrentCategory = categories.get(0);
        List<String> categoriesName = new ArrayList<>();
        for (Category category : categories) {
            categoriesName.add(category.getShortName());
        }
        mCategories = categories;
        sCategory.attachDataSource(categoriesName);
        sCategory.hideArrow();
    }

    @OnClick(R.id.b_submit)
    protected void onSubmitClick() {
        if (etInputSearchWords.getText().toString().isEmpty()) {
            showSnackBar(getString(R.string.no_keyword_faiil_load_result_message));
            return;
        }

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etInputSearchWords.getWindowToken(), 0);

        SearchParams searchParams = new SearchParams();
        List<String> keyWords = new ArrayList<>();
        String inputData = etInputSearchWords.getText().toString();

        String[] strings = inputData.split(" ");
        for (String word : strings) {
            if (!word.isEmpty()) {
                keyWords.add(word);
            }
        }

        searchParams.setKeywords(keyWords);
        searchParams.setCategory(mCurrentCategory.getName());
        mMainView.showProductFragment(searchParams);
    }

    @Override
    public void onLoadFailed(String message) {
        hideProgress();
        showSnackBar(message);
    }
}


