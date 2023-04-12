package qyf3;

import java.util.Date;

public class BillNew {

        public double get_time(String name1,String name2, Date StartTime, Date EndTime) //name用来判断是否为夏令时还是冬令时
        {
            double nm = 1000 * 60;
            long diff;
            double min;
            long e = EndTime.getTime();
            long s = StartTime.getTime();
            double sum = 0;
            if(name1.equals("夏令时"))
            {
                e -= 1000 * 60 * 60;
                if(name2.equals("夏令时"))
                    s -= 1000 * 60 * 60;
                if(e<s)
                    min = -1;
                else {
                    diff = e - s;
                    min = diff / nm;
                }
            }
            else if(name1.equals("冬令时")) {
                e += 1000 * 60 * 60;
                if(name2.equals("冬令时"))
                    s += 1000 * 60 * 60;
                if(e < s)
                    min=-1;
                else {
                    diff = e - s;
                    min = diff / nm;
                }
            }
            else {
                if(e < s)
                    min = -1;
                else {
                    diff = e - s;
                    min = diff / nm;
                }
            }
            min=Math.ceil(min);
            if(min == -1)
                return min;
            else if(min <= 20)
            {
                sum = min * 0.05;
            }
            else {
                sum = 1 + ( min - 20 ) * 0.1;
            }
            String  str = String.format("%.2f",sum);
            sum = Double.parseDouble(str);
            return sum;
        }
    }

