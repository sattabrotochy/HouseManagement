package com.Shuvo.myapplication.Class;

public class FlatMoreDetls {

    String name,phn_number,address, floorId, FlatBadRoom, FlatBathroom, FlatQunty, FlatPrice, parkingYesNo, Active_Inactive, image;

    public FlatMoreDetls() {
    }

    public FlatMoreDetls(String name, String phn_number, String address, String floorId, String flatBadRoom, String flatBathroom, String flatQunty, String flatPrice, String parkingYesNo, String active_Inactive, String image) {
        this.name = name;
        this.phn_number = phn_number;
        this.address = address;
        this.floorId = floorId;
        FlatBadRoom = flatBadRoom;
        FlatBathroom = flatBathroom;
        FlatQunty = flatQunty;
        FlatPrice = flatPrice;
        this.parkingYesNo = parkingYesNo;
        Active_Inactive = active_Inactive;
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

    public String getFlatBathroom() {
        return FlatBathroom;
    }

    public void setFlatBathroom(String flatBathroom) {
        FlatBathroom = flatBathroom;
    }

    public String getFlatQunty() {
        return FlatQunty;
    }

    public void setFlatQunty(String flatQunty) {
        FlatQunty = flatQunty;
    }

    public String getFlatPrice() {
        return FlatPrice;
    }

    public void setFlatPrice(String flatPrice) {
        FlatPrice = flatPrice;
    }

    public String getParkingYesNo() {
        return parkingYesNo;
    }

    public void setParkingYesNo(String parkingYesNo) {
        this.parkingYesNo = parkingYesNo;
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
