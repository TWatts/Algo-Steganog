package edu.neumont.security;

import java.math.BigInteger;

/**
 * Use this to cipher a message character by character.
 * 
 * This is different than the private key only in distribution:  Anyone can have this key, whereas only one party has the private key.
 * 
 * @author jcummings
 *
 */
public class SimplePublicKey implements PublicKey {
	private static final long serialVersionUID = 1L;
	
	private BigInteger n, e;
	
	public SimplePublicKey(int n, int e) {
		this.n = BigInteger.valueOf(n);
		this.e = BigInteger.valueOf(e);
	}
	@Override
	public int getN() {
		return n.intValue();
	}

	@Override
	public int getE() {
		return e.intValue();
	}

	@Override
	public int cipher(int b) {
		return new BigInteger(String.valueOf(b)).modPow(e, n).intValue();
	}

}
