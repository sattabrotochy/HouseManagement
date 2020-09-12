package com.Shuvo.myapplication.Class;

public class FlatFrontModel {

    int id;
    String name, address, floorId, FlatBadRoom, FlatPrice,Active_Inactive,image;

    public FlatFrontModel() {
    }

    public FlatFrontModel(int id, String name, String address, String floorId, String flatBadRoom, String flatPrice, String active_Inactive, String image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.floorId = floorId;
        FlatBadRoom = flatBadRoom;
        FlatPrice = flatPrice;
        Active_Inactive = active_Inactive;
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

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFlatBadRoom() {
        return FlatBadRoom;
    }

    public void setFlatBadRoom(String flatBadRoom) {
        FlatBadRoom = flatBadRoom;
    }

    public String getFlatPrice() {
        return FlatPrice;
    }

    public void setFlatPrice(String flatPrice) {
        FlatPrice = flatPrice;
    }

    public String getActive_Inactive() {
        return Active_Inactive;
    }

    public void setActive_Inactive(String active_Inactive) {
        Active_Inactive = active_Inactive;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
