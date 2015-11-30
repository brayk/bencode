package com.kameronbray.test;
import org.junit.runners.Suite;
import org.junit.runner.RunWith;

/**
 * Kameron Bray (2015) - JUnit4 starter for all the bencode test cases.
 *
 * @author Kameron Bray
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({BDecodeTest.class, BEncodeTest.class})
public class AllTests {
}