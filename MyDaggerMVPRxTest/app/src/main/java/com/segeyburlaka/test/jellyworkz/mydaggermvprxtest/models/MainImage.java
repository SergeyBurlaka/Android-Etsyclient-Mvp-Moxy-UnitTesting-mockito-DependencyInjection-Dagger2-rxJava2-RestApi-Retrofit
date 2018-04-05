package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "url_170x135",
        "url_570xN"
})
public class MainImage implements Parcelable {

    public static final Creator<MainImage> CREATOR = new Creator<MainImage>() {
        @Override
        public MainImage createFromParcel(Parcel source) {
            return new MainImage(source);
        }

        @Override
        public MainImage[] newArray(int size) {
            return new MainImage[size];
        }
    };
    @JsonProperty("url_170x135")
    private String mThumbnailUrls;

    @JsonProperty("url_570xN")
    private String mPhotoUrl;

    public MainImage() {
    }

    protected MainImage(Parcel in) {
        this.mThumbnailUrls = in.readString();
        this.mPhotoUrl = in.readString();
    }

    public MainImage(MainImage mainImage) {
        this.mThumbnailUrls = mainImage.getThumbnailUrls();
        this.mPhotoUrl = mainImage.getPhotoUrl();
    }

    public String getThumbnailUrls() {
        return mThumbnailUrls;
    }

    public void setThumbnailUrls(String thumbnailUrls) {
        mThumbnailUrls = thumbnailUrls;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        mPhotoUrl = photoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mThumbnailUrls);
        dest.writeString(this.mPhotoUrl);
    }
}
