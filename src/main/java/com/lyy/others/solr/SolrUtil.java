package com.lyy.others.solr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SolrUtil {

	/**
	 * 转换为solr格式日期
	 * @param date yyyy-MM-dd HH:mm:ss
	 * @return yyyy-MM-ddTHH:mm:ssZ
	 */
	public static String toSolrDate(String date) {
		if(date.equals("") || date == "") {
			return null;
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String solrDate = "";
		try {
			Date dateObj = sdf1.parse(date);
			Long jsgjMilliseconds= dateObj.getTime() - 8*60*60*1000;
			dateObj = new Date(jsgjMilliseconds);
			solrDate = sdf2.format(dateObj);
		} catch (ParseException e) {
			solrDate = date;
			e.printStackTrace();
		}
		return solrDate;
	}

}

