package com.alexberemart.jhtultimate.rest;

import com.alexberemart.core.rest.AbstractRestService;
import com.alexberemart.jhtultimate.model.vo.PlayerPrediction;
import com.alexberemart.jhtultimate.model.vo.StartupEntry;
import com.alexberemart.jhtultimate.model.vo.StartupOptions;
import com.alexberemart.jhtultimate.model.vo.StartupOptionsPositions;
import com.alexberemart.jhtultimate.services.StartupEntryServices;
import com.google.inject.servlet.RequestScoped;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Component("documentTypeCodeRestService")
@Path("startupEntries")
@RequestScoped
public class StartupEntryService extends AbstractRestService {

    @Autowired
    StartupEntryServices startupEntryServices;

    @POST
    @Path("calculateStartup")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStartup(JsonNode jsonNode) throws IOException {
        final List<PlayerPrediction> playerPredictionList = new ObjectMapper().readValue(jsonNode, new TypeReference<List<PlayerPrediction>>() {});

        StartupOptions startupOptions = new StartupOptions();

        StartupOptionsPositions startupOptionsPositions = new StartupOptionsPositions();
        startupOptionsPositions.setPosition("KEE");
        startupOptionsPositions.setValue(1);
        startupOptions.getMinPositions().add(startupOptionsPositions);

        List<StartupEntry> result;
        result = startupEntryServices.createStartup(playerPredictionList, startupOptions);
        return ok(result);
    }
}
