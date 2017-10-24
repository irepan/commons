/**
 * 
 */
package com.itappservices.commons.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Irepan
 *
 */
public class WebServiceUtils {

    public static List<MapEntry> convertMapToMapEntry(Map<String,Object> conv) {
        List<MapEntry> result = new ArrayList<MapEntry>(0);
        Iterator<Entry<String,Object>> iter = conv.entrySet().iterator();
        while (iter.hasNext()) {
          Entry<String,Object> item = iter.next();
          result.add(new MapEntry(item.getKey(),item.getValue()));
        }
        return result;
      }
    public static Map<String,Object> convertMapEntryToMap(List<MapEntry> list){
    	Map<String, Object> result = new HashMap<String,Object>();
    	for (MapEntry entry:list){
    		result.put(entry.getKey(), entry.getValue());
    	}
    	return result;
    }
/*    public static String getEntryQuery(String key, Object value){
    	String result = "";
    	String keyName = key.replaceAll("\\.", "");
    	if (value instanceof DateRange){
    		result = key + " between :" + keyName + "Start and :" + keyName + "End";
    	} else if (value instanceof String) {
    		result = key + " LIKE :" + keyName;
    	} else if (value instanceof GreaterThanDate) {
    		result = key + " >= " + keyName;
    	} else if (ObjectRange.class.isAssignableFrom(value.getClass())) {
    		result = key + " between :" + keyName + "Start and :" + keyName + "End";    		
    	} else {
    		result = key + " = :" + keyName;
    	}
    	return result;
    }
    public static void addParameter(Query query, Entry<String, Object> entry){
    	String keyName = entry.getKey().replaceAll("\\.", "");
		if (entry.getValue() instanceof DateRange){
			DateRange dr = (DateRange) entry.getValue();
			Calendar st = Calendar.getInstance();
			st.setTime(dr.getStart());
			query.setCalendar(keyName + "Start", st);
			Calendar et = Calendar.getInstance();
			et.setTime(dr.getEnd());
			et.set(Calendar.HOUR_OF_DAY, 23);
			et.set(Calendar.MINUTE, 59);
			et.set(Calendar.SECOND, 59);
			et.set(Calendar.MILLISECOND, 999);
			query.setCalendar(keyName + "End", et);
		} else if (entry.getValue() instanceof String) {
			String auxString = "%" + String.valueOf(entry.getValue()) + "%";
			query.setString(keyName, auxString);
		} else if (entry.getValue() instanceof GreaterThanDate) {
			query.setDate("keyName", ((GreaterThanDate)entry.getValue()).getAsOfDate());
		}else if (ObjectRange.class.isAssignableFrom(entry.getValue().getClass())) {
			ObjectRange<?> or = (ObjectRange<?>) entry.getValue();
			query.setParameter(keyName + "Start", or.getStart());
			query.setParameter(keyName + "End", or.getEnd());
		} else {
			query.setParameter(keyName, entry.getValue());
		}
    }
*/
}
