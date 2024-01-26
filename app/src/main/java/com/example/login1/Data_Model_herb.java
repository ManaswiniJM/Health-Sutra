package com.example.login1;

public class Data_Model_herb {

    private String Plant_Name,P_Des;
    private int Image;

    public Data_Model_herb(String plant_name, String p_des, int image) {
        Plant_Name = plant_name;
        P_Des=p_des;
        Image = image;
    }

    public String getPlant_Name() {
        return Plant_Name;
    }

    public void setPlant_Name(String plant_Name) {
        Plant_Name = plant_Name;
    }

    public String getP_Des() {
        return P_Des;
    }

    public void setP_Des(String p_Des) {
        P_Des = p_Des;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
