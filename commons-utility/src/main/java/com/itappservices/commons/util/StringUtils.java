package com.itappservices.commons.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
	public static String appendTabs(String input, int level){
		StringBuilder sb = new StringBuilder();
		String suffix = "";
		for (int i =0 ; i<level; i++){
			suffix += "\t";
		}
		String lines[] = input.split("\\r?\\n");
		for (int i=0;i<lines.length;i++){
			if (i>0){
				sb.append("\r\n");
			}
			sb.append(suffix + lines[i]);
		}
		return sb.toString();
	}
	public static String[] splitCSV(String text, String separator){
		String[] tokens = text.split(separator + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)",-1);
		List<String> result=new ArrayList<String>();
		for (String token:tokens){
			result.add(token.replaceAll("(^\")|(\"$)", "").trim());
		}
		return result.toArray(new String[result.size()]);
		
	}
	public static String[] splitCSV(String text){
		return splitCSV(text,",");
	}
/*		String[] tokens = text.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)",-1);
		List<String> result=new ArrayList<String>();
		for (String token:tokens){
			result.add(token.replaceAll("(^\")|(\"$)", "").trim());
		}
		return result.toArray(new String[result.size()]);
	}*/
}
