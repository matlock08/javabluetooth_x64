package rom.roni.blue;

import java.io.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.obex.*;
import com.machinezoo.sourceafis.*;
import java.nio.file.Paths;
import java.nio.file.Files;


public class RFCommClient {
	private final static byte CMD_ENROLID=0x02;		//Enroll in Device
    private final static byte CMD_VERIFY=0x03;		//Verify in Device
    private final static byte CMD_IDENTIFY=0x04;	//Identify in Device
    private final static byte CMD_DELETEID=0x05;	//Delete in Device
    private final static byte CMD_CLEARID=0x06;		//Clear in Device

    private final static byte CMD_ENROLHOST=0x07;	//Enroll to Host
    private final static byte CMD_CAPTUREHOST=0x08;	//Capture to Host
    private final static byte CMD_MATCH=0x09;		//Match
    private final static byte CMD_WRITEFPCARD=0x0A;	//Write Card Data
    private final static byte CMD_READFPCARD=0x0B;	//Read Card Data
    private final static byte CMD_CARDSN=0x0E;		//Read Card Sn
    private final static byte CMD_GETSN=0x10;		//Get Device SN
    private final static byte CMD_FPCARDMATCH=0x13; //Card Match

    private final static byte CMD_WRITEDATACARD = 0x14;    //Write Card Data
    private final static byte CMD_READDATACARD = 0x15;     //Read Card Data

    private final static byte CMD_GETBAT=0x21;
    private final static byte CMD_GETIMAGE=0x30;
    private final static byte CMD_GETCHAR=0x31;
    private final static byte CMD_UPCARDSN=0x43;

	private StreamConnection clientSession;
    private OutputStream out;
    private BufferedInputStream br;
    
	public RFCommClient(String[] args ) throws IOException,InterruptedException  {
		String serverURL;
        if ((args != null) && (args.length > 0)) {
            serverURL = args[0];
        } else {
            String[] searchArgs = null;
            ServicesSearch services = new ServicesSearch(searchArgs);
            if (services.getServiceFound().size() == 0) {
                System.out.println("RFComm service not found");
                return;
            }
            // Select the first service found
            serverURL = services.getServiceFound().get(0);
        }

        System.out.println("Connecting to " + serverURL);

		clientSession = (StreamConnection)Connector.open(serverURL);

		out = clientSession.openOutputStream();
        br = new BufferedInputStream(clientSession.openInputStream());
		    
	}

	public void close() {
		try {
			out.close();
			br.close();
			clientSession.close();
		} catch ( IOException ie ) {
			System.out.println(ie.getMessage());
		}
	}



    public static void main(String[] args) throws IOException, InterruptedException {
		RFCommClient rfcomm = new RFCommClient(args);     
        
		/*
		byte[] probeImage = rfcomm.getFingerPrintImageAsBMP(5000);
        FingerprintTemplate probe = new FingerprintTemplate(probeImage);
        FingerprintTemplate candidate = new FingerprintTemplate(probeImage);

        FingerprintMatcher matcher = new FingerprintMatcher(probe);
        double score = matcher.match(candidate);

		System.out.println(); 
        System.out.println("Score " + score);    
		*/


		/*
		byte[] templateDevice = rfcomm.enrollHost(5000);
		
		FingerprintTemplate template = new FingerprintTemplate(templateDevice);

		System.out.println(template.json() );

		byte[] templateCaptureByte = rfcomm.captureHost(5000);
		
		FingerprintTemplate templateCapture = new FingerprintTemplate(templateCaptureByte);

		System.out.println(templateCapture.json() );

		FingerprintMatcher matcher = new FingerprintMatcher(template);
        double score = matcher.match(templateCapture);

		System.out.println(); 
        System.out.println("Score " + score); 
		*/

		byte[] templateDevice = rfcomm.enrollHost(5000);
		
		byte[] templateCaptureByte = rfcomm.captureHost(5000);

		byte[] aux = rfcomm.matchDevice(5000, templateDevice, templateCaptureByte );
        
		rfcomm.close();
    }

	/**
	 * Method to get the raw image on BMP format from device
	 */
	public byte[] getFingerPrintImageAsBMP(long timeout ) throws IOException {
		byte databuff[]=new byte[73728];
        int i = 0;
		sendCommand(CMD_GETIMAGE, null, 0);         

        try {
            Thread.sleep(timeout);
        } catch ( java.lang.InterruptedException ie ) {

        }

        while ( br.available() != 0 ) {
            databuff[i] = (byte)br.read();
            i++;
        }
        
        return receiveCommandImage(databuff,i);
	}

	/**
	 * Method to get the template on device specific format for enroll
	 */
	public byte[] enrollHost(long timeout ) throws IOException {
		byte[] databuff = new byte[1024];
		int i = 0;
		sendCommand(CMD_ENROLHOST, null, 0);         

        try {
            Thread.sleep(timeout);
        } catch ( java.lang.InterruptedException ie ) {

        }

        while ( br.available() != 0 ) {
            databuff[i] = (byte)br.read();
            i++;
        }
        
        return receiveCommand(databuff,i);
	}

	/**
	 * Method to get the template on device specific format to capture
	 */
	public byte[] captureHost(long timeout ) throws IOException {
		byte[] databuff = new byte[1024];
		int i = 0;
		sendCommand(CMD_CAPTUREHOST, null, 0);         

        try {
            Thread.sleep(timeout);
        } catch ( java.lang.InterruptedException ie ) {

        }

        while ( br.available() != 0 ) {
            databuff[i] = (byte)br.read();
            i++;
        }
        
        return receiveCommand(databuff,i);
	}

	public byte[] matchDevice(long timeout, byte []enrol, byte []capture ) throws IOException {
		byte[] buffer = new byte[1024];
		byte[] databuff = new byte[1024];
		int i = 0;

		memcpy(buffer,0,enrol,0,512);
		memcpy(buffer,512,capture,0,512);
		
		sendCommand(CMD_MATCH, buffer, 1024);         

        try {
            Thread.sleep(timeout);
        } catch ( java.lang.InterruptedException ie ) {

        }

        while ( br.available() != 0 ) {
            databuff[i] = (byte)br.read();
            i++;
        }
        
        return receiveCommand(databuff,i);
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
    
    private byte[] receiveCommandImage(byte[] databuf,int datasize) { 
		    		
		if(datasize>=15200){
			byte[] bmpdata=getFingerprintImage(databuf,152,200);
               
			return bmpdata;
		}
   		
		return null;
	}
		
	private byte[] receiveCommand(byte[] databuf,int datasize) {
		

   		    	if((databuf[0]=='F')&&(databuf[1]=='T'))	{
   		    		switch(databuf[4]) {
   		    		case CMD_ENROLID: {
   		    				if(databuf[7]==1) {
   		    					int id=databuf[8]+(databuf[9]<<8);
   		    					System.out.println("Enrol Succeed:"+String.valueOf(id));
   		    				}
   		    				else
   		    					System.out.println("Enrol Fail");
   		    					
   		    			}
   		    			break;
   		    		case CMD_VERIFY: {
   		    				if(databuf[7]==1)
   		    					System.out.println("Verify Succeed");
   		    				else
   		    					System.out.println("Verify Fail");
   		    			}
   		    			break;
   		    		case CMD_IDENTIFY: {
   		    				if(databuf[7]==1) {
   		    					int id=(byte)(databuf[8])+(byte)((databuf[9]<<8)&0xF0);
   		        				System.out.println("Search Result:"+String.valueOf(id));
   		    				} else
   		    					System.out.println("Search Fail");
   		    			}
   		    			break;
   		    		case CMD_DELETEID:
   		    			{
   		    				if(databuf[7]==1)
   		    					System.out.println("Delete Succeed");
   		    				else
   		    					System.out.println("Delete Fail");
   		    			}
   		    			break;
   		    		case CMD_CLEARID: {
   		    				if(databuf[7]==1)
   		    					System.out.println("Clear Succeed");
   		    				else
   		    					System.out.println("Clear Fail");
   		    			}
   		    			break;
   		    		case CMD_ENROLHOST: {
   		    				int size=(byte)(databuf[5])+((databuf[6]<<8)&0xFF00)-1;
   		    				if(databuf[7]==1) {
								System.out.println("Enrol Succeed");
								byte[] buffer = new byte[size];
   		    					memcpy(buffer,0,databuf,8,size);   		    					
   		    					return buffer;
   		    					
   		    				}else
   		    					System.out.println("Enrol Fail");
   		    			}
   		    			break;
   		    		case CMD_CAPTUREHOST: {
   		    				int size=(byte)(databuf[5])+((databuf[6]<<8)&0xFF00)-1;
   		    				if(databuf[7]==1) {
   		    					System.out.println("Capture Succeed");
								byte[] buffer = new byte[size];
   		    					memcpy(buffer,0,databuf,8,size);   		    					
   		    					return buffer;
   		    				}
   		    				else
   		    					System.out.println("Capture Fail");
   		    			}
   		    			break;
   		    		case CMD_MATCH:	{
   		    				int score=(byte)(databuf[8])+((databuf[9]<<8)&0xF0);
   		    				if(databuf[7]==1)
   		    					System.out.println("Match Succeed:"+String.valueOf(score));
   		    				else
   		    					System.out.println("Match Fail"+String.valueOf(score));
   		    			}
   		    			break;
   		    		case CMD_WRITEFPCARD: {
   		    				if(databuf[7]==1)
   		    					System.out.println("Write Card Succeed");
   		    				else
   		    					System.out.println("Write Card Fail");
   		    			}
   		    			break;
   		    		case CMD_READFPCARD: {
   		    				int size=(byte)(databuf[5])+((databuf[6]<<8)&0xF0);
   		    				if(size>0)
   		    				{
   		    					//memcpy(mCardData,0,databuf,7,size);
   		    					//mCardSize=size;
   		    					System.out.println("Read Card Succeed");
   		    				}
   		    				else
   		    					System.out.println("Read Card Fail");
   		    			}
   		    			break;
   		    		case CMD_CARDSN: {
   		    				if(databuf[7]==1)
   		    					System.out.println("Match Succeed");
   		    				else
   		    					System.out.println("Match Fail");
   		    			}
   		    			break;
   		    		case CMD_GETSN:{
   		    				int size=(byte)(databuf[5])+((databuf[6]<<8)&0xFF00)-1;
   		    				if(databuf[7]==1) {
   		    					byte[] snb=new byte[32];
   		    					memcpy(snb,0,databuf,8,size);
   		    					String sn = null;
   		    					try {
   		    						sn = new String(snb,0,size,"UNICODE");
   		    					} catch (UnsupportedEncodingException e) {
   		    						e.printStackTrace();
   		    					}
   		    					System.out.println("SN:"+sn);
   		    				}
   		    				else
   		    					System.out.println("Get SN Fail");
   		    			}
   		    			break;
   		    		case CMD_GETBAT:{
   		    				int size=(byte)(databuf[5])+((databuf[6]<<8)&0xFF00)-1;
   		    				if(size>0)
   		    				{
   		    					//memcpy(mBat,0,databuf,8,size);    			    					
   		    					System.out.println("Battery Value:"); //+Integer.toString(mBat[0]/10)+"."+Integer.toString(mBat[0]%10)+"V");
   		    				}else
   		    					System.out.println("Get Battery Value Fail");
   		    			}
   		    			break;
					}
   		    	}   				
   			
           
   		return null;
            
    }
}