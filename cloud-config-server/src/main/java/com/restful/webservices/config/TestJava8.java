package com.restful.webservices.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestJava8 {

	public static void main(String[] args) {

		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		System.out.println(strings.parallelStream().filter(string -> !string.isEmpty()).collect(Collectors.toList()).size());

	}

}
