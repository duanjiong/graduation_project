package test;

import lib.Help;

public class test2 {
	public static void main(String[] args) {
		float a = (float)31.440353;
		byte[] bytes = new byte[4];
		
		Help.putFloat(bytes, a, 0);
		System.out.println(Help.getFloat(bytes, 0));
		
	}
}
