/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uv.gui.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import uv.fei.tutorias.bussinesslogic.SolucionesProblematicaAcademicaDAO;
import uv.fei.tutorias.domain.Problematica;

/**
 * FXML Controller class
 *
 * @author Valea
 */
public class CU21RegistrarSolucionAProblematicaAcademicaController implements Initializable {

    @FXML
    private TextArea txtSolucon;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnCerrar;
    private TextField btnProblematica;
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
    Problematica problematicaAcademico=null;

    
    final static Logger log = Logger.getLogger(CU21RegistrarSolucionAProblematicaAcademicaController.class);
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    private void clickEvento(ActionEvent event) {

    }
    
    private void llenarCampos(Problematica problematica){
       
       txtProblematica.setText(problematica.getTitulo());
       txtDescripcion.setText(problematica.getDescripcion());
       txtDocente.setText(problematica.getDocente());
       txtEE.setText(problematica.getExperiencia());
       txtFecha.setText(problematica.getFechatutoria());
      
       
    }

    void recibirParametros(Problematica problematica) {
       llenarCampos(problematica);
       problematicaAcademico=problematica;
    }
    
  
    @FXML
    private void registrarSolucion(ActionEvent event) throws IOException {
        
        
       if(!txtSolucon.getText().isEmpty()){
           String solucion= txtSolucon.getText();
           int idproblematica=problematicaAcademico.getIdproblemaacademica();
           SolucionesProblematicaAcademicaDAO instance = new SolucionesProblematicaAcademicaDAO();
           try {
               int resultado=instance.registrarSolucion(solucion,idproblematica);
               if (resultado==1){
                    JOptionPane.showMessageDialog(null, "Solución agregada correctamente");
                   
               }
           } catch (SQLException ex) {
                avisoSinConexion();    
                log.warn(ex);
           }
       } else {
            JOptionPane.showMessageDialog(null, "Campo de Solución vacio");
       }
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
