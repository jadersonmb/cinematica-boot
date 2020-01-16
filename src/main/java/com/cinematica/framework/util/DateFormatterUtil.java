package com.cinematica.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatterUtil {

	public static Date formatarStringParaData(String stringToDate, String mascara) {
		DateFormat dateFormat = new SimpleDateFormat(mascara);
		try {
			return dateFormat.parse(stringToDate);
		} catch (ParseException e) {
			throw new IllegalArgumentException("A data enviada para o método de formatação de data está em um formato "
					+ "não esperado. O formato deve ser: yyyy-MM-dd HH:mm:ss");
		}
	}
	
	public static Date formatarStringParaDataConfiguracao(String stringToDate, String mascara) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(mascara);
			return dateFormat.parse(stringToDate);
	}
	
	public static String formatarDataParaString(Date dateToString, String mascara) {
		DateFormat dateFormat = new SimpleDateFormat(mascara);
		return dateFormat.format(dateToString);
	}
}
