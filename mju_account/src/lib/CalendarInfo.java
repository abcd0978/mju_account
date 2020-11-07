package lib;

public class CalendarInfo 
{
	public boolean is_yun(int year)//윤년인지계산해줌
	{
		if(year%4==0 && year%100!=0 || year%400==0)
			return true;
		else
			return false;
	}
	public int firstdate(int year,int month)//해당 년도,달의 1일의 요일을 구해줌
	{
		int total_days=0;
		for(int i=1;i<year;i++)
		{
			if(is_yun(i))
				total_days+=366;
			else
				total_days+=365;
		}
		for(int i=1;i<month;i++)
		{
			if(is_yun(year) && i==2)
				total_days +=29;
			else if(i==4 || i==6 || i==9 || i==11)
				total_days +=30;
			else if(i==2)
				total_days+=28;
			else
				total_days+=31;
		}
		return (total_days%7) + 1;
	}
	public int leap_date(int year,int month)//해당 달의 
	{
		int leap_day=0;
		if(is_yun(year) && month == 2)
			leap_day+=29;
		else if(month==2)
			leap_day+=28;
		else if(month==4 || month==6 || month==9 || month==11)
			leap_day+=30;
		else
			leap_day+=31;
		return leap_day;
	}
}
