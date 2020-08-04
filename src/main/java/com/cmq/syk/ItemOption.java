package com.cmq.syk;

import java.util.HashMap;
import java.util.Map;

public class ItemOption {

    private Map<String, Integer> rarityMap;
    private Map<String, Integer> qualityMap;
    private Map<String, Integer> typeMap;

    public ItemOption() {
        rarityMap = new HashMap<>();
        rarityMap.put(ItemConst.CN_RARITY_IMMORTAL, ItemConst.INDEX_RARITY_IMMORTAL);
        rarityMap.put(ItemConst.CN_RARITY_MYTHICAL, ItemConst.INDEX_RARITY_MYTHICAL);
        rarityMap.put(ItemConst.CN_RARITY_RARE, ItemConst.INDEX_RARITY_RARE);
        rarityMap.put(ItemConst.CN_RARITY_LEGENDARY, ItemConst.INDEX_RARITY_LEGENDARY);
        rarityMap.put(ItemConst.CN_RARITY_ARCANA, ItemConst.INDEX_RARITY_ARCANA);
        rarityMap.put(ItemConst.CN_RARITY_UNCOMMON, ItemConst.INDEX_RARITY_UNCOMMON);


        qualityMap = new HashMap<>();
        qualityMap.put(ItemConst.CN_QUALITY_STANDARD, ItemConst.INDEX_QUALITY_STANDARD);
        qualityMap.put(ItemConst.CN_QUALITY_GENUINE, ItemConst.INDEX_QUALITY_GENUINE);
        qualityMap.put(ItemConst.CN_QUALITY_STRANGE, ItemConst.INDEX_QUALITY_STRANGE);
        qualityMap.put(ItemConst.CN_QUALITY_LUCKY, ItemConst.INDEX_QUALITY_LUCKY);
        qualityMap.put(ItemConst.CN_QUALITY_INFUSED, ItemConst.INDEX_QUALITY_INFUSED);
        qualityMap.put(ItemConst.CN_QUALITY_UNUSUAL, ItemConst.INDEX_QUALITY_UNUSUAL);

        typeMap = new HashMap<>();
        typeMap.put(ItemConst.CN_TYPE_WEARABLE, ItemConst.INDEX_TYPE_WEARABLE);
        typeMap.put(ItemConst.CN_TYPE_COURIER, ItemConst.INDEX_TYPE_COURIER);
        typeMap.put(ItemConst.CN_TYPE_WARD, ItemConst.INDEX_TYPE_WARD);
        typeMap.put(ItemConst.CN_TYPE_BUNDLE, ItemConst.INDEX_TYPE_BUNDLE);
        typeMap.put(ItemConst.CN_TYPE_TREASURE, ItemConst.INDEX_TYPE_TREASURE);
        typeMap.put(ItemConst.CN_TYPE_GEM, ItemConst.INDEX_TYPE_GEM);
    }

    public static final String[] RARITY_OPTION = {
            ItemConst.CN_RARITY_IMMORTAL,
            ItemConst.CN_RARITY_MYTHICAL,
            ItemConst.CN_RARITY_RARE,
            ItemConst.CN_RARITY_LEGENDARY,
            ItemConst.CN_RARITY_ARCANA,
            ItemConst.CN_RARITY_UNCOMMON
    };

    public static final String[] QUALITY_OPTION = {
            ItemConst.CN_QUALITY_STANDARD,
            ItemConst.CN_QUALITY_GENUINE,
            ItemConst.CN_QUALITY_STRANGE,
            ItemConst.CN_QUALITY_LUCKY,
            ItemConst.CN_QUALITY_INFUSED,
            ItemConst.CN_QUALITY_UNUSUAL
    };

    public static final String[] TYPE_OPTION = {
            ItemConst.CN_TYPE_WEARABLE,
            ItemConst.CN_TYPE_COURIER,
            ItemConst.CN_TYPE_WARD,
            ItemConst.CN_TYPE_BUNDLE,
            ItemConst.CN_TYPE_TREASURE,
            ItemConst.CN_TYPE_GEM
    };

    public int getValueIndex(int type, String value) {
        int result = 0;
        Integer intV = null;
        if (type == 1) {
            intV = qualityMap.get(value);
        } else if (type == 2){
            intV = typeMap.get(value);
        } else if (type == 3){
            intV = rarityMap.get(value);
        }
        if (intV != null){
            result = intV.intValue();
        }
        return result;
    }
}
