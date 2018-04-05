package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_favorite;

import android.content.Context;
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
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BaseFragment;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.main.MainView;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.products_list.ProductAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductFavoriteFragment extends BaseFragment<ProductFavoritePresenter> implements
        ProductFavoriteView,
        SwipeRefreshLayout.OnRefreshListener,
        ProductAdapter.AdapterCallback {

    private static final String LIST_STATE = "ListState";

    @InjectPresenter
    ProductFavoritePresenter mProductFavoritePresenter;
    @BindView(R.id.rv_products)
    RecyclerView rvProducts;
    @BindView(R.id.tv_no_data_placeholder)
    FrameLayout tvNoDataPlaceholder;
    @BindView(R.id.spl_swipeContainer)
    SwipeRefreshLayout splSwipeContainer;
    @BindView(R.id.tv_title_results)
    TextView tvResultsTitle;

    private ProductFavoriteFragmentHost mMainView;
    private ProductAdapter mProductAdapter;

    public static ProductFavoriteFragment newInstance() {
        return new ProductFavoriteFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            mMainView = (ProductFavoriteFragmentHost) context;
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

        splSwipeContainer.setOnRefreshListener(this);
        splSwipeContainer.setColorSchemeResources(R.color.colorAccent);
        mProductAdapter = new ProductFavoriteAdapter(this);
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

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            Parcelable listState = savedInstanceState.getParcelable(LIST_STATE);
            rvProducts.getLayoutManager().onRestoreInstanceState(listState);

        } else {
            loadProducts();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save scroll position
        if (rvProducts != null) {
            outState.putParcelable(LIST_STATE, rvProducts.getLayoutManager().onSaveInstanceState());
        }
    }

    private void loadProducts() {
        mProductFavoritePresenter.loadProductsBase();
    }

    @Override
    public void onRefresh() {
        mProductFavoritePresenter.onRefresh();
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
        tvNoDataPlaceholder.setVisibility(View.INVISIBLE);
        if (mProductAdapter != null) {
            mProductAdapter.clear();
            mProductAdapter.addAll(results);
        }
    }

    @Override
    public void successRefreshed(List<Product> products) {
        hideProgress();
        splSwipeContainer.setRefreshing(false);
        onLoadedSuccess(products);
    }

    @Override
    public void successDeletedProduct(Product product) {
        showSnackBar(getString(R.string.remove_base_operation_message));
        mProductAdapter.remove(product);
    }

    @Override
    public void onProductItemClick(Bundle bundle) {
        /*get clone of product object*/
        Product product = bundle.getParcelable(ProductAdapter.AdapterCallback.PRODUCT_EXTRA);
        mMainView.showProductDetail(product);
    }

    @Override
    public void retryPageLoad() {
        //do nothing
    }

    @Override
    public void onFavoriteCheck(Bundle bundle) {
        /*get clone of product object*/
        Product product = bundle.getParcelable(ProductAdapter.AdapterCallback.PRODUCT_EXTRA);
        mProductFavoritePresenter.deleteProductFromFavorite(product);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(ProductFavoriteFragment.this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(OnChangedFavoriteProductsBaseEvent stickyEvent) {
        loadProducts();
        EventBus.getDefault().removeStickyEvent(stickyEvent); // don't forget to remove the sticky event if youre done with i
    }
}