package com.roni.blue;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.bluetooth.*;

/**
 * Minimal Device Discovery example.
 */
public class RemoteDeviceDiscovery implements DiscoveryListener {

    private final List<RemoteDevice> devicesDiscovered = new ArrayList<RemoteDevice>();
    final Object inquiryCompletedEvent = new Object();

    public RemoteDeviceDiscovery() throws BluetoothStateException, InterruptedException {
        devicesDiscovered.clear();

        synchronized(inquiryCompletedEvent) {
            boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, this);
            if (started) {
                inquiryCompletedEvent.wait();
            }
        }
    }

    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
        devicesDiscovered.add(btDevice);
        try {
            System.out.println("     name " + btDevice.getFriendlyName(false));
        } catch (IOException cantGetDeviceName) {
        }
    }

    public void inquiryCompleted(int discType) {
        synchronized(inquiryCompletedEvent){
            inquiryCompletedEvent.notifyAll();
        }
    }

    public void serviceSearchCompleted(int transID, int respCode) {
    }

    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
    }

    public List<RemoteDevice> getRemoteDevices() {
        return devicesDiscovered;
    }

    
}