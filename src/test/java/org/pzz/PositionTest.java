package org.pzz;

import org.junit.Before;
import org.junit.Test;
import org.pzz.entity.Direction;
import org.pzz.entity.Position;

import static org.junit.Assert.assertEquals;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao
 * @create: 2022-07-20 22:08
 **/
public class PositionTest {
    private Position position;
    @Before
    public void setUp() throws Exception {
        position = new Position(0,0);
    }

    @Test
    public void testNextPosition_North() {
        assertEquals(new Position(0,1), position.nextPosition(Direction.NORTH));
    }

    @Test
    public void testNextPosition_East() {
        assertEquals(new Position(1,0), position.nextPosition(Direction.EAST));
    }

    @Test
    public void testNextPosition_South() {
        assertEquals(new Position(0,-1), position.nextPosition(Direction.SOUTH));
    }

    @Test
    public void testNextPosition_West() {
        assertEquals(new Position(-1,0), position.nextPosition(Direction.WEST));
    }

    @Test
    public void testPositionToString() {
        assertEquals("(0,0)", position.toString());
    }
}
