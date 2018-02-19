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
        byte[] sendBuffer = {'F','T',0,0,0x08,0,0,0,0}; 
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
        char[] buff = new char[9];
        char[] buffer = new char[512];
        br.read(buff, 0, 9 );
        System.out.println("RCV ");
        
        System.out.print( buff[0] ); // F
        System.out.print( buff[1] ); // T
        System.out.print( " " );
        System.out.print( String.format("%04x ", (int)buff[4]) ); // Command 0x08
        int size=(byte)(buff[5])+((buff[6]<<8)&0xFF00)-1;
        System.out.print( " " );
        System.out.print( size ); // Size
        System.out.print( " " );
        System.out.print( (int)buff[7] ); // Status 
        br.read(buffer, 0, size );

	byte buf[]=new byte[1024];
        // memcpy(buf,0,buffer,0,512); // From DB
        // memcpy(buf,512,buffer,0,512); // From Reader

        
        
        
        
        clientSession.close();
    }
}