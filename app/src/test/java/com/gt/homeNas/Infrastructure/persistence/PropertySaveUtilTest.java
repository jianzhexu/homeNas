package com.gt.homeNas.Infrastructure.persistence;

import junit.framework.TestCase;

public class PropertySaveUtilTest extends TestCase {

    public void testSaveKV() {
        PropertySaveUtil.saveKey("a","b");
        String val = PropertySaveUtil.getKey("a");
        assertEquals(val, "b");
    }
}