package com.redhat.thermostat.storage.mongodb.internal;

import com.redhat.byteman.thermostat.helper.DummyCreds;
import com.redhat.byteman.thermostat.helper.DummySslConf;
import com.redhat.thermostat.storage.core.DescriptorParsingException;
import com.redhat.thermostat.storage.core.StatementExecutionException;
import com.redhat.thermostat.storage.core.Storage;

/**
 * Created by alex on 5/28/15.
 */
public class MongoAccessor {

    private final String url;

    public MongoAccessor(String url) {
        this.url = url;
    }

    public Storage connect() throws DescriptorParsingException, StatementExecutionException {
        MongoStorage st = new MongoStorage(url, new DummyCreds(), new DummySslConf());
        st.getConnection().connect();
        return st;
    }
}
