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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import uv.fei.tutorias.bussinesslogic.ReporteTutorDAO;
import uv.fei.tutorias.domain.*;
import uv.mensajes.Alertas;

public class CU09ConsultarReportesporTutorController implements Initializable {

    @FXML
    private TableView tblReportes;
    @FXML
    private TableColumn<?, ?> cPeriodo;
    @FXML
    private TableColumn<?, ?> cTutoria;
    @FXML
    private TableColumn<?, ?> cTutor;
    @FXML
    private TableColumn<?, ?> cFecha;
    @FXML
    private TextField txtNombreTutor;
    
    final static Logger log = Logger.getLogger(CU09ConsultarReportesporTutorController.class); 
    @FXML
    private Button btnCerrar;
    
    ProgramaEducativo programaEducativo;
    Usuario usuarioActivo;
    Alertas alertas = new Alertas();
    
     public void recibirParametros(Usuario usuarioRecibido, ProgramaEducativo programaRecibido) throws SQLException {
        usuarioActivo = usuarioRecibido;
        programaEducativo = programaRecibido;
         this.cargarDatosReportes();
     }

    private void cargarDatosReportes(){
        try{
         ReporteTutorDAO instance = new  ReporteTutorDAO();
         ArrayList<ReporteTutor> reportesTutor= new ArrayList<>();
         reportesTutor = instance.consultarReportesTutor(programaEducativo.getIdProgramaEducativo());

         if (!reportesTutor.isEmpty()){

         this.cPeriodo.setCellValueFactory(new PropertyValueFactory("Periodo"));
         this.cTutoria.setCellValueFactory(new PropertyValueFactory("numTutoria"));
         this.cTutor.setCellValueFactory(new PropertyValueFactory("nombreTutor"));
         this.cFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
         ObservableList<ReporteTutor> reportes = FXCollections.observableArrayList() ;
         for (ReporteTutor reporte1 : reportesTutor){
             reportes.add(reporte1); 
         }

          tblReportes.setItems(reportes); 
          
         }
         else{
            alertas.mostrarAlertaNoHayReportes();
         }
        }catch (SQLException ex) {
            avisoSinConexion();
            log.warn(ex);
        }
    }
      private final ListChangeListener<ReporteTutor> selectorTablaReporte=new ListChangeListener<ReporteTutor> (){
            @Override
            public void onChanged (ListChangeListener.Change<? extends ReporteTutor> c){
            getDatosReporteSeleccionado();
       }
    
    };
      
     public ReporteTutor getTablaReporte (){
        ReporteTutor ReporteSelecciondo=null; 
        if (tblReportes != null){
            List<ReporteTutor> tabla=tblReportes.getSelectionModel ().getSelectedItems ();
            if (tabla.size ()== 1){
               ReporteSelecciondo=tabla.get(0);
                
            } 

          }
        return ReporteSelecciondo;
     }  
     public int getDatosReporteSeleccionado(){
      final ReporteTutor reporte =getTablaReporte ();
      int idsesion=0;
      if (reporte!=null){
         idsesion=reporte.getIdsesion();
       }
       return idsesion;
     }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ObservableList<ReporteTutor> tablaReporteSeleccionado=tblReportes.getSelectionModel().getSelectedItems();
        tablaReporteSeleccionado.addListener (selectorTablaReporte); 
        
    }    

    @FXML
    private void consultarReporteSeleccionado(ActionEvent event)  {
        ReporteTutor reporte= this.getTablaReporte();
        
        if(reporte != null){
            abrirReporte();
        }else{
           JOptionPane.showMessageDialog(null, "Seleccione al menos una Reporte");
        }
                                                                                    
    
        
    }
    
    private void abrirReporte() {
       int idsesion=getDatosReporteSeleccionado();
       try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/uv/gui/interfaces/CU09ConsultarReporteporTutor.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

       
        CU09ConsultarReporteporTutorController controlador = (CU09ConsultarReporteporTutorController) fxmlLoader.getController();
        controlador.recibirParametros(idsesion);

        stage.showAndWait();

    } catch (IOException ex) {
        System.out.println("IO Exception: " + ex.getMessage());
    }
    }
       private void avisoSinConexion(){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Sin conexión con la Base de Datos");
           alert.setHeaderText("Intente más tarde. Por favor.");
           alert.showAndWait();
    
    } 

    @FXML
    private void filtrarNombreTutor(KeyEvent event) {
        try{
         ReporteTutorDAO instance = new  ReporteTutorDAO();
         ArrayList<ReporteTutor> reportesTutor= new ArrayList<>();
         reportesTutor = instance.consultarReportesTutor(2);
            if(!reportesTutor.isEmpty()){
                ObservableList<ReporteTutor> tablaTutores = FXCollections.observableArrayList() ;

        
                String filtroTutores =  this.txtNombreTutor.getText();


                if (!(filtroTutores.isEmpty())){
                  for (ReporteTutor tutor : reportesTutor){
                     if (tutor.getNombreTutor().toLowerCase().contains(filtroTutores.toLowerCase())) 
                         tablaTutores.add(tutor); 
                }

                     tblReportes.setItems(tablaTutores);  
                }
                else{
                    for (ReporteTutor tutor: reportesTutor){
                       tablaTutores.add(tutor); 
                    }
                    tblReportes.setItems(tablaTutores);  
                }
            }
         }catch (SQLException ex) { 
             //this.avisoSinConexion();
             log.warn(ex);
         } 
       
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
