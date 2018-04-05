package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models;


import android.os.Parcel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.interfaces.Model;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "category_id",
        "name",
        "short_name"
})
public class Category implements Model<Long> {

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
    @JsonProperty("category_id")
    private Long mId;
    @JsonProperty("name")
    private String mName;
    @JsonProperty("short_name")
    private String mShortName;

    public Category() {
    }

    protected Category(Parcel in) {
        this.mId = (Long) in.readValue(Long.class.getClassLoader());
        this.mName = in.readString();
        this.mShortName = in.readString();
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getShortName() {
        return mShortName;
    }

    public void setShortName(String shortName) {
        mShortName = shortName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mId);
        dest.writeString(this.mName);
        dest.writeString(this.mShortName);
    }
}
