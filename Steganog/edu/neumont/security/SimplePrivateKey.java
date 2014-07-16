package edu.neumont.security;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Use this to cipher a message character by character.
 * 
 * @author jcummings
 *
 */
public class SimplePrivateKey implements PrivateKey {
	private static final long serialVersionUID = 1L;
	
	private BigInteger d;
	private BigInteger n;
	private PublicKey pk;
	
	public SimplePrivateKey() {
		this(BigInteger.probablePrime(15, new SecureRandom()).intValue(), BigInteger.probablePrime(15, new SecureRandom()).intValue(), 65537);
	}
	
	public SimplePrivateKey(int p, int q, int e) {
		this.n = BigInteger.valueOf(p).multiply(BigInteger.valueOf(q));
		this.pk = new SimplePublicKey(n.intValue(), e);
		this.d = BigInteger.valueOf(e).modInverse(BigInteger.valueOf((p-1)*(q-1)));
	}

	@Override
	public PublicKey getPublicKey() {
		return pk;
	}

	@Override
	public int cipher(int b) {
		return new BigInteger(String.valueOf(b)).modPow(d, n).intValue();
	}
}
