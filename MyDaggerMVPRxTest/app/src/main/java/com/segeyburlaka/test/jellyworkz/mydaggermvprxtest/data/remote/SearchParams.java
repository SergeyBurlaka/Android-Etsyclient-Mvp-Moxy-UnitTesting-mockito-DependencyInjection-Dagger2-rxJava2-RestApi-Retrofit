package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SearchParams implements Parcelable {
    public static final Creator<SearchParams> CREATOR = new Creator<SearchParams>() {
        @Override
        public SearchParams createFromParcel(Parcel source) {
            return new SearchParams(source);
        }

        @Override
        public SearchParams[] newArray(int size) {
            return new SearchParams[size];
        }
    };
    private Integer mOffset;
    private Integer mLimit;
    private List<String> mKeywords;
    private String category;

    public SearchParams() {
        //default constructor
    }

    public SearchParams(Integer offset, Integer limit, List<String> keywords, String category) {
        mOffset = offset;
        mLimit = limit;
        mKeywords = keywords;
        this.category = category;
    }

    protected SearchParams(Parcel in) {
        this.mOffset = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mLimit = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mKeywords = in.createStringArrayList();
        this.category = in.readString();
    }

    public Integer getOffset() {
        return mOffset;
    }

    public void setOffset(Integer offset) {
        mOffset = offset;
    }

    public Integer getLimit() {
        return mLimit;
    }

    public void setLimit(Integer limit) {
        mLimit = limit;
    }

    public List<String> getKeywords() {
        return mKeywords;
    }

    public void setKeywords(List<String> keywords) {
        mKeywords = keywords;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mOffset);
        dest.writeValue(this.mLimit);
        dest.writeStringList(this.mKeywords);
        dest.writeString(this.category);
    }
}
