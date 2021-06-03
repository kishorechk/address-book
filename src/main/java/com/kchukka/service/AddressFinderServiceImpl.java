package com.kchukka.service;

import com.kchukka.configuration.AddressFilterConfiguration;
import com.kchukka.model.Address;
import com.kchukka.model.Gender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Address Finder Service Implementation
 */
public class AddressFinderServiceImpl implements AddressFinderService {
    private static Logger logger = LogManager.getLogger(AddressFinderServiceImpl.class);

    /**
     * return the address list for for given criteria
     *
     * currently support filter by Gender
     */
    public List<Address> getAddressList(List<Address> addressList, AddressFilterConfiguration filterConfiguration) {
        if(filterConfiguration!=null) {
            if(filterConfiguration.getGender()!=null) {
                return addressList.stream().filter(address -> address.getGender().compareTo(filterConfiguration.getGender())==0).collect(Collectors.toList());
            }
        }
        return addressList;
    }

    @Override
    public Address getOldestPerson(List<Address> addressList) {
        Collections.sort(addressList, new Comparator<Address>() {
            public int compare(Address o1, Address o2) {
                return o1.getDob().compareTo(o2.getDob());
            }
        });
        return addressList.get(0);
    }

    @Override
    public long getAgeDifference(Address one, Address two) {
        long startTime = one.getDob().getTime();
        long endTime = two.getDob().getTime();
        long diffTime = endTime - startTime;
        long diffDays = diffTime / (1000 * 60 * 60 * 24);
        return diffDays;
    }
}
