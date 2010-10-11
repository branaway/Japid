package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import play.Logger;
import play.Play;
import play.exceptions.ConfigurationException;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.SerializingTranscoder;

public class MyMemClient {
	static MemcachedClient client;
	private static SerializingTranscoder tc;

	private MyMemClient() {

	}

	public static void init() throws IOException {
        tc = new SerializingTranscoder() {

            @Override
            protected Object deserialize(byte[] data) {
            	if(data == null) 
            		return null;
            	
                try {
                    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data)) {
                        @Override
                        protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
                            String name = desc.getName();
                            System.out.println("class to resolve: " + name);
							try {
								Class<?> loadClass = Play.classloader.loadClass(name);
								System.out.println("class resolved to: " + loadClass.getName());
								return loadClass;
							} catch (Exception e) {
								// must do this, or some class such as [C won't get resolved. Might be an inadequate impl in the Play.classloader
//								System.out.println(e);
//								e.printStackTrace();
								return super.resolveClass(desc);
							}
                        }
                    };
                    
					return ois.readObject();
                } catch (Exception e) {
                    Logger.error(e, "Could not deserialize");
                }
                return null;
            }
        };
		
		System.setProperty("net.spy.log.LoggerImpl", "net.spy.memcached.compat.log.Log4JLogger");
		if (Play.configuration.containsKey("memcached.host")) {
			// bran changed to use the binary protocol
			// client = new MemcachedClient(new BinaryConnectionFactory(),
			// AddrUtil.getAddresses(Play.configuration.getProperty("memcached.host")));
			client = new MemcachedClient(AddrUtil.getAddresses(Play.configuration.getProperty("memcached.host")));
		} else if (Play.configuration.containsKey("memcached.1.host")) {
			int nb = 1;
			String addresses = "";
			while (Play.configuration.containsKey("memcached." + nb + ".host")) {
				addresses += Play.configuration.get("memcached." + nb + ".host") + " ";
				nb++;
			}
			// client = new MemcachedClient(new BinaryConnectionFactory(),
			// AddrUtil.getAddresses(addresses));
			client = new MemcachedClient(AddrUtil.getAddresses(addresses));
		} else {
			throw new ConfigurationException(("Bad configuration for memcached"));
		}
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param expiration
	 *            ttl in seconds
	 * @return
	 */
	public static boolean safeAdd(String key, Object value, int expiration) {
		Future<Boolean> future = client.add(clean(key), expiration, value, tc);
		try {
			return future.get(1, TimeUnit.SECONDS);
		} catch (Exception e) {
			future.cancel(false);
		}
		return false;
	}

	public static Object get(String key) {
		Future<Object> future = client.asyncGet(clean(key), tc);
		try {
			return future.get(1, TimeUnit.SECONDS);
		} catch (Exception e) {
			future.cancel(false);
		}
		return null;
	}

	/**
	 * @param key
	 * @return
	 * @author bran
	 */
	private static String clean(String key) {
		key = key.replace(' ', '_');
		return key;
	}

	public static void flush() {
		Future<Boolean> flush = client.flush();
		try {
			if (flush.get(5, TimeUnit.SECONDS)) {
				System.out.println("memcache flushed");
			}
		} catch (Exception e) {
			flush.cancel(false);
		}
	}
}
