package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import qyf3.BillNew;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillNewTest {

    public Date StrToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/phoneFee.csv")
    @DisplayName("等价类划分")
    void parameters_error_test(String a,String e,String b,String c,double d) {
        BillNew billNew = new BillNew();
        Date st=StrToDate(b);
        Date et=StrToDate(c);
        double fee=billNew.get_time(e,a,st,et);
        assertEquals(d, fee);
    }

}
