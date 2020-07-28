package jku.bac.amqp.Offering.comm;


import com.rabbitmq.tools.json.JSONWriter;
import jku.bac.amqp.Offering.entity.TransferItem;
import jku.bac.amqp.Offering.service.AMQPOfferingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class OfferingClientCom {
    @Autowired
    private AMQPOfferingService amqpOfferingService;

    @RabbitListener(queues = "clientoffering.rpc.req")
    public List<TransferItem> receive(String in) {
        System.out.println("Request came in!");
        List<TransferItem> itemList = new ArrayList<TransferItem>();
        try{
            itemList = amqpOfferingService.getAllItems();
        } catch (Exception e){
            e.printStackTrace();
        }

        return itemList;
    }

}