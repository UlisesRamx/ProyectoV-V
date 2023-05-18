package TutoriasApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class TutoriasApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/uv/gui/interfaces/InicioSesionGUI.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/uv/fei/tutorias/img/logoUV.png"));
        stage.setTitle("Iniciar sesion");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}