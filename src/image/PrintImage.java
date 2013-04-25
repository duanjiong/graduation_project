package image;

public class PrintImage {

	public static void print(Image image) {
		System.out.println("hei="+image.height+";width="+image.width);
		for (int m = 0; m < image.numcomps; m++){
			for (int i = 0; i < image.height; i++) {
				for (int j = 0; j < image.width; j++) {
					System.out.print(image.comps[m][i*image.width+j]+" ");
				}
				System.out.print('\n');
		}
		}
	}
}
