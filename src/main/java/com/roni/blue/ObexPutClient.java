package rom.roni.blue;

import java.io.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.obex.*;

public class ObexPutClient {
    public static final byte[] JOSE = {(byte)0x4d,(byte)0x52,(byte)0x00,(byte)0x20,(byte)0x32,(byte)0x30,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x7a,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xfc,(byte)0x01,(byte)0x44,(byte)0x00,(byte)0xc5,(byte)0x00,(byte)0xc5,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x50,(byte)0x1a,(byte)0x40,(byte)0x60,(byte)0x00,(byte)0x24,(byte)0x73,(byte)0x5d,(byte)0x40,(byte)0x2c,(byte)0x00,(byte)0x2a,(byte)0x0e,(byte)0x5d,(byte)0x40,(byte)0xd5,(byte)0x00,(byte)0x36,(byte)0x47,(byte)0x5d,(byte)0x40,(byte)0xc3,(byte)0x00,(byte)0x59,(byte)0x46,(byte)0x5d,(byte)0x40,(byte)0x44,(byte)0x00,(byte)0x64,(byte)0xb9,(byte)0x5d,(byte)0x40,(byte)0x2f,(byte)0x00,(byte)0x78,(byte)0x34,(byte)0x5d,(byte)0x40,(byte)0xa8,(byte)0x00,(byte)0x39,(byte)0x41,(byte)0x5d,(byte)0x40,(byte)0xdd,(byte)0x00,(byte)0x7e,(byte)0x36,(byte)0x5d,(byte)0x40,(byte)0xc0,(byte)0x00,(byte)0xb2,(byte)0x32,(byte)0x5d,(byte)0x40,(byte)0xac,(byte)0x00,(byte)0xb7,(byte)0x20,(byte)0x5d,(byte)0x40,(byte)0x23,(byte)0x00,(byte)0xfc,(byte)0xee,(byte)0x5d,(byte)0x40,(byte)0x46,(byte)0x01,(byte)0x05,(byte)0x6f,(byte)0x5d,(byte)0xac,(byte)0x3e,(byte)0x00,(byte)0x25,(byte)0x1c,(byte)0x5d,(byte)0xac,(byte)0xfd,(byte)0x00,(byte)0x30,(byte)0x57,(byte)0x5d,(byte)0xac,(byte)0x51,(byte)0x00,(byte)0x3d,(byte)0x1c,(byte)0x5d,(byte)0xac,(byte)0x40,(byte)0x00,(byte)0x4f,(byte)0x14,(byte)0x5d,(byte)0xac,(byte)0x7c,(byte)0x00,(byte)0x57,(byte)0x62,(byte)0x5d,(byte)0xac,(byte)0x3a,(byte)0x00,(byte)0x65,(byte)0x4f,(byte)0x5d,(byte)0xac,(byte)0x71,(byte)0x00,(byte)0x26,(byte)0x59,(byte)0x5d,(byte)0xac,(byte)0xfd,(byte)0x00,(byte)0xd6,(byte)0x12,(byte)0x5d,(byte)0x40,(byte)0x76,(byte)0x00,(byte)0x78,(byte)0x37,(byte)0x57,(byte)0x40,(byte)0x21,(byte)0x00,(byte)0xc2,(byte)0x55,(byte)0x57,(byte)0xac,(byte)0x2e,(byte)0x00,(byte)0xc9,(byte)0xe5,(byte)0x57,(byte)0x40,(byte)0x6a,(byte)0x00,(byte)0x13,(byte)0x53,(byte)0x51,(byte)0xac,(byte)0x41,(byte)0x00,(byte)0x7b,(byte)0x2f,(byte)0x51,(byte)0xac,(byte)0x5a,(byte)0x00,(byte)0x70,(byte)0xa8,(byte)0x3e,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x1e};
    private OutputStream out;
    private InputStream in;
    private BufferedInputStream br;

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

        new ObexPutClient(serverURL);
        /*
        StreamConnection clientSession = (StreamConnection)Connector.open(serverURL);
        
        out = clientSession.openOutputStream();
        in = clientSession.openInputStream();
        br = new BufferedInputStream(in);

        // Capture Host 
        int sum1 = 0;
        byte[] sendBuffer = {'F','T',0,0,0x08,0,0,0,0}; 
        for (int i=0;i<7;i++) {
            sum1 += sendBuffer[i];
        }
        sum1 &= 0x00ff;
        sendBuffer[7] = (byte)sum1;
        sendBuffer[8] = (byte)(sum1>>8);
        out.write( sendBuffer );
        out.flush();

        System.out.println("SENT ");

        
        byte[] buff = new byte[9];
        byte[] buffer = new byte[512];
        br.read(buff, 0, 9 );
        System.out.println("RCV ");
        
        System.out.print( (char)buff[0] ); // F
        System.out.print( (char)buff[1] ); // T
        System.out.print( " " );
        System.out.print( String.format("%04x ", (int)buff[4]) ); // Command 0x08
        int size=(byte)(buff[5])+((buff[6]<<8)&0xFF00)-1;
        System.out.print( " " );
        System.out.print( size ); // Size
        System.out.print( " " );
        System.out.print( (int)buff[7] ); // Status 
        br.read(buffer, 0, size );
        System.out.println();
        
        br.skip( br.available() );

        System.out.println("SEND MATCH");
	    byte buf[]=new byte[1024];
                        //Origen   Dest    Eleme
        System.arraycopy( JOSE, 0, buf, 0, JOSE.length ); // From DB
        System.arraycopy( buffer, 0, buf, JOSE.length, size ); // From Device

        for (int i = 0; i < buf.length;i++ ) {
            System.out.print( String.format("%02x ", (byte)buf[i]) ); // Command 0x08
        }
        System.out.println();

        int sendsize=9+1024;
    	byte[] sendbuf = new byte[sendsize];
    	sendbuf[0]='F';
    	sendbuf[1]='T';
    	sendbuf[2]=0;
    	sendbuf[3]=0;
    	sendbuf[4]=0x09;
    	sendbuf[5]=(byte)(1024);
    	sendbuf[6]=(byte)(1024>>8);
    	if(1024>0) {
    		for(int i=0;i<1024;i++) {
    			sendbuf[7+i]=buf[i];
    		}
    	}
    	int sum=ObexPutClient.calcCheckSum(sendbuf,(7+1024));
    	sendbuf[7+1024]=(byte)(sum);
    	sendbuf[8+1024]=(byte)(sum>>8);
        out.write( sendbuf );
        out.flush();
        
        // REad 
        byte[] bufferResp = new byte[12];
        
        br.read(bufferResp, 0, 10 );
        System.out.println("RCV ");
        
        System.out.print( (char)bufferResp[0] ); // F
        System.out.print( (char)bufferResp[1] ); // T
        System.out.print( " " );
        System.out.print( String.format("%04x ", (int)bufferResp[4]) ); // Command 0x08
        int score=(byte)(bufferResp[8])+((bufferResp[9]<<8)&0xF0);

        System.out.print( " " );
        System.out.print( score ); // Size
        System.out.print( " " );
        System.out.print( (int)bufferResp[7] ); // Status 
        
        System.out.println();
        
        clientSession.close();
        */
    }

    public ObexPutClient(String serverURL) throws IOException {
        StreamConnection clientSession = (StreamConnection)Connector.open(serverURL);
        byte[] buf = new byte[1024];
        byte[] buff = new byte[9];
        byte[] buffer = new byte[512];
        out = clientSession.openOutputStream();
        in = clientSession.openInputStream();
        br = new BufferedInputStream(in);

        sendCommand((byte)0x08, null, 0); // Enrol Host

        br.read(buff, 0, 9 );
        int size=(byte)(buff[5])+((buff[6]<<8)&0xFF00)-1;
        br.read(buffer, 0, size );
        br.skip( br.available() );

        System.arraycopy( JOSE, 0, buf, 0, JOSE.length ); // From DB
        System.arraycopy( buffer, 0, buf, JOSE.length, size ); // From Device

        sendCommand((byte)0x09,buf,1024);

        byte[] bufferResp = new byte[12];        
        br.read(bufferResp, 0, 10 );
        int score=(byte)(bufferResp[8])+((bufferResp[9]<<8)&0xF0);
        if(bufferResp[7]==1)
            System.out.print("Match Succeed:"+String.valueOf(score));
        else
            System.out.print("Match Fail");
            
        br.skip( br.available() );

        clientSession.close();
    }

    private void sendCommand(byte cmdid,byte[] data,int size) throws IOException {

        int sendsize=9+size;
        byte[] sendbuf = new byte[sendsize];
        sendbuf[0]='F';
        sendbuf[1]='T';
        sendbuf[2]=0;
        sendbuf[3]=0;
        sendbuf[4]=cmdid;
        sendbuf[5]=(byte)(size);
        sendbuf[6]=(byte)(size>>8);
        if(size>0) {
            for(int i=0;i<size;i++) {
                sendbuf[7+i]=data[i];
            }
        }
        int sum=calcCheckSum(sendbuf,(7+size));
        sendbuf[7+size]=(byte)(sum);
        sendbuf[8+size]=(byte)(sum>>8);


        out.write( sendbuf );
        out.flush();

        
    }

    private int calcCheckSum(byte[] buffer,int size) {
		int sum=0;
		for(int i=0;i<size;i++) {
			sum=sum+buffer[i];
		}
		return (sum & 0x00ff);
	}
}