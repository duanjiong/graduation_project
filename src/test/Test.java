package test;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import draw.Draw;
import preprocess.Preprocess;
import wavelet.WaveletInverseTrans;
import wavelet.WaveletTrans;
import image.Image;
import image.PrintImage;
import image.bmp.Bmp;

public class Test {
	public static void main(String[] args) {
		Bmp bmp = new Bmp();
		Image image = bmp.bmpToImage("ceshi.bmp");
		WaveletTrans.truncImage(image);
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.add(panel);
		frame.setTitle("Server");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(image.width, image.height);
		frame.setVisible(true);
		
		WaveletTrans.waveletTrans(image, 3);
		WaveletInverseTrans.waveletInverseTrans(image, 3);
		Graphics g = panel.getGraphics();
		Draw.draw(image, g);
	}
}
