package com.redhat.byteman.thermostat.helper;

import com.redhat.thermostat.shared.config.SSLConfiguration;

import java.io.File;

/**
 * Created by alex on 5/28/15.
 */
public class DummySslConf implements SSLConfiguration {

    @Override
    public File getKeystoreFile() {
        return null;
    }

    @Override
    public String getKeyStorePassword() {
        return null;
    }

    @Override
    public boolean enableForCmdChannel() {
        return false;
    }

    @Override
    public boolean enableForBackingStorage() {
        return false;
    }

    @Override
    public boolean disableHostnameVerification() {
        return false;
    }
}
