package com.kchukka.configuration;

import com.kchukka.model.Gender;

public class AddressFilterConfiguration {

    private Gender gender;

    public AddressFilterConfiguration(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


}
