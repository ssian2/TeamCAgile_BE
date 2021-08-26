package com.kainos.jobnight;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Util {
	public static String createURLWithPort(String url, int port) {
		return "http://localhost:" + port + url;
	}

	public static String loadResourceAsString(String resource) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(resource);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		return br.lines().collect(Collectors.joining());
	}
}
