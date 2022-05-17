package com.amazonaws.samples;

import java.util.List;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.S3Object;

public class ImageThread extends Thread{
	
	private int i;

	public ImageThread(int i) {
		this.i = i;
	}
	
	@Override
	public void run() {
		String photo = "ss";
	    String bucket = "arepbucket2";


	    AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion("us-east-1").build();



    	DetectLabelsRequest request = new DetectLabelsRequest()
    	         .withImage(new Image()
    	         .withS3Object(new S3Object()
    	         .withName(photo+i+".png").withBucket(bucket)))
    	         .withMaxLabels(10)
    	         .withMinConfidence(75F);
    	try {
    	       DetectLabelsResult result = rekognitionClient.detectLabels(request);
    	       List <Label> labels = result.getLabels();
    	       System.out.println("Detected labels for " + photo+i+".png");
    	       for (Label label: labels) {
    	          System.out.println(label.getName() + ", " + label.getInstances().size());
    	       }
    	       
    	    } catch(AmazonRekognitionException e) {
    	       e.printStackTrace();
    	    }
    	
    	System.out.println("-------------------------------");
	}
}
