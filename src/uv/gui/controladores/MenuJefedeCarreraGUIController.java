package uv.gui.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.ProgramaEducativoDAO;
import uv.fei.tutorias.domain.ProgramaEducativo;
import uv.fei.tutorias.domain.Usuario;

public class MenuJefedeCarreraGUIController implements Initializable {
    Usuario usuarioActivo;
    ProgramaEducativo programaEducativo;

    public void recibirParametros (Usuario usuario){
        usuarioActivo = usuario;
        ProgramaEducativoDAO programaEducativoDAO = new ProgramaEducativoDAO();
        programaEducativo = programaEducativoDAO.obtenerProgramaEducativodeUsuario(usuarioActivo.getCuentaUV());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void abrirConsultarReportePorTutor(ActionEvent event) throws IOException, SQLException {
        Stage stageMenuJefe = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/CU09ConsultarReportesporTutor.fxml").openStream());
        CU09ConsultarReportesporTutorController  cu09ConsultarReportesporTutorController = (CU09ConsultarReportesporTutorController) loader.getController();
        cu09ConsultarReportesporTutorController.recibirParametros(usuarioActivo, programaEducativo);
        Scene scene = new Scene(root);
        stageMenuJefe.setScene(scene);
        stageMenuJefe.setTitle("Consultar reporte por tutor");
        stageMenuJefe.alwaysOnTopProperty();
        stageMenuJefe.initModality(Modality.APPLICATION_MODAL);
        stageMenuJefe.show();
    }

    @FXML
    private void abrirConsultarSoluciones(ActionEvent event) throws IOException, SQLException {
        Stage stageMenuJefe = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/CU21ConsultarProblematicas.fxml").openStream());
        CU21ConsultarProblematicasController CU21ConsultarProblematicasController = (CU21ConsultarProblematicasController) loader.getController();
        CU21ConsultarProblematicasController.recibirParametros(usuarioActivo, programaEducativo);
        Scene scene = new Scene(root);
        stageMenuJefe.setScene(scene);
        stageMenuJefe.setTitle("Modificar / eliminar problematicas");
        stageMenuJefe.alwaysOnTopProperty();
        stageMenuJefe.initModality(Modality.APPLICATION_MODAL);
        stageMenuJefe.show();
    }
}
