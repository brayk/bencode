package com.kameronbray.bencode;

import java.util.ArrayList;
import java.util.TreeMap;

import com.kameronbray.exceptions.UnexpectedInputException;

/**
 * Kameron Bray (2015) - Decodes a bencode byte string into appropriate Java
 * Objects (Integers, Byte Strings, List, and Dictionaries(Java Map)).
 *
 * @author Kameron Bray
 */

public class BDecode {

	private static String decodeString;

	/**
	 * Uses the first character in decodeInput string to determine/parse what
	 * object is to be constructed.
	 * 
	 * @param decodeInput
	 *            the bencode string you wish to decode into a java object.
	 * @return the decoded bencode value, either int, string, list,
	 *         dictionary(map)
	 */
	public static Object decode(final String decodeInput) {
		decodeString = decodeInput;
		Character identifier;
		while (decodeInput != null) {
			identifier = decodeString.charAt(0);
			switch (identifier.charValue()) {
			case 'i':
				return decodeInteger();
			case 'l':
				return decodeList();
			case 'd':
				return decodeDict();
			default:
				return decodeString();
			}
		}
		return null;
	}

	/**
	 * @return the list object containing the nested decoded objects
	 */
	private static Object decodeList() {
		ArrayList<Object> listVal = new ArrayList<Object>();

		Character identifier = decodeString.charAt(0);
		decodeString = decodeString.substring(1);
		while (identifier != 'e') {
			listVal.add(decode(decodeString));
			identifier = decodeString.charAt(0);
		}
		decodeString = decodeString.substring(1);
		return listVal;
	}

	/**
	 * @return the map object containing the nested decoded objects
	 */
	private static Object decodeDict() {
		TreeMap<String, Object> dictVal = new TreeMap<String, Object>();

		Character myChar = decodeString.charAt(0);
		decodeString = decodeString.substring(1);
		while (myChar != 'e') {
			String key = (String) decode(decodeString);
			Object value = decode(decodeString);
			dictVal.put(key, value);
			myChar = decodeString.charAt(0);
		}
		decodeString = decodeString.substring(1);
		return dictVal;
	}

	/**
	 * @return the integer object representation of the bencode value
	 */
	private static Object decodeInteger() {
		int i = 0;
		decodeString = decodeString.substring(1);
		if (decodeString.charAt(0) == '0' && Character.isDigit(decodeString.charAt(1))) {
			throw new UnexpectedInputException("Invalid leading Zero");
		}
		if (decodeString.charAt(0) == '-') {
			i++;
			if (decodeString.charAt(1) == '0') {
				throw new UnexpectedInputException("Invalid leading Zero");
			}
		}
		while (Character.isDigit(decodeString.charAt(i))) {
			i++;
		}
		int intVal;
		intVal = Integer.parseInt(decodeString.subSequence(0, i).toString());
		decodeString = decodeString.substring(i + 1);
		return intVal;
	}

	/**
	 * @return the string object representation of the bencode value
	 */
	private static Object decodeString() {
		if (Character.isDigit(decodeString.charAt(0))) {
			int i = 0;
			while (Character.isDigit(decodeString.charAt(i))) {
				i++;
			}
			if (decodeString.charAt(i) == (':')) {
				int strLen = Integer.parseInt(decodeString.substring(0, i));
				i++;
				String byteStringVal = decodeString.substring(i, i + strLen);
				decodeString = decodeString.substring(i + strLen);
				return byteStringVal;
			} else {
				throw new UnexpectedInputException("Invalid character: " + decodeString.charAt(0));
			}
		} else {
			throw new UnexpectedInputException("Invalid character: " + decodeString.charAt(0));
		}
	}
}
