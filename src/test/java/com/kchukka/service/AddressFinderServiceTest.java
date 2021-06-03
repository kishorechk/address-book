package com.kchukka.service;

import com.kchukka.configuration.AddressFileConfiguration;
import com.kchukka.configuration.AddressFilterConfiguration;
import com.kchukka.model.Address;
import com.kchukka.model.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddressFinderServiceTest {
    AddressFileConfiguration addressFileConfiguration;
    AddressDataService addressDataService;
    AddressParserService addressParserService;
    List<Address> addressList;
    AddressFinderService addressFinderService;

    @BeforeEach
    void init() {
        URL resource = getClass().getClassLoader().getResource("AddressBook");
        addressFileConfiguration = new AddressFileConfiguration();
        addressFileConfiguration.setFilePath(resource.getPath());
        addressFileConfiguration.setDelimiter(",");

        addressDataService = new FileAddressDataServiceImpl(addressFileConfiguration);

        addressDataService = new FileAddressDataServiceImpl(addressFileConfiguration);
        addressParserService = new AddressParserServiceImpl(addressFileConfiguration, addressDataService);
        addressList = addressParserService.getAddressList();
        addressFinderService = new AddressFinderServiceImpl();
    }

    @Test
    public void test_getMales() {

        List<Address> result = addressFinderService.getAddressList(addressList, new AddressFilterConfiguration(Gender.MALE));
        assertEquals(3, result.size());
        assertEquals("Bill McKnight", result.get(0).getName());
    }

    @Test
    public void test_getFemales() {
        List<Address> result = addressFinderService.getAddressList(addressList, new AddressFilterConfiguration(Gender.FEMALE));
        assertEquals(2, result.size());
        assertEquals("Gemma Lane", result.get(0).getName());
    }

    @Test
    public void test_getOldestPerson() {
        Address result = addressFinderService.getOldestPerson(addressList);
        assertEquals("Wes Jackson", result.getName());
    }

    @Test
    public void test_getAgeDifference() {
        long result = addressFinderService.getAgeDifference(addressList.get(0), addressList.get(1));
        assertEquals(2862, result);
    }

}