package edu.neumont.io;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import edu.neumont.security.PublicKey;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private byte[] body;
	private Map<String, Object> headers = new HashMap<String, Object>();
	
	public byte[] getBody() {
		return body;
	}
	public void setBody(byte[] body) {
		this.body = body;
	}
	
	public void addSignature(PublicKey pk, byte[] signature) {
		headers.put("signedWith", pk);
		headers.put("signature", signature);
	}
	
	public PublicKey getSignedWith() {
		return (PublicKey)headers.get("signedWith");
	}
	
	public byte[] getSignature() {
		return (byte[])headers.get("signature");
	}
	
	public Object getHeader(String string) {
		return headers.get(string);
	}
}
