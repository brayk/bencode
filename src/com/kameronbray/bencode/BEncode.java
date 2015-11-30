package com.kameronbray.bencode;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.kameronbray.exceptions.UnexpectedInputException;

/**
 * Kameron Bray (2015) - Encodes appropriate Java Objects (Integers, Byte
 * Strings, List, and Dictionaries(Java Map)).
 *
 * @author Kameron Bray
 */
public class BEncode {

	/**
	 * @param object
	 *            the java object to encode in bencode (int, string, list, map)
	 * @return the string representing the object in bencode
	 */
	@SuppressWarnings("unchecked")
	public static String encode(final Object object) {
		String encode = "";
		if (object instanceof Integer) {
			encode += encodeInt((Integer) object);
		} else if (object instanceof List) {
			encode += encodeList((List<Object>) object);
		} else if (object instanceof String) {
			encode += encodeByteString((String) object);
		} else if (object instanceof Map) {
			encode += encodeDict((Map<String, Object>) object);
		} else {
			throw new UnexpectedInputException("Invalid object to encode: <" + object + ">.");
		}
		return encode;
	}

	/**
	 * @param intToEncode
	 * @return string representing the java object in bencode
	 */
	private static String encodeInt(final int intToEncode) {
		final String encode = "i" + intToEncode + "e";
		return encode;
	}

	/**
	 * @param dictToEncode
	 * @return string representing the java object in bencode
	 */
	private static String encodeDict(final Map<String, Object> dictToEncode) {
		TreeMap<String, Object> dictToEncodeSorted = new TreeMap<String, Object>();
		dictToEncodeSorted.putAll(dictToEncode);
		String encode = "d";
		final Iterator<Entry<String, Object>> iter = dictToEncodeSorted.entrySet().iterator();
		while (iter.hasNext()) {
			final Entry<String, Object> keyValuePair = iter.next();
			encode += encode(keyValuePair.getKey()) + encode(keyValuePair.getValue());
		}
		encode += "e";
		return encode;
	}

	/**
	 * @param byteStringToEncode
	 * @return string representing the java object in bencode
	 */
	private static String encodeByteString(final String byteStringToEncode) {
		String encode = "" + byteStringToEncode.length();
		encode += ":" + byteStringToEncode;
		return encode;
	}

	/**
	 * @param list
	 * @return string representing the java object in bencode
	 */
	private static String encodeList(final List<Object> list) {
		String encode = "l";
		for (final Object object : list) {
			encode += encode(object);
		}
		encode += "e";
		return encode;

	}

}
