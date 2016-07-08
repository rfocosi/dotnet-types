package br.com.dod.dotnet.types;

import br.com.dod.types.IntegerType;

/**
 * 32-bit unsigned integer.
 */
public class DWORD extends IntegerType { 
	private static final long serialVersionUID = 6986285767425387076L;
	
	public static final int SIZE = 4;

	public DWORD() {
		this(0);
	}

	public DWORD(long value) {
		super(SIZE, value, true);
	}

	public DWORD(char[] chars) {
		super(SIZE, chars);
	}
	
	/**
	 * Low WORD.
	 *
	 * @return Low WORD.
	 */
	public WORD getLow() {
		return new WORD(longValue() & 0xFF);
	}

	/**
	 * High WORD.
	 *
	 * @return High WORD.
	 */
	public WORD getHigh() {
		return new WORD((longValue() >> 16) & 0xFF);
	}

	@Override
	public String toString() {
		return new String(this.getBytes());
	}
}
