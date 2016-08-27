package br.com.dod.types;

/**
 * This is a modified version of 'java-native-access/jna'
 * 
 * I didn't want to import a whole library only because of this class :P
 * 
 * @author rfocosi@gmail.com
 * 
 * Original comments:
 *
 * Represents a native integer value, which may have a platform-specific size
 * (e.g. <code>long</code> on unix-based platforms).
 *
 * May optionally indicate an unsigned attribute, such that when a value is
 * extracted into a larger-sized container (e.g. <code>int</code> retrieved
 * via {@link Number#longValue}, the value will be unsigned.  Default behavior
 * is signed.
 *
 * @author wmeissner@gmail.com
 * @author twalljava@java.net
 */
public abstract class IntegerType extends Number { 
	private static final long serialVersionUID = 4066273140829707238L;

	private int size;
	private Number number;
	private boolean unsigned;
	// Used by native code
	private long value;

	/** Create a zero-valued signed IntegerType. */
	public IntegerType(int size) {
		this(size, 0, false);
	}

	/** Create a zero-valued optionally unsigned IntegerType. */
	public IntegerType(int size, boolean unsigned) {
		this(size, 0, unsigned);
	}

	/** Create a signed IntegerType with the given value. */
	public IntegerType(int size, long value) {
		this(size, value, false);
	}

	public IntegerType(int size, char[] chars) {
		this.size = size;
		byte[] bytes = new byte[size];
		for (int i=0; i < bytes.length; i++) {
			bytes[i] = (byte) chars[i];
		}

		long bl = 0;
		for (int i = 0; i < bytes.length; i++)
		{
			bl += ((long) bytes[i] & 0xff) << (8 * i);
		}

		setValue(bl);
	}

	/** Create an optionally signed IntegerType with the given value. */
	public IntegerType(int size, long value, boolean unsigned) {
		this.size = size;
		this.unsigned = unsigned;
		setValue(value);
	}

	/** Change the value for this data. */
	public void setValue(long value) {
		long truncated = value;
		this.value = value;
		switch (size) {
		case 1:
			if (unsigned) this.value = value & 0xFFL;
			truncated = (byte) value;
			this.number = new Byte((byte) value);
			break;
		case 2:
			if (unsigned) this.value = value & 0xFFFFL;
			truncated = (short) value;
			this.number = new Short((short) value);
			break;
		case 4:
			if (unsigned) this.value = value & 0xFFFFFFFFL;
			truncated = (int) value;
			this.number = new Integer((int) value);
			break;
		case 8:
			this.number = new Long(value);
			break;
		default:
			throw new IllegalArgumentException("Unsupported size: " + size);
		}
		if (size < 8) {
			long mask = ~((1L << (size*8)) - 1);
			if ((value < 0 && truncated != value)
					|| (value >= 0 && (mask & value) != 0)) {
				throw new IllegalArgumentException("Argument value 0x"
						+ Long.toHexString(value) + " exceeds native capacity ("
						+ size + " bytes) mask=0x" + Long.toHexString(mask));
			}
		}
	}

	public byte[] getBytes() {
		return getBytes(size);
	}
	
	private byte[] getBytes(int size) {			
		long num = longValue();
		int loopSize = size - 1;
		byte[] buf = new byte[size];
		for (int i = loopSize; i >= 0; i--) {
			buf[Math.abs(i-loopSize)] = (byte) (num & 0xff);
			num >>>= 8;
		}	        
		return buf;
	}
	
	public int getSize() {
		return size;
	}

	public long getUInt32() {
		byte[] bytes = getBytes(4);
		long value = bytes[0] & 0xFF;
		value |= (bytes[1] << 8) & 0xFFFF;
		value |= (bytes[2] << 16) & 0xFFFFFF;
		value |= (bytes[3] << 24) & 0xFFFFFFFF;
		return value;
	}

	public char[] getChars() {
		byte[] b = getBytes();
		char[] r = new char[b.length]; 
		for (int i = 0; i < r.length; i++) {
			r[i] = (char) b[i];
		}
		return r;
	}

	public Object toNative() {
		return number;
	}

	public Class<? extends Number> nativeType() {
		return number.getClass();
	}

	public int intValue() {
		return (int)value;
	}

	public long longValue() {
		return value;
	}

	public float floatValue() {
		return number.floatValue();
	}

	public double doubleValue() {
		return number.doubleValue();
	}

	public char charValue() {
		return (char) longValue();
	}

	public boolean equals(Object rhs) {
		return rhs instanceof IntegerType
				&& number.equals(((IntegerType)rhs).number);
	}

	public String toString() {
		return number.toString();
	}

	public int hashCode() {
		return number.hashCode();
	}

}