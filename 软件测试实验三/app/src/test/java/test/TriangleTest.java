package test;

import yctest2.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName(value="三角形边界测试")
class TriangleTest {
    @DisplayName(value="三角形一般边界测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/fileTest1.csv",numLinesToSkip =0,encoding = "UTF-8")
    void fileTest1(int a, int b,int c,String expected) throws ParseException {

        Triangle triangle=new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }

    @DisplayName(value="三角形健壮性最坏情况测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/fileTest2.csv",numLinesToSkip =0,encoding = "UTF-8")
    void fileTest2(int a, int b,int c,String expected) throws ParseException {

        Triangle triangle=new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }

    @DisplayName("三角形健壮测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/fileTest3.csv",numLinesToSkip =0,encoding = "UTF-8")
    void fileTest3(int a, int b,int c,String expected) throws ParseException {

        Triangle triangle=new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }

    @DisplayName(value="三角形最坏情况测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/fileTest4.csv",numLinesToSkip =0,encoding = "UTF-8")
    void fileTest4(int a, int b,int c,String expected) throws ParseException {

        Triangle triangle=new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }
}