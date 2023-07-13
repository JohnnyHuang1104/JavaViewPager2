package com.example.javaviewpager2.viewpager2;

import java.util.ArrayList;
import java.util.List;

public class MyPerson {
    String name;
    String gender;
    long birthday;
    int imageview;

    public MyPerson(
            String name,
            String gender,
            long birthday,
            int imageview
    ) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.imageview = imageview;

    }
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public long getBirthday() {
        return birthday;
    }

    public int getImageview(){return imageview;}

}