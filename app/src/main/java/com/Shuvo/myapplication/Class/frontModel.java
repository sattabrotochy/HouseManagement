package com.Shuvo.myapplication.Class;

public class frontModel {

    String   name, location, house_floor, house_rnt_price, house_room, Image;

    int id;
    public frontModel() {
    }

    public frontModel(String name, String location, String house_floor, String house_rnt_price, String house_room, String image, int id) {
        this.name = name;
        this.location = location;
        this.house_floor = house_floor;
        this.house_rnt_price = house_rnt_price;
        this.house_room = house_room;
        Image = image;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHouse_floor() {
        return house_floor;
    }

    public void setHouse_floor(String house_floor) {
        this.house_floor = house_floor;
    }

    public String getHouse_rnt_price() {
        return house_rnt_price;
    }

    public void setHouse_rnt_price(String house_rnt_price) {
        this.house_rnt_price = house_rnt_price;
    }

    public String getHouse_room() {
        return house_room;
    }

    public void setHouse_room(String house_room) {
        this.house_room = house_room;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
