package app.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.beans.Dealer;

public class DealersDAO {

    public static Map<String,Dealer> dealers = new HashMap<>();

    public void loadDealers(JsonNode rootNode, ObjectMapper objectMapper) {
        dealers = new HashMap<>();
        JsonNode dealersNode = rootNode.path("dealers");
        Iterator<JsonNode> d = dealersNode.elements();
        while (d.hasNext()) {
            try {
                Dealer dealer = objectMapper.treeToValue(d.next(), Dealer.class);
                dealers.put(dealer.getId(),dealer);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

    }

    public void clearDealers(){
        dealers.clear();
    }

    public Dealer create(Dealer dealer){
        dealers.put(dealer.getId(),dealer);
        return dealer;
    }

    public Dealer update(Dealer dealer){
        return dealers.replace(dealer.getId(), dealer);
    }

}