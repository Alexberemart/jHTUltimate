package com.alexberemart.utils;

import com.alexberemart.jhtultimate.model.vo.PlayerPrediction;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerPredictionTestLoader {

    public List<PlayerPrediction> load() throws IOException {
        List<PlayerPrediction> playerPredictionList = new ArrayList<PlayerPrediction>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(this.getClass().getClassLoader().getResourceAsStream("com/alexberemart/jhtultimate/playerPrediction.json"));
        for (JsonNode node : jsonNode) {
            PlayerPrediction playerPrediction = new PlayerPrediction();
            playerPrediction.setName(node.path("name").asText());
            playerPrediction.setAttribute(node.path("attribute").asText());
            playerPrediction.setValue(node.path("value").asDouble());
            playerPredictionList.add(playerPrediction);
        }
        return playerPredictionList;
    }
}
