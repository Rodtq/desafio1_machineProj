/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lenovo.lic.machine.model;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 *
 * @author rodtq
 */
public class MachinePool {

    //Pool of existing Machines
    private NavigableMap<Long, Machine> pool;
    private static MachinePool instance = null;

    private MachinePool() {

    }

    public static MachinePool getInstance() {
        if (instance == null) {
            instance = new MachinePool();
            instance.pool = new TreeMap<>();

        }
        return instance;
    }

    /**
     * @return the pool
     */
    public NavigableMap<Long, Machine> getPool() {

        return pool;
    }

    /**
     * @param pool the pool to set
     */
    public void setPool(NavigableMap<Long, Machine> pool) {
        this.pool = pool;
    }

}
