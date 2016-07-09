package com.alexberemart.jhtultimate.services;

import com.alexberemart.jhtultimate.exceptions.FixedPositionsOverloadException;
import com.alexberemart.jhtultimate.model.vo.StartupPlayerPosition;
import com.alexberemart.jhtultimate.model.vo.PlayerPrediction;
import com.alexberemart.jhtultimate.model.vo.StartupEntry;
import com.alexberemart.jhtultimate.model.vo.StartupOptions;
import org.springframework.stereotype.Service;

import java.util.*;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;
import static org.hamcrest.Matchers.equalTo;

@Service
public class StartupEntryServices {

    public List<StartupEntry> createStartup(List<PlayerPrediction> playerPredictionList, StartupOptions startupOptions) {

        List<StartupEntry> result = new ArrayList<StartupEntry>();

        Collections.sort(playerPredictionList, predictionComparator);
        Integer positionCount = 11;

        Long start = System.currentTimeMillis();

        if (startupOptions.getFixedStartupPlayerPositions().size() > positionCount){
            throw new FixedPositionsOverloadException("FixedPositionsOverload");
        }

        //Posiciones Forzadas
        for (StartupPlayerPosition startupPlayerPosition : startupOptions.getFixedStartupPlayerPositions()){
            StartupEntry entry = new StartupEntry();
            entry.setName(startupPlayerPosition.getName());
            entry.setPosition(startupPlayerPosition.getPosition());
            entry.setValue(1.0);
            result.add(entry);
        }

        Integer remainingPositionCount = positionCount - result.size();

        for (Integer i = 0; i < remainingPositionCount; i++){

            PlayerPrediction selectedPlayer = new PlayerPrediction();

            if (startupOptions.getMinPositions().size() >= (remainingPositionCount - i)){
                selectedPlayer = select(playerPredictionList, having(on(PlayerPrediction.class).getAttribute(), equalTo(startupOptions.getMinPositions().get(0).getPosition()))).get(0);
            }else {
                selectedPlayer = playerPredictionList.get(0);
            }

            StartupEntry entry = new StartupEntry();
            entry.setName(selectedPlayer.getName());
            entry.setPosition(selectedPlayer.getAttribute());
            entry.setValue(selectedPlayer.getValue());
            result.add(entry);

            manageMaxNumberOfPositions(selectedPlayer, result, playerPredictionList);
            manageMaxNumberOfSamePlayer(selectedPlayer, playerPredictionList);
        }

        Long end = System.currentTimeMillis();
        System.out.println("time " + (end - start));

        return result;
    }

    private Comparator<PlayerPrediction> predictionComparator = new Comparator<PlayerPrediction>() {
        public int compare(PlayerPrediction m1, PlayerPrediction m2) {
            Double first = m2.getValue();
            return first.compareTo(m1.getValue());
        }
    };

    private void manageMaxNumberOfPositions(PlayerPrediction selectedPlayer, List<StartupEntry> result, List<PlayerPrediction> playerPredictionList) {
        String positionLevelOneText = selectedPlayer.getAttribute().getPositionLevelTwo().getPositionLevelOne().toString();
        Integer positionLevelOneMaxNumberOfPlayers = selectedPlayer.getAttribute().getPositionLevelTwo().getPositionLevelOne().getMaxNumberOfPlayer();
        Integer selectedPositionCount =
                select(result,
                        having(on(StartupEntry.class).getAttributeDescription(),
                                equalTo(positionLevelOneText)))
                        .size();
        if (Objects.equals(selectedPositionCount, positionLevelOneMaxNumberOfPlayers)) {
            List<PlayerPrediction> entriesToRemove =
                    select(playerPredictionList,
                            having(on(PlayerPrediction.class).getAttributeDescription(),
                                    equalTo(positionLevelOneText)));

            playerPredictionList.removeAll(entriesToRemove);
        }
    }

    private void manageMaxNumberOfSamePlayer(PlayerPrediction selectedPlayer, List<PlayerPrediction> playerPredictionList) {
        List<PlayerPrediction> entriesToRemove =
                select(playerPredictionList,
                        having(on(PlayerPrediction.class).getName(),
                                equalTo(selectedPlayer.getName())));

        playerPredictionList.removeAll(entriesToRemove);
    }
}
