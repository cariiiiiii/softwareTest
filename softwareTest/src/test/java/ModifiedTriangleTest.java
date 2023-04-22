import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/***
 * 
 * @author tiger
 * @date 2021年3月4日-下午8:43:13
 * @description 三角形测试用例
 */
class ModifiedTriangleTest {
    private final Triangle triangle = new Triangle();
    
    @Test
    @DisplayName("输入错误")
    void parameters_error_test() {
        assertEquals("输入错误", triangle.classify(0, 4, 5));
    }
    
    @Test
    @DisplayName("不等边三角形")
    void scalene_test() {
        assertEquals("不等边三角形", triangle.classify(3, 4, 6));
    }

    @Test
    @DisplayName("等腰三角形")
    void isosceles_test() {
        assertEquals("等腰三角形", triangle.classify(3, 3, 5));
    }
    
    @Test
    @DisplayName("等边三角形")
    void equilateral_test() {
        assertEquals("等边三角形", triangle.classify(3, 3, 3));
    }

    @Test
    @DisplayName("非三角形")
    void notTriangle() {
        assertEquals("非三角形", triangle.classify(3, 3, 6));
    }
}
