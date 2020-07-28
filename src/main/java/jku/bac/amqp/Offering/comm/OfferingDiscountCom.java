package jku.bac.amqp.Offering.comm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jku.bac.amqp.Offering.entity.TransferItem;
import jku.bac.amqp.Offering.service.AMQPOfferingService;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

public class OfferingDiscountCom {
    @Autowired
    AMQPOfferingService amqpOfferingService;

    @Autowired
    @Qualifier(value = "offeringDiscountTemplate")
    private RabbitTemplate template;
    @Autowired
    @Qualifier(value = "offeringDiscountExchange")
    private DirectExchange exchange;
    @Autowired
    @Qualifier(value = "offeringDiscountQueue")
    private Queue queue;
    @Autowired
    @Qualifier(value= "odSyncQueue")
    private Queue syncQueue;

    public List<TransferItem> getDiscountedList(List<TransferItem> itemList){
        ArrayList objArr = (ArrayList) template.convertSendAndReceive(exchange.getName(),"offeringdiscount", itemList);
        ObjectMapper mapper = new ObjectMapper();
        List<TransferItem> transferItemList = mapper.convertValue(objArr, new TypeReference<List<TransferItem>>() {});
        return transferItemList;
    }

    public void removeItemFromDiscount(String itemLabel){
        template.convertAndSend(syncQueue.getName(),itemLabel);
    }
}
