package com.Shuvo.myapplication.Class;

public class HuslndModelFront {
    int id;
    String name, address, houlan_qunty, houLnd_Price, house_floor,image;

    public HuslndModelFront() {
    }


    public HuslndModelFront(int id, String name, String address, String houlan_qunty, String houLnd_Price, String house_floor, String image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.houlan_qunty = houlan_qunty;
        this.houLnd_Price = houLnd_Price;
        this.house_floor = house_floor;
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

    public String getHouse_floor() {
        return house_floor;
    }

    public void setHouse_floor(String house_floor) {
        this.house_floor = house_floor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
