
package uv.gui.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import uv.fei.tutorias.bussinesslogic.TutorDAO;
import uv.fei.tutorias.domain.ProgramaEducativo;
import uv.fei.tutorias.domain.Tutor;
import uv.fei.tutorias.domain.Usuario;
import uv.mensajes.Alertas;

public class RegistrarTutorAcademicoGUIController implements Initializable {

    @FXML
    private Button btGuardar;

    @FXML
    private TextField tfApellidoMaterno;

    @FXML
    private TextField tfApellidoPaterno;

    @FXML
    private TextField tfCorreo;

    @FXML
    private TextField tfCuenta;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPassword;

    Alertas alertas = new Alertas();

    @FXML
    private AnchorPane panelTutor;

    final static Logger log = Logger.getLogger(RegistrarTutorAcademicoGUIController.class);
    private Usuario usuarioActivo;
    private ProgramaEducativo programaEducativoActivo;
    
    public void recibirParametros(Usuario usuarioRecibido, ProgramaEducativo programaEducativo) throws SQLException{
        usuarioActivo = usuarioRecibido;
        programaEducativoActivo = programaEducativo;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void enviarInformacion(ActionEvent event) {
        if (tfCuenta.getText().isEmpty() || tfNombre.getText().isEmpty() || tfApellidoPaterno.getText().isEmpty() || tfApellidoMaterno.getText().isEmpty() || tfCorreo.getText().isEmpty() || tfPassword.getText().isEmpty() || tfCuenta.getText().isEmpty()) {
            alertas.mostrarAlertaCamposVacios();
            } else {
                TutorDAO nuevoTutorDAO = new TutorDAO();
                Tutor nuevoTutor = new Tutor();
                nuevoTutor.setCuentaUV(tfCuenta.getText());
                nuevoTutor.setNombreTutor(tfNombre.getText());
                nuevoTutor.setApellidoPaternoTutor(tfApellidoPaterno.getText());
                nuevoTutor.setApellidoMaternoTutor(tfApellidoMaterno.getText());
                nuevoTutor.setCorreo(tfCorreo.getText());
                nuevoTutor.setPassword(tfPassword.getText());
                try {
                    int registro = nuevoTutorDAO.registrarTutor(nuevoTutor);
                    if (registro > 0) {
                        alertas.mostrarAlertaRegistroExitoso();
                    }
                } catch (SQLException e) {
                    alertas.mostrarAlertaErrorConexionDB();
                    log.warn(e);
            }
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
   
}