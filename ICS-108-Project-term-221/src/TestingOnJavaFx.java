import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestingOnJavaFx extends Application {

  int randomImageIndex = (int) (Math.random() * 5);

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create a pane to hold the image views
    StackPane stackPane = new StackPane();

    HBox hbox = new HBox(5);
    hbox.setAlignment(Pos.CENTER);
    hbox.setPadding(new Insets(50, 50, 50, 50));

    Image[] arrayOfImages = new Image[5];
    arrayOfImages[0] = new Image("file:images/images1.jpeg");
    arrayOfImages[1] = new Image("file:images/images2.jpeg");
    arrayOfImages[2] = new Image("file:images/images3.jpeg");
    arrayOfImages[3] = new Image("file:images/images4.jpeg");
    arrayOfImages[4] = new Image("file:images/images5.jpeg");

    // Image image = new Image(arrayOfImages[randomImageIndex]);
    ImageView imageView = new ImageView(arrayOfImages[randomImageIndex]);
    imageView.setFitHeight(400);
    imageView.setFitWidth(600);
    stackPane.getChildren().add(imageView);

    Button previousButton = new Button("previous");
    previousButton.setPrefWidth(100);
    hbox.getChildren().add(previousButton);
    previousButton.setOnAction(e -> {
      if (randomImageIndex > 0) {
        randomImageIndex--;
        imageView.setImage(arrayOfImages[randomImageIndex]);
      }

    });
    Button nextButton = new Button("next");
    nextButton.setPrefWidth(100);
    hbox.getChildren().add(nextButton);
    nextButton.setOnAction(e -> {
      if (randomImageIndex < (arrayOfImages.length - 1)) {
        randomImageIndex++;
        imageView.setImage(arrayOfImages[randomImageIndex]);
      }
    });

    Button slideshow = new Button("slide show");
    slideshow.setPrefWidth(100);
    hbox.getChildren().add(slideshow);
    slideshow.setOnAction(e -> {
      Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), (evt) -> {
        // boolean tracker = true;
        // while(tracker)
        if (randomImageIndex == 0) {
          for (int i = 0; i < arrayOfImages.length; i++) {
            imageView.setImage(arrayOfImages[randomImageIndex++]);
          }
        }else if (randomImageIndex == (arrayOfImages.length - 1)) {
          for (int i = 0; i < arrayOfImages.length; i++) {
            imageView.setImage(arrayOfImages[randomImageIndex--]);
          }
        }
        // else{
        //   for (int i = randomImageIndex; i < arrayOfImages.length; i++) {
        //     imageView.setImage(arrayOfImages[randomImageIndex++]);
        //   }
        // }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
      });


    BorderPane borderPane = new BorderPane();
    borderPane.setBottom(hbox);
    borderPane.setCenter(stackPane);

    Scene scene = new Scene(borderPane, 450, 100);
    primaryStage.setScene(scene); // Place the scene in the stage\

    primaryStage.setTitle("ShowImage"); // Set the stage title
    primaryStage.show(); // Display the stage
  }

  public static void main(String[] args) {
    launch(args);
  }
}
