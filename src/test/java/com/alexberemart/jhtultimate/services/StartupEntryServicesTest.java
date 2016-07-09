package com.alexberemart.jhtultimate.services;

import com.alexberemart.jhtultimate.exceptions.FixedPositionsOverloadException;
import com.alexberemart.jhtultimate.model.enums.PlayerPosition;
import com.alexberemart.jhtultimate.model.vo.*;
import com.alexberemart.utils.PlayerPredictionTestLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:com/alexberemart/jhtultimate/context.xml"
})
public class StartupEntryServicesTest {

    @Autowired
    StartupEntryServices startupEntryServices;

    @Autowired
    PlayerPredictionTestLoader playerPredictionTestLoader;

    List<PlayerPrediction> playerPredictionList = new ArrayList<PlayerPrediction>();

    @Before
    public void setUp() throws IOException {
        playerPredictionList = playerPredictionTestLoader.load();
    }

    @Test
    public void createStartup(){

        StartupOptionsPositions startupOptionsPositions = new StartupOptionsPositions();
        startupOptionsPositions.setPosition(com.alexberemart.jhtultimate.model.enums.PlayerPosition.valueOf("KEE"));
        startupOptionsPositions.setValue(1);

        StartupPlayerPosition startupPlayerPosition = new StartupPlayerPosition();
        startupPlayerPosition.setName("Elias Metall");
        startupPlayerPosition.setPosition(PlayerPosition.DLN);

        StartupOptions startupOptions = new StartupOptions();
        startupOptions.getMinPositions().add(startupOptionsPositions);
        startupOptions.getFixedStartupPlayerPositions().add(startupPlayerPosition);

        List<StartupEntry> result = startupEntryServices.createStartup(playerPredictionList, startupOptions);
        Assert.assertEquals(11, result.size());
        Assert.assertTrue(select(result, having(on(StartupEntry.class).getPosition(), equalTo(PlayerPosition.KEE))).size() >= 1);
    }

    @Test(expected = FixedPositionsOverloadException.class)
    public void createStartupFixedPositionsOverload(){

        StartupOptionsPositions startupOptionsPositions = new StartupOptionsPositions();
        startupOptionsPositions.setPosition(com.alexberemart.jhtultimate.model.enums.PlayerPosition.valueOf("KEE"));
        startupOptionsPositions.setValue(1);

        StartupPlayerPosition startupPlayerPosition = new StartupPlayerPosition();
        startupPlayerPosition.setName("Elias Metall");
        startupPlayerPosition.setPosition(PlayerPosition.DLN);

        StartupOptions startupOptions = new StartupOptions();
        startupOptions.getMinPositions().add(startupOptionsPositions);
        for (Integer i = 0; i < 12; i++) {
            startupOptions.getFixedStartupPlayerPositions().add(startupPlayerPosition);
        }

        List<StartupEntry> result = startupEntryServices.createStartup(playerPredictionList, startupOptions);
        Assert.assertEquals(11, result.size());
    }

    @Test
    public void createStartupFixedPositions(){

        StartupOptionsPositions startupOptionsPositions = new StartupOptionsPositions();
        startupOptionsPositions.setPosition(com.alexberemart.jhtultimate.model.enums.PlayerPosition.valueOf("KEE"));
        startupOptionsPositions.setValue(1);

        StartupPlayerPosition startupPlayerPosition = new StartupPlayerPosition();
        startupPlayerPosition.setName("Elias Metall");
        startupPlayerPosition.setPosition(PlayerPosition.DLN);

        StartupOptions startupOptions = new StartupOptions();
        startupOptions.getMinPositions().add(startupOptionsPositions);
        startupOptions.getFixedStartupPlayerPositions().add(startupPlayerPosition);

        List<StartupEntry> result = startupEntryServices.createStartup(playerPredictionList, startupOptions);
        Assert.assertEquals(11, result.size());
        Assert.assertNotNull(result);
    }

}
