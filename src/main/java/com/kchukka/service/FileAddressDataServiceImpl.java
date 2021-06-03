package com.kchukka.service;

import com.kchukka.configuration.AddressFileConfiguration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileAddressDataServiceImpl implements AddressDataService {
    private static Logger logger = LogManager.getLogger(FileAddressDataServiceImpl.class);

    private AddressFileConfiguration addressFileConfiguration;

    public FileAddressDataServiceImpl(AddressFileConfiguration addressFileConfiguration) {
        this.addressFileConfiguration = addressFileConfiguration;
    }

    @Override
    public List<String> getAddressList() {
        List<String> lines = null;

        try {
            lines = Files.readAllLines(Paths.get(addressFileConfiguration.getFilePath()));
        } catch (IOException e) {
            logger.error("Unable to read the file, please provide correct file path.");
            throw new RuntimeException("Unable to read the file, please provide correct file path.");
        }

        return lines;
    }
}
