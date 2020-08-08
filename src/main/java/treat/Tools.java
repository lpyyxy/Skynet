package treat;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Random;

public final class Tools {
	private static final Base64.Decoder DECODER = Base64.getDecoder();
	private static final Base64.Encoder ENCODER = Base64.getEncoder();
	
	public static byte[] base64Decoder(String text) {
		return DECODER.decode(text);
	}
	
	public static String base64Encoder(byte[] text) {
		return ENCODER.encodeToString(text);
	}
	
	private static final Random RANDOM = new Random();
	
	public static int random(int max) {
		return RANDOM.nextInt(max+1);
	}
	public static int random() {
		return RANDOM.nextInt();
	}
	public static int byteArrayToInt(byte[] data) {
		return byteArrayToInt(0,data);
	}
	public static int byteArrayToInt(int subscript,byte[] data) {
		return (data[subscript]&0xff)|((data[subscript+1]&0xff)<<8)|((data[2]&0xff)<<16)|((data[3]&0xff)<<24);
	}
	public static short byteArrayToShort(byte[] data) {
		return byteArrayToShort(0,data);
	}
	public static short byteArrayToShort(int subscript,byte[] data) {
		return (short) ((data[subscript]&0xff)|((data[subscript+1]&0xff)<<8));
	}
	public static byte[] intToByteArray(int data) {
		byte[] bytes=new byte[4];
		bytes[3]=(byte)(data>>24);
		bytes[2]=(byte)(data>>16);
		bytes[1]=(byte)(data>>8);
		bytes[0]=(byte)data;
		return bytes;
	}
	public static byte[] shortToByteArray(short data) {
		byte[] bytes=new byte[2];
		bytes[1]=(byte)(data>>8);
		bytes[0]=(byte)data;
		return bytes;
	}
	public static short intToShort(int data) {
		return (short)data;
	}
	public static int shortToInt(short data) {
		return ((int)data)&0x0000ffff;
	}
	
	public static String byteArrayToString(byte[] data,int start) {
		System.arraycopy(data,start, data, 0,data.length-start);
		try {
			return new String(data,0,data.length-start,"UnicodeBigUnmarked");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static byte[] byteArrayMerge(byte[]... Byte) {
		if(Byte.length!=0) {
			int length=0;
			for(int i=0;i<Byte.length;i++) {
				length=length+Byte[i].length;
			}
			byte[] Command=new byte[length];
			int j=0;
			for(int i=0;i<Byte.length;i++) {
				System.arraycopy(Byte[i],0, Command, j,Byte[i].length);
				j=j+Byte[i].length;
			}
			return Command;
		}
		return null;
	}
	public static byte[] stringToByteArray(String tail) throws UnsupportedEncodingException {
		return tail.getBytes("UnicodeBigUnmarked");
	}
	
	public static byte[] byteArrayCut(int subscript,int length,byte[] data) {
		byte[] dataCopy=data;
		byte[] dataNew=new byte[length];
		System.arraycopy(dataCopy,subscript, dataNew,0,length);
		dataCopy=null;
		return dataNew;
	}
	public static byte[] byteArrayCutSubscript(int subscript,byte[] data) {
		return byteArrayCut(subscript,data.length-subscript,data);
	}
	public static byte[] byteArrayCutLength(int length,byte[] data) {
		return byteArrayCut(0,length,data);
	}
	
	
}
