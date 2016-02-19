package org.morewax.support;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by byuan on 2/17/16.
 */
public class TreeNodeTests {
    @Test
    public void testWithEmptyTree() throws Exception {
        TreeNode r1 = null;
        TreeNode nr1;

        List<String> t1 = TreeNode.to(r1);
        Assert.assertEquals(t1.size(), 1);
        nr1 = TreeNode.from(t1);
        Assert.assertNull(nr1);
    }

    @Test
    public void testWithSingleNodeTree() throws Exception {
        TreeNode r  = new TreeNode(1);
        TreeNode n1;

        List<String> t = TreeNode.to(r);
        Assert.assertEquals(t.size(), 3);
        Assert.assertEquals(t.get(0), "1");
        Assert.assertEquals(t.get(1), "#");
        Assert.assertEquals(t.get(2), "#");

        n1 = TreeNode.from(t);
        Assert.assertTrue(n1.left == null && n1.right == null);
    }

    @Test
    public void testWithComplexTree() throws Exception {
        List<String> t = Arrays.asList(
                                                 "25"
                             ,"18"                                   ,"50"
                   ,"19"             ,"20"                ,"35"                ,"60"
                ,"#" ,"15"     ,"18"      ,"25"     ,"20",     "40",     "55",     "70"
                   ,"#", "#", "#", "#", "#", "#", "#", "25", "#", "#", "#", "#", "#", "#"
                                                    ,"#", "#"
        );

        TreeNode r = TreeNode.from(t);
        Assert.assertNotNull(r);
        Assert.assertEquals(r.val, 25);
        Assert.assertEquals(r.left.val, 18);
        Assert.assertEquals(r.right.val, 50);

        List<String> t1 = TreeNode.to(r);
        Assert.assertEquals(t1, t);
    }
}
