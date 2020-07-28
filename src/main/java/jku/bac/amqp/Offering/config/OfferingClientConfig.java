package jku.bac.amqp.Offering.config;

import jku.bac.amqp.Offering.comm.CartOfferingCom;
import jku.bac.amqp.Offering.comm.OfferingClientCom;
import jku.bac.amqp.Offering.comm.OfferingDiscountCom;
import jku.bac.amqp.Offering.entity.TransferItem;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class OfferingClientConfig {

    @Bean
    public Queue queue() {
        return new Queue("clientoffering.rpc.req");
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("clientoffering.rpc");
    }

    @Bean
    public Binding binding(DirectExchange exchange, Queue queue){
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("clientoffering");
    }

    @Bean
    public MessageConverter jsonMessageConverter()
    {
        Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();
        jsonMessageConverter.setClassMapper(classMapper());
        return jsonMessageConverter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    OfferingClientCom offeringClientCom() {
        return new OfferingClientCom();
    }

    //==============================================================
    @Bean
    CartOfferingCom cartOfferingCom() {
        return new CartOfferingCom();
    }
    //==============================================================
    @Bean
    OfferingDiscountCom offeringDiscountCom(){
        return new OfferingDiscountCom();
    }

    @Bean(name="offeringDiscountQueue")
    public Queue odQueue(){
        return new Queue("offeringdiscount.msg");
    }

    @Bean(name="odSyncQueue")
    public Queue syncQueue(){
        return new Queue("offeringdiscount.sync");
    }

    @Bean(name="offeringDiscountExchange")
    public DirectExchange odExchange(){
        return new DirectExchange("offeringdiscount.rpc");
    }

    @Bean
    public DefaultClassMapper classMapper(){
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("jku.bac.amqp.discount.entity.TransferItem", TransferItem.class);
        //idClassMapping.put("java.util.ArrayList", ArrayList.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }

    @Bean(name="offeringDiscountTemplate")
    public RabbitTemplate odTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
