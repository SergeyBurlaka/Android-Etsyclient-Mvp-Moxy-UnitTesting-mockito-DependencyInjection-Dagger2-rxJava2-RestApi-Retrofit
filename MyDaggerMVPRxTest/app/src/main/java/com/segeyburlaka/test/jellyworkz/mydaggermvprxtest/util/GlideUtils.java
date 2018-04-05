package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Util for working with images
 */
public final class GlideUtils {

    private GlideUtils() {
    }

    /**
     * Loads image from uri to ImageView with placeHolder
     *
     * @param imageView Instance of {@link ImageView} where you want set an Image
     * @param imageUri  Uri to the image
     */
    public static void loadImage(@NonNull ImageView imageView, @NonNull String imageUri) {
        Glide.with(imageView.getContext())
                .load(imageUri)
                .into(imageView);
    }

    /**
     * Loads image from uri to ImageView with placeHolder
     *
     * @param imageView   Instance of {@link ImageView} where you want set an Image
     * @param imageUri    Uri to the image
     * @param placeholder Drawable resource for placeholder
     */
    public static void loadImage(@NonNull ImageView imageView, @NonNull String imageUri, @DrawableRes int placeholder) {
        Glide.with(imageView.getContext())
                .load(imageUri)
                .placeholder(placeholder)
                .error(placeholder)
                .crossFade()
                .into(imageView);
    }

    /**
     * Loads avatar from uri to ImageView with placeHolder and use transform
     *
     * @param imageView   Instance of {@link ImageView} where you want set an Image
     * @param imageUri    Uri to the image
     * @param placeholder Drawable resource for placeholder
     */
    public static void loadAvatar(@NonNull ImageView imageView, @NonNull String imageUri, @DrawableRes int placeholder) {
        Glide.with(imageView.getContext())
                .load(imageUri)
                .error(R.drawable.image_placeholder)
                .placeholder(placeholder)
                .bitmapTransform(new CropCircleTransformation(imageView.getContext()))
                .into(imageView);
    }

    /**
     * Loads avatar from uri to ImageView with placeHolder and use transform
     *
     * @param imageView   Instance of {@link ImageView} where you want set an Image
     * @param imageUri    Uri to the image
     * @param placeholder Drawable resource for placeholder
     */
    public static void loadSupplierLogo(@NonNull ImageView imageView, @NonNull String imageUri, @DrawableRes int placeholder) {
        Glide.with(imageView.getContext())
                .load(imageUri)
                .error(R.drawable.image_placeholder)
                .placeholder(placeholder)
                .bitmapTransform(new RoundedCornersTransformation(imageView.getContext(), 2, 0))
                .into(imageView);
    }
}
