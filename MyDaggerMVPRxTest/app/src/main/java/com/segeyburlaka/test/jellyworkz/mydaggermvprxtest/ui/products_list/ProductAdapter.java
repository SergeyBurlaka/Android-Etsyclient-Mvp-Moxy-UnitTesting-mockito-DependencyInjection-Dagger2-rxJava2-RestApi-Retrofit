package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.products_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.MyEtsyAppApplication;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BaseListAdapter;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.GlideUtils;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.TextUtil.removeStartingIndex;


public class ProductAdapter extends BaseListAdapter<Product, ProductAdapter.ViewHolder> {

    public static final int ITEM = 0;
    public static final int LOADING = 1;

    private final WeakReference<AdapterCallback> mViewReference;
    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private String errorMsg;

    protected ProductAdapter(@NonNull AdapterCallback view, @NonNull List<Product> mUsers) {
        super(mUsers);
        mViewReference = new WeakReference<>(view);
    }

    protected ProductAdapter(@NonNull AdapterCallback view) {
        super();
        mViewReference = new WeakReference<>(view);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM) {
            //show items
            View viewItem = inflater.inflate(R.layout.item_product, parent, false);
            viewHolder = new ProductViewHolder(viewItem);
        } else if (viewType == LOADING) {
            //loading new in end of items
            View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
            viewHolder = new LoadingViewHolder(viewLoading);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int item) {
        int position = viewHolder.getAdapterPosition();
        Product product = getItems().get(position);
        int itemViewType = getItemViewType(position);

        if (itemViewType == ITEM) {
            ProductViewHolder productViewHolder = (ProductViewHolder) viewHolder;
            String productName = product.getName();
            //setting name, added NPE check(!)
            productViewHolder.tvChildName.setText(removeStartingIndex(productName != null && !productName.isEmpty() ? product.getName() : "No title"));

            //loading Image, added NPE check(!)
            GlideUtils.loadImage(
                    productViewHolder.ivProductThumbnail,
                    product.getMainImage() != null ? product.getMainImage().getThumbnailUrls() : "drawable://" + R.drawable.image_placeholder,
                    R.drawable.image_placeholder);

            productViewHolder.cbAddToFavorite.setChecked(product.getFavorite());

        } else if (itemViewType == LOADING) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) viewHolder;
            if (retryPageLoad) {
                loadingViewHolder.mErrorLayout.setVisibility(View.VISIBLE);
                loadingViewHolder.mProgressBar.setVisibility(View.GONE);
                loadingViewHolder.mErrorTxt.setText(
                        errorMsg != null ?
                                errorMsg :
                                MyEtsyAppApplication.getInstance().getString(R.string.error_msg_unknown));
            } else {
                loadingViewHolder.mErrorLayout.setVisibility(View.GONE);
                loadingViewHolder.mProgressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == getItemCount() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    /**
     * Methods Helpers for Pagination
     */
    public void add(Product product) {
        getItems().add(product);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addAll(List<Product> products) {
        if (products == null || products.size() == 0) {
            return;
        }
        int previousItemsSize = getItems().size();
        getItems().addAll(products);
        // Notify the adapter about the newly added items
        notifyItemRangeInserted(previousItemsSize, products.size());
    }

    public void remove(Product product) {
        int position = getItems().indexOf(product);
        if (position > -1) {
            getItems().remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        retryPageLoad = false;
        isLoadingAdded = true;
        add(new Product());
    }

    void removeLoadingFooter() {
        isLoadingAdded = false;
        int position = getItemCount() - 1;
        Product result = getItem(position);

        if (result != null) {
            getItems().remove(position);
            notifyItemRemoved(position);
        }
    }

    Product getItem(int position) {
        return getItems().get(position);
    }

    /**
     * Displays Pagination retry footer view along with appropriate errorMsg
     *
     * @param show
     * @param errorMsg to display if page load fails
     */
    void showRetry(boolean show, @Nullable String errorMsg) {
        retryPageLoad = show;
        notifyItemChanged(getItems().size() - 1);
        if (errorMsg != null) this.errorMsg = errorMsg;
    }

    public interface AdapterCallback {
        public static String PRODUCT_EXTRA = "productExtra";

        void onProductItemClick(Bundle bundle);

        void retryPageLoad();

        void onFavoriteCheck(Bundle bundle);
    }

    abstract class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ProductViewHolder extends ViewHolder {

        @BindView(R.id.tv_product_name)
        TextView tvChildName;

        @BindView(R.id.cb_add_favorite)
        CheckBox cbAddToFavorite;

        @BindView(R.id.iv_photo_product)
        ImageView ivProductThumbnail;

        ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                        AdapterCallback reference = mViewReference.get();
                        if (mViewReference.get() != null && isValidPos(getAdapterPosition())) {
                            Product product = getItems().get(getAdapterPosition());
                            Bundle bundle = new Bundle();
                            bundle.putParcelable(AdapterCallback.PRODUCT_EXTRA, product);
                            /*send clone of Product object, instead giving direct reference.*/
                            reference.onProductItemClick(bundle);
                        }
                    }
            );

            cbAddToFavorite.setOnClickListener(v -> {
                CheckBox cb = (CheckBox) v;
                AdapterCallback reference = mViewReference.get();

                Product product = getItems().get(getAdapterPosition());
                product.setFavorite(cb.isChecked());
                if (mViewReference.get() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(AdapterCallback.PRODUCT_EXTRA, product);
                     /*send clone of Product object, instead giving direct reference.*/
                    reference.onFavoriteCheck(bundle);
                }
            });
        }

        private boolean isValidPos(int position) {
            return position >= 0 && position < getItems().size();
        }
    }

    /**
     * Loading Holder for pagination
     */
    protected class LoadingViewHolder extends ViewHolder implements View.OnClickListener {
        private ProgressBar mProgressBar;
        private ImageButton mRetryBtn;
        private TextView mErrorTxt;
        private LinearLayout mErrorLayout;

        LoadingViewHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.loadmore_progress);
            mRetryBtn = (ImageButton) itemView.findViewById(R.id.loadmore_retry);
            mErrorTxt = (TextView) itemView.findViewById(R.id.loadmore_errortxt);
            mErrorLayout = (LinearLayout) itemView.findViewById(R.id.loadmore_errorlayout);
            mRetryBtn.setOnClickListener(this);
            mErrorLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loadmore_retry:
                case R.id.loadmore_errorlayout:
                    showRetry(false, null);
                    AdapterCallback reference = mViewReference.get();
                    if (mViewReference.get() != null) {
                        reference.retryPageLoad();
                    }
                    break;
            }
        }
    }
}
