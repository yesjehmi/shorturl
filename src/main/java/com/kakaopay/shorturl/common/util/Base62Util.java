package com.kakaopay.shorturl.common.util;

public class Base62Util {


	public static final String BASE62_CHARACTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static final int BASE_LENGTH = BASE62_CHARACTER.length();

	private Base62Util() {}

	public static String fromDecimal(long i) {
		StringBuilder sb = new StringBuilder("");
		if (i == 0) {
			return "a";
		}
		while (i > 0) {
			i = fromDecimal(i, sb);
		}
		return sb.reverse().toString();
	}

	private static long fromDecimal(long i, final StringBuilder sb) {
		int rem = (int)i % BASE_LENGTH;
		sb.append(BASE62_CHARACTER.charAt(rem));
		return i / BASE_LENGTH;
	}

	public static long toDecimal(String str) {
		return toDecimal(new StringBuilder(str).reverse().toString().toCharArray());
	}

	private static long toDecimal(char[] chars) {
		long n = 0;
		for (int i = chars.length - 1; i >= 0; i--) {
			n += toDecimal(BASE62_CHARACTER.indexOf(chars[i]), i);
		}
		return n;
	}

	private static long toDecimal(int n, int pow) {
		return n * (int) Math.pow(BASE_LENGTH, pow);
	}
}
