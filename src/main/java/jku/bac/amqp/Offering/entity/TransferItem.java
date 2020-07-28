package jku.bac.amqp.Offering.entity;

import java.io.Serializable;

public class TransferItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String label;
    private double prize;
    private int  amount;

    public TransferItem(){}

    public TransferItem(String id, String label, double prize, int amount) {
        this.id = id;
        this.label = label;
        this.prize = prize;
        this. amount = amount;
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
