package com.kchukka.app;

import com.kchukka.service.AddressFinderService;
import com.kchukka.service.AddressFinderServiceImpl;
import com.kchukka.service.AddressParserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Main class
 *
 */
public class App 
{
    private static Logger logger = LogManager.getLogger(AddressFinderServiceImpl.class);

    private AddressFinderService addressFinderService;
    private AddressParserService AddressParserService;

    public App(AddressFinderService addressFinderService, AddressParserService addressParserService) {
        this.addressFinderService = addressFinderService;
        this.AddressParserService = addressParserService;
    }

    public static void main( String[] args ) {

    }
}
