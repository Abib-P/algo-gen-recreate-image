import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App extends Application {

    public static App AppInstance;

    private Stage stage;

    private AlgoGenController algoGenController;

    public static void launchApp() throws IOException {
        Platform.startup(() -> {
        });

        if (AppInstance == null) {
            AppInstance = new App();

            Platform.runLater(() -> {
                Stage stage = new Stage();

                stage.sceneProperty().addListener((observableValue, scene, newScene) -> {
                    stage.setMinWidth(newScene.getWidth());
                    stage.setMinHeight(newScene.getHeight());
                });

                try {
                    AppInstance.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private App() throws IOException {
        super();
        Image image = new Image(new File("src/main/resources/test.png").toURI().toString());
        PolygonFactory polygonFactory = new PolygonFactory(image.getHeight(), image.getWidth());
        ImageComparator imageComparator = new ImageComparator();
        BufferedImage bufferedImage = ImageIO.read(new File("src/main/resources/test.png"));
        Population population = new Population(polygonFactory, bufferedImage, imageComparator);
        algoGenController = new AlgoGenController(population);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        Scene mainScene = new Scene(algoGenController);
        this.stage.setScene(mainScene);


        this.stage.setTitle("Biomim");

        this.stage.show();
    }
}
