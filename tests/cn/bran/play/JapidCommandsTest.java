package cn.bran.play;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class JapidCommandsTest {

	@Test
	public void testMkdir() {
		File[] mkdir = JapidCommands.mkdir("tests/testmkdir");
		for (File f : mkdir) {
			assertTrue(f.exists());
		}
	}
}
