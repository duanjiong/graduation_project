/**
 * 
 */
package server;

import image.Image;
import image.bmp.Bmp;

import java.io.*;
import java.net.*;
import java.util.regex.Pattern;

import wavelet.WaveletTrans;
import write.Write;

/**
 * @author Duan Jiong <djduanjiong@gmail.com>
 *
 */


public class Server extends ServerSocket {
	private static final int SERVER_PORT = 10000;

	public Server() throws IOException {
		super(SERVER_PORT);
		try {
			while (true) {
				Socket socket = accept();
				new CreateServerThread(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// --- CreateServerThread
	class CreateServerThread extends Thread {
		private Socket client;

		public CreateServerThread(Socket s) throws IOException {
			client = s;
			start();
		}

		public void run() {
			Image image = new Bmp().bmpToImage("ceshi.bmp");
			try {
				WaveletTrans.truncImage(image);
				WaveletTrans.waveletTrans(image, 3);
				Write.write(client, image);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} finally {
				try {
					client.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		new Server();
	}
}