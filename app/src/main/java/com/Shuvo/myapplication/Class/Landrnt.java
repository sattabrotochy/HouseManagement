package com.Shuvo.myapplication.Class;

public class Landrnt {


    int id;
    String name, address, per_qty_price, land_qty, land_price,active_ckeck,image;

    public Landrnt() {
    }

    public Landrnt(int id, String name, String address, String per_qty_price, String land_qty, String land_price, String active_ckeck, String image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.per_qty_price = per_qty_price;
        this.land_qty = land_qty;
        this.land_price = land_price;
        this.active_ckeck = active_ckeck;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPer_qty_price() {
        return per_qty_price;
    }

    public void setPer_qty_price(String per_qty_price) {
        this.per_qty_price = per_qty_price;
    }

    public String getLand_qty() {
        return land_qty;
    }

    public void setLand_qty(String land_qty) {
        this.land_qty = land_qty;
    }

    public String getLand_price() {
        return land_price;
    }

    public void setLand_price(String land_price) {
        this.land_price = land_price;
    }

    public String getActive_ckeck() {
        return active_ckeck;
    }

    public void setActive_ckeck(String active_ckeck) {
        this.active_ckeck = active_ckeck;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
