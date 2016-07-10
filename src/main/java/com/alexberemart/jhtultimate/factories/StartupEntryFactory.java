package com.alexberemart.jhtultimate.factories;

import com.alexberemart.jhtultimate.model.vo.PlayerPrediction;
import com.alexberemart.jhtultimate.model.vo.StartupEntry;
import com.alexberemart.jhtultimate.model.vo.StartupPlayerPosition;
import org.springframework.stereotype.Component;

@Component
public class StartupEntryFactory {

    public StartupEntry createStartup(PlayerPrediction playerPrediction) {
        StartupEntry entry = new StartupEntry();
        entry.setName(playerPrediction.getName());
        entry.setPosition(playerPrediction.getAttribute());
        entry.setValue(playerPrediction.getValue());
        entry.setMaxRange(playerPrediction.getMaxRange());
        return entry;
    }

    public StartupEntry createStartup(StartupPlayerPosition startupPlayerPosition) {
        StartupEntry entry = new StartupEntry();
        entry.setName(startupPlayerPosition.getName());
        entry.setPosition(startupPlayerPosition.getPosition());
        entry.setValue(1.0);
        return entry;
    }
}
