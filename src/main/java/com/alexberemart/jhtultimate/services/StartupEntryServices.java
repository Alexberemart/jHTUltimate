package com.alexberemart.jhtultimate.services;

import com.alexberemart.jhtultimate.exceptions.FixedPositionsOverloadException;
import com.alexberemart.jhtultimate.exceptions.MaxRangeLimitException;
import com.alexberemart.jhtultimate.factories.StartupEntryFactory;
import com.alexberemart.jhtultimate.model.vo.PlayerPrediction;
import com.alexberemart.jhtultimate.model.vo.StartupEntry;
import com.alexberemart.jhtultimate.model.vo.StartupOptions;
import com.alexberemart.jhtultimate.model.vo.StartupPlayerPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@Service
public class StartupEntryServices {

    @Autowired
    StartupEntryFactory startupEntryFactory;

    public List<StartupEntry> createStartup(List<PlayerPrediction> playerPredictionList, StartupOptions startupOptions) {

        List<StartupEntry> result = new ArrayList<>();

        manageMaxRange(playerPredictionList, startupOptions);
        manageExclusions(playerPredictionList, startupOptions);

        Collections.sort(playerPredictionList, predictionComparator);
        Integer positionCount = 11;

        Long start = System.currentTimeMillis();

        if (startupOptions.getFixedStartupPlayerPositions().size() > positionCount){
            throw new FixedPositionsOverloadException("FixedPositionsOverload");
        }

        if (startupOptions.getMaxRange() != null) {
            if (startupOptions.getMaxRange() < 0) {
                throw new MaxRangeLimitException("Max Range must be greater than zero");
            }
        }

        //Posiciones Forzadas
        for (StartupPlayerPosition startupPlayerPosition : startupOptions.getFixedStartupPlayerPositions()){
            result.add(startupEntryFactory.createStartup(startupPlayerPosition));
        }

        Integer remainingPositionCountAfterFixedPositions = positionCount - result.size();

        for (Integer i = 0; i < remainingPositionCountAfterFixedPositions; i++){

            PlayerPrediction selectedPlayer = getNextPlayer(playerPredictionList, startupOptions, remainingPositionCountAfterFixedPositions - i);
            result.add(startupEntryFactory.createStartup(selectedPlayer));
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

    private void manageMaxRange(List<PlayerPrediction> playerPredictionList, StartupOptions startupOptions) {

        if (startupOptions.getMaxRange() == null){
            return;
        }

        List<PlayerPrediction> entriesToRemove =
                select(playerPredictionList,
                        having(on(PlayerPrediction.class).getMaxRange(),
                                greaterThan(startupOptions.getMaxRange())));

        playerPredictionList.removeAll(entriesToRemove);
    }

    private void manageExclusions(List<PlayerPrediction> playerPredictionList, StartupOptions startupOptions) {
        for (StartupPlayerPosition startupPlayerPosition : startupOptions.getExcludedStartupPlayerPositions()){
            List<PlayerPrediction> entriesFiltered =
                    select(playerPredictionList,
                            having(on(PlayerPrediction.class).getName(),
                                    equalTo(startupPlayerPosition.getName())));
            List<PlayerPrediction> entriesToRemove =
                    select(entriesFiltered,
                            having(on(PlayerPrediction.class).getAttribute(),
                                    equalTo(startupPlayerPosition.getPosition())));
            playerPredictionList.removeAll(entriesToRemove);
        }
    }

    private PlayerPrediction getNextPlayer(List<PlayerPrediction> playerPredictionList, StartupOptions startupOptions, Integer remainingPositionCount) {

        if (startupOptions.getMinPositions().size() >= (remainingPositionCount)){
            return select(playerPredictionList, having(on(PlayerPrediction.class).getAttribute(), equalTo(startupOptions.getMinPositions().get(0).getPosition()))).get(0);
        }else {
            return playerPredictionList.get(0);
        }
    }
}
