import com.cmq.syk.Item;
import com.cmq.syk.ItemConst;
import com.cmq.syk.ItemOption;
import com.cmq.syk.ItemPrice;
import com.cmq.syk.util.JsonStream;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class HelloFX extends Application {

    ListView<Item> listView;

    private Button c5RateButton;
    private Button buffRateButton;

    private Button searchButton;

    private ChoiceBox<String> rarityBox;
    private ChoiceBox<String> qualityBox;
    private ChoiceBox<String> typeBpx;

    private ItemOption itemOption;

    @Override
    public void start(Stage stage) {
        itemOption = new ItemOption();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            rarityBox = (ChoiceBox) root.lookup("#id_rarity");
            qualityBox = (ChoiceBox) root.lookup("#id_quality");
            typeBpx = (ChoiceBox) root.lookup("#id_type");
            listView = (ListView<Item>) root.lookup("#id_list");
            c5RateButton = (Button) root.lookup("#id_c5_rate");
            buffRateButton = (Button) root.lookup("#id_buff_rate");
            searchButton = (Button) root.lookup("#id_search");
            initListView(listView);
            if (rarityBox != null) {
                rarityBox.getItems().addAll(ItemOption.RARITY_OPTION);
                rarityBox.setValue(ItemOption.RARITY_OPTION[0]);
            }

            if (qualityBox != null) {
                qualityBox.getItems().addAll(ItemOption.QUALITY_OPTION);
                qualityBox.setValue(ItemOption.QUALITY_OPTION[0]);
            }

            if (typeBpx != null) {
                typeBpx.getItems().addAll(ItemOption.TYPE_OPTION);
                typeBpx.setValue(ItemOption.TYPE_OPTION[0]);
            }

            c5RateButton.setOnAction(actionEvent -> {
                System.out.println("setOnAction1");
                freshList(1);
            });

            buffRateButton.setOnAction(actionEvent -> {
                System.out.println("setOnAction2");
                freshList(2);
            });

            searchButton.setOnAction(actionEvent -> {
                System.out.println("searchButton");
                search();
            });

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        stage.setTitle("人脸识别");
        stage.setScene(new Scene(root, 600, 550));
        stage.show();
    }

    private void search() {
        String filePrefix = "/Users/shayankuan/Documents/gopath/src/cmqqq/price";
        String fileSuffix = ".txt";

        String rarityValue = rarityBox.getValue();
        String typeValue = typeBpx.getValue();
        String qualityValue = qualityBox.getValue();

        int rarityIndex = itemOption.getValueIndex(3, rarityValue);
        int typeIndex = itemOption.getValueIndex(2, typeValue);
        int qualityIndex = itemOption.getValueIndex(1, qualityValue);

        File file = new File(filePrefix + String.valueOf(qualityIndex) + String.valueOf(typeIndex) + String.valueOf(rarityIndex) + fileSuffix);
        try {
            FileInputStream in = new FileInputStream(file);
            JsonStream.getInstance().readJsonArray(in, new JsonStream.ObjectCallback<List<Item>>() {
                @Override
                public void onObject(List<Item> items) {
                    ObservableList<Item> list = FXCollections.observableArrayList();
                    list.addAll(items);
                    listView.setItems(list);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initListView(ListView<Item> listView) {
        listView.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>() {
            @Override
            public ListCell<Item> call(ListView<Item> itemListView) {
                ListCell<Item> listCell = new ListCell<>() {
                    @Override
                    protected void updateItem(Item item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty == false) {
                            VBox vBox = new VBox();
                            Label name = new Label(item.itemName);

                            ItemPrice steamPrice = item.priceMap.get(ItemConst.TAG_STEAM);
                            Button steam = new Button("steam :" + steamPrice.price);
                            steam.setOnAction(actionEvent -> {
                                Runtime runtime = Runtime.getRuntime();
                                try {
                                    runtime.exec("open " + steamPrice.url);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            vBox.getChildren().addAll(name, steam);


                            ItemPrice c5Price = item.priceMap.get(ItemConst.TAG_C5);
                            if (c5Price != null){
                                Button c5 = new Button("c5 :" + c5Price.price + ", rate :" + c5Price.rate);
                                c5.setOnAction(actionEvent -> {
                                    Runtime runtime = Runtime.getRuntime();
                                    try {
                                        runtime.exec("open " + c5Price.url);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                                vBox.getChildren().add(c5);
                            }

                            ItemPrice buffPrice = item.priceMap.get(ItemConst.TAG_BUFF);
                            if (buffPrice != null){
                                Button buff = new Button("buff :" + buffPrice.price + ", rate :" + buffPrice.rate);
                                buff.setOnAction(actionEvent -> {
                                    Runtime runtime = Runtime.getRuntime();
                                    try {
                                        runtime.exec("open " + buffPrice.url);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                                vBox.getChildren().add(buff);
                            }

                            this.setGraphic(vBox);

                        }
                    }
                };

                return listCell;
            }
        });
    }

    private int sortIndex;
    private List<Item> itemsList;

    private void freshList(int sortIndex) {
        String tag = sortIndex == 1 ? ItemConst.TAG_C5 : ItemConst.TAG_BUFF;
        listView.getItems().sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                int result = 0;
                ItemPrice price1 = o1.priceMap.get(tag);
                ItemPrice price2 = o2.priceMap.get(tag);
                if (price1 == null && price2 != null) {
                    return 1;
                } else if (price2 == null && price1 != null) {
                    return -1;
                } else {
                    if (price1 != null) {
                        return price1.rate > price2.rate ? -1 : 1;
                    }
                }

                return result;
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

    private void initController() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(location);
//        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
//        Parent root = fxmlLoader.load();
//        fxmlLoader.getController()
    }

}