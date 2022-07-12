package com.candolp.common.requests;

import org.aspectj.apache.bcel.classfile.annotation.NameValuePair;

import java.util.HashMap;
import java.util.List;

public class CreateDeliveryWithDroneRequest {

    String droneSerialNumber;
    HashMap<String, Integer> orderItems;

    public String getDroneSerialNumber() {
        return droneSerialNumber;
    }

    public void setDroneSerialNumber(String droneSerialNumber) {
        this.droneSerialNumber = droneSerialNumber;
    }

    public HashMap<String, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(HashMap<String, Integer> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        StringBuilder item = new StringBuilder("[");
        int count = 0;
        for (String id: orderItems.keySet()){
            if (count> 0) item.append(",");
            item.append("\"").append(item).append("\":\"").append(orderItems.get(id)).append("\"");
        }
        item.append("]");

        return "CreateDeliveryWithDroneRequest{" +
                "droneSerialNumber:'" + droneSerialNumber + '\'' +
                ", orderItems:" + item.toString() +
                '}';
    }
}
