package com.vaadin.tutorial.crm.backend;

public class Product {
    Long plu;
    String name;
    Double price;
    int cid;
    Boolean ageRestricted;

    public Product(Long plu, String name, Double price, int cid, Boolean ageRestricted) {
        this.plu = plu;
        this.name = name;
        this.price = price;
        this.cid = cid;
        this.ageRestricted = ageRestricted;
    }

    public Long getPlu() {
        return plu;
    }

    public void setPlu(Long plu) {
        this.plu = plu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Boolean getAgeRestricted() {
        return ageRestricted;
    }

    public void setAgeRestricted(Boolean ageRestricted) {
        this.ageRestricted = ageRestricted;
    }
}
