package jku.bac.amqp.Offering.entity;

import org.springframework.data.annotation.Id;

public class AMQPItem {

    @Id
    private String id;

    private String label;
    private double prize;
    private int  amount;

    public AMQPItem() {}

    public AMQPItem(String label, double prize, int amount) {
        this.label = label;
        this.prize = prize;
        this. amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Item[id=%s, label='%s' , prize='%f']",id , label, prize);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
