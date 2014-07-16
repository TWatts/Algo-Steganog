import java.io.IOException;
import java.awt.Color;

public class Steganog {

	
	/**
	* Takes a clean image and changes the prime-indexed pixels to secretly carry the message
	**/
	public Picture embedIntoImage(Picture cleanImage, String message) throws IOException {
		Picture toReturn = cleanImage;
		int messageCount=0;
		char currentChar = message.charAt(messageCount);
		int width = toReturn.width();
		int height = toReturn.height();
		PrimeIterator pI = new PrimeIterator(((message.length()/25)+1)*100);
		int nextPrime = pI.next();
		int pixCount=0;
		Color holdColor;
		for(int w = 0;w<width;w++){
			for(int h = 0;h<height;h++){
				if(pixCount==nextPrime){
					//System.out.println(nextPrime +":"+pixCount);
					holdColor = toReturn.get(w, h);
					int holdInt = holdColor.getRGB()/10000;
					System.out.println(holdColor.getRGB() + " : " + holdInt + " : " + holdInt*10000);
					holdColor = new Color(holdInt*10000+((int)currentChar));
					//System.out.println(currentChar +": "+((int)currentChar));
					toReturn.set(w, h, holdColor);
					nextPrime=pI.next();
					messageCount++;
					currentChar = message.charAt(messageCount);
				}
				pixCount++;
			}
		}
		return toReturn;
	}
	/**
	* Retreives the embedded secret from the secret-carrying image
	*/
	public String retreiveFromImage(Picture imageWithSecretMessage) throws IOException { 
		String toReturn = "";
		int width = imageWithSecretMessage.width();
		int height = imageWithSecretMessage.height();
		PrimeIterator pI = new PrimeIterator(((50/25)+1)*100);
		int nextPrime = pI.next();
		int pixCount=0;
		Color holdColor;
		for(int w = 0;w<width;w++){
			for(int h = 0;h<height;h++){
				if(pixCount==nextPrime){
					//System.out.println(nextPrime +":"+pixCount);
					holdColor = imageWithSecretMessage.get(w, h);
					System.out.println(holdColor.getRGB() + " : " + holdColor.getRGB()%10000);
					char holdChar = (char) ((char)-1*holdColor.getRGB()%10000);
					toReturn += holdChar;
					nextPrime=pI.next();
				}
				pixCount++;
			}
		}
		return toReturn;
	}

	
}
