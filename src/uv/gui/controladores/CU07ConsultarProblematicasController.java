
package uv.gui.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import uv.fei.tutorias.bussinesslogic.ProblematicaAcademicaDAO;
import uv.fei.tutorias.domain.Problematica;
import uv.fei.tutorias.domain.ProblematicaReporte;
import org.apache.log4j.Logger;
import uv.fei.tutorias.domain.ProgramaEducativo;
import uv.fei.tutorias.domain.Usuario;
import uv.mensajes.Alertas;

/**
 * FXML Controller class
 *
 * @author Valea
 */
public class CU07ConsultarProblematicasController implements Initializable {

    @FXML
    private TableView tblProblematicas;
    @FXML
    private TableColumn colIdProblematica;
    @FXML
    private TableColumn colFecha;
    @FXML
    private TableColumn colTitulo;
    @FXML
    private TableColumn colExperiencia;
    @FXML
    private Button btnCerrar;
    @FXML
    private Label lblSugerencia;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    
    ProgramaEducativo programaEducativo;
    Usuario usuarioActivo;
    Alertas alertas = new Alertas();
    final static Logger log = Logger.getLogger(CU07ConsultarProblematicasController.class); 

    public void recibirParametros(Usuario usuarioRecibido, ProgramaEducativo programaRecibido) {
        usuarioActivo = usuarioRecibido;
        programaEducativo = programaRecibido;
        try {
            this.establecerListaProblematicas();
        } catch (SQLException ex) {
            log.warn(ex);
        }
    }

     private final ListChangeListener<ProblematicaReporte> selectorTablaProblematicas = new ListChangeListener<ProblematicaReporte>() {
         @Override
        public void onChanged(ListChangeListener.Change<? extends ProblematicaReporte> change) {
        }
     }; 
     
        public ProblematicaReporte getTablaProblematicaSeleccionada() {
        ProblematicaReporte problematicaReporte =null;
        if (tblProblematicas != null) {
            List<ProblematicaReporte> tabla = tblProblematicas.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                problematicaReporte = tabla.get(0);
            }
        }
        return problematicaReporte;
    }
    
   private void establecerListaProblematicas() throws SQLException {
        int idprograma=programaEducativo.getIdProgramaEducativo();
        String cuentauv= usuarioActivo.getCuentaUV();
        colIdProblematica.setCellValueFactory(new PropertyValueFactory<ProblematicaReporte, String>("idProblematicaAcademica"));
        colExperiencia.setCellValueFactory(new PropertyValueFactory<ProblematicaReporte, String>("experiencia"));
        colFecha.setCellValueFactory(new PropertyValueFactory<ProblematicaReporte, String>("fecha"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<ProblematicaReporte, String>("titulo"));
        ArrayList<ProblematicaReporte> problematicaReportes;
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        problematicaReportes = problematicaAcademicaDAO.consultarTodasLasProblematicasPorProgramaEducativoCuenta(idprograma, cuentauv);
        ObservableList<ProblematicaReporte> problematicaAcademicaObservableList = FXCollections.observableArrayList();
        if(!problematicaReportes.isEmpty()) {
            for (ProblematicaReporte problematicaReporte : problematicaReportes) {
                problematicaAcademicaObservableList.add(problematicaReporte);
            }
        }else {
           this.avisoSinConexion();
        }
        tblProblematicas.setItems(problematicaAcademicaObservableList);
    }
     
    private void avisoSinConexion(){
        alertas.mostrarAlertaErrorConexionDB();
    } 
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ObservableList<ProblematicaReporte> problematicaReportes = tblProblematicas.getSelectionModel().getSelectedItems();
        problematicaReportes.addListener(selectorTablaProblematicas);
    }    

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void modificarProblematica(ActionEvent event) {
        ProblematicaReporte problematica=this.getTablaProblematicaSeleccionada();
        if (problematica!=null){
            abrirProblematica(problematica);
        
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione al menos una Reporte");
        }
    }
    
    private void abrirProblematica(ProblematicaReporte problematica){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/uv/gui/interfaces/CU07ModificarProblematica.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Modificar Problemática");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            CU07ModificarProblematicaController controlador = (CU07ModificarProblematicaController) fxmlLoader.getController();
            controlador.recibirParametros(problematica);
            stage.showAndWait();
        } catch (IOException ex) {
            log.warn(ex);
        }
    
    }

    @FXML
    private void eliminarProblematica(ActionEvent event) {
        ProblematicaReporte problematica=this.getTablaProblematicaSeleccionada();
        if (problematica!=null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmación");
            alert.setContentText("¿Estas seguro de confirmar la acción?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
            System.out.print("Has pulsado en aceptar");
            ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
                try {
                    int resultado1= problematicaAcademicaDAO.eliminarProblematicaSesiones(problematica);
                    int resultado= problematicaAcademicaDAO.eliminarProblematica(problematica);
                    if(resultado1+resultado==2)
                        JOptionPane.showMessageDialog(null, "Problemática eliminada correctamente.");
                    
                } catch (SQLException ex) {
                    avisoSinConexion();
                    log.warn(ex);
                }
            }
        
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione al menos una Reporte");
        }
    }
    
    

      
    
}
