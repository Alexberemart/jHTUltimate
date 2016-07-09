package com.alexberemart.jhtultimate.model.enums;

import com.alexberemart.jhtultimate.model.vo.StartupEntry;
import com.alexberemart.jhtultimate.model.vo.StartupOptions;
import com.alexberemart.jhtultimate.model.vo.StartupOptionsPositions;
import com.alexberemart.jhtultimate.model.vo.StartupPlayerPosition;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:com/alexberemart/jhtultimate/context.xml"
})
public class PlayerPositionTest {

    @Test
    public void parse(){
        PlayerPosition playerPosition = PlayerPosition.parse(2);
        Assert.assertEquals(PlayerPosition.DCN, playerPosition);
        playerPosition = PlayerPosition.parse(-1);
        Assert.assertNull(playerPosition);
    }

    @Test
    public void asMap(){
        Map map = PlayerPosition.asMap();
        Assert.assertNotNull(map);
    }

}
