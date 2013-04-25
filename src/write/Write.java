/**
 * 
 */
package write;

import java.io.DataOutputStream;
import java.net.Socket;

import image.Image;

/**
 * @author Duan Jiong <djduanjiong@gmail.com>
 *
 */
public class Write {

	public static void write(Socket socket, Image image) throws Exception {
		DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		
		output.writeInt(image.height);
		output.writeInt(image.width);
		output.writeInt(image.numcomps);
		
		int count = 0;
		int w = 0;
		int h = 0;
		int flag = 0;
		for (int a = 3; a > 0; a--) {
			
		
			w = image.width/(int)Math.pow(2, a);
			h = image.height/(int)Math.pow(2, a);
			
			for(int b = 1; b <= 3; b++) {
				
				if (a == 3 && flag == 0) {
					b = 0;
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
				
				
				int countTemp = count;
				for (int c = 0; c < 3; c++) {
					count = countTemp;
					for (int d = 0; d < h; d++) {
						for (int e = 0; e < w; e++) {
							output.writeFloat(image.comps[c][count]);
							count++;
						}
						count += image.width-w;
						
					}
				}
			}
		}
	}
	
}
