package com.alexberemart.jhtultimate.services;

import com.alexberemart.jhtultimate.exceptions.FixedPositionsOverloadException;
import com.alexberemart.jhtultimate.model.vo.PlayerPosition;
import com.alexberemart.jhtultimate.model.vo.PlayerPrediction;
import com.alexberemart.jhtultimate.model.vo.StartupEntry;
import com.alexberemart.jhtultimate.model.vo.StartupOptions;
import org.springframework.stereotype.Component;
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
        Map<String, Integer> positionCounter = new HashMap<String, Integer>();
        Integer positionCount = 11;

        Long start = System.currentTimeMillis();

        if (startupOptions.getFixedPlayerPositions().size() > positionCount){
            throw new FixedPositionsOverloadException("FixedPositionsOverload");
        }

        //Posiciones Forzadas
        for (PlayerPosition playerPosition : startupOptions.getFixedPlayerPositions()){
            StartupEntry entry = new StartupEntry();
            entry.setName(playerPosition.getName());
            entry.setPosition(playerPosition.getPosition());
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

            //NO puede haber mas de 3 delanteros
            String subString = selectedPlayer.getAttribute().toString().substring(0, 1);
            Integer value = positionCounter.get(subString);
            if (value == null) {
                positionCounter.put(subString, 1);
            } else {
                positionCounter.remove(subString);
                positionCounter.put(subString, value + 1);
                if ((subString.equals("A")) && (value == 3)){
                    List<PlayerPrediction> entriesToRemove = new ArrayList<PlayerPrediction>();
                    for (PlayerPrediction playerPrediction : playerPredictionList){
                        if (playerPrediction.getAttribute().toString().substring(0, 1).equals("A")){
                            entriesToRemove.add(playerPrediction);
                        }
                    }
                    playerPredictionList.removeAll(entriesToRemove);
                }
            }

            //Borramos el resto de entradas con ese nombre
            List<PlayerPrediction> entriesToRemove = new ArrayList<PlayerPrediction>();
            for (PlayerPrediction playerPrediction : playerPredictionList){
                if (playerPrediction.getName().equals(selectedPlayer.getName())){
                    entriesToRemove.add(playerPrediction);
                }
            }
            playerPredictionList.removeAll(entriesToRemove);
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

}
