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

import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Byteman Helper that sends data to Thermostat
 */
public class ThermostatHelper {

    private static final AtomicInteger counter = new AtomicInteger();
    private static MongoClient mc;

    public static void activated() throws UnknownHostException {
        mc = new MongoClient("127.0.0.1", 27518);
        System.out.println("ThermostatHelper#activated");
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

    public boolean sendToThermostat(String message) {
        System.out.println("sendToThermostat: [" + message + "]");
        DB db = mc.getDB("thermostat");
        DBCollection col = db.getCollection("byteman-test-1");
        BasicDBObject doc = new BasicDBObject("message_" + counter.incrementAndGet(), message);
        col.insert(doc);
        return true;
    }
}
