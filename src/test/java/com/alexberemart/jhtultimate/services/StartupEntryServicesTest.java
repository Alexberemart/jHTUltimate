package com.alexberemart.jhtultimate.services;

import com.alexberemart.jhtultimate.model.vo.PlayerPrediction;
import com.alexberemart.jhtultimate.model.vo.StartupOptions;
import com.alexberemart.jhtultimate.model.vo.StartupOptionsPositions;
import com.alexberemart.utils.PlayerPredictionTestLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        StartupOptions startupOptions = new StartupOptions();

        StartupOptionsPositions startupOptionsPositions = new StartupOptionsPositions();
        startupOptionsPositions.setPosition("KEE");
        startupOptionsPositions.setValue(1);
        startupOptions.getMinPositions().add(startupOptionsPositions);

        startupEntryServices.createStartup(playerPredictionList, startupOptions);
    }

}
