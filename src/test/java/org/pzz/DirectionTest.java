package org.pzz;

import org.junit.Before;
import org.junit.Test;
import org.pzz.entity.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao
 * @create: 2022-07-20 21:30
 **/
public class DirectionTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testNextLeftDirection_North() {
        Direction direction = Direction.NORTH.nextLeftDirection();
        assertEquals(direction, Direction.WEST);
    }

    @Test
    public void testNextLeftDirection_East() {
        Direction direction = Direction.EAST.nextLeftDirection();
        assertEquals(direction, Direction.NORTH);
    }

    @Test
    public void testNextLeftDirection_South() {
        Direction direction = Direction.SOUTH.nextLeftDirection();
        assertEquals(direction, Direction.EAST);
    }

    @Test
    public void testNextLeftDirection_West() {
        Direction direction = Direction.WEST.nextLeftDirection();
        assertEquals(direction, Direction.SOUTH);
    }

    @Test
    public void testNextRightDirection_North() {
        Direction direction = Direction.NORTH.nextRightDirection();
        assertEquals(direction, Direction.EAST);
    }

    @Test
    public void testNextRightDirection_East() {
        Direction direction = Direction.EAST.nextRightDirection();
        assertEquals(direction, Direction.SOUTH);
    }

    @Test
    public void testNextRightDirection_South() {
        Direction direction = Direction.SOUTH.nextRightDirection();
        assertEquals(direction, Direction.WEST);
    }

    @Test
    public void testNextRightDirection_West() {
        Direction direction = Direction.WEST.nextRightDirection();
        assertEquals(direction, Direction.NORTH);
    }

    @Test
    public void testGetDirection_North() {
        assertEquals(Direction.getDirection("NORTH"),Direction.NORTH);
    }

    @Test
    public void testGetDirection_East() {
        assertEquals(Direction.getDirection("EAST"),Direction.EAST);
    }

    @Test
    public void testGetDirection_South() {
        assertEquals(Direction.getDirection("SOUTH"),Direction.SOUTH);
    }

    @Test
    public void testGetDirection_West() {
        assertEquals(Direction.getDirection("WEST"),Direction.WEST);
    }

    @Test
    public void testGetDirection_Null() {
        assertNull(Direction.getDirection("Gibberish"));
    }
}
