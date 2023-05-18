/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uv.gui.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import uv.fei.tutorias.bussinesslogic.ProblematicaAcademicaDAO;
import uv.fei.tutorias.domain.ProblematicaReporte;

/**
 * FXML Controller class
 *
 * @author Valea
 */
public class CU07ModificarProblematicaController implements Initializable {

    @FXML
    private TextField txtEE;
    @FXML
    private TextField txtDocente;
    @FXML
    private TextField txtFecha;
    @FXML
    private TextField txtProblematica;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private Button btnGuardar;
    ProblematicaReporte problematicaAcademico=null;
    
    final static Logger log = Logger.getLogger(CU07ModificarProblematicaController.class); 
    @FXML
    private Button btnCerrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void guardarCambios(ActionEvent event) {
        if(!txtProblematica.getText().isEmpty() && !txtDescripcion.getText().isEmpty() ){
         problematicaAcademico.setTitulo(txtProblematica.getText());
         problematicaAcademico.setDescripcion(txtDescripcion.getText());  
         ProblematicaAcademicaDAO instance = new ProblematicaAcademicaDAO();
           try {
               int resultado=instance.actualizarProblematica(problematicaAcademico);
               if (resultado==1){
                    JOptionPane.showMessageDialog(null, "Cambios realizados correctamente");
                   
               }
           } catch (SQLException ex) {
                avisoSinConexion();      
                log.warn(ex);
           }
       } else {
            JOptionPane.showMessageDialog(null, "Campos Vacios");
       }
    }
    
       private void llenarCampos(ProblematicaReporte problematica){
       
       txtProblematica.setText(problematica.getTitulo());
       txtDescripcion.setText(problematica.getDescripcion());
       txtDocente.setText(problematica.getNombreDocente());
       txtEE.setText(problematica.getExperiencia());
       txtFecha.setText(problematica.getFecha());
      
       
    }

    void recibirParametros(ProblematicaReporte problematica) {
        
     llenarCampos(problematica);
     problematicaAcademico=problematica;
     
}
    
         private void avisoSinConexion(){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Sin conexión con la Base de Datos");
           alert.setHeaderText("Intente más tarde. Por favor.");
           alert.showAndWait();
    
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
