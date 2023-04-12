package qyf3;

import java.io.*;
import java.text.*;
import java.util.*;

public class Bill {
    private String beginTime;
    private String endTime;

    public String getBill(String time1, String time2) throws ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date1 = format1.parse(time1);
        Date date2 = format1.parse(time2);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        long phonetime = cal2.getTime().getTime() - cal1.getTime().getTime();
        long minute;

        if(phonetime>=0 && phonetime/1000<60 || phonetime%60000>0) //不到一分钟按一分钟计算
            minute = phonetime/1000/60+1;
        else
            minute = phonetime/1000/60;

        if(cal1.get(Calendar.MONTH) == Calendar.OCTOBER && cal1.get(Calendar.DATE) >= 25 && cal1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            //开始时间在十月最后一个周日
            if(cal1.get(Calendar.HOUR_OF_DAY)==2 && cal2.get(Calendar.HOUR_OF_DAY)==2 && cal2.get(Calendar.HOUR_OF_DAY)<3 && cal2.get(Calendar.MINUTE)<=cal1.get(Calendar.MINUTE)) {
                //因为转换而出现通话时间为负的情况
                minute+=60;
                if(minute>1200 || minute <=0){
                    System.out.println("输入错误");
                    return "输入错误";
                }else {
                    System.out.println("1通话时长："+minute);
                    System.out.println("通话账单："+Money(minute));
                    return Money(minute)+"";
                }

            }
            else if(cal1.get(Calendar.HOUR_OF_DAY)<2 && cal2.get(Calendar.HOUR_OF_DAY)>=2) {
                //开始时间是两点前，结束时间是两点后（经历第二次转换
                if(minute>1200 || minute <=0){
                    System.out.println("输入错误");
                    return "输入错误";
                }else {
                    System.out.println("2通话时长：" + minute + "或" + (minute + 60));
                    System.out.println("通话账单：" + Money(minute) + "或" + Money(minute + 60));
                    return Money(minute) + "或" + Money(minute + 60);
                }
            }
            else {
                if(minute>1200 || minute <=0){
                    System.out.println("输入错误");
                    return "输入错误";
                }else {
                    System.out.println("3通话时长："+minute);
                    System.out.println("通话账单："+Money(minute));
                    return Money(minute)+"";
                }

            }

        }
        else{
            if(cal2.get(Calendar.MONTH) == Calendar.OCTOBER && cal2.get(Calendar.DATE) >= 25 && cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                if(cal2.get(Calendar.HOUR_OF_DAY)>=2)
                    minute +=60;
            }
            else {
                if((cal1.get(Calendar.MONTH) == Calendar.MARCH && cal1.get(Calendar.DATE) >= 25 && cal1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)){
                    //开始时间在三月最后一个周日
                    if(cal1.get(Calendar.HOUR_OF_DAY)<2 && cal2.get(Calendar.HOUR_OF_DAY)>=3)
                        //开始时间在两点前，结束时间在三点后（经过了第一次转换
                        minute -= 60;
                }
                else{//开始时间不在三月最后一个周日
                    if((cal2.get(Calendar.MONTH) == Calendar.MARCH && cal2.get(Calendar.DATE) >= 25 && cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)){
                        //但是结束时间在（从前一天打到这一天
                        if(cal2.get(Calendar.HOUR_OF_DAY)>=3 )
                            minute -= 60;
                    }
                }
            }
            if(minute>1200 || minute <=0){
                System.out.println("输入错误");
                return "输入错误";
            }else{
                System.out.println("4通话时长："+minute);
                System.out.println("通话账单："+Money(minute));
                return Money(minute)+"";
            }
        }



    }

    public String Money(long minute) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        if(minute<=20){
            return nf.format(1.0*minute*0.05 );
        }
        else
            return nf.format(1+(minute-20)*0.1) ;
    }


}

