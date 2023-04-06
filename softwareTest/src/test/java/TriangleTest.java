import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
/***
 *
 * @author tiger
 * @date 2021年3月4日-下午8:43:13
 * @description 三角形测试用例
 */
class TriangleTest {

    @Test
    @DisplayName(value="不等边三角形")
    void testTriangle() {
        Triangle triangle = new Triangle();

        String type = triangle.classify(3, 4, 6);
        assertEquals("不等边三角形", type);
    }

    @Test
    void equallaterialTriangle() {
        Triangle triangle = new Triangle();

        String type = triangle.classify(3, 3, 3);
        assertEquals("等边三角形", type);
    }

    @Test
    void notTriangle() {
        Triangle triangle = new Triangle();

        String type = triangle.classify(3, 3, 6);
        assertEquals("非三角形", type);
    }

    @ParameterizedTest
    @CsvSource({
            "3,4,6,不等边三角形",
            "3,3,3,等边三角形",
            "3,3,6,非三角形"
    })
    void paramTriangle(int a, int b,int c,String expected) {
        Triangle triangle = new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }
    @DisplayName(value="三角形一般边界测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形一般边界测试用例.csv",numLinesToSkip =1,encoding = "UTF-8")
    void fileTest(int m,int a, int b,int c,String expected) throws ParseException {

        Triangle triangle=new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }
    @DisplayName(value="三角形健壮性最坏情况测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形健壮性最坏情况测试用例.csv",numLinesToSkip =1,encoding = "UTF-8")
    void fileTest1(int m,int a, int b,int c,String expected) throws ParseException {

        Triangle triangle=new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }
    @DisplayName(value="三角形健壮测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形健壮测试用例.csv",numLinesToSkip =1,encoding = "UTF-8")
    void fileTest2(int m,int a, int b,int c,String expected) throws ParseException {

        Triangle triangle=new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }
    @DisplayName(value="三角形弱一般等价类测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形弱一般等价类测试用例.csv",numLinesToSkip =1,encoding = "UTF-8")
    void fileTest3(int m,int a, int b,int c,String expected) throws ParseException {

        Triangle triangle=new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }
    @DisplayName(value="三角形最坏情况测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形最坏情况测试用例.csv",numLinesToSkip =1,encoding = "UTF-8")
    void fileTest4(int m,int a, int b,int c,String expected) throws ParseException {

        Triangle triangle=new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }
}