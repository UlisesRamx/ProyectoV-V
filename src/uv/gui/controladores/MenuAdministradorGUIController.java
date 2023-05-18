package uv.gui.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuAdministradorGUIController implements Initializable {

    @FXML
    private ImageView imgFondo;
    @FXML
    private Button btnCosultarOferta;
    @FXML
    private Button btnAsignarDocentes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void abrirOfertaAcademica(ActionEvent event) throws IOException {
        Stage stageOfertaAcademica = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/CU37ConsultarOfertaAcademicaGUI.fxml").openStream());
        Scene scene = new Scene(root);
        stageOfertaAcademica.setScene(scene);
        stageOfertaAcademica.setTitle("Oferta Academica");
        stageOfertaAcademica.alwaysOnTopProperty();
        stageOfertaAcademica.initModality(Modality.APPLICATION_MODAL);
        stageOfertaAcademica.show();
    }

    @FXML
    private void abrirAsignarDocentesExperiencias(ActionEvent event) throws IOException {
        Stage stageAsignarDocenteExperiencia = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/CU46AsignarEE.fxml").openStream());
        Scene scene = new Scene(root);
        stageAsignarDocenteExperiencia.setScene(scene);
        stageAsignarDocenteExperiencia.setTitle("Asignar docentes a experiencias");
        stageAsignarDocenteExperiencia.alwaysOnTopProperty();
        stageAsignarDocenteExperiencia.initModality(Modality.APPLICATION_MODAL);
        stageAsignarDocenteExperiencia.show();
    }
    
}
