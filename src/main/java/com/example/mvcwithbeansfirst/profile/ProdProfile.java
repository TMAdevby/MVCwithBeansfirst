package com.example.mvcwithbeansfirst.profile;

public class ProdProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is PRODUCTION";
    }
}
