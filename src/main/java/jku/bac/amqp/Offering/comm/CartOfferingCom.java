package jku.bac.amqp.Offering.comm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jku.bac.amqp.Offering.entity.TransferItem;
import jku.bac.amqp.Offering.service.AMQPOfferingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CartOfferingCom {
    @Autowired
    private AMQPOfferingService amqpOfferingService;

    @RabbitListener(queues = "cartoffering.msg")
    public void receive(ArrayList objArr) {
        ObjectMapper mapper = new ObjectMapper();
        List<TransferItem> transferItemList = mapper.convertValue(objArr, new TypeReference<List<TransferItem>>() {});
        System.out.println("Incoming itemList from Cart to remove from DB");
        amqpOfferingService.removeItems(transferItemList);
    }
}
