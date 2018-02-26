package rom.roni.blue;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;
import javax.bluetooth.*;

/**
 *
 * Minimal Services Search example.
 */
public class ServicesSearch implements DiscoveryListener {

    static final UUID OBEX_OBJECT_PUSH = new UUID(0x0003);
    static final UUID SPP = new UUID("446118f08b1e11e29e960800200c9a66", false);
    private final List<String> serviceFound = new ArrayList<String>();
    private final Object serviceSearchCompletedEvent = new Object();

    public ServicesSearch(String args[]) throws IOException,InterruptedException  {
        // First run RemoteDeviceDiscovery and use discoved device
        RemoteDeviceDiscovery discovery = new RemoteDeviceDiscovery();
        
        serviceFound.clear();

        UUID serviceUUID = OBEX_OBJECT_PUSH;
        if ((args != null) && (args.length > 0)) {
            serviceUUID = new UUID(args[0], false);
        }

        UUID[] searchUuidSet = new UUID[] { serviceUUID };
        int[] attrIDs =  new int[] {
                0x0100 // Service name
        };

        for(RemoteDevice btDevice : discovery.getRemoteDevices() ) {
            
            synchronized(serviceSearchCompletedEvent) {
                LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrIDs, searchUuidSet, btDevice, this);
                serviceSearchCompletedEvent.wait();
            }
        }
    }

    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
    }

    public void inquiryCompleted(int discType) {
    }

    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
        for (int i = 0; i < servRecord.length; i++) {
            String url = servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            if (url == null) {
                continue;
            }
            serviceFound.add(url);
            DataElement serviceName = servRecord[i].getAttributeValue(0x0100);
            if (serviceName != null) {
                System.out.println("service " + serviceName.getValue() + " found " + url);
            } else {
                System.out.println("service found " + url);
            }
        }
    }

    public void serviceSearchCompleted(int transID, int respCode) {
        synchronized(serviceSearchCompletedEvent){
            serviceSearchCompletedEvent.notifyAll();
        }
    }

    public List<String> getServiceFound() {
        return serviceFound;
    }


}