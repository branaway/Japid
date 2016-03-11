/**
 * 
 */
package cn.bran.play.routing;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import play.mvc.Router.Route;

/**
 * @author bran
 *
 */
public class RouterClassTest {

	@Test
	public void test() {
		RouterClass rc = new RouterClass(AutoPathSample.class, "/myapp", 0);
		List<Route> routes = rc.buildRoutes();
		assertEquals(6, routes.size());
		routes.stream().forEach(r -> System.out.println(r));
		String sum = routes.stream().map(r ->r.toString()).collect(Collectors.joining());
		assertTrue(sum.contains("GET /myapp/cn.bran.play.routing.AutoPathSample.bb/{aa}/{bb}.html"));
		assertTrue(sum.contains("POST /myapp/cn.bran.play.routing.AutoPathSample.bb ->"));
		assertTrue(sum.contains("GET /myapp/cn.bran.play.routing.AutoPathSample.bar/{hi}/{there}"));
		assertTrue(sum.contains("POST /myapp/cn.bran.play.routing.AutoPathSample.bar"));
	}

	@Test
	public void testGetParamNames() {
		List<String> names = Arrays.stream(AutoPathSample.class.getDeclaredMethods())
				.map(m -> RouterMethod.getMethodParamNames(m)).collect(Collectors.toList());
		names.forEach(s -> System.out.println(s));
		assertEquals(3, names.size());
	}
}
