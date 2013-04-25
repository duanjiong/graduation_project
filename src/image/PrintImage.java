package image;

public class PrintImage {

	public static void print(Image image, int num) {
		
		for (int m = 0; m < image.numcomps; m++){
			for (int i = 0; i < image.height/Math.pow(2, num); i++) {
				for (int j = 0; j < image.width/Math.pow(2, num); j++) {
					System.out.print(image.comps[m][i*image.width+j]+" ");
				}
				System.out.print('\n');
		}
		}
	}
}
