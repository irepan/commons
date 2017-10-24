/**
 * 
 */
package com.itappservices.commons.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author irepan
 *
 */
public class StreamUtils {

	public static void CopyStream(InputStream is, OutputStream os) throws IOException{
		byte[] buffer = new byte[1024];
		int len = is.read(buffer);
		while (len != -1) {
			os.write(buffer, 0, len);
			len = is.read(buffer);
		}
		os.flush();
	}
	public static byte[] InputStreamToByteArray(InputStream is) throws IOException{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try{
			CopyStream(is,os);
		}catch (IOException e){
			throw e;
		}finally{
			try{
				is.close();
			}catch(IOException ie){
				ie.printStackTrace();
			}
			try{
				os.close();
			}catch(IOException ie){
				ie.printStackTrace();
			}
		}
		return os.toByteArray();
	}
	public static void ByteArrayToOutputStream(byte[] bar, OutputStream os) throws IOException{
		ByteArrayInputStream is = new ByteArrayInputStream(bar);
		try{
			CopyStream(is,os);
		}catch (IOException e){
			throw e;
		}finally{
			try{
				is.close();
			}catch(IOException ie){
				ie.printStackTrace();
			}
		}		
	}
}
