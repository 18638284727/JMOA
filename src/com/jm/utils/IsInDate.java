package com.jm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class IsInDate {

	public static boolean isDate(String date, String startTime, String endTime) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date aTime = sdf.parse(date);
		Date sTime = sdf.parse(startTime);
		Date eTime = sdf.parse(endTime);
		if (aTime.getTime() == sTime.getTime() || aTime.getTime() == eTime.getTime()) {
            return true;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(aTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(sTime);

        Calendar end = Calendar.getInstance();
        end.setTime(eTime);

        if (calendar.after(begin) && calendar.before(end)) {
            return true;
        } else {
            return false;
        }
	}
	
}
