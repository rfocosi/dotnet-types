package br.com.dod.dotnet.types;

import java.io.EOFException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class CHARTest {

	@Test
	public void test() throws EOFException, IOException {

		CHAR dnChar = new CHAR('A');

		Assert.assertEquals('A', dnChar.charValue());
		Assert.assertEquals("A", dnChar.toString());
		Assert.assertEquals(65, dnChar.intValue());
		Assert.assertEquals(65, dnChar.longValue());
		Assert.assertEquals(65, dnChar.getUInt32());
		Assert.assertEquals(Byte.class, dnChar.nativeType());
		Assert.assertEquals(CHAR.SIZE, dnChar.getBytes().length);
	}
}
