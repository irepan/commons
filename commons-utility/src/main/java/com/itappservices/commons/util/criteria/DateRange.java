/**
 * 
 */
package com.itappservices.commons.util.criteria;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Irepan
 *
 */
public class DateRange implements ObjectRange<Date> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3400257353364767312L;
	private Date start = null;
	private Date end = null;
	private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

	/**
	 * 
	 */
	public DateRange() {
		super();
	}
	public DateRange(Date start, Date end) {
		this();
		this.setStart(start);
		this.setEnd(end);
	}
	@Override
	public void setStart(Date start) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
/*		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);*/
		this.start = cal.getTime();
		if (null != this.end && this.end.before(this.start)){
			Date auxDate = this.end; 
			this.end = this.start;
			this.start = auxDate;
		}
	}
	@Override
	public void setEnd(Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
/*		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);*/
		this.end = cal.getTime();
		if (null != this.start && this.start.after(this.end)){
			Date auxDate = this.start;
			this.start = this.end;
			this.end = auxDate;
		}
	}
	@Override
	public Date getStart() {
		return start;
	}
	@Override
	public Date getEnd() {
		return end;
	}
	@Override
	public String toString(){
		return "DateRange Start :" + DATE_FORMAT.format(start) + " End:" + DATE_FORMAT.format(end);
	}
}
