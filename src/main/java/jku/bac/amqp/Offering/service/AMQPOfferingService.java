package jku.bac.amqp.Offering.service;

import jku.bac.amqp.Offering.comm.OfferingDiscountCom;
import jku.bac.amqp.Offering.entity.AMQPItem;
import jku.bac.amqp.Offering.entity.TransferItem;
import jku.bac.amqp.Offering.repo.AMQPItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AMQPOfferingService {

    @Autowired
    private AMQPItemRepo AMQPItemRepo;

    @Autowired
    private OfferingDiscountCom offeringDiscountCom;


    public void insertItems() throws Exception {
        AMQPItemRepo.deleteAll();
        AMQPItemRepo.save(new AMQPItem("Screw", 0.2, 1000));
        AMQPItemRepo.save(new AMQPItem("Screwdriver", 19.99, 10));
        AMQPItemRepo.save(new AMQPItem("Drill", 199.99, 1));
    }

    public List<TransferItem> getAllItems() throws Exception {
        //fetching all items
        List<AMQPItem> AMQPItemList = AMQPItemRepo.findAll();
        List<TransferItem> transferItemList = itemListTransform(AMQPItemList);
        transferItemList = offeringDiscountCom.getDiscountedList(transferItemList);
        return transferItemList;
    }

    private static List<TransferItem> itemListTransform(List<AMQPItem> itemList){
        List<TransferItem> transferItemList = new ArrayList<>();
        for(AMQPItem item : itemList){
            TransferItem transferItem = new TransferItem(
                    item.getId(),
                    item.getLabel(),
                    item.getPrize(),
                    item.getAmount());
            transferItemList.add(transferItem);
        }
        return transferItemList;
    }

    public void removeItems(List<TransferItem> itemList){
        for(TransferItem transferItem : itemList){
            AMQPItem item = AMQPItemRepo.findByLabel(transferItem.getLabel());
            if(item.getAmount() - transferItem.getAmount() >= 0){
                item.setAmount(item.getAmount() - transferItem.getAmount());
                if(item.getAmount() == 0){
                    AMQPItemRepo.delete(item);
                    offeringDiscountCom.removeItemFromDiscount(item.getLabel());
                    System.out.println("Should remove here");
                } else {
                    System.out.println("Purchase complete!");
                    AMQPItemRepo.save(item);
                }
            }else{
                System.out.println(transferItem.getAmount()+" x "+transferItem.getLabel()+" kann nicht gekauft werden. Bestand zu gering!" );
            }
        }
    }
}
