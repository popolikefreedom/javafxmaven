import com.cmq.syk.ItemConst;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.Controller;

import java.io.IOException;

public class HelloFX extends Application {


    @Override
    public void start(Stage stage) {
//        String javaVersion = System.getProperty("java.version");
//        String javafxVersion = System.getProperty("javafx.version");
//        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
//        Scene scene = new Scene(new StackPane(l), 640, 480);
//        stage.setScene(scene);
//        stage.show();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            ChoiceBox<String> box = (ChoiceBox) root.lookup("#id_rarity");
            if (box != null) {
                box.getItems().addAll(ItemConst.CN_RARITY_IMMORTAL, ItemConst.CN_RARITY_MYTHICAL);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 600, 550));
        stage.show();
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