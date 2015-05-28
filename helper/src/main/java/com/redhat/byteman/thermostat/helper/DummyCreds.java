package com.redhat.byteman.thermostat.helper;

import com.redhat.thermostat.storage.core.StorageCredentials;

/**
 * Created by alex on 5/28/15.
 */
public class DummyCreds implements StorageCredentials {

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public char[] getPassword() {
        return new char[0];
    }
}
