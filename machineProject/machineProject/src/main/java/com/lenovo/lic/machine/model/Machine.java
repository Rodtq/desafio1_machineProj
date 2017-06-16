/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lenovo.lic.machine.model;

/**
 *
 * @author vntrotq
 */

public class Machine {

    //Machine Specification
    private long id;
    private String model;
    private String serialNumber;
    private String name;
    private String processor;
    private String memory;
    private String hd;
    private String temperature;
    private int powerStatus;
    private int[] address;
    private int machineStatus;

    public Machine() {
    }

    public Machine(Machine machine) {
        this.id = machine.id;
        this.model = machine.model;
        this.serialNumber = machine.serialNumber;
        this.name = machine.name;
        this.processor = machine.processor;
        this.memory = machine.memory;
        this.hd = machine.hd;
        this.temperature = machine.temperature;
        this.powerStatus = machine.powerStatus;
        this.address = machine.address;
        this.machineStatus = machine.machineStatus;

    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getProcessor() {
        return processor;
    }

    public String getMemory() {
        return memory;
    }

    public String getHd() {
        return hd;
    }

    public String getTemperature() {
        return temperature;
    }

    public int getPowerStatus() {
        return powerStatus;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param processor the processor to set
     */
    public void setProcessor(String processor) {
        this.processor = processor;
    }

    /**
     * @param memory the memory to set
     */
    public void setMemory(String memory) {
        this.memory = memory;
    }

    /**
     * @param hd the hd to set
     */
    public void setHd(String hd) {
        this.hd = hd;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    /**
     * @param powerStatus the powerStatus to set set 0 to powerOff set 1 to
     * powerOn
     */
    public void setPowerStatus(int powerStatus) {
        if (powerStatus == 1) {
            setAddress(addressGen());
        } else {
            int[] resetAddress = {0, 0, 0, 0};
            setAddress(resetAddress);
            setMachineStatus(0);
        }
        this.powerStatus = powerStatus;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the address
     */
    public int[] getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(int[] address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + (int) id;
        result = 31 * result + model.hashCode();
        result = 31 * result + serialNumber.hashCode();
        result = 31 * result + processor.hashCode();
        result = 31 * result + memory.hashCode();
        result = 31 * result + hd.hashCode();
        result = 31 * result + temperature.hashCode();
        result = 31 * result + powerStatus;
        return result;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof Machine)) {
            return false;
        }

        Machine machine = (Machine) o;

        return machine.name.equals(name)
                && machine.id == id
                && machine.processor.equals(processor)
                && machine.hd.equals(hd)
                && machine.memory.equals(memory)
                && machine.powerStatus == powerStatus
                && machine.serialNumber.equals(serialNumber)
                && machine.temperature.equals(temperature)
                && machine.model.equals(model);
    }

    private int[] addressGen() {
        int[] result = {127, 0, 0, 1};
        MachinePool mp = MachinePool.getInstance();
        if (mp.getPool().isEmpty()) {
            return result;
        }
        long lastKey = mp.getPool().lastKey();
        int[] lastAddress = mp.getPool().get(lastKey).getAddress();

        if (lastAddress == null) {
            return result;
        }
        if (lastAddress[3] <= 255) {
            result[3] += 1;
        } else if (lastAddress[2] <= 255) {
            result[2] += 1;
        } else if (lastAddress[1] <= 255) {
            result[1] += 1;
        } else if (lastAddress[0] <= 255) {
            return null;
        }
        return result;
    }

    /**
     * @return the machineStatus 0 to available 1 to unavailable
     */
    public int getMachineStatus() {
        return machineStatus;
    }

    /**
     * @param machineStatus the machineStatus to set
     */
    public void setMachineStatus(int machineStatus) {
        this.machineStatus = machineStatus;
    }

}
