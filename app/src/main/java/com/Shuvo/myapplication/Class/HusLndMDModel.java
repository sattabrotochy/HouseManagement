package com.Shuvo.myapplication.Class;

public class HusLndMDModel  {

    String  name,phn_number,address,houlan_type,house_floor,houlan_qunty,houLnd_Price,houlan_document,houLan_taxClr,houlan_parking,active_inactive,image;


    public HusLndMDModel() {
    }

    public HusLndMDModel(String name, String phn_number, String address, String houlan_type, String house_floor, String houlan_qunty, String houLnd_Price, String houlan_document, String houLan_taxClr, String houlan_parking, String active_inactive, String image) {
        this.name = name;
        this.phn_number = phn_number;
        this.address = address;
        this.houlan_type = houlan_type;
        this.house_floor = house_floor;
        this.houlan_qunty = houlan_qunty;
        this.houLnd_Price = houLnd_Price;
        this.houlan_document = houlan_document;
        this.houLan_taxClr = houLan_taxClr;
        this.houlan_parking = houlan_parking;
        this.active_inactive = active_inactive;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhn_number() {
        return phn_number;
    }

    public void setPhn_number(String phn_number) {
        this.phn_number = phn_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHoulan_type() {
        return houlan_type;
    }

    public void setHoulan_type(String houlan_type) {
        this.houlan_type = houlan_type;
    }

    public String getHouse_floor() {
        return house_floor;
    }

    public void setHouse_floor(String house_floor) {
        this.house_floor = house_floor;
    }

    public String getHoulan_qunty() {
        return houlan_qunty;
    }

    public void setHoulan_qunty(String houlan_qunty) {
        this.houlan_qunty = houlan_qunty;
    }

    public String getHouLnd_Price() {
        return houLnd_Price;
    }

    public void setHouLnd_Price(String houLnd_Price) {
        this.houLnd_Price = houLnd_Price;
    }

    public String getHoulan_document() {
        return houlan_document;
    }

    public void setHoulan_document(String houlan_document) {
        this.houlan_document = houlan_document;
    }

    public String getHouLan_taxClr() {
        return houLan_taxClr;
    }

    public void setHouLan_taxClr(String houLan_taxClr) {
        this.houLan_taxClr = houLan_taxClr;
    }

    public String getHoulan_parking() {
        return houlan_parking;
    }

    public void setHoulan_parking(String houlan_parking) {
        this.houlan_parking = houlan_parking;
    }

    public String getActive_inactive() {
        return active_inactive;
    }

    public void setActive_inactive(String active_inactive) {
        this.active_inactive = active_inactive;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
