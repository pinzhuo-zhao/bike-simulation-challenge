package org.pzz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pzz.entity.Grid;
import org.pzz.entity.Position;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao
 * @create: 2022-07-20 21:45
 **/
public class GridTest {
    private Grid grid;
    @Before
    public void setUp() throws Exception {
        this.grid = new Grid(7,7);
    }

    @Test
    public void testIsLegalPosition_OnPoint_1() {
        assertTrue(grid.isLegalPosition(new Position(grid.getLength(),grid.getLength())));
    }

    @Test
    public void testIsLegalPosition_OnPoint_2() {
        assertTrue(grid.isLegalPosition(new Position(0,0)));
    }

    @Test
    public void testIsLegalPosition_OffPoint_1() {
        assertFalse(grid.isLegalPosition(new Position(grid.getLength()+1,7)));
    }

    @Test
    public void testIsLegalPosition_OffPoint_2() {
        assertFalse(grid.isLegalPosition(new Position(grid.getLength(),grid.getLength()+1)));
    }

    @Test
    public void testIsLegalPosition_OffPoint_3() {
        assertFalse(grid.isLegalPosition(new Position(-1,0)));
    }

    @Test
    public void testIsLegalPosition_OffPoint_4() {
        assertFalse(grid.isLegalPosition(new Position(0,-1)));
    }




}
