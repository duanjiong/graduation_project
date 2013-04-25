/**
 * 
 */
package lib;

/**
 * @author Duan Jiong <djduanjiong@gmail.com>
 *
 */
public class Help {

	 public static void putFloat(byte[] bb, float x, int index) {  
	        // byte[] b = new byte[4];  
	        int l = Float.floatToIntBits(x);  
	        for (int i = 0; i < 4; i++) {  
	            bb[index + i] = new Integer(l).byteValue();  
	            l = l >> 8;  
	        }  
	 } 
	 
	 public static float getFloat(byte[] b, int index) {  
	        int l;  
	        l = b[index + 0];  
	        l &= 0xff;  
	        l |= ((long) b[index + 1] << 8);  
	        l &= 0xffff;  
	        l |= ((long) b[index + 2] << 16);  
	        l &= 0xffffff;  
	        l |= ((long) b[index + 3] << 24);  
	        return Float.intBitsToFloat(l);  
	 } 
	
}
