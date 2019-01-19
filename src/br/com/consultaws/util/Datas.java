package br.com.consultaws.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Datas {

	public static final String formatoDataBR = "dd/MM/yyyy", formatoDataEUA = "yyyy-MM-dd";

	// converte de String para data
	public static Date convertStringEmData(String data, String sdf) {
		if (data == null || data.equals(""))
			return null;
		try {
			SimpleDateFormat formato = new SimpleDateFormat(sdf);
			Date date = (java.util.Date) formato.parse(data);
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// converte de String para data
	public static Date convertStringEmData(String data) {
		if (data == null || data.equals(""))
			return null;
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = (java.util.Date) formato.parse(data);
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String convertDataEmString(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatoDataEUA);
		return sdf.format(data);
	}

}