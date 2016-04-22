package br.com.dod.dotnet.types;

import java.io.EOFException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class WORDTest {

	@Test
	public void test() throws EOFException, IOException {
		
		WORD dnWord = new WORD('A');
				
		Assert.assertEquals('A', dnWord.charValue());		
		Assert.assertEquals(65, dnWord.intValue());
		Assert.assertEquals(65, dnWord.byteValue());
		Assert.assertEquals(65, dnWord.longValue());
		Assert.assertEquals(65, dnWord.getUInt32());
		Assert.assertEquals(WORD.SIZE, dnWord.getBytes().length);
		Assert.assertEquals(Short.class, dnWord.nativeType());
		
		byte[] cmpBytes = {65,0};
		byte[] dnCharBytes= dnWord.getBytes();
		
		for (int idx = 0; idx < dnCharBytes.length; idx++) {
			Assert.assertEquals(cmpBytes[idx], dnCharBytes[idx]);
		}		
	}
}
