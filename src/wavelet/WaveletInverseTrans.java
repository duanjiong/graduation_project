/**
 * 
 */
package wavelet;

import image.Image;

/**
 * @author Duan Jiong <djduanjiong@gmail.com>
 *
 */
public class WaveletInverseTrans {

	public static void herizontalWavelet(Image image) {
		float[][] temp = new float[image.numcomps][image.height*image.width];
		
		for (int num = 0; num < image.numcomps; num++) {
			for (int h = 0; h < image.height; h++) {
				for (int w = 0; w < image.width/2; w++) {
					temp[num][h*image.width+2*w] = image.comps[num][h*image.width + w];
				}
				for (int w = 0; w < image.width/2; w++) {
					temp[num][h*image.width+2*w+1] = image.comps[num][h*image.width + image.width/2 + w];
					
				}
			}
		}
		
		image.comps = temp;
		
		for (int i = 0; i < image.numcomps; i++) {
			for (int j = 0; j < image.height; j++) {
				int index;
				for (index = 2; index < image.width; index += 2) {
					//System.out.println("wavelet 1, index="+index);
					temp[i][j*image.width+index] = (float) (image.comps[i][j*image.width+index]
							- (image.comps[i][j*image.width+index-1]+image.comps[i][j*image.width+index+1] +2)*0.25);
					
				}
				temp[i][j*image.width] = (float) (image.comps[i][j*image.width]
						- (image.comps[i][j*image.width+1]+image.comps[i][j*image.width+1] +2)*0.25);
			}
		}
		
		for (int i = 0; i < image.numcomps; i++) {
			for (int j = 0; j < image.height; j++) {
				int index;
				for (index = 1; index < image.width-1; index += 2) {
					//System.out.println("wavelet 2, index="+index);
					temp[i][j*image.width+index] = (float) (image.comps[i][j*image.width+index]
							+ (temp[i][j*image.width+index-1]+temp[i][j*image.width+index+1])*0.5);
					
				}
				temp[i][j*image.width+index] = (float) (image.comps[i][j*image.width+index]
						+ (temp[i][j*image.width+index-1] + 
								(image.comps[i][j*image.width+index-1] - (image.comps[i][j*image.width+index]+image.comps[i][j*image.width+index-2]+2)*0.25)
								)*0.5);
			}
		}
		
		image.comps = temp;
		
	}
	
	public static void verticalWavelet(Image image) {
		float[][] temp = new float[image.numcomps][image.height*image.width];
		
		for (int num = 0; num < image.numcomps; num++) {
			for (int h = 0; h < image.width; h++) {
				for (int w = 0; w < image.height/2; w++) {
					temp[num][(2*w)*image.width+h] = image.comps[num][w*image.width + h];
					
				}
				for (int w = 0; w < image.height/2; w++) {
					temp[num][(2*w+1)*image.width+h] = image.comps[num][(image.height/2 + w)*image.width + h];
				}
			}
		}
		
		image.comps = temp;
		
		for (int i = 0; i < image.numcomps; i++) {
			for (int j = 0; j < image.width; j++) {
				int index;
				for (index = 2; index < image.height; index += 2) {
					//System.out.println("wavelet 3, index="+index);
					temp[i][index*image.width+j] = (float) (image.comps[i][index*image.width+j]
							- (image.comps[i][(index-1)*image.width+j]+image.comps[i][(index+1)*image.width+j] +2)*0.25);
					
				}
				temp[i][j] = (float) (image.comps[i][j]
						- (image.comps[i][image.width+j]+image.comps[i][image.width+j] +2)*0.25);
				
			}
		}
		
		for (int i = 0; i < image.numcomps; i++) {
			for (int j = 0; j < image.width; j++) {
				int index;
				for (index = 1; index < image.height-1; index += 2) {
					//System.out.println("wavelet 4, index="+index);
					temp[i][index*image.width+j] = (float) (image.comps[i][index*image.width+j]
							+ (temp[i][(index-1)*image.width+j]+temp[i][(index+1)*image.width+j])*0.5);
					
				}
				temp[i][index*image.width+j] = (float) (image.comps[i][(index)*image.width+j]
						+ (temp[i][(index-1)*image.width+j] + 
								(image.comps[i][(index-1)*image.width+j] - (image.comps[i][index*image.width+j]+image.comps[i][(index-2)*image.width+j]+2)*0.25)
								)*0.5);
			}
		}
		
		image.comps = temp;
	}
	
	public static void waveletInverseTrans(Image image, int num) {
		Image temp = null;
		
		for (int n = num; n > 0; n--) {
			temp = new Image(image.height/(int)Math.pow(2, n-1), image.width/(int)Math.pow(2, n-1), image.numcomps);
			
			for (int m = 0; m < temp.numcomps; m++) {
				for (int h = 0; h < temp.height; h++) {
					for (int w = 0; w < temp.width; w++) {
						temp.comps[m][h*temp.width + w] = image.comps[m][h*image.width+w];
					}
				}
			}
			
			verticalWavelet(temp);
			herizontalWavelet(temp);
			
			for (int m = 0; m < temp.numcomps; m++) {
				for (int h = 0; h < temp.height; h++) {
					for (int w = 0; w < temp.width; w++) {
						image.comps[m][h*image.width+w] = temp.comps[m][h*temp.width + w];
					}
				}
			}
		}
		
	}
}
