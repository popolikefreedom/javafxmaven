package com.cmq.syk;

import java.util.HashMap;
import java.util.Map;

public class Item {

    public Item() {
        priceMap = new HashMap<>();
    }

    public String itemName;
    public Map<String, ItemPrice> priceMap;

}
