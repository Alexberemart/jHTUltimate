package com.alexberemart.jhtultimate.model.enums;

import com.alexberemart.jhtultimate.AbstractJHTUltimateTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

public class PositionLevelOneTest extends AbstractJHTUltimateTest {

    @Test
    public void parse(){
        PositionLevelOne positionLevelOne = PositionLevelOne.parse(2);
        Assert.assertEquals(PositionLevelOne.D, positionLevelOne);
        positionLevelOne = PositionLevelOne.parse(-1);
        Assert.assertNull(positionLevelOne);
    }

    @Test
    public void asMap(){
        Map map = PositionLevelOne.asMap();
        Assert.assertNotNull(map);
    }

}
