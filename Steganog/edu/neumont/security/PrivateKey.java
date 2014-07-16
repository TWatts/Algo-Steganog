package edu.neumont.security;

import java.io.Serializable;

public interface PrivateKey extends Serializable {
	PublicKey getPublicKey();
	int cipher(int b);
}
