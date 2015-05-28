package com.redhat.byteman.thermostat.helper;

import com.redhat.thermostat.storage.core.Category;
import com.redhat.thermostat.storage.core.Entity;
import com.redhat.thermostat.storage.core.Key;
import com.redhat.thermostat.storage.core.Persist;
import com.redhat.thermostat.storage.model.BasePojo;
import com.redhat.thermostat.storage.model.TimeStampedPojo;

import java.util.Arrays;

/**
 * Created by alex on 5/28/15.
 */
@Entity
public class TestStat extends BasePojo implements TimeStampedPojo {

    public static final Key<Double> SOME_KEY = new Key<>("someStat");

    public static final Category<TestStat> CATEGORY = new Category<>("some-test-stats", TestStat.class,
            Arrays.<Key<?>>asList(Key.AGENT_ID, Key.VM_ID, Key.TIMESTAMP, SOME_KEY), Arrays.<Key<?>>asList(Key.TIMESTAMP));

    private long timeStamp;
    private String vmId;
    private String someStat;

    public TestStat() {
        super(null);
    }

    public TestStat(String writerId, long timeStamp, String vmId, String someStat) {
        super(writerId);
        this.timeStamp = timeStamp;
        this.vmId = vmId;
        this.someStat = someStat;
    }

    @Override
    @Persist
    public long getTimeStamp() {
        return timeStamp;
    }

    @Persist
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Persist
    public String getVmId() {
        return vmId;
    }

    @Persist
    public void setVmId(String vmId) {
        this.vmId = vmId;
    }

    @Persist
    public String getSomeStat() {
        return someStat;
    }

    @Persist
    public void setSomeStat(String someStat) {
        this.someStat = someStat;
    }
}
