package com.example.javaviewpager2;

import java.util.ArrayList;
import java.util.List;

public class MyPerson {
    String name;
    int gender;
    long birthday;

    public MyPerson(
            String name,
            int gender,
            long birthday
    ) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;

    }
    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public long getBirthday() {
        return birthday;
    }

}
