package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.products_list;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.SearchParams;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BaseFragment;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.main.MainView;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_favorite.OnChangedFavoriteProductsBaseEvent;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.pagination.PaginationScrollListener;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.preferences.PrefProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.SearchStringUtil.getSearchString;


public class ProductFragment extends BaseFragment<ProductPresenter> implements
        ProductView,
        SwipeRefreshLayout.OnRefreshListener,
        ProductAdapter.AdapterCallback,
        EasyPermissions.PermissionCallbacks {

    private static final String[] write_permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private final static int RC_WRITE_EXTERNAL_STORAGE = 102;

    private static final String SEARCH_PARAM_EXTRA = "SearchParamsExtra";
    private static final int FIRST_ITEM = 0;
    private static final int LOAD_ITEMS_LIMIT = 10;
    private static final String IS_LOADING_FLAG_STATE = "isLoading";
    private static final String IS_LAST_ITEM_FLAG_STATE = "isLastItems";
    private static final String OFFSET_STATE = "offset";
    private static final String LIST_STATE = "ListState";
    private static final String SEARCH_PARAMS_STATE = "SearchParams";
    @InjectPresenter
    ProductPresenter mProductPresenter;

    @BindView(R.id.rv_products)
    RecyclerView rvProducts;
    @BindView(R.id.tv_no_data_placeholder)
    FrameLayout tvNoDataPlaceholder;
    @BindView(R.id.spl_swipeContainer)
    SwipeRefreshLayout splSwipeContainer;
    @BindView(R.id.tv_title_results)
    TextView tvResultsTitle;

    private ProductFragmentHost mMainView;
    private ProductAdapter mProductAdapter;

    private boolean isLoading = false;
    private boolean isLastItems = false;
    private int offset = FIRST_ITEM;
    private SearchParams mSearchParams;
    private Product mSaveProduct;

    public static ProductFragment newInstance(SearchParams searchParams) {
        Bundle args = new Bundle();
        args.putParcelable(SEARCH_PARAM_EXTRA, searchParams);
        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            mMainView = (ProductFragmentHost) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSearchParams = getArguments().getParcelable(SEARCH_PARAM_EXTRA);

        splSwipeContainer.setOnRefreshListener(this);
        splSwipeContainer.setColorSchemeResources(R.color.colorAccent);
        mProductAdapter = new ProductAdapter(this);
        rvProducts.setHasFixedSize(true);

        final GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mProductAdapter.getItemViewType(position)) {
                    case ProductAdapter.ITEM:
                        return 1;
                    case ProductAdapter.LOADING:
                        return 2; //number of columns of the grid
                    default:
                        return -1;
                }
            }
        });

        rvProducts.setLayoutManager(mLayoutManager);
        rvProducts.setItemAnimator(new DefaultItemAnimator());
        rvProducts.setAdapter(mProductAdapter);
        rvProducts.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                offset += LOAD_ITEMS_LIMIT;
                loadNext();
            }

            @Override
            public boolean isLastItems() {
                return isLastItems;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            isLoading = savedInstanceState.getBoolean(IS_LOADING_FLAG_STATE, false);
            isLastItems = savedInstanceState.getBoolean(IS_LAST_ITEM_FLAG_STATE, false);
            mSearchParams = savedInstanceState.getParcelable(SEARCH_PARAMS_STATE);
            offset = savedInstanceState.getInt(OFFSET_STATE, 0);
            Parcelable listState = savedInstanceState.getParcelable(LIST_STATE);
            rvProducts.getLayoutManager().onRestoreInstanceState(listState);

        } else {
            loadProducts();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_LOADING_FLAG_STATE, isLoading);
        outState.putBoolean(IS_LAST_ITEM_FLAG_STATE, isLastItems);
        outState.putInt(OFFSET_STATE, offset);
        outState.putParcelable(SEARCH_PARAMS_STATE, mSearchParams);
        //save scroll position
        if (rvProducts != null) {
            outState.putParcelable(LIST_STATE, rvProducts.getLayoutManager().onSaveInstanceState());
        }
    }

    private void loadProducts() {
        showProgress();
        mProductPresenter.loadProducts(getSearchParams());
    }

    private void loadNext() {
        mProductPresenter.loadNext(getSearchParams());
    }

    @Override
    public void onRefresh() {
        //drop all values
        offset = FIRST_ITEM;
        isLastItems = false;
        isLoading = false;
        //reload products
        mProductPresenter.onRefresh(getSearchParams());
    }

    SearchParams getSearchParams() {
        if (mSearchParams == null) {
            return null;
        }
        mSearchParams.setOffset(offset);
        mSearchParams.setLimit(LOAD_ITEMS_LIMIT);
        return mSearchParams;
    }

    @Override
    public void onLoadFailed(String errorMessage) {
        splSwipeContainer.setRefreshing(false);
        hideProgress();
        showSnackBar(errorMessage);
    }

    @Override
    public void onLoadedSuccess(List<Product> results) {
        hideProgress();
        if (results == null || results.isEmpty()) {
            PrefProvider.getInstance().saveResultsCounter(0);
            if (mSearchParams != null) {
                tvResultsTitle.setTextColor(Color.RED);

                tvResultsTitle.setText(String.format(Locale.getDefault(), getString(R.string.no_results_holder), getSearchString(mSearchParams.getKeywords())));
            }
            return;
        }
        tvNoDataPlaceholder.setVisibility(View.INVISIBLE);

        if (mSearchParams != null && mSearchParams.getKeywords() != null) {
            tvResultsTitle.setText(
                    String.format(Locale.getDefault(),
                            getString(R.string.result_title),
                            PrefProvider.getInstance().getResultsCounter(),
                            getSearchString(mSearchParams.getKeywords())
                    ));
        }

        if (mProductAdapter != null) {
            mProductAdapter.clear();
            mProductAdapter.addAll(results);
            if (!isLastItems) {
                mProductAdapter.addLoadingFooter();
            }
        }
    }

    @Override
    public void onLoadedNextSuccess(List<Product> products) {
        splSwipeContainer.setRefreshing(false);
        if (products == null || products.isEmpty()) {
            mProductAdapter.removeLoadingFooter();
            isLastItems = true;
            return;
        }

        if (mProductAdapter != null) {
            mProductAdapter.removeLoadingFooter();
            isLoading = false;
            mProductAdapter.addAll(products);
            mProductAdapter.addLoadingFooter();
        }
    }

    @Override
    public void successRefreshed(List<Product> products) {
        splSwipeContainer.setRefreshing(false);
        onLoadedSuccess(products);
    }

    @Override
    public void interruptLoading() {
        this.isLastItems = true;
    }

    @Override
    public void successUpdateFavorite(Product product, boolean isAdded) {
        EventBus.getDefault().postSticky(new OnChangedFavoriteProductsBaseEvent());
        if (isAdded) {
            showSnackBar(getString(R.string.success_base_operation_message));
        } else {
            showSnackBar(getString(R.string.remove_base_operation_message));
        }
    }

    @Override
    public void onLoadNextFailed(String message) {
        if (mProductAdapter != null) {
            //show stub with retry button
            showSnackBar(message);
            mProductAdapter.showRetry(true, message);
        }
    }

    @Override
    public void onProductItemClick(Bundle bundle) {
        /*get clone of product object*/
        Product product = bundle.getParcelable(ProductAdapter.AdapterCallback.PRODUCT_EXTRA);
        mMainView.showProductDetail(product);
    }

    @Override
    public void retryPageLoad() {
        loadNext();
    }

    @Override
    public void onFavoriteCheck(Bundle bundle) {
        mSaveProduct = bundle.getParcelable(ProductAdapter.AdapterCallback.PRODUCT_EXTRA);
        onWrightPermissions();
    }

    @AfterPermissionGranted(RC_WRITE_EXTERNAL_STORAGE)
    private void onWrightPermissions() {
        if (!EasyPermissions.hasPermissions(getActivity(), write_permissions)) {
            EasyPermissions.requestPermissions(this,
                    "This sample will write and read you extnernal storage",
                    RC_WRITE_EXTERNAL_STORAGE, write_permissions);
            return;
        } else {
            if (mSaveProduct != null && mSaveProduct.getMainImage() != null) {
                mProductPresenter.saveProductBase(mSaveProduct);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Some permissions have been granted
        // ...
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Some permissions have been denied
        // ...
        if (mSaveProduct != null && mSaveProduct.getMainImage() != null) {
            mProductPresenter.saveProductBase(mSaveProduct);
        }
    }
}