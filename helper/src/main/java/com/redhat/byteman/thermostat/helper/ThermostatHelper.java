/*
 * Copyright 2015 Red Hat, Inc.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 2, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; see the file COPYING.  If not see
 * <http://www.gnu.org/licenses/>.
 */

package com.redhat.byteman.thermostat.helper;

import com.mongodb.*;
import com.redhat.thermostat.storage.core.DescriptorParsingException;
import com.redhat.thermostat.storage.core.StatementExecutionException;
import com.redhat.thermostat.storage.core.Storage;
import com.redhat.thermostat.storage.mongodb.internal.MongoAccessor;

import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Byteman Helper that sends data to Thermostat
 */
public class ThermostatHelper {

    private static final AtomicInteger counter = new AtomicInteger();
    private static MongoClient mc;
    private static Storage st;

    public static void activated() throws UnknownHostException, DescriptorParsingException, StatementExecutionException {
        st = new MongoAccessor("mongodb://127.0.0.1:27518").connect();
        System.out.println("ThermostatHelper#activated");
        System.out.println("Mongo connected: [" + st.getConnection().isConnected() +"]");
    }

    public static void installed(String ruleName) {
        System.out.println("ThermostatHelper#installed: [" + ruleName + "]");
    }

    public static void uninstalled(String ruleName) {
        System.out.println("ThermostatHelper#uninstalled: [" + ruleName + "]");
    }

    public static void deactivated() {
        mc.close();
        System.out.println("ThermostatHelper#deactivated");
    }

    public boolean sendToThermostat(String message) throws DescriptorParsingException, StatementExecutionException {
        System.out.println("sendToThermostat: [" + message + "]");
        new TestStatDAO().add(st, new TestStat("42", 42, "42", message));
        return true;
    }
}
