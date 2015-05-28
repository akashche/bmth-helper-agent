package com.redhat.byteman.thermostat.helper;

import com.redhat.thermostat.storage.core.*;

/**
 * Created by alex on 5/28/15.
 */
public class TestStatDAO {

    public static final String QUERY = "ADD " + TestStat.CATEGORY.getName() +
            " SET '" + Key.AGENT_ID.getName() + "' = ?s , " +
            "'" + Key.VM_ID.getName() + "' = ?s , " +
            "'" + Key.TIMESTAMP.getName() + "' = ?l , " +
            "'" + TestStat.SOME_KEY.getName() + "' = ?s";

    public void add(Storage st, TestStat stat) throws DescriptorParsingException, StatementExecutionException {
        st.registerCategory(TestStat.CATEGORY);
        StatementDescriptor<TestStat> desc = new StatementDescriptor<>(TestStat.CATEGORY, QUERY);
        PreparedStatement<TestStat> prepared;
        prepared = st.prepareStatement(desc);
        prepared.setString(0, stat.getAgentId());
        prepared.setString(1, stat.getVmId());
        prepared.setLong(2, stat.getTimeStamp());
        prepared.setString(3, stat.getSomeStat());
        prepared.execute();
    }
}
