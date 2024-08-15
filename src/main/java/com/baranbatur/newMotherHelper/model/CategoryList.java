package com.baranbatur.newMotherHelper.model;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;
//Bir kategorinin içinde bulunan alınması gereken öğeleri içeren sınıf.

@Entity
@Table(name = "category_list")
public class CategoryList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String itemName;
    private boolean is_added = false;

    private String iconUrl;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public CategoryList(String itemName, Category category, String iconUrl, boolean is_added) {
        this.itemName = itemName;
        this.category = category;
        this.is_added = is_added;
        this.iconUrl = iconUrl;
    }

    public CategoryList() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public boolean isIs_added() {
        return is_added;
    }

    public void setIs_added(boolean is_added) {
        this.is_added = is_added;
    }

}
