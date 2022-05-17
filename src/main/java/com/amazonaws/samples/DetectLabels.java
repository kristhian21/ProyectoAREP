package com.amazonaws.samples;


public class DetectLabels {

 public static void main(String[] args) throws Exception {
	 for(int j = 0; j<5; j++) {
	    for(int i = 1; i < 10; i++) {
	    	ImageThread it = new ImageThread(i);
	    	it.start();
	    }
	 }
 }
}