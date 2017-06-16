/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lenovo.lic.machine.controller;

import java.util.Collection;
import com.lenovo.lic.machine.model.Machine;
import com.lenovo.lic.machine.model.MachinePool;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rodtq
 */
@RestController
public class MachineController {

    //url for test:
    //localhost:9001/getmachines
    @RequestMapping(value = "/getmachines", method = RequestMethod.GET)
    public Collection<Machine> getMachines() {
        MachinePool mp = MachinePool.getInstance();
        return mp.getPool().values();
    }

    //url for test:
    //localhost:9001/getmachine?id=1
    @RequestMapping(value = "/getmachine", method = RequestMethod.GET)
    public Machine getMachine(long id) {
        MachinePool mp = MachinePool.getInstance();
        Machine result = mp.getPool().get(id);
        return result;
    }

    //url for test:
    //localhost:9001/add
    //Payload example:
    //{
    //"id": 1,
    //"model": "teste1",
    //"serialNumber": "asd1",
    //"name": "nome",
    //"processor": "i7",
    //"memory": "8g",
    //"hd": "1tb",
    //"temperature": "23",
    //"powerStatus": 0,
    // "address":[0,0,0,0] 
    //}
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public boolean CreateMachine(@RequestBody Machine machine) {

        MachinePool mp = MachinePool.getInstance();
        int[] resetAddress = {0, 0, 0, 0};
        machine.setAddress(resetAddress);
        machine.setMachineStatus(0);
        try {
            mp.getPool().put(machine.getId(), machine);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //url for test:
    //localhost:9001/edit
    //Payload example:
    //{
    //"id": 1,
    //"model": "teste2",
    //"serialNumber": "asd2",
    //"name": "nome 2",
    //"processor": "i7",
    //"memory": "8g",
    //"hd": "1tb"
    //}
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public boolean editMachine(@RequestBody Machine machine) {
        if (machine.getId() == 0) {
            return false;
        }
        MachinePool mp = MachinePool.getInstance();
        try {
            mp.getPool().replace(machine.getId(), machine);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //url for test:
    //localhost:9001/power?id=1&togglePower=1 --> machine on
    //localhost:9001/power?id=1&togglePower=0 --machine off
    @RequestMapping(value = "/power", method = RequestMethod.GET)
    public boolean powerMachine(long id, int togglePower) {
        MachinePool mp = MachinePool.getInstance();
        try {
            mp.getPool().get(id).setPowerStatus(togglePower);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //url for test:
    //localhost:9001/connect
    //Payload example:
    //[127,0,0,2]
    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public boolean connectToMachine(@RequestBody int[] address) {
        MachinePool mp = MachinePool.getInstance();
        Optional<Machine> machine = mp.getPool().values().stream().filter(m -> Arrays.equals(m.getAddress(), address)).findFirst();
        if (machine.isPresent()) {
            if (machine.get().getMachineStatus() == 1) {
                return false;
            }
            if (machine.get().getPowerStatus() == 1) {
                machine.get().setMachineStatus(1);
            }
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/disconnect", method = RequestMethod.POST)
    public boolean disconnectToMachine(@RequestBody int[] address) {
        MachinePool mp = MachinePool.getInstance();
        Optional<Machine> machine = mp.getPool().values().stream().filter(m -> Arrays.equals(m.getAddress(), address)).findFirst();
        if (machine.isPresent()) {
            if (machine.get().getPowerStatus() == 1) {
                machine.get().setMachineStatus(0);
            }
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/exclude", method = RequestMethod.GET)
    public boolean excludeMachine(long id) {
        MachinePool mp = MachinePool.getInstance();
        try {
            mp.getPool().remove(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
