package br.com.dod.dotnet.types;

import br.com.dod.types.IntegerType;

/**
 * 8-bit unsigned integer.
 */
public class CHAR extends IntegerType { 
	private static final long serialVersionUID = -7291480139912740232L;

	public static final int SIZE = 1;

	public CHAR() {
		this(0);
	}

	public CHAR(long value) {
		super(SIZE, value, true);
	}
	
	@Override
	public String toString() {
		return new String(this.getBytes());
	}
}
