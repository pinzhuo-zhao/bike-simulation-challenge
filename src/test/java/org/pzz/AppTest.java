package org.pzz;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pzz.entity.Direction;
import org.pzz.entity.Position;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

   /* @Test
    public void name() {
        Result config = PropertiesUtil.getProperties("configww", "simulation.grid.size");
        System.out.println(config.getData().get("simulation.grid.size"));
    }*/

    @Test
    public void DirectionTest() {
        Direction direction = Direction.NORTH.nextLeftDirection();
        System.out.println(direction);
    }

    @Test
    public void DirectionTest2() {
        Position pos = new Position(5,3);
        System.out.println(pos);
    }


}
