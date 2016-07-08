package br.com.dod.dotnet.types;

import br.com.dod.types.IntegerType;

/**
 * 16-bit unsigned integer.
 */
public class WORD extends IntegerType { 
	private static final long serialVersionUID = 5296841893435112173L;

	public static final int SIZE = 2;

	public WORD() {
		this(0);
	}

	public WORD(long value) {
		super(SIZE, value, true);
	}
	
	public WORD(char[] chars) {
		super(SIZE, chars);
	}
}
