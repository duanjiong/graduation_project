/**
 * 
 */
package client;

import image.Image;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import read.Read;

/**
 * @author Duan Jiong <djduanjiong@gmail.com>
 *
 */
public class Client extends JFrame{
	public static Socket socket = null;
	public JPanel panel = null;
	
	public Client() {
		panel = new JPanel();
		add(panel);
	}

	public static void main(String[] args) throws Exception {
		Client client = new Client();
		
		client.socket = new Socket("127.0.0.1", 10000);
		DataInputStream input = new DataInputStream(socket.getInputStream());
		
		int heigth = input.readInt();
		int width = input.readInt();
		int numcomps = input.readInt();
		client.setSize(width, heigth);
		client.setTitle("client");
		client.setLocationRelativeTo(null);
		client.setResizable(false);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.setVisible(true);
		
		
		Graphics g = client.panel.getGraphics();
		
		Image image = new Image(heigth, width, numcomps);
		Read.read(input, g, image);
	}
}

