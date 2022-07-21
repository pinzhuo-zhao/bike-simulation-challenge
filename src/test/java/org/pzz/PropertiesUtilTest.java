package org.pzz;

import org.junit.Test;
import org.pzz.utils.PropertiesUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
 * @create: 2022-07-20 23:17
 **/
public class PropertiesUtilTest {
    @Test
    public void testGetProperties_OnPoint() {
        String length = PropertiesUtil.getProperties("config", "simulation.grid.length");
        assertEquals(length,"7");
    }

    @Test
    public void testGetProperties_OffPoint1() {
        String test = PropertiesUtil.getProperties("config", "gibberish");
        assertNull(test);
    }

    @Test
    public void testGetProperties_OffPoint2() {
        String test = PropertiesUtil.getProperties("test", "gibberish");
        assertNull(test);
    }

}
