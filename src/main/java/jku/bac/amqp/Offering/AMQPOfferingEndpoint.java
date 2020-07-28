package jku.bac.amqp.Offering;

import jku.bac.amqp.Offering.service.AMQPOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AMQPOfferingEndpoint {
    @Autowired
    AMQPOfferingService amqpOfferingService;

    @GetMapping("/resetDB")
    public String resetDB() throws Exception {
        amqpOfferingService.insertItems();
        return "Offering database has been reset!";
    }
}
