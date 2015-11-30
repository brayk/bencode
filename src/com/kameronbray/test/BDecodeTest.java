
package com.kameronbray.test;

import java.util.Iterator;
import java.util.Map.Entry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kameronbray.bencode.BDecode;
import com.kameronbray.exceptions.UnexpectedInputException;

/**
 * Kameron Bray (2015) - Tests BDecode.class to decode Dictionaries, Lists, Byte
 * Strings, and Integers into Bencode. Covers basic cases as well as nested
 * structures such as lists inside dictionaries, dictionaries inside of lists,
 * invalid input, and the rules such as non-negative zero and leading zero
 * integers ordering within dictionaries.
 *
 * @author Kameron Bray
 */

public class BDecodeTest extends AbstractBencodeTest {

	@BeforeClass
	public static void setup()
	{
		console_out("-------- Decode Test Start --------");
	}
	
	// ******* TEST METHODS ******* \\
	@Test
	public void intDecode_Tests() {
		final Iterator<Entry<String, Object>> iter = INT_TEST_SET.entrySet().iterator();
		while (iter.hasNext()) {
			final Entry<String, Object> keyValuePair = (Entry<String, Object>) iter.next();
			console_out(keyValuePair.getValue());
			console_out(BDecode.decode((String) keyValuePair.getKey()));
			Assert.assertEquals(keyValuePair.getValue(), BDecode.decode((String) keyValuePair.getKey()));
		}
	}

	@Test
	public void byteStringDecode_Tests() {
		final Iterator<Entry<String, Object>> iter = STRING_TEST_SET.entrySet().iterator();
		while (iter.hasNext()) {
			final Entry<String, Object> keyValuePair = (Entry<String, Object>) iter.next();
			console_out(keyValuePair.getValue());
			console_out(BDecode.decode((String) keyValuePair.getKey()));
			Assert.assertEquals(keyValuePair.getValue(), BDecode.decode((String) keyValuePair.getKey()));
		}
	}

	@Test
	public void dictEncode_Tests() {
		final Iterator<Entry<String, Object>> iter = DICT_TEST_SET.entrySet().iterator();
		while (iter.hasNext()) {
			final Entry<String, Object> keyValuePair = (Entry<String, Object>) iter.next();
			console_out(keyValuePair.getValue());
			console_out(BDecode.decode(keyValuePair.getKey()));
			Assert.assertEquals(keyValuePair.getValue(), BDecode.decode(keyValuePair.getKey()));
		}
	}

	@Test
	public void listEncode_Tests() {
		final Iterator<Entry<String, Object>> iter = LIST_TEST_SET.entrySet().iterator();
		while (iter.hasNext()) {
			final Entry<String, Object> keyValuePair = iter.next();
			console_out(keyValuePair.getValue());
			console_out(BDecode.decode(keyValuePair.getKey()));
			Assert.assertEquals(keyValuePair.getValue(), BDecode.decode(keyValuePair.getKey()));
		}
	}

	// ******** Invalid Check Tests ******* \\
	@Test(expected = UnexpectedInputException.class)
	public void int_LeadingZero_Test() {
		BDecode.decode("i05e");
	}

	@Test(expected = UnexpectedInputException.class)
	public void int_NegativeZero_Test() {

		BDecode.decode("i-0e");
	}
	
	@Test(expected = UnexpectedInputException.class)
	public void int_NegativeString_Test() {
		BDecode.decode("-5:spam4");
	}

}
