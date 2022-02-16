import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AlgoGenController extends BorderPane {
    private static final String FXML_FILE = "algogen.fxml";

    private final Population population;

    private int actualGen;

    @FXML
    public MenuItem open;

    @FXML
    public MenuItem save;

    @FXML
    public MenuItem quit;

    @FXML
    public ImageView originalImage;

    @FXML
    public ImageView geneticImage;

    @FXML
    public Label nbGen;

    public AlgoGenController(Population population) throws IOException {
        actualGen = 0;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(FXML_FILE));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();

        this.population = population;
        originalImage.setImage(SwingFXUtils.toFXImage(population.getOriginalImage(), null));


        population.initPopulation(50, 20);
        Individual best = population.getBestOfThisGeneration();
        geneticImage.setImage(SwingFXUtils.toFXImage(best.getBufferedImage(), null));
    }

    @FXML
    @SuppressWarnings("unused") // used by FXML
    public void onNextGenButton(ActionEvent actionEvent) throws IOException, ExecutionException, InterruptedException {
        actualGen += 1;
        population.startNewRound();
        BufferedImage image = population.getBestOfThisGeneration().getBufferedImage();
        geneticImage.setImage(SwingFXUtils.toFXImage(image, null));
        nbGen.setText("Gen : " + actualGen);
    }

    @FXML
    @SuppressWarnings("unused") // used by FXML
    public void onRestartButton(ActionEvent actionEvent) throws IOException, ExecutionException, InterruptedException {
        actualGen = 0;
        population.initPopulation(50, 20);
        Individual best = population.getBestOfThisGeneration();
        geneticImage.setImage(SwingFXUtils.toFXImage(best.getBufferedImage(), null));
        nbGen.setText("Gen : " + actualGen);
    }

    @FXML
    @SuppressWarnings("unused") // used by FXML
    public void onStartStopButton(ActionEvent actionEvent) throws IOException, ExecutionException, InterruptedException {
        System.out.println("nop");
    }
}
