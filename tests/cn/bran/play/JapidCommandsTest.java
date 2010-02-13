package cn.bran.play;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class JapidCommandsTest {

	@Test
	public void testMkdir() {
		List<File> mkdir = JapidCommands.mkdir("tests/testmkdir");
		for (File f : mkdir) {
			System.out.println("verify existence: " + f.getPath());
			assertTrue(f.exists());
		}
	}

	/**
	 * mk the dirs for sample app
	 */
	@Test
	public void makeDampleDir() {
		List<File> mkdir = JapidCommands.mkdir(".");
		for (File f : mkdir) {
			System.out.println("verify existence: " + f.getPath());
			assertTrue(f.exists());
		}
	}
}
