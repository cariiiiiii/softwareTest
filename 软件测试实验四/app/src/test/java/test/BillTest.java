package test;

import qyf3.Bill;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName(value="电话账单测试")
public class BillTest {
    @Test
    @DisplayName(value="通话不足1分钟")
    void test() throws ParseException {
        Bill bill = new Bill();
        String type = bill.getBill("20201025013005" ,"20201025115555");
        assertEquals("61.6或67.6" , type);
    }


    @DisplayName(value="电话账单等价类测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/phoneFree.csv",numLinesToSkip =1,encoding = "UTF-8")
    void fileTest4(int index, String time1, String time2, String expected) throws ParseException {

        Bill bill = new Bill();
        String type = bill.getBill(time1, time2);
        assertEquals(expected, type);
    }
}
