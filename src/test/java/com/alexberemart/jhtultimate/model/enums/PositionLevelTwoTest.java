package com.alexberemart.jhtultimate.model.enums;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:com/alexberemart/jhtultimate/context.xml"
})
public class PositionLevelTwoTest {

    @Test
    public void parse(){
        PositionLevelTwo positionLevelTwo = PositionLevelTwo.parse(2);
        Assert.assertEquals(PositionLevelTwo.DC, positionLevelTwo);
        positionLevelTwo = PositionLevelTwo.parse(-1);
        Assert.assertNull(positionLevelTwo);
    }

    @Test
    public void asMap(){
        Map map = PositionLevelTwo.asMap();
        Assert.assertNotNull(map);
    }

}
