package rom.roni.blue;

import java.io.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.obex.*;

public class ObexPutClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        String serverURL;
        if ((args != null) && (args.length > 0)) {
            serverURL = args[0];
        } else {
            String[] searchArgs = null;
            // Connect to OBEXPutServer from examples
            // searchArgs = new String[] { "11111111111111111111111111111123" };
            ServicesSearch.main(searchArgs);
            if (ServicesSearch.serviceFound.size() == 0) {
                System.out.println("OBEX service not found");
                return;
            }
            // Select the first service found
            serverURL = (String)ServicesSearch.serviceFound.elementAt(0);
        }

        System.out.println("Connecting to " + serverURL);

        //ClientSession clientSession = (ClientSession) Connector.open(serverURL);
        StreamConnection clientSession = (StreamConnection)Connector.open(serverURL);
        
        OutputStream out = clientSession.openOutputStream();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
        int sum = 0;
        byte[] sendBuffer = {'F','T',0,0,0x07,0,0,0,0}; 
        for (int i=0;i<7;i++) {
            sum += sendBuffer[i];
        }
        sum &= 0x00ff;
        sendBuffer[7] = (byte)sum;
        sendBuffer[8] = (byte)(sum>>8);
        out.write( sendBuffer );
        out.flush();

        System.out.println("SENT ");

        InputStream in = clientSession.openInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        char[] buff = new char[32];
        br.read(buff, 0, 32 );
        System.out.println("RCV ");
        for (int i = 0; i < 32; i++ ) {
            System.out.print( String.format("%04x ", (int)buff[i]) );
        }
        for (int i = 0; i < 32; i++ ) {
            System.out.print( buff[i] );
        }
        System.out.println();
        
        
        clientSession.close();
    }
}