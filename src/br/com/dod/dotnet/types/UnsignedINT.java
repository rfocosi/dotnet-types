package br.com.dod.dotnet.types;

import br.com.dod.types.IntegerType;

/**
 * 16-bit unsigned integer.
 */
public class UnsignedINT extends IntegerType { 
	private static final long serialVersionUID = 5296841893435112173L;

	public static final int SIZE = 4;

	/**
	 * Instantiates a new word.
	 */
	public UnsignedINT() {
		this(0);
	}

	/**
	 * Instantiates a new word.
	 *
	 * @param value
	 *            the value
	 */
	public UnsignedINT(long value) {
		super(SIZE, value, true);
	}
	
}



