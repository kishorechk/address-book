package com.kchukka.service;

import com.kchukka.configuration.AddressFilterConfiguration;
import com.kchukka.model.Address;

import java.util.List;

public interface AddressFinderService {
    List<Address> getAddressList(List<Address> addressList, AddressFilterConfiguration filterConfiguration);

    Address getOldestPerson(List<Address> addressList);

    long getAgeDifference(Address one, Address two);
}
