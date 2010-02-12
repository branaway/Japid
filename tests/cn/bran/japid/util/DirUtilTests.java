package cn.bran.japid.util;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Set;

import org.junit.Test;

import cn.bran.play.DirUtil;

public class DirUtilTests {
	@Test
	public void testOrphan() {
		File src = new File("tests/testdir");
		Set<File> orphan = DirUtil.findOrphanJava(src, src);
		assertEquals(1, orphan.size());
		File next = orphan.iterator().next();
		System.out.println(next);
		assertTrue(next.getName().endsWith("C.java"));
	}
}
