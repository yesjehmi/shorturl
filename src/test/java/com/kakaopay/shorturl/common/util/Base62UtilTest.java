package com.kakaopay.shorturl.common.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class Base62UtilTest {

    @Test
    public void Base62_converting_success() {
        assertEquals(811271799, Base62Util.toDecimal("24a1b"));
        assertEquals("24a1b", Base62Util.fromDecimal(811271799));
    }
}
