import java.io.IOException;


public class SteganogMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Steganog steg = new Steganog();
		Picture pic = new Picture("C:\\Users\\twatts\\Pictures\\pissy.jpg");
		//pic.show();
		
		String message = "I don’t have a favorite doctor. I have not followed any Doctor Who at all.";
		Picture dirtyPic = steg.embedIntoImage(pic, message);
		dirtyPic.save("C:\\Users\\twatts\\Pictures\\dirtyPissy.jpg");
		
		pic = new Picture("C:\\Users\\twatts\\Pictures\\dirtyPissy.jpg");
		String dirtyMessage = steg.retreiveFromImage(pic);
		System.out.println(dirtyMessage);
		
		
	}

}
