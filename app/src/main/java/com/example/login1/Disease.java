package com.example.login1;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


//model class

public class Disease implements Serializable {
    private String name;
    private String symptoms;
    private String remedies;
    private String dosAndDonts;

    public Disease() {
    }

    public Disease(String name, String symptoms, String remedies, String dosAndDonts) {
        this.name = name;
        this.symptoms = symptoms;
        this.remedies = remedies;
        this.dosAndDonts = dosAndDonts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getRemedies() {
        return remedies;
    }

    public void setRemedies(String remedies) {
        this.remedies = remedies;
    }

    public String getDosAndDonts() {
        return dosAndDonts;
    }

    public void setDosAndDonts(String dosAndDonts) {
        this.dosAndDonts = dosAndDonts;
    }
}

