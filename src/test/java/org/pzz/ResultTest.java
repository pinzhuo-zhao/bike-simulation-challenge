package org.pzz;

import org.junit.Test;
import org.pzz.utils.Result;

import static org.junit.Assert.*;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
 * @create: 2022-07-20 23:25
 **/
public class ResultTest {
    @Test
    public void testOk() {
        Result result = Result.ok();
        assertTrue(result.getSuccess());
        assertEquals(result.getCode(),200);
    }

    @Test
    public void testError() {
        Result result = Result.error();
        assertFalse(result.getSuccess());
        assertEquals(result.getCode(),400);
    }

    @Test
    public void testData() {
        String value = "value";
        Result result = Result.ok().data("key", value);
        assertEquals(result.getData().get("key"),value);
    }
}
