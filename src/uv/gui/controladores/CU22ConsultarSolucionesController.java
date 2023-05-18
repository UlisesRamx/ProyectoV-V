/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uv.gui.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import uv.fei.tutorias.bussinesslogic.SolucionesProblematicaAcademicaDAO;
import uv.fei.tutorias.domain.Problematica;
import uv.fei.tutorias.domain.ProgramaEducativo;
import uv.fei.tutorias.domain.Usuario;

/**
 * FXML Controller class
 *
 * @author Valea
 */
public class CU22ConsultarSolucionesController implements Initializable {

    @FXML
    private TableView  tblProbematicas;
    @FXML
    private TableColumn<?, ?> cFecha;
    @FXML
    private TableColumn<?, ?> cProblematica;
    @FXML
    private TableColumn<?, ?> cExperiencia;
    @FXML
    private TableColumn<?, ?> cDocente;
    @FXML
    private Button btnConsultar;
    final static Logger log = Logger.getLogger(CU07ConsultarProblematicasController.class); 
    @FXML
    private Button btnCerrar;

    ProgramaEducativo programaEducativo;
    Usuario usuarioActivo;
    
     public void recibirParametros(Usuario usuarioRecibido, ProgramaEducativo programaRecibido) throws SQLException {
        usuarioActivo = usuarioRecibido;
        programaEducativo = programaRecibido;
        cargarDatosTabla();
    }
    
    private void cargarDatosTabla(){
        SolucionesProblematicaAcademicaDAO instance = new SolucionesProblematicaAcademicaDAO();
        int idprograma=programaEducativo.getIdProgramaEducativo();

        try {
            ArrayList<Problematica> problematicas= instance.consultarSoluciones(idprograma);
            if(!problematicas.isEmpty()){
            
                this.cFecha.setCellValueFactory(new PropertyValueFactory("fechatutoria"));
                this.cProblematica.setCellValueFactory(new PropertyValueFactory("titulo"));
                this.cExperiencia.setCellValueFactory(new PropertyValueFactory("experiencia"));
                this.cDocente.setCellValueFactory(new PropertyValueFactory("docente"));
                ObservableList<Problematica> tablaproblematicas = FXCollections.observableArrayList() ;
                
            for (Problematica problema1: problematicas){
                      tablaproblematicas.add(problema1); 
             }
                tblProbematicas.setItems(tablaproblematicas);
                
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sin problemáticas");
                alert.setHeaderText("No se encontraron problemáticas registradas");
                alert.showAndWait();
            
            }
            
            
        } catch (SQLException ex) {
             avisoSinConexion();
             log.warn(ex);
        }
    
    
    }
    
        private void avisoSinConexion(){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Sin conexión con la Base de Datos");
           alert.setHeaderText("Intente más tarde. Por favor.");
           alert.showAndWait();
    
    } 
        
          private final ListChangeListener<Problematica> selectorTablaReporte=new ListChangeListener<Problematica> (){
            @Override
            public void onChanged (ListChangeListener.Change<? extends Problematica> c){
            //getDatosReporteSeleccionado();
       }
    
    };
            
       public Problematica getTablaProblematica (){
       Problematica problematicaSeleccionada=null; 
        if (tblProbematicas != null){
            List<Problematica> tabla=tblProbematicas.getSelectionModel ().getSelectedItems ();
            if (tabla.size ()== 1){
              problematicaSeleccionada=tabla.get(0);
            } 
          }
        return problematicaSeleccionada;
     } 
       
          private void abrirProblematica(Problematica problematica){
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/uv/gui/interfaces/CU22ConsultarSolucion.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Registrar Solución");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);


            CU22ConsultarSolucionController controlador = (CU22ConsultarSolucionController) fxmlLoader.getController();
            controlador.recibirParametros(problematica);

            stage.showAndWait();

        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex.getMessage());
        }
    
    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosTabla();
    }    

    @FXML
    private void consultarSolucion(ActionEvent event) {
         Problematica problematica= this.getTablaProblematica();
        if (problematica!=null){
             abrirProblematica(problematica);
        
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione al menos una Reporte");
        }
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
