package edu.neumont.security;

import java.io.Serializable;

public interface PublicKey extends Serializable {
	int getN();
	int getE();
	int cipher(int b);
}
