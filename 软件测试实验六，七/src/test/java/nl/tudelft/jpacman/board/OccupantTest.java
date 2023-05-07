package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import nl.tudelft.jpacman.sprite.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test suite to confirm that {@link Unit}s correctly (de)occupy squares.
 *
 * @author Jeroen Roosen 
 *
 */
class OccupantTest {

    /**
     * The unit under test.
     */
    private Unit unit;

    /**
     * Resets the unit under test.
     */
    @BeforeEach
    void setUp() {
        unit = new BasicUnit();
    }

    /**
     * Asserts that a unit has no square to start with.
     */
    @Test
    void noStartSquare() {

        //创建一个Unit对象
        //检查他的square属性是否为Null
        //assertNull(unit.getSquare());
        assertThat(unit).isNotNull();
        assertThat(unit.hasSquare()).isFalse();
       /* // Remove the following placeholder:
        assertThat(unit).isNotNull();
        assertThat(unit.hasSquare()).isFalse();*/
        
    }

    /**
     * Tests that the unit indeed has the target square as its base after
     * occupation.
     */
    @Test
    void testOccupy() {
    	/*Square square = new BasicSquare();

    	unit.occupy(square);
    	
    	assertThat(unit.getSquare()).isEqualTo(square);
    	assertThat(square.getOccupants()).contains(unit);*/

        Square square = new BasicSquare();

        // Occupy the square with the unit
        unit.occupy(square);

        // Check that the square contains the unit
        assertThat(square.getOccupants()).contains(unit);

        // Check that the unit's square property is set to the occupied square
        assertEquals(square, unit.getSquare());
    }

    //这个测试方法会创建一个新的Square和两个Unit对象。它首先用第一个Unit占据Square，然后再次占据Square，但这次是使用第二个Unit。然后它会检查Square是否包含第二个Unit（不应该包含第一个Unit），以及第二个Unit的square属性是否被正确设置为目标Square。
    @Test
    public void testReoccupy() {
        Square square = new BasicSquare();
        Unit unit1 = new BasicUnit();
        Unit unit2 = new BasicUnit();

        // Occupy the square with the first unit
        unit1.occupy(square);


        // Re-occupy the square with the second unit
        unit2.occupy(square);

        // Check that the square contains the second unit (not the first)
        assertThat(square.getOccupants()).contains(unit1);
        assertThat(square.getOccupants()).contains(unit2);

        // Check that the second unit's square property is set to the occupied square
        assertEquals(square, unit2.getSquare());
    }
}
