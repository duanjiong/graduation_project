/**
 * 
 */
package read;

import image.Image;
import image.PrintImage;

import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.sql.Time;

import lib.Help;

import draw.Draw;

import wavelet.WaveletInverseTrans;

/**
 * @author Duan Jiong <djduanjiong@gmail.com>
 *
 */
public class Read {
	public static void read(DataInputStream input, Graphics g, Image image) throws Exception {
		Image temp = null;
		
		int count = 0;
		int w = 0;
		int h = 0;
		int flag = 0;
		for (int a = 3; a > 0; a--) {
			
			w = image.width/(int)Math.pow(2, a);
			h = image.height/(int)Math.pow(2, a);
			
			
			
			for(int b = 1; b <= 3; b++) {
				
				temp = new Image(h*2, w*2, image.numcomps);
				
				if (a == 3 && flag == 0) {
					b = 0;
					temp = new Image(h, w, image.numcomps);
					flag = 1;
				}
				
				switch (b) {
				case 1:
					count = w;
					break;
				case 2:
					count = h*image.width;
					break;
				case 3:
					count = h*image.width + w;
					break;
				case 0:
					count = 0;
					break;
				}
				
//				int countTemp = count;
//				for (int c = 0; c < 3; c++) {
//					count = countTemp;
//					for (int d = 0; d < h; d++) {
//						for (int e = 0; e < w; e++) {
//							image.comps[c][count] = input.readFloat();
//							count++;
//						}
//						count += image.width-w;
//					}
//				}
				
				
				int countTemp = count;
				byte[] bytes = new byte[w*4];
				for (int c = 0; c < image.numcomps; c++) {
					count = countTemp;
					for (int d = 0; d < h; d++) {
						input.readFully(bytes, 0, w*4);
						System.out.println("read:"+d+":"+bytes[0]);
						for (int e = 0; e < w; e++) {
							image.comps[c][count] = Help.getFloat(bytes, e*4);
							count++;
						}
						count += image.width - w;
					}
				}
				
			
				for (int m = 0; m < 3; m++) {
					for (int n = 0; n < temp.height; n++) {
						for (int p = 0; p < temp.width; p++) {
							temp.comps[m][n*temp.width + p] = image.comps[m][n*image.width + p];
						}
					}
				}
				
				if (b == 0) {
					Draw.draw(temp, g);
					continue;
					//return;
				}
				
				
				WaveletInverseTrans.waveletInverseTrans(temp, 4-a);
				Draw.draw(temp, g);	
			}
		}
		
//		WaveletInverseTrans.waveletInverseTrans(image, 3);
//		Draw.draw(image, g);
	}
}
