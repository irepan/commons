package com.itappservices.commons.io.socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws Exception {
		String fileName = null;

		try {
			fileName = args[0];
		} catch (Exception e) {
			System.err.println("Enter the name of the file :");
			System.exit(1);
		}
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Scanner scanner = null;
		Socket socket = null;
		try {
			
			scanner = new Scanner(System.in);
			String file_name = scanner.nextLine();

			File file = new File(file_name);
			socket = new Socket("localhost", 3332);
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());

			oos.writeObject(file.getName());

			fis = new FileInputStream(file);
			byte[] buffer = new byte[Server.BUFFER_SIZE];
			Integer bytesRead = 0;

			while ((bytesRead = fis.read(buffer)) > 0) {
				oos.writeObject(bytesRead);
				oos.writeObject(Arrays.copyOf(buffer, buffer.length));
			}

			System.exit(0);
		} finally {
			if (scanner != null) {
				scanner.close();
			}
			if (oos != null ){
				oos.close();
			}
			if (ois != null) {
				ois.close();		
			}
			if (fis != null) {
				fis.close();
			}
			if (socket != null) {
				socket.close();
			}
		}
	}

}