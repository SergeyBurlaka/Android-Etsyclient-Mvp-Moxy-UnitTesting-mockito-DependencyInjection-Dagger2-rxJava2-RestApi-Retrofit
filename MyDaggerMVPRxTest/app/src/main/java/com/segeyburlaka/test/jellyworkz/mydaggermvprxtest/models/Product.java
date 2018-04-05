package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models;

import android.os.Parcel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.interfaces.Model;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "listing_id",
        "title",
        "price",
        "currency_code",
        "description",
        "MainImage"
})
public class Product implements Model<Long> {

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
    @JsonProperty("listing_id")
    private Long mId; //listing_id
    @JsonProperty("title")
    private String mName;
    @JsonProperty("price")
    private String mPrice;
    @JsonProperty("currency_code")
    private String mCurrencyCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(mId, product.mId) &&
                Objects.equals(mName, product.mName) &&
                Objects.equals(mPrice, product.mPrice) &&
                Objects.equals(isFavorite, product.isFavorite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mName, mPrice, isFavorite);
    }

    @JsonProperty("description")
    private String mDescription;
    @JsonProperty("MainImage")
    private MainImage mMainImage;
    @JsonIgnore
    private Boolean isFavorite = false;

    public Product() {
    }

    public Product(Parcel in) {
        this.mId = (Long) in.readValue(Long.class.getClassLoader());
        this.mName = in.readString();
        this.mPrice = in.readString();
        this.mCurrencyCode = in.readString();
        this.mDescription = in.readString();
        this.mMainImage = in.readParcelable(MainImage.class.getClassLoader());
        this.isFavorite = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public Product setFavorite(Boolean favorite) {
        isFavorite = favorite;
        return this;
    }

    @Override
    public Long getId() {
        return mId;
    }

    @Override
    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        mCurrencyCode = currencyCode;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public MainImage getMainImage() {
        return mMainImage;
    }

    public void setMainImage(MainImage mainImage) {
        mMainImage = mainImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mId);
        dest.writeString(this.mName);
        dest.writeString(this.mPrice);
        dest.writeString(this.mCurrencyCode);
        dest.writeString(this.mDescription);
        dest.writeParcelable(this.mMainImage, flags);
        dest.writeValue(this.isFavorite);
    }
}
