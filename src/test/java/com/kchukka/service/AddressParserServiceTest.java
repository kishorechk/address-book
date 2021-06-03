package com.kchukka.service;

import com.kchukka.configuration.AddressFileConfiguration;
import com.kchukka.model.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AddressParserServiceTest {
    @Test
    public void test_getAddressList() throws IOException {
        URL resource = getClass().getClassLoader().getResource("AddressBook");
        AddressFileConfiguration AddressFileConfiguration = new AddressFileConfiguration();
        AddressFileConfiguration.setFilePath(resource.getPath());
        AddressFileConfiguration.setDelimiter(",");
        AddressDataService AddressDataService = new FileAddressDataServiceImpl(AddressFileConfiguration);
        AddressParserService AddressParserService = new AddressParserServiceImpl(AddressFileConfiguration, AddressDataService);
        List<Address> AddressList = AddressParserService.getAddressList();
        assertNotNull(AddressList);
        assertEquals(5, AddressList.size());
        Address Address = AddressList.get(0);
        assertNotNull(Address);
        assertEquals("Bill McKnight",Address.getName());
    }

    @Test()
    public void test_getAddressMapExceptionThrown() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            AddressFileConfiguration AddressFileConfiguration = new AddressFileConfiguration();
            AddressFileConfiguration.setFilePath("AddressBook2");
            AddressFileConfiguration.setDelimiter(",");
            AddressDataService AddressDataService = new FileAddressDataServiceImpl(AddressFileConfiguration);
            AddressParserService AddressParserService = new AddressParserServiceImpl(AddressFileConfiguration, AddressDataService);
            AddressParserService.getAddressList();
        });
    }
}