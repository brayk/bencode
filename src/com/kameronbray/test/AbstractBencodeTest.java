package com.kameronbray.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * Kameron Bray (2015) - Defines the expected bidirectional conversion behavior
 * for bencoded strings/objects;
 *
 * @author Kameron Bray
 */

abstract class AbstractBencodeTest {
	// DEBUG SETTINGS
	protected static boolean CONSOLE_OUT_ENABLED = true;

	// **********************************************
	// ***** INTEGER TEST SETS *****
	// >> OBJECTS
	protected static int NEGATIVE_FOURTEE_TWO = -42;
	protected static int FOURTEE_TWO = 42;
	protected static int ZERO = 0;
	// << ENCODED
	protected static String NEGATIVE_FOURTEE_TWO_ENCODED = "i-42e";
	protected static String FOURTEE_TWO_ENCODED = "i42e";
	protected static String ZERO_ENCODED = "i0e";
	// MAP OF ENCODED VALUES TO THEIR OBEJECTS
	protected static TreeMap<String, Object> INT_TEST_SET = new TreeMap<String, Object>() {
		{
			put(NEGATIVE_FOURTEE_TWO_ENCODED, NEGATIVE_FOURTEE_TWO);
			put(FOURTEE_TWO_ENCODED, FOURTEE_TWO);
			put(ZERO_ENCODED, ZERO);
		}
	};

	// *********************************************
	// ***** STRING TEST SETS AND EXPECTATIONS *****
	// >> OBJECTS
	protected static String EMPTY_STRING = "";
	protected static String SPAM = "spam";
	protected static String SPAM_FOUR = "spam4";
	protected static String FOUR_SPAM = "4spam";
	protected static String QUESTION_FOUR_SPAM = "?4spam";
	// << ENCODED
	protected static String EMPTY_STRING_ENCODED = "0:";
	protected static String SPAM_ENCODED = "4:spam";
	protected static String SPAM_FOUR_ENCODED = "5:spam4";
	protected static String FOUR_SPAM_ENCODED = "5:4spam";
	protected static String QUESTION_FOUR_SPAM_ENCODED = "6:?4spam";
	// MAP OF ENCODED VALUES TO THEIR OBEJECTS
	protected static TreeMap<String, Object> STRING_TEST_SET = new TreeMap<String, Object>() {
		{
			put(EMPTY_STRING_ENCODED, EMPTY_STRING);
			put(SPAM_ENCODED, SPAM);
			put(SPAM_FOUR_ENCODED, SPAM_FOUR);
			put(FOUR_SPAM_ENCODED, FOUR_SPAM);
			put(QUESTION_FOUR_SPAM_ENCODED, QUESTION_FOUR_SPAM);
		}
	};

	// *** ALl FURTHER CONSTANTS ARE CONSTRUCTED THROUGH COMBINATION OF
	// *** PREVIOUSLY DEFINED CONSTANTS

	// *********************************************
	// ***** LIST TEST SETS AND EXPECTATIONS *****
	// >> OBJECTS
	protected static ArrayList<Object> INT_STRING_LIST = new ArrayList<Object>(
			Arrays.asList(NEGATIVE_FOURTEE_TWO, FOURTEE_TWO, QUESTION_FOUR_SPAM));
	protected static ArrayList<Object> LIST_INT_STRING_LIST = new ArrayList<Object>(
			Arrays.asList(INT_STRING_LIST, NEGATIVE_FOURTEE_TWO, FOURTEE_TWO, QUESTION_FOUR_SPAM));
	// << ENCODED
	protected static String INT_STRING_LIST_ENCODED = "l" + NEGATIVE_FOURTEE_TWO_ENCODED + FOURTEE_TWO_ENCODED
			+ QUESTION_FOUR_SPAM_ENCODED + "e";
	protected static String LIST_INT_STRING_LIST_ENCODED = "l" + INT_STRING_LIST_ENCODED + NEGATIVE_FOURTEE_TWO_ENCODED
			+ FOURTEE_TWO_ENCODED + QUESTION_FOUR_SPAM_ENCODED + "e";
			// MAP OF ENCODED VALUES TO THEIR OBEJECTS
			// @ EXTRA LIST SETUP

	// *********************************************
	// ***** DICT TEST SETS AND EXPECTATIONS *****
	// >> OBJECTS
	protected static TreeMap<String, Object> INT_STRING_LIST_DICT = new TreeMap<String, Object>() {
		{
			put(SPAM, FOURTEE_TWO);
			put(SPAM_FOUR, FOUR_SPAM);
			put(QUESTION_FOUR_SPAM, INT_STRING_LIST); // tests a nested list
														// inside a dict
		}
	};
	protected static TreeMap<String, Object> DICT_INT_STRING_LIST_DICT = new TreeMap<String, Object>() {
		{
			put(SPAM, FOURTEE_TWO);
			put(SPAM_FOUR, INT_STRING_LIST_DICT); // tests a nested dict inside
													// a dict

			put(QUESTION_FOUR_SPAM, INT_STRING_LIST); // tests a nested list
														// inside a dict
		}
	};
	// << ENCODED
	protected static String INT_STRING_LIST_DICT_ENCODED = "d" + QUESTION_FOUR_SPAM_ENCODED + INT_STRING_LIST_ENCODED
			+ SPAM_ENCODED + FOURTEE_TWO_ENCODED + SPAM_FOUR_ENCODED + FOUR_SPAM_ENCODED + "e";
	protected static String DICT_INT_STRING_LIST_DICT_ENCODED = "d" + QUESTION_FOUR_SPAM_ENCODED
			+ INT_STRING_LIST_ENCODED + SPAM_ENCODED + FOURTEE_TWO_ENCODED + SPAM_FOUR_ENCODED
			+ INT_STRING_LIST_DICT_ENCODED + "e";

	// MAP OF ENCODED VALUES TO THEIR OBEJECTS
	protected static TreeMap<String, Object> DICT_TEST_SET = new TreeMap<String, Object>() {
		{
			put(INT_STRING_LIST_DICT_ENCODED, INT_STRING_LIST_DICT);
			put(DICT_INT_STRING_LIST_DICT_ENCODED, DICT_INT_STRING_LIST_DICT);
		}
	};

	// *********************************************
	// ***** EXTRA LIST/DICT TEST AND EXPECTATIONS *****
	// >> OBJECTS
	protected static ArrayList<Object> DICT_LIST_INT_STRING_LIST = new ArrayList<Object>(
			Arrays.asList(DICT_INT_STRING_LIST_DICT, INT_STRING_LIST, FOURTEE_TWO, SPAM));
	// << ENCODED
	protected static String DICT_LIST_INT_STRING_LIST_EXPECTATION = "l" + DICT_INT_STRING_LIST_DICT_ENCODED
			+ INT_STRING_LIST_ENCODED + FOURTEE_TWO_ENCODED + SPAM_ENCODED + "e";
	// MAP OF ENCODED VALUES TO THEIR OBEJECTS
	protected static TreeMap<String, Object> LIST_TEST_SET = new TreeMap<String, Object>() {
		{
			put(INT_STRING_LIST_ENCODED, INT_STRING_LIST);
			put(LIST_INT_STRING_LIST_ENCODED, LIST_INT_STRING_LIST);
			put(DICT_LIST_INT_STRING_LIST_EXPECTATION, DICT_LIST_INT_STRING_LIST); // nested
																					// dict
																					// and
																					// list
																					// inside
																					// a
																					// list
		}
	};

	// ******* LOGGING HELPER *********
	protected static void console_out(Object out) {
		if (CONSOLE_OUT_ENABLED) {
			System.out.println(out);
		}
	}
}
