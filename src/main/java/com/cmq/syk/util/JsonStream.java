package com.cmq.syk.util;


import com.alibaba.fastjson.JSONReader;
import com.cmq.syk.Item;
import com.cmq.syk.ItemConst;
import com.cmq.syk.ItemPrice;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class JsonStream {
    public static final String TAG = "JsonStream";


    private static JsonStream mJsonStream;

    public static JsonStream getInstance() {
        if (mJsonStream == null) {
            mJsonStream = new JsonStream();
        }
        return mJsonStream;
    }

    private JsonStream() {

    }

    public void readJsonArray(InputStream in, ObjectCallback<List<Item>> callback) {
        JSONReader jsonReader = new JSONReader(new InputStreamReader(in));
        List<Item> list = new ArrayList<>();
        jsonReader.startArray();
        while (jsonReader.hasNext()) {
            jsonReader.startObject();
            Item item = new Item();
            while (jsonReader.hasNext()) {
                String objKey = jsonReader.readString();
                if ("Name".equals(objKey)) {
                    item.itemName = jsonReader.readString();
                    list.add(item);
                } else if ("C5price".equals(objKey)) {
                    float price = jsonReader.readObject(Float.TYPE);
                    if (price > 0.01) {
                        ItemPrice itemPrice = new ItemPrice();
                        itemPrice.price = price;
                        item.priceMap.put(ItemConst.TAG_C5, itemPrice);
                    }
                } else if ("C5num".equals(objKey)) {
                    String number = jsonReader.readString();
                    ItemPrice priceItem = item.priceMap.get(ItemConst.TAG_C5);
                    if (priceItem != null) {
                        priceItem.number = number;
                    }
                } else if ("C5rate".equals(objKey)) {
                    float rate = jsonReader.readObject(Float.TYPE);
                    ItemPrice priceItem = item.priceMap.get(ItemConst.TAG_C5);
                    if (priceItem != null) {
                        priceItem.rate = rate;
                    }
                } else if ("C5url".equals(objKey)) {
                    String url = jsonReader.readString();
                    ItemPrice priceItem = item.priceMap.get(ItemConst.TAG_C5);
                    if (priceItem != null) {
                        priceItem.url = url;
                    }
                } else if ("BuffPrice".equals(objKey)) {
                    float price = jsonReader.readObject(Float.TYPE);
                    if (price > 0.01) {
                        ItemPrice itemPrice = new ItemPrice();
                        itemPrice.price = price;
                        item.priceMap.put(ItemConst.TAG_BUFF, itemPrice);
                    }
                } else if ("BuffNum".equals(objKey)) {
                    String number = jsonReader.readString();
                    ItemPrice priceItem = item.priceMap.get(ItemConst.TAG_BUFF);
                    if (priceItem != null) {
                        priceItem.number = number;
                    }
                } else if ("Buffrate".equals(objKey)) {
                    float rate = jsonReader.readObject(Float.TYPE);
                    ItemPrice priceItem = item.priceMap.get(ItemConst.TAG_BUFF);
                    if (priceItem != null) {
                        priceItem.rate = rate;
                    }
                } else if ("Buffurl".equals(objKey)) {
                    String url = jsonReader.readString();
                    ItemPrice priceItem = item.priceMap.get(ItemConst.TAG_BUFF);
                    if (priceItem != null) {
                        priceItem.url = url;
                    }
                } else if ("SteamPrice".equals(objKey)) {
                    float price = jsonReader.readObject(Float.TYPE);
                    if (price > 0.01) {
                        ItemPrice itemPrice = new ItemPrice();
                        itemPrice.price = price;
                        item.priceMap.put(ItemConst.TAG_STEAM, itemPrice);
                    }
                } else if ("SteamNum".equals(objKey)) {
                    String number = jsonReader.readString();
                    ItemPrice priceItem = item.priceMap.get(ItemConst.TAG_STEAM);
                    if (priceItem != null) {
                        priceItem.number = number;
                    }
                } else if ("Steamurl".equals(objKey)) {
                    String url = jsonReader.readString();
                    ItemPrice priceItem = item.priceMap.get(ItemConst.TAG_STEAM);
                    if (priceItem != null) {
                        priceItem.url = url;
                    }
                } else {
                    jsonReader.readObject();
                }
            }
            jsonReader.endObject();
        }
        jsonReader.endArray();
        jsonReader.close();
        if (callback != null){
            callback.onObject(list);
        }
//        jsonReader.startObject();
//
//        while (jsonReader.hasNext()) {
//            String key = jsonReader.readString();
//            if ("type".equals(key)) {
//                String value = jsonReader.readObject().toString();
//                city.setType(value);
//            } else if ("name".equals(key)) {
//                String value = jsonReader.readObject().toString();
//                city.setName(value);
//            } else if ("crs".equals(key)) {
//                String value = jsonReader.readObject().toString();
//                city.setCrs(value);
//                cityCallback.onObject(city);
//            } else if ("features".equals(key)) {
//                jsonReader.startArray();
//                while (jsonReader.hasNext()) {
//                    GisInfoData data = new GisInfoData();
//                    jsonReader.startObject();
//                    while (jsonReader.hasNext()) {
//                        String objKey = jsonReader.readString();
//                        if ("type".equals(objKey)) {
//                            String value = jsonReader.readObject().toString();
//                            data.setType(value);
//                        } else if ("properties".equals(objKey)) {
//                            String value = jsonReader.readObject().toString();
//                            data.setData(value);
//                        } else if ("geometry".equals(objKey)) {
//                            String value = jsonReader.readObject().toString();
//                            data.setGeometry(value);
//                            GisGeometry geometry = JsonParser.getInstance().parseJson(value, GisGeometry.class);
//                            Double[][] array = geometry.getCoordinates()[0][0];
//                            data.setLatitude(array[0][1]);
//                            data.setLongitude(array[0][0]);
//                        }
//                    }
//                    jsonReader.endObject();
//                    dataCallback.onObject(data);
//                }
//                jsonReader.endArray();
//            }
//
//        }
//
//        jsonReader.endObject();

    }

//    public int writeJson(OutputStream out, GisInfoCity cityData, List<GisInfoData> data) {
//        int result = -1;
//
//        JSONWriter writer = new JSONWriter(new OutputStreamWriter(out));
//        writer.startObject();
//        writer.writeKey("type");
//        writer.writeValue(cityData.getType());
//        writer.writeKey("name");
//        writer.writeValue(cityData.getName());
//        writer.writeKey("crs");
//        writer.writeObject(JSONObject.parse(cityData.getCrs()));
//        writer.writeKey("features");
//        writer.startArray();
//        for (GisInfoData infoData : data) {
//            writer.startObject();
//            writer.writeKey("type");
//            writer.writeValue(infoData.getType());
//            writer.writeKey("properties");
//            JSONObject obs = getProperties(infoData.getData());
//            writerJsonObject(writer, obs);
////            writer.writeObject(obs);
//            writer.writeKey("geometry");
//            writer.writeObject(JSONObject.parse(infoData.getGeometry()));
//            writer.endObject();
//        }
//        writer.endArray();
//        writer.endObject();
//        try {
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    private void writerJsonObject(JSONWriter writer, JSONObject obs) {
//        writer.startObject();
//        Iterator<Map.Entry<String, Object>> iterator = obs.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, Object> entry = iterator.next();
//            writer.writeKey(entry.getKey());
//            writer.writeValue(entry.getValue());
//        }
//        writer.endObject();
//    }
//
//    private JSONObject ob;
//    private JSONObject obNew;
//
//    private JSONObject getProperties(String data) {
//        ob = JSON.parseObject(data);
//        if (obNew == null) {
//            obNew = new JSONObject(true);
//        } else {
//            obNew.clear();
//        }
//
//        for (AssetsReader.Properties value : AssetsReader.Properties.values()) {
//            String key = value.name().toLowerCase();
//            if (AssetsReader.Properties.MIANJI == value) {
//                if (ob.containsKey(key)) {
//                    obNew.put(value.getName(), Double.valueOf(ob.getString(key)));
//                } else if (ob.containsKey(value.getName())) {
//                    obNew.put(value.getName(), Double.valueOf(ob.getString(value.getName())));
//                } else {
//                    obNew.put(value.getName(), 0.0);
//                }
//
//            } else {
//                if (ob.containsKey(key)) {
//                    obNew.put(value.getName(), ob.get(key).toString());
//                } else if (ob.containsKey(value.getName())) {
//                    obNew.put(value.getName(), ob.get(value.getName()));
//                } else {
//                    obNew.put(value.getName(), "");
//                }
//            }
//        }
//
//        return obNew;
//    }
//
//
    public static interface ObjectCallback<T> {
        void onObject(T t);
    }


}

