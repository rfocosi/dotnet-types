package br.com.dod.dotnet.types;

import java.io.EOFException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class DWORDTest {

	@Test
	public void test() throws EOFException, IOException {
		
		DWORD dnWord = new DWORD('A');
				
		Assert.assertEquals('A', dnWord.charValue());		
		Assert.assertEquals(65, dnWord.intValue());
		Assert.assertEquals(65, dnWord.byteValue());
		Assert.assertEquals(65, dnWord.longValue());
		Assert.assertEquals(65, dnWord.getUInt32());
		Assert.assertEquals(DWORD.SIZE, dnWord.getBytes().length);
		Assert.assertEquals(Integer.class, dnWord.nativeType());
		
		byte[] cmpBytes = {65,0,0,0};		
		byte[] dnCharBytes= dnWord.getBytes();
		
		for (int idx = 0; idx < dnCharBytes.length; idx++) {
			Assert.assertEquals(cmpBytes[idx], dnCharBytes[idx]);
		}
		
	}
}
