package org.morewax.support;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by byuan on 12/31/15.
 */
public class UtilsTests {
    @Test
    public void testKthOrder() throws Exception {
        int[] a1 = new int[]{1, 1, 4, 5, 7};
        int result = Utils.select(a1, 0, a1.length-1, 2);
        Assert.assertEquals(result, 1);

        int[] a2 = new int[]{1, 1, 4, 5, 7};
        result = Utils.select(a2, 0, a2.length-1, 3);
        Assert.assertEquals(result, 4);

        int[] a3 = new int[]{1, 1, 4, 8, 5, 7};
        result = Utils.select(a3, 0, a3.length-1, 3);
        Assert.assertEquals(result, 4);
    }

    @Test
    public void testGetMedian() throws Exception {
        int[] result;
        int[] a1 = new int[]{1, 1, 4, 5, 7};
        result = Utils.getMedians(a1);
        Assert.assertEquals(result[0], 4);
        Assert.assertEquals(result[1], 4);

        int[] a2 = new int[]{1, 2};
        result = Utils.getMedians(a2);
        Assert.assertEquals(result[0], 1);
        Assert.assertEquals(result[1], 2);

        int[] a3 = new int[]{1, 1, 4, 8, 5, 7};
        result = Utils.getMedians(a3);
        Assert.assertEquals(result[0], 4);
        Assert.assertEquals(result[1], 5);

        int[] a4 = new int[]{1};
        result = Utils.getMedians(a4);
        Assert.assertEquals(result[0], 1);
        Assert.assertEquals(result[1], 1);
    }
}
