package com.alexberemart.jhtultimate.services;

import com.alexberemart.jhtultimate.AbstractJHTUltimateTest;
import com.alexberemart.jhtultimate.exceptions.FixedPositionsOverloadException;
import com.alexberemart.jhtultimate.model.enums.PlayerPosition;
import com.alexberemart.jhtultimate.model.enums.PositionLevelOne;
import com.alexberemart.jhtultimate.model.vo.*;
import com.alexberemart.utils.PlayerPredictionTestLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.equalTo;

public class StartupEntryServicesTest extends AbstractJHTUltimateTest {

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
        Assert.assertTrue(select(result, having(on(StartupEntry.class).getAttributeDescription(), equalTo(PositionLevelOne.A.toString()))).size() == PositionLevelOne.A.getMaxNumberOfPlayer());
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
