package com.kchukka.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Address {

    private String name;
    private Gender gender;
    private Date dob;

    public Address(String name, Gender gender, Date dob) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return name.equals(address.name) && gender == address.gender && dob.equals(address.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, dob);
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                '}';
    }
}
