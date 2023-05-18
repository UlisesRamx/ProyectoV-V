package uv.gui.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.PeriodoDAO;
import uv.fei.tutorias.bussinesslogic.SesionTutoriaDAO;
import uv.fei.tutorias.domain.Periodo;
import uv.fei.tutorias.domain.ProgramaEducativo;
import uv.fei.tutorias.domain.SesionTutoria;
import uv.fei.tutorias.domain.Usuario;
import uv.mensajes.Alertas;
import org.apache.log4j.Logger;


public class ModificarFechasDeSesionDeTutoriaController implements Initializable {

    @FXML
    private DatePicker dpPrimeraSesion;
    @FXML
    private DatePicker dpSegundaSesion;
    @FXML
    private DatePicker dpTerceraSesion;
    @FXML
    private AnchorPane panelModificarSesionTutoria;
    @FXML
    private TextField tfPeriodoActivo;
    @FXML
    private Text txtPrimeraTutoria;
    @FXML
    private Text txtSegundaTutoria;
    @FXML
    private Text txtTerceraTutoria;

    private Usuario usuarioActivo;
    private ProgramaEducativo programaEducativoActivo;
    Alertas alertas = new Alertas();
    final static Logger log = Logger.getLogger(ModificarFechasDeSesionDeTutoriaController.class);

    public void recibirParametros(Usuario usuarioRecibido, ProgramaEducativo programaEducativo) throws SQLException{
        usuarioActivo = usuarioRecibido;
        programaEducativoActivo = programaEducativo;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PeriodoDAO periodoDao = new PeriodoDAO();
        Periodo periodo = new Periodo();
        try {
            periodo = periodoDao.consultarPeriodoActivo();
            tfPeriodoActivo.setText(periodo.getFechaInicio()+ " - " + periodo.getFechaFin());
            tfPeriodoActivo.setEditable(false);
        } catch (SQLException ex) {
            alertas.mostrarAlertaErrorConexionDB();
            log.warn(ex);
        }
    }    

    @FXML
    private void guardarInformacion(ActionEvent event) throws SQLException {
        modificarSesion(dpPrimeraSesion, txtPrimeraTutoria);
        modificarSesion(dpSegundaSesion, txtSegundaTutoria);
        modificarSesion(dpTerceraSesion, txtTerceraTutoria);
    }
    
    public void modificarSesion(DatePicker fechaTutoria, Text numeroTutoria) throws SQLException{
        PeriodoDAO periodoDao = new PeriodoDAO();
        Periodo periodo = new Periodo();
        periodo = periodoDao.consultarPeriodoActivo();
        int idPeriodo = periodo.getIdPeriodo();
        SesionTutoriaDAO SesionTutoriaDAO = new SesionTutoriaDAO();
        SesionTutoria nuevaSesionTutoria = new SesionTutoria();
        String fecha = fechaTutoria.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        nuevaSesionTutoria.setFechaTutoria(fecha);
        String numTutoria = numeroTutoria.getText();
        try{
            SesionTutoriaDAO.actualizarFechasDeSesionTutoria(nuevaSesionTutoria, idPeriodo, numTutoria);
        }catch(SQLException e){
            alertas.mostrarAlertaErrorConexionDB();
        }
    }

    @FXML
    private void cancelarModificacion(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
