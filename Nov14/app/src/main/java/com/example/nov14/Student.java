package com.example.nov14;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Student implements Parcelable {
    String name;
    String school;
    int year;

    public Student() {
        name = "";
        school = "";
        year = 0;
    }

    public Student(String name , String school) {
        this.name = name;
        this.school = school;
    }

    protected Student(Parcel in) {
        name = in.readString();
        school = in.readString();
        year = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(school);
        parcel.writeInt(year);
    }
}
