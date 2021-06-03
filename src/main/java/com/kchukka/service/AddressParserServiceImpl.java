package com.kchukka.service;

import com.kchukka.configuration.AddressFileConfiguration;
import com.kchukka.model.Address;
import com.kchukka.model.Gender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Address Parser Service Implementation
 *
 */
public class AddressParserServiceImpl implements AddressParserService{
    private static Logger logger = LogManager.getLogger(AddressParserServiceImpl.class);

    private AddressFileConfiguration addressFileConfiguration;

    private AddressDataService addressDataService;

    public AddressParserServiceImpl(AddressFileConfiguration addressFileConfiguration, AddressDataService addressDataService) {
        this.addressFileConfiguration = addressFileConfiguration;
        this.addressDataService = addressDataService;
    }

    /**
     * reads the csv file and return list of Addresss
     *
     */
    public List<Address> getAddressList() {
        String delimiter = addressFileConfiguration.getDelimiter();
        List<Address> result = new ArrayList<>();

        List<String> lines =  addressDataService.getAddressList();

        for(int i=0; i<lines.size(); i++) {
            String[] contents = lines.get(i).split(delimiter);
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
                Address address = new Address(contents[0].trim(), contents[1].trim().equals("Male")? Gender.MALE:Gender.FEMALE, simpleDateFormat.parse( contents[2].trim() ));
                result.add(address);
            }
            catch (DateTimeParseException | ParseException parseException) {
                logger.error("Unable to parse Address line, ignoring the line");
            }
        }
        return result;
    }
}
