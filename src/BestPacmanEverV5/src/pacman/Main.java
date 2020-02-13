package pacman;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
    @Override
    public void start(Stage theStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/Start.fxml"));
        theStage.setTitle( "Pacman" );
        theStage.setResizable(false);
        Scene theScene = new Scene( root, 1225, 600);
        theStage.setScene( theScene );
        theScene.getStylesheets().add("./cssStyle/StartPageStyle.css");
        theStage.show();
    }

    /**
     * @author YimingLI 20031525
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}