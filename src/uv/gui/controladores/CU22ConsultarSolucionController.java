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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
public class CU22ConsultarSolucionController implements Initializable {

    @FXML
    private TextField txtEE;
    @FXML
    private TextField txtDocente;
    @FXML
    private TextField txtFecha;
    @FXML
    private TextField txtProblematica;
    @FXML
    private TextField txtSolucon;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnModificar;
    @FXML
    private TextField txtDescripcion;
    Problematica problematicaAcademico=null;

    /**
     * Initializes the controller class.
     */
    final static Logger log = Logger.getLogger(CU22ConsultarSolucionController.class); 
    @FXML
    private Button btnCerrar;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void guardarCambios(ActionEvent event) {
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

    @FXML
    private void modificarSolucion(ActionEvent event) {
         txtSolucon.setDisable(false);
         btnGuardar.setVisible(true);
    }

    void recibirParametros(Problematica problematica) {
            problematicaAcademico=problematica;    
            llenarCampos();
    }
    private void avisoSinConexion(){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Sin conexión con la Base de Datos");
           alert.setHeaderText("Intente más tarde. Por favor.");
           alert.showAndWait();
    
    }
    private void llenarCampos(){
       
       txtProblematica.setText(problematicaAcademico.getTitulo());
       txtDescripcion.setText(problematicaAcademico.getDescripcion());
       txtDocente.setText(problematicaAcademico.getDocente());
       txtEE.setText(problematicaAcademico.getExperiencia());
       txtFecha.setText(problematicaAcademico.getFechatutoria());
       txtSolucon.setText(problematicaAcademico.getSolucion());
      
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
