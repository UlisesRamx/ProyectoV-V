package uv.gui.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

public class RegistrarFechasDeCierreParaLaEntregaDelReporteController implements Initializable {

    

    Stage stage;
    @FXML
    private DatePicker dpPrimerReporte;

    @FXML
    private DatePicker dpSegundoReporte;

    @FXML
    private DatePicker dpTercerReporte;

    @FXML
    private AnchorPane panelFechaEntregaReporte;

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
            //lblPeriodoActivo.setEnabled(false);
            
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarFechasDeSesionDeTutoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void cancelarRegistro(ActionEvent event) {
        /*Optional<ButtonType> respuesta = Alertas.mostrarAlertaBoton(Alert.AlertType.ERROR, "Cancelar", "Confirmar cancelar registro",
                "¿Esta seguro de que desea cancelar el registro?");
        if (respuesta.get() == ButtonType.OK) {
                stage = (Stage) panelFechaEntregaReporte.getScene().getWindow();
                stage.close();
        }*/
    }

    @FXML
    private void enviarInformacion(ActionEvent event) throws SQLException {
        registrarSesion(dpPrimerReporte, txtPrimeraTutoria);
        registrarSesion(dpSegundoReporte, txtSegundaTutoria);
        registrarSesion(dpTercerReporte, txtTerceraTutoria);
        
    }
    
    public void registrarSesion(DatePicker fechaReporte, Text txtPrimeraTutoria) throws SQLException{
        PeriodoDAO periodoDao = new PeriodoDAO();
        Periodo periodo = new Periodo();
        periodo = periodoDao.consultarPeriodoActivo();
        int idPeriodo = periodo.getIdPeriodo();
        
        SesionTutoriaDAO SesionTutoriaDAO = new SesionTutoriaDAO();
        SesionTutoria nuevaSesionTutoria = new SesionTutoria();
        String fecha = fechaReporte.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        nuevaSesionTutoria.setFechaCierreReportes(fecha);
        
        String numTutoria = txtPrimeraTutoria.getText();
        
        try{
        SesionTutoriaDAO.registrarFechaDeCierreDeReporte(nuevaSesionTutoria, idPeriodo,numTutoria);
        }catch(SQLException e){
           // mostrarAlertaErrorConexionDB();
        }
        
    }
    
    
}
