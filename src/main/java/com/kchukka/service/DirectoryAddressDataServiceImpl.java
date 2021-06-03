package com.kchukka.service;

import com.kchukka.configuration.AddressFileConfiguration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryAddressDataServiceImpl implements AddressDataService {
    private static Logger logger = LogManager.getLogger(DirectoryAddressDataServiceImpl.class);

    private AddressFileConfiguration addressFileConfiguration;

    public DirectoryAddressDataServiceImpl(AddressFileConfiguration addressFileConfiguration) {
        this.addressFileConfiguration = addressFileConfiguration;
    }

    @Override
    public List<String> getAddressList() {
        List<String> lines = new ArrayList<>();

        final File folder = new File(addressFileConfiguration.getFilePath());
        File[] files = listFilesForFolder(folder);

        for(File file:files) {
            List<String> addressList = getAddressData(file);
            lines.addAll(addressList);
        }
        Collections.sort(lines);

        return lines;
    }

    private File[] listFilesForFolder(final File folder) {
        return folder.listFiles();
    }

    private List<String> getAddressData(File file) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            logger.error("Unable to read the file, please provide correct file path.");
            throw new RuntimeException("Unable to read the file, please provide correct file path.");
        }
        return lines;
    }
}
