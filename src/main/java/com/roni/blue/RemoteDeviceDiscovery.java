package rom.roni.blue;

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
                System.out.println("wait for device inquiry to complete...");
                inquiryCompletedEvent.wait();
                System.out.println(devicesDiscovered.size() +  " device(s) found");
            }
        }
    }

    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
        System.out.println("Device " + btDevice.getBluetoothAddress() + " found");
        devicesDiscovered.add(btDevice);
        try {
            System.out.println("     name " + btDevice.getFriendlyName(false));
        } catch (IOException cantGetDeviceName) {
        }
    }

    public void inquiryCompleted(int discType) {
        System.out.println("Device Inquiry completed!");
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

    public static void main(String[] args) throws IOException, InterruptedException {
        new RemoteDeviceDiscovery();
        
    }

}