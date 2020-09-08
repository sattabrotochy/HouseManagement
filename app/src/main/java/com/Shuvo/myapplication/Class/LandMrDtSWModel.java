package com.Shuvo.myapplication.Class;

public class LandMrDtSWModel {


    String name,phn_number, address, per_qty_price, land_qty, land_price, land_py_Date, mini_leave_date, mini_adv_date;

    public LandMrDtSWModel() {
    }

    public LandMrDtSWModel(String name, String phn_number, String address, String per_qty_price, String land_qty, String land_price, String land_py_Date, String mini_leave_date, String mini_adv_date) {
        this.name = name;
        this.phn_number = phn_number;
        this.address = address;
        this.per_qty_price = per_qty_price;
        this.land_qty = land_qty;
        this.land_price = land_price;
        this.land_py_Date = land_py_Date;
        this.mini_leave_date = mini_leave_date;
        this.mini_adv_date = mini_adv_date;
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

    public String getLand_py_Date() {
        return land_py_Date;
    }

    public void setLand_py_Date(String land_py_Date) {
        this.land_py_Date = land_py_Date;
    }

    public String getMini_leave_date() {
        return mini_leave_date;
    }

    public void setMini_leave_date(String mini_leave_date) {
        this.mini_leave_date = mini_leave_date;
    }

    public String getMini_adv_date() {
        return mini_adv_date;
    }

    public void setMini_adv_date(String mini_adv_date) {
        this.mini_adv_date = mini_adv_date;
    }
}
