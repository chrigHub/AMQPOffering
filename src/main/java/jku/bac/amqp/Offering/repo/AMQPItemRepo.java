package jku.bac.amqp.Offering.repo;


import jku.bac.amqp.Offering.entity.AMQPItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AMQPItemRepo extends MongoRepository<AMQPItem, String> {
    public AMQPItem findByLabel(String label);
}
