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

public class MenuCoordinadorGUIController implements Initializable {

    Usuario usuarioActivo = new Usuario();
    ProgramaEducativo programaEducativo = new ProgramaEducativo();

    public void recibirParametros (Usuario usuario){
        usuarioActivo = usuario;
        ProgramaEducativoDAO programaEducativoDAO = new ProgramaEducativoDAO();
        programaEducativo = programaEducativoDAO.obtenerProgramaEducativodeUsuario(usuarioActivo.getCuentaUV());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void abrirRegistrarFechasdeTutoria(ActionEvent event) throws IOException{
        Stage stageMenuJefe = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/RegistrarFechasDeSesionDeTutoria.fxml").openStream());
        Scene scene = new Scene(root);
        stageMenuJefe.setScene(scene);
        stageMenuJefe.setTitle("Registrar fechas de tutorias");
        stageMenuJefe.alwaysOnTopProperty();
        stageMenuJefe.initModality(Modality.APPLICATION_MODAL);
        stageMenuJefe.show();
    }

    @FXML
    private void abrirModificarFechasdeTutoria(ActionEvent event) throws IOException{
        Stage stageMenuJefe = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/ModificarFechasDeSesionDeTutoria.fxml").openStream());
        Scene scene = new Scene(root);
        stageMenuJefe.setScene(scene);
        stageMenuJefe.setTitle("Modificar fechas de tutorias");
        stageMenuJefe.alwaysOnTopProperty();
        stageMenuJefe.initModality(Modality.APPLICATION_MODAL);
        stageMenuJefe.show();
    }

    @FXML
    private void abrirRegistrarFechasdeCierredeReporte(ActionEvent event) throws IOException {
        Stage stageMenuJefe = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/RegistrarFechasDeCierreParaLaEntregaDelReporte.fxml").openStream());
        Scene scene = new Scene(root);
        stageMenuJefe.setScene(scene);
        stageMenuJefe.setTitle("Registrar fechas de cierre de reporte");
        stageMenuJefe.alwaysOnTopProperty();
        stageMenuJefe.initModality(Modality.APPLICATION_MODAL);
        stageMenuJefe.show();
    }

    @FXML
    private void abrirModificarFechasdeCierredeReporte(ActionEvent event) throws IOException {
        Stage stageMenuJefe = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/ModificarFechaDeCierreParaLaEntregaDelReporte.fxml").openStream());
        Scene scene = new Scene(root);
        stageMenuJefe.setScene(scene);
        stageMenuJefe.setTitle("Modificar fecha de cierre de reporte");
        stageMenuJefe.alwaysOnTopProperty();
        stageMenuJefe.initModality(Modality.APPLICATION_MODAL);
        stageMenuJefe.show();
    }

    @FXML
    private void abrirRegistrarTutor(ActionEvent event) throws IOException {
        Stage stageMenuJefe = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/RegistrarTutorAcademicoGUI.fxml").openStream());
        Scene scene = new Scene(root);
        stageMenuJefe.setScene(scene);
        stageMenuJefe.setTitle("Registrar tutor");
        stageMenuJefe.alwaysOnTopProperty();
        stageMenuJefe.initModality(Modality.APPLICATION_MODAL);
        stageMenuJefe.show();
    }

    @FXML
    private void abrirConsultarTutorado(ActionEvent event) throws IOException{
        Stage stageMenuJefe = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/ConsultarEstudiante.fxml").openStream());
        Scene scene = new Scene(root);
        stageMenuJefe.setScene(scene);
        stageMenuJefe.setTitle("Consultar tutorados");
        stageMenuJefe.alwaysOnTopProperty();
        stageMenuJefe.initModality(Modality.APPLICATION_MODAL);
        stageMenuJefe.show();
    }

    @FXML
    private void abrirConsultarTutor(ActionEvent event) throws IOException, SQLException{
        Stage stageMenuTutor = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/CU32ConsultarTutorGUI.fxml").openStream());
        CU32ConsultarTutorGUIController  cu32ConsultarTutorGUIController = (CU32ConsultarTutorGUIController) loader.getController();
        cu32ConsultarTutorGUIController.recibirParametros(usuarioActivo, programaEducativo);
        Scene scene = new Scene(root);
        stageMenuTutor.setScene(scene);
        stageMenuTutor.setTitle("Consultar tutores");
        stageMenuTutor.alwaysOnTopProperty();
        stageMenuTutor.initModality(Modality.APPLICATION_MODAL);
        stageMenuTutor.show();
    }

    @FXML
    private void abrirConsultarReportesPorTutor(ActionEvent event) throws IOException, SQLException {
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
    private void abrirAsignarTutor(ActionEvent event) throws IOException, SQLException {
        Stage stageMenuCoordinador = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/CU35AsignarTutor.fxml").openStream());
        CU35AsignarTutorController  cu35AsignarTutorController = (CU35AsignarTutorController) loader.getController();
        cu35AsignarTutorController.recibirParametros(programaEducativo);
        Scene scene = new Scene(root);
        stageMenuCoordinador.setScene(scene);
        stageMenuCoordinador.setTitle("Aginar tutor a tutorado");
        stageMenuCoordinador.alwaysOnTopProperty();
        stageMenuCoordinador.initModality(Modality.APPLICATION_MODAL);
        stageMenuCoordinador.show();
    }
    
}
