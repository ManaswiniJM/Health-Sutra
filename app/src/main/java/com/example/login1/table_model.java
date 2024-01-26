package com.example.login1;

public class table_model {

    private String Plant_Name,P_Des,P_sans,P_ver,P_bot,P_fam,P_part;
    private int Image;

    public table_model() {
    }

    public table_model(String plant_Name, String p_Des, String p_sans, String p_ver, String p_bot, String p_fam, String p_part, int image) {
        Plant_Name = plant_Name;
        P_Des = p_Des;
        P_sans = p_sans;
        P_ver = p_ver;
        P_bot = p_bot;
        P_fam = p_fam;
        P_part = p_part;
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

    public String getP_sans() {
        return P_sans;
    }

    public void setP_sans(String p_sans) {
        P_sans = p_sans;
    }

    public String getP_ver() {
        return P_ver;
    }

    public void setP_ver(String p_ver) {
        P_ver = p_ver;
    }

    public String getP_bot() {
        return P_bot;
    }

    public void setP_bot(String p_bot) {
        P_bot = p_bot;
    }

    public String getP_fam() {
        return P_fam;
    }

    public void setP_fam(String p_fam) {
        P_fam = p_fam;
    }

    public String getP_part() {
        return P_part;
    }

    public void setP_part(String p_part) {
        P_part = p_part;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }


}
