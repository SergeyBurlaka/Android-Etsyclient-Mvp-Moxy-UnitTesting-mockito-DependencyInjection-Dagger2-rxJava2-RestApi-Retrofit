package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class ProductEntity {
    @Id
    private Long id;
    private String name;
    private String price;
    private String currencyCode;
    private String description;
    private String thumbnailUrl;
    private String photoUrl;
    private Boolean isFavorite;

    @Generated(hash = 881890454)
    public ProductEntity(Long id, String name, String price, String currencyCode,
                         String description, String thumbnailUrl, String photoUrl,
                         Boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.currencyCode = currencyCode;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.photoUrl = photoUrl;
        this.isFavorite = isFavorite;
    }

    @Generated(hash = 27353230)
    public ProductEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Boolean getIsFavorite() {
        return this.isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

}
