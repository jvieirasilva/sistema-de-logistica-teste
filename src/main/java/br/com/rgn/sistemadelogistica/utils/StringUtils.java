package br.com.rgn.sistemadelogistica.utils;

public class StringUtils {
	public static boolean isBlank(String info){
		return !(info != null && info.trim() != "");
	}
}
