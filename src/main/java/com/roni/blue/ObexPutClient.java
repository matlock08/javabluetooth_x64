package rom.roni.blue;

import java.io.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.obex.*;
import com.machinezoo.sourceafis.*;
import java.nio.file.Paths;
import java.nio.file.Files;


public class ObexPutClient {
    
    public static final byte[] JOSE_ENROLL = {(byte)0x46,(byte)0x4D,(byte)0x52,(byte)0x00,(byte)0x20,(byte)0x32,(byte)0x30,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x7A,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xFC,(byte)0x01,(byte)0x44,(byte)0x00,(byte)0xC5,(byte)0x00,(byte)0xC5,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x54,(byte)0x18,(byte)0x40,(byte)0x61,(byte)0x00,(byte)0x35,(byte)0x13,(byte)0x5D,(byte)0x40,(byte)0x36,(byte)0x00,(byte)0x4E,(byte)0x38,(byte)0x5D,(byte)0x40,(byte)0x52,(byte)0x00,(byte)0x66,(byte)0x33,(byte)0x5D,(byte)0x40,(byte)0x22,(byte)0x00,(byte)0xA3,(byte)0x4C,(byte)0x5D,(byte)0x40,(byte)0xA0,(byte)0x00,(byte)0xA5,(byte)0x1E,(byte)0x5D,(byte)0x40,(byte)0x66,(byte)0x00,(byte)0xF2,(byte)0x6E,(byte)0x5D,(byte)0x80,(byte)0x73,(byte)0x00,(byte)0x2C,(byte)0x8E,(byte)0x5D,(byte)0x80,(byte)0x9F,(byte)0x00,(byte)0x46,(byte)0x60,(byte)0x5D,(byte)0x80,(byte)0xBF,(byte)0x00,(byte)0x53,(byte)0x4F,(byte)0x5D,(byte)0x80,(byte)0x78,(byte)0x00,(byte)0x69,(byte)0xDD,(byte)0x5D,(byte)0x80,(byte)0x93,(byte)0x00,(byte)0x72,(byte)0x5E,(byte)0x5D,(byte)0x80,(byte)0x2E,(byte)0x00,(byte)0x76,(byte)0x44,(byte)0x5D,(byte)0x80,(byte)0xCE,(byte)0x00,(byte)0x7C,(byte)0x3E,(byte)0x5D,(byte)0x80,(byte)0xA2,(byte)0x00,(byte)0xC4,(byte)0x13,(byte)0x5D,(byte)0x80,(byte)0x47,(byte)0x00,(byte)0xE5,(byte)0xEC,(byte)0x5D,(byte)0x40,(byte)0x43,(byte)0x00,(byte)0xB0,(byte)0x53,(byte)0x57,(byte)0x80,(byte)0x50,(byte)0x00,(byte)0xB6,(byte)0xE4,(byte)0x57,(byte)0x40,(byte)0x2E,(byte)0x00,(byte)0xF5,(byte)0x60,(byte)0x51,(byte)0x40,(byte)0x2A,(byte)0x01,(byte)0x02,(byte)0xE7,(byte)0x51,(byte)0x40,(byte)0xD3,(byte)0x01,(byte)0x03,(byte)0x17,(byte)0x51,(byte)0x80,(byte)0x96,(byte)0x00,(byte)0x89,(byte)0x3B,(byte)0x51,(byte)0x80,(byte)0xC8,(byte)0x01,(byte)0x01,(byte)0x8A,(byte)0x51,(byte)0x80,(byte)0x3A,(byte)0x01,(byte)0x03,(byte)0xED,(byte)0x51,(byte)0x40,(byte)0x8C,(byte)0x00,(byte)0x83,(byte)0x54,(byte)0x4B,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
    public static final byte[] JOSE_CAPTUR = {(byte)0x46,(byte)0x4D,(byte)0x52,(byte)0x00,(byte)0x20,(byte)0x32,(byte)0x30,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x7A,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xFC,(byte)0x01,(byte)0x44,(byte)0x00,(byte)0xC5,(byte)0x00,(byte)0xC5,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x5A,(byte)0x13,(byte)0x40,(byte)0xCA,(byte)0x00,(byte)0x43,(byte)0xF2,(byte)0x5D,(byte)0x40,(byte)0x5F,(byte)0x00,(byte)0x48,(byte)0x3B,(byte)0x5D,(byte)0x40,(byte)0x25,(byte)0x00,(byte)0x5B,(byte)0x4B,(byte)0x5D,(byte)0x40,(byte)0x71,(byte)0x00,(byte)0x6B,(byte)0x43,(byte)0x5D,(byte)0x40,(byte)0x96,(byte)0x00,(byte)0x80,(byte)0x48,(byte)0x5D,(byte)0x40,(byte)0x3B,(byte)0x00,(byte)0x8F,(byte)0x56,(byte)0x5D,(byte)0x40,(byte)0x66,(byte)0x00,(byte)0x9E,(byte)0x52,(byte)0x5D,(byte)0x40,(byte)0x58,(byte)0x00,(byte)0xC2,(byte)0x5A,(byte)0x5D,(byte)0x40,(byte)0x85,(byte)0x00,(byte)0xE4,(byte)0xEB,(byte)0x5D,(byte)0x80,(byte)0x98,(byte)0x00,(byte)0x2A,(byte)0x2D,(byte)0x5D,(byte)0x80,(byte)0xAD,(byte)0x00,(byte)0x3D,(byte)0x1D,(byte)0x5D,(byte)0x80,(byte)0x94,(byte)0x00,(byte)0x3F,(byte)0x31,(byte)0x5D,(byte)0x80,(byte)0xCF,(byte)0x00,(byte)0x67,(byte)0xC5,(byte)0x5D,(byte)0x80,(byte)0xAB,(byte)0x00,(byte)0xC3,(byte)0xF3,(byte)0x5D,(byte)0x80,(byte)0x4B,(byte)0x00,(byte)0xFC,(byte)0xE4,(byte)0x5D,(byte)0x80,(byte)0xD2,(byte)0x00,(byte)0x9F,(byte)0x13,(byte)0x57,(byte)0x80,(byte)0xD3,(byte)0x00,(byte)0xAC,(byte)0x72,(byte)0x57,(byte)0x80,(byte)0xD3,(byte)0x00,(byte)0x86,(byte)0x41,(byte)0x51,(byte)0x40,(byte)0xD4,(byte)0x00,(byte)0x7C,(byte)0x47,(byte)0x4B,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
    private OutputStream out;
    private InputStream in;
    private BufferedInputStream br;
    public byte mUpImage[]=new byte[73728];//36864
    public int mUpImageSize=0;

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

    
        byte[] probeImage = Files.readAllBytes(Paths.get("image1.bmp"));
        byte[] candidateImage = Files.readAllBytes(Paths.get("image2.bmp"));
        FingerprintTemplate probe = new FingerprintTemplate(probeImage);
        FingerprintTemplate candidate = new FingerprintTemplate(candidateImage);

        FingerprintMatcher matcher = new FingerprintMatcher(probe);
        double score = matcher.match(candidate);

        System.out.println(score);
    
        new ObexPutClient(serverURL);
    }

    public ObexPutClient(String serverURL) throws IOException {
        StreamConnection clientSession = (StreamConnection)Connector.open(serverURL);
        byte[] buf = new byte[1024];
        byte[] buff = new byte[9];
        byte[] buffer = new byte[512];
        out = clientSession.openOutputStream();
        in = clientSession.openInputStream();
        br = new BufferedInputStream(in);
/*
        sendCommand((byte)0x08, null, 0); // capture to host

        br.read(buff, 0, 9 );
        int size=(byte)(buff[5])+((buff[6]<<8)&0xFF00)-1;
        br.read(buffer, 0, size );
        //for (int i = 0; i < buffer.length;i++ ) {
        //    System.out.print( String.format("(byte)0x%02x,", (byte)buffer[i]) ); 
        //}
        int available = br.available();
        //System.out.println("Skiping " + available + "  ");
        br.skip( available );


        //System.arraycopy( JOSE_ENROLL, 0, buf, 0, JOSE_ENROLL.length ); // From DB
        //System.arraycopy( JOSE_CAPTUR, 0, buf, JOSE_ENROLL.length, JOSE_CAPTUR.length ); // From Device

        memcpy(buf,0,JOSE_ENROLL,0,512);
        memcpy(buf,512,JOSE_CAPTUR,0,512);

        sendCommand((byte)0x09,buf,1024);

        byte[] bufferResp = new byte[10];        
        br.read(bufferResp, 0, 10 );
        int score=(byte)(bufferResp[8])+((bufferResp[9]<<8)&0xF0);
        if(bufferResp[7]==1)
            System.out.print("Match Succeed:"+String.valueOf(score));
        else
            System.out.print("Match Fail"+String.valueOf(score));
            
        br.skip( br.available() );
        */
        System.out.println("Image");
        sendCommand((byte)0x30, null, 0); // capture to host

        byte databuff[]=new byte[73728];
        int i = 0;

        try {
            Thread.sleep(5000);
        } catch ( java.lang.InterruptedException ie ) {

        }
        while ( br.available() != 0 ) {
            databuff[i] = (byte)br.read();
            i++;
        }
        System.out.println();
        System.out.println("Receive " + i);
        ReceiveCommand(databuff,i);

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

        for (int i = 0; i < sendbuf.length;i++ ) {
            System.out.print( String.format("%02x ", (byte)sendbuf[i]) ); 
        }
        
    }

    private void memcpy(byte[] dstbuf,int dstoffset,byte[] srcbuf,int srcoffset,int size) {
		for(int i=0;i<size;i++) {
            try {
			    dstbuf[dstoffset+i]=srcbuf[srcoffset+i];
            } catch (NullPointerException e) {
            }
		}
        return;
	}

    private int calcCheckSum(byte[] buffer,int size) {
		int sum=0;
		for(int i=0;i<size;i++) {
			sum=sum+buffer[i];
		}
		return (sum & 0x00ff);
	}

    private byte[] changeByte(int data) {
		byte b4 = (byte) ((data) >> 24);
		byte b3 = (byte) (((data) << 8) >> 24);
		byte b2 = (byte) (((data) << 16) >> 24);
		byte b1 = (byte) (((data) << 24) >> 24);
		byte[] bytes = { b1, b2, b3, b4 };
		return bytes;
	}

    private byte[] toBmpByte(int width, int height, byte[] data) {
		byte[] buffer = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);

			int bfType = 0x424d;
			int bfSize = 54 + 1024 + width * height;
			int bfReserved1 = 0;
			int bfReserved2 = 0;
			int bfOffBits = 54 + 1024;

			dos.writeShort(bfType);
			dos.write(changeByte(bfSize), 0, 4);
			dos.write(changeByte(bfReserved1), 0, 2);
			dos.write(changeByte(bfReserved2), 0, 2);
			dos.write(changeByte(bfOffBits), 0, 4);

			int biSize = 40;
			int biWidth = width;
			int biHeight = height;
			int biPlanes = 1;
			int biBitcount = 8;
			int biCompression = 0;
			int biSizeImage = width * height;
			int biXPelsPerMeter = 0;
			int biYPelsPerMeter = 0;
			int biClrUsed = 256;
			int biClrImportant = 0;

			dos.write(changeByte(biSize), 0, 4);
			dos.write(changeByte(biWidth), 0, 4);
			dos.write(changeByte(biHeight), 0, 4);
			dos.write(changeByte(biPlanes), 0, 2);
			dos.write(changeByte(biBitcount), 0, 2);
			dos.write(changeByte(biCompression), 0, 4);
			dos.write(changeByte(biSizeImage), 0, 4);
			dos.write(changeByte(biXPelsPerMeter), 0, 4);
			dos.write(changeByte(biYPelsPerMeter), 0, 4);
			dos.write(changeByte(biClrUsed), 0, 4);
			dos.write(changeByte(biClrImportant), 0, 4);

			byte[] palatte = new byte[1024];
			for (int i = 0; i < 256; i++) {
				palatte[i * 4] = (byte) i;
				palatte[i * 4 + 1] = (byte) i;
				palatte[i * 4 + 2] = (byte) i;
				palatte[i * 4 + 3] = 0;
			}
			dos.write(palatte);

			dos.write(data);
			dos.flush();
			buffer = baos.toByteArray();
			dos.close();
			baos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}
    
    public byte[] getFingerprintImage(byte[] data,int width,int height) {
		if (data == null) {
			return null;
		}
		byte[] imageData = new byte[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			imageData[i * 2] = (byte) (data[i] & 0xf0);
			imageData[i * 2 + 1] = (byte) (data[i] << 4 & 0xf0);
		}
		byte[] bmpData = toBmpByte(width, height, imageData);
		return bmpData;
	}
    
    private void ReceiveCommand(byte[] databuf,int datasize) { 
    	System.out.println("datasize " + datasize);
    		memcpy(mUpImage,mUpImageSize,databuf,0,datasize);
			mUpImageSize=mUpImageSize+datasize;
			if(mUpImageSize>=15200){
				byte[] bmpdata=getFingerprintImage(mUpImage,152,200);
                try {
				FileOutputStream fos = new FileOutputStream("image.bmp");
                fos.write(bmpdata);
                fos.close();
                } catch (Exception e  ){

                }
                
				mUpImageSize=0;
			
			}
   		
           /*
           else{
 			memcpy(mCmdData,mCmdSize,databuf,0,datasize);
   	   		mCmdSize=mCmdSize+datasize;   			
   			int totalsize=(byte)(mCmdData[5])+((mCmdData[6]<<8)&0xFF00)+9;   			
   			if(mCmdSize>=totalsize){
   				mCmdSize=0;
   				mIsWork=false;
   		    	if((mCmdData[0]=='F')&&(mCmdData[1]=='T'))	{
   		    		switch(mCmdData[4]) {
   		    		case CMD_PASSWORD: {    				
   		    			}
   		    			break;
   		    		case CMD_ENROLID: {
   		    				if(mCmdData[7]==1) {
   		    					int id=mCmdData[8]+(mCmdData[9]<<8);
   		    					AddStatusList("Enrol Succeed:"+String.valueOf(id));
   		    				}
   		    				else
   		    					AddStatusList("Enrol Fail");
   		    					
   		    			}
   		    			break;
   		    		case CMD_VERIFY: {
   		    				if(mCmdData[7]==1)
   		    					AddStatusList("Verify Succeed");
   		    				else
   		    					AddStatusList("Verify Fail");
   		    			}
   		    			break;
   		    		case CMD_IDENTIFY: {
   		    				if(mCmdData[7]==1) {
   		    					int id=(byte)(mCmdData[8])+(byte)((mCmdData[9]<<8)&0xF0);
   		        				AddStatusList("Search Result:"+String.valueOf(id));
   		    				} else
   		    					AddStatusList("Search Fail");
   		    			}
   		    			break;
   		    		case CMD_DELETEID:
   		    			{
   		    				if(mCmdData[7]==1)
   		    					AddStatusList("Delete Succeed");
   		    				else
   		    					AddStatusList("Delete Fail");
   		    			}
   		    			break;
   		    		case CMD_CLEARID: {
   		    				if(mCmdData[7]==1)
   		    					AddStatusList("Clear Succeed");
   		    				else
   		    					AddStatusList("Clear Fail");
   		    			}
   		    			break;
   		    		case CMD_ENROLHOST: {
   		    				int size=(byte)(mCmdData[5])+((mCmdData[6]<<8)&0xFF00)-1;
   		    				if(mCmdData[7]==1) {
   		    					memcpy(mRefData,0,mCmdData,8,size);
   		    					mRefSize=size;
   		    					//ת��
   		    
   		    					AddStatusList("Len="+String.valueOf(mRefSize));
   		    					AddStatusListHex(mRefData,mRefSize);
   		    					AddStatusList("Enrol Succeed");
   		    				}else
   		    					AddStatusList("Enrol Fail");
   		    			}
   		    			break;
   		    		case CMD_CAPTUREHOST: {
   		    				int size=(byte)(mCmdData[5])+((mCmdData[6]<<8)&0xFF00)-1;
   		    				if(mCmdData[7]==1) {
   		    					memcpy(mMatData,0,mCmdData,8,size);
   		    					mMatSize=size;
   		    					//ת��
   		    
   		    					AddStatusList("Len="+String.valueOf(mMatSize));
   		    					AddStatusListHex(mMatData,mMatSize);
   		    					AddStatusList("Capture Succeed");
   		    				}
   		    				else
   		    					AddStatusList("Capture Fail");
   		    			}
   		    			break;
   		    		case CMD_MATCH:	{
   		    				int score=(byte)(mCmdData[8])+((mCmdData[9]<<8)&0xF0);
   		    				if(mCmdData[7]==1)
   		    					AddStatusList("Match Succeed:"+String.valueOf(score));
   		    				else
   		    					AddStatusList("Match Fail");
   		    			}
   		    			break;
   		    		case CMD_WRITECARD: {
   		    				if(mCmdData[7]==1)
   		    					AddStatusList("Write Card Succeed");
   		    				else
   		    					AddStatusList("Write Card Fail");
   		    			}
   		    			break;
   		    		case CMD_READCARD: {
   		    				int size=(byte)(mCmdData[5])+((mCmdData[6]<<8)&0xF0);
   		    				if(size>0)
   		    				{
   		    					memcpy(mCardData,0,mCmdData,7,size);
   		    					mCardSize=size;
   		    					AddStatusList("Read Card Succeed");
   		    				}
   		    				else
   		    					AddStatusList("Read Card Fail");
   		    			}
   		    			break;
   		    		case CMD_CARDID: {
   		    				if(mCmdData[7]==1)
   		    					AddStatusList("Match Succeed");
   		    				else
   		    					AddStatusList("Match Fail");
   		    			}
   		    			break;
   		    		case CMD_CARDFINGER: {
   							if(mCmdData[7]==1)
   								AddStatusList("Match Succeed");
   							else
   								AddStatusList("Match Fail");
   						}
   						break;
   		    		case CMD_UPCARDSN:
   		    		case CMD_CARDSN: {
   		    				int size=(byte)(mCmdData[5])+((mCmdData[6]<<8)&0xF0)-1;
   		    				if(size>0) {
   		    					memcpy(mCardSn,0,mCmdData,8,size);    			    					
   		    					AddStatusList("Read Card SN Succeed:"+Integer.toHexString(mCardSn[0]&0xFF)+Integer.toHexString(mCardSn[1]&0xFF)+Integer.toHexString(mCardSn[2]&0xFF)+Integer.toHexString(mCardSn[3]&0xFF));
   		    				}
   		    				else
   		    					AddStatusList("Read Card SN Fail");
   		    			}
   		    			break;
   		    		case CMD_GETSN:{
   		    				int size=(byte)(mCmdData[5])+((mCmdData[6]<<8)&0xFF00)-1;
   		    				if(mCmdData[7]==1) {
   		    					byte[] snb=new byte[32];
   		    					memcpy(snb,0,mCmdData,8,size);
   		    					String sn = null;
   		    					try {
   		    						sn = new String(snb,0,size,"UNICODE");
   		    					} catch (UnsupportedEncodingException e) {
   		    						e.printStackTrace();
   		    					}
   		    					AddStatusList("SN:"+sn);
   		    				}
   		    				else
   		    					AddStatusList("Get SN Fail");
   		    			}
   		    			break;
   		    		case CMD_PRINTCMD:{
   							if(mCmdData[7]==1){
   								AddStatusList("Print OK");
   							}else{
   								AddStatusList("Print Fail");
   							}
   		    			}
   						break;
   		    		case CMD_GETBAT:{
   		    				int size=(byte)(mCmdData[5])+((mCmdData[6]<<8)&0xFF00)-1;
   		    				if(size>0)
   		    				{
   		    					memcpy(mBat,0,mCmdData,8,size);    			    					
   		    					AddStatusList("Battery Value:"+Integer.toString(mBat[0]/10)+"."+Integer.toString(mBat[0]%10)+"V");
   		    				}else
   		    					AddStatusList("Get Battery Value Fail");
   		    			}
   		    			break;
   		    		case CMD_GETCHAR:{
   		    				int size=(byte)(mCmdData[5])+((mCmdData[6]<<8)&0xFF00)-1;
		    				if(mCmdData[7]==1) {
		    					memcpy(mMatData,0,mCmdData,8,size);
		    					mMatSize=size;
		    					AddStatusList("Len="+String.valueOf(mMatSize));
		    					AddStatusList("Get Data Succeed");
		    					AddStatusListHex(mMatData,mMatSize);
		    				}
		    				else
		    					AddStatusList("Get Data Fail");
   		    			}
   		    			break;
   		    		}
   		    	}   				
   			}
           
   		}
               */
    }
}