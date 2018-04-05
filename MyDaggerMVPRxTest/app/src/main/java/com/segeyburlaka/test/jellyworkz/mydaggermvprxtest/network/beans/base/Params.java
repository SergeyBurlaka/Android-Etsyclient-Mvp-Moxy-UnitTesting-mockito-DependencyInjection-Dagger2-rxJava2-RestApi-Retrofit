package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.beans.base;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "mLimit",
        "mOffset",
        "mPage",
        "mKeywords",
        "sort_on",
        "sort_order",
        "min_price",
        "max_price",
        "mColor",
        "color_accuracy",
        "mTags",
        "mCategory",
        "mLocation",
        "mLat",
        "mLon",
        "mRegion",
        "geo_level",
        "accepts_gift_cards",
        "translate_keywords"
})
public class Params {

    @JsonProperty("mLimit")
    private String mLimit;
    @JsonProperty("mOffset")
    private String mOffset;
    @JsonProperty("mPage")
    private Object mPage;
    @JsonProperty("mKeywords")
    private String mKeywords;
    @JsonProperty("sort_on")
    private String mSortOn;
    @JsonProperty("sort_order")
    private String mSortOrder;
    @JsonProperty("min_price")
    private Object mMinPrice;
    @JsonProperty("max_price")
    private Object mMaxPrice;
    @JsonProperty("mColor")
    private Object mColor;
    @JsonProperty("color_accuracy")
    private Integer mColorAccuracy;
    @JsonProperty("mTags")
    private Object mTags;
    @JsonProperty("mCategory")
    private String mCategory;
    @JsonProperty("mLocation")
    private Object mLocation;
    @JsonProperty("mLat")
    private Object mLat;
    @JsonProperty("mLon")
    private Object mLon;
    @JsonProperty("mRegion")
    private Object mRegion;
    @JsonProperty("geo_level")
    private String mGeoLevel;
    @JsonProperty("accepts_gift_cards")
    private String mAcceptsGiftCards;
    @JsonProperty("translate_keywords")
    private String mTranslateKeywords;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("mLimit")
    public String getLimit() {
        return mLimit;
    }

    @JsonProperty("mLimit")
    public void setLimit(String limit) {
        this.mLimit = limit;
    }

    @JsonProperty("mOffset")
    public String getOffset() {
        return mOffset;
    }

    @JsonProperty("mOffset")
    public void setOffset(String offset) {
        this.mOffset = offset;
    }

    @JsonProperty("mPage")
    public Object getPage() {
        return mPage;
    }

    @JsonProperty("mPage")
    public void setPage(Object page) {
        this.mPage = page;
    }

    @JsonProperty("mKeywords")
    public String getKeywords() {
        return mKeywords;
    }

    @JsonProperty("mKeywords")
    public void setKeywords(String keywords) {
        this.mKeywords = keywords;
    }

    @JsonProperty("sort_on")
    public String getSortOn() {
        return mSortOn;
    }

    @JsonProperty("sort_on")
    public void setSortOn(String sortOn) {
        this.mSortOn = sortOn;
    }

    @JsonProperty("sort_order")
    public String getSortOrder() {
        return mSortOrder;
    }

    @JsonProperty("sort_order")
    public void setSortOrder(String sortOrder) {
        this.mSortOrder = sortOrder;
    }

    @JsonProperty("min_price")
    public Object getMinPrice() {
        return mMinPrice;
    }

    @JsonProperty("min_price")
    public void setMinPrice(Object minPrice) {
        this.mMinPrice = minPrice;
    }

    @JsonProperty("max_price")
    public Object getMaxPrice() {
        return mMaxPrice;
    }

    @JsonProperty("max_price")
    public void setMaxPrice(Object maxPrice) {
        this.mMaxPrice = maxPrice;
    }

    @JsonProperty("mColor")
    public Object getColor() {
        return mColor;
    }

    @JsonProperty("mColor")
    public void setColor(Object color) {
        this.mColor = color;
    }

    @JsonProperty("color_accuracy")
    public Integer getColorAccuracy() {
        return mColorAccuracy;
    }

    @JsonProperty("color_accuracy")
    public void setColorAccuracy(Integer colorAccuracy) {
        this.mColorAccuracy = colorAccuracy;
    }

    @JsonProperty("mTags")
    public Object getTags() {
        return mTags;
    }

    @JsonProperty("mTags")
    public void setTags(Object tags) {
        this.mTags = tags;
    }

    @JsonProperty("mCategory")
    public String getCategory() {
        return mCategory;
    }

    @JsonProperty("mCategory")
    public void setCategory(String category) {
        this.mCategory = category;
    }

    @JsonProperty("mLocation")
    public Object getLocation() {
        return mLocation;
    }

    @JsonProperty("mLocation")
    public void setLocation(Object location) {
        this.mLocation = location;
    }

    @JsonProperty("mLat")
    public Object getLat() {
        return mLat;
    }

    @JsonProperty("mLat")
    public void setLat(Object lat) {
        this.mLat = lat;
    }

    @JsonProperty("mLon")
    public Object getLon() {
        return mLon;
    }

    @JsonProperty("mLon")
    public void setLon(Object lon) {
        this.mLon = lon;
    }

    @JsonProperty("mRegion")
    public Object getRegion() {
        return mRegion;
    }

    @JsonProperty("mRegion")
    public void setRegion(Object region) {
        this.mRegion = region;
    }

    @JsonProperty("geo_level")
    public String getGeoLevel() {
        return mGeoLevel;
    }

    @JsonProperty("geo_level")
    public void setGeoLevel(String geoLevel) {
        this.mGeoLevel = geoLevel;
    }

    @JsonProperty("accepts_gift_cards")
    public String getAcceptsGiftCards() {
        return mAcceptsGiftCards;
    }

    @JsonProperty("accepts_gift_cards")
    public void setAcceptsGiftCards(String acceptsGiftCards) {
        this.mAcceptsGiftCards = acceptsGiftCards;
    }

    @JsonProperty("translate_keywords")
    public String getTranslateKeywords() {
        return mTranslateKeywords;
    }

    @JsonProperty("translate_keywords")
    public void setTranslateKeywords(String translateKeywords) {
        this.mTranslateKeywords = translateKeywords;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
