
package com.kameronbray.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kameronbray.bencode.BEncode;
import com.kameronbray.exceptions.UnexpectedInputException;

/**
 * Kameron Bray (2015) - Tests BEncode.class to encode Dictionaries, Lists, Byte
 * Strings, and Integers into Bencode. Covers basic cases as well as nested
 * structures such as lists inside dictionaries, dictionaries inside of lists,
 * invalid input, and the rules such as lexicographical ordering within
 * dictionaries.
 *
 * @author Kameron Bray
 */

public class BEncodeTest extends AbstractBencodeTest {
	
	@BeforeClass
	public static void setup()
	{
		console_out("-------- Encode Test Start --------");
	}
	
	// ******* TEST METHODS *******
	@Test
	public void listEncode_Tests() {
		final Iterator<Entry<String, Object>> iter = LIST_TEST_SET.entrySet().iterator();
		while (iter.hasNext()) {
			final Entry<String, Object> keyValuePair = iter.next();
			console_out(keyValuePair.getKey());
			console_out(BEncode.encode((ArrayList<Object>) keyValuePair.getValue()));
			Assert.assertEquals(keyValuePair.getKey(), BEncode.encode((ArrayList<Object>) keyValuePair.getValue()));
		}
	}

	@Test
	public void intEncode_Tests() {
		final Iterator<Entry<String, Object>> iter = INT_TEST_SET.entrySet().iterator();
		while (iter.hasNext()) {
			final Entry<String, Object> keyValuePair = (Entry<String, Object>) iter.next();
			console_out(keyValuePair.getKey());
			console_out(BEncode.encode((int) keyValuePair.getValue()));
			Assert.assertEquals(keyValuePair.getKey(), BEncode.encode((int) keyValuePair.getValue()));
		}
	}

	@Test
	public void byteStringEncode_Tests() {
		final Iterator<Entry<String, Object>> iter = STRING_TEST_SET.entrySet().iterator();
		while (iter.hasNext()) {
			final Entry<String, Object> keyValuePair = (Entry<String, Object>) iter.next();
			console_out(keyValuePair.getKey());
			console_out(BEncode.encode((String) keyValuePair.getValue()));
			Assert.assertEquals(keyValuePair.getKey(), BEncode.encode((String) keyValuePair.getValue()));
		}
	}

	@Test
	public void dictEncode_Tests() {
		final Iterator<Entry<String, Object>> iter = DICT_TEST_SET.entrySet().iterator();
		while (iter.hasNext()) {
			final Entry<String, Object> keyValuePair = (Entry<String, Object>) iter.next();
			console_out(keyValuePair.getKey());
			console_out(BEncode.encode((TreeMap<String, Object>) keyValuePair.getValue()));
			Assert.assertEquals(keyValuePair.getKey(),
					BEncode.encode((TreeMap<String, Object>) keyValuePair.getValue()));
		}
	}

	@Test(expected = UnexpectedInputException.class)
	public void invalidObject_Test() {
		final Object invalidObject = false;
		BEncode.encode(invalidObject);
	}

}
