package uv.gui.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import uv.fei.tutorias.bussinesslogic.ReporteTutorDAO;
import uv.fei.tutorias.domain.Asistencia;
import uv.fei.tutorias.domain.ProblematicaReporte;
import uv.fei.tutorias.domain.ReporteTutor;
import uv.mensajes.Alertas;

public class CU09ConsultarReporteporTutorController implements Initializable {

    @FXML
    private TableView  tblTutorados;
    @FXML
    private TableColumn<?, ?> cMatricula;
    @FXML
    private TableColumn<?, ?> cNombre;
    @FXML
    private TableColumn<?, ?> cAsistencia;
    @FXML
    private TableView  tblProblematicas;
    @FXML
    private TableColumn<?, ?> cEE;
    @FXML
    private TableColumn<?, ?> cDocente;
    @FXML
    private TableColumn<?, ?> cProblematica;
    @FXML
    private TextArea txtComentarios;
    @FXML
    private Label lblPeriodo;
    @FXML
    private Label lblPrograma;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblTutor;
    @FXML
    private Label lblIdsesion;
    @FXML
    private Label llave;
    @FXML
    private Button btnCerrar;
    
    final static Logger log = Logger.getLogger(CU09ConsultarReporteporTutorController.class); 
    int idsesion=0;
    Alertas alertas = new Alertas();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void recibirParametros(int i) {
        idsesion=i;
        llave.setText(Integer.toString(idsesion));
        cargarDatos();
    }
    
    private void cargarEncabezado(){
        ReporteTutorDAO instance= new ReporteTutorDAO();
        ReporteTutor encabezado= new ReporteTutor();
        try {
            encabezado=instance.cargarEncabezadoReporte(idsesion);
        } catch (SQLException ex) {
            log.warn(ex);
        }
        if (encabezado !=null){
            lblPeriodo.setText(encabezado.getPeriodo());
            lblPrograma.setText(encabezado.getProgramaeducativo());
            lblFecha.setText(encabezado.getFecha());
            lblTutor.setText(encabezado.getNombreTutor());
        }
    }

    private void cargarListaAsistencia(){
        ReporteTutorDAO instance=new ReporteTutorDAO();
        ArrayList<Asistencia> asistencias= new ArrayList(); 
        try {
            asistencias=instance.cargarListaAsistencia(idsesion);
        } catch (SQLException ex) {
            alertas.mostrarAlertaErrorConexionDB();
            log.warn(ex);
        }

        if(!asistencias.isEmpty()){
            this.cMatricula.setCellValueFactory(new PropertyValueFactory("matricula"));
            this.cNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
            this.cAsistencia.setCellValueFactory(new PropertyValueFactory("asistencia"));
            ObservableList<Asistencia> tablaAsitencias = FXCollections.observableArrayList() ;

             for (Asistencia asistencia1 : asistencias){
                 tablaAsitencias.add(asistencia1); 
             }

              tblTutorados.setItems(tablaAsitencias); 

        }else{
            alertas.mostrarAlertaNohayTutorados();
        }
    }

    private void cargarComentario(){
        ReporteTutorDAO instance=new ReporteTutorDAO();
        String comentario=null;
        try {
            comentario= instance.cargarComentariosGenerales(idsesion);
        } catch (SQLException ex) {
            alertas.mostrarAlertaErrorConexionDB();
        }
        if(comentario!=null){
            txtComentarios.setText(comentario);
        }else{
           txtComentarios.setText("Sin comentarios del tutor");
        }
    
    }

    private void cargarProblematicas(){
        ReporteTutorDAO instance=new ReporteTutorDAO();
        ArrayList<ProblematicaReporte> problematicas= new ArrayList(); 
        try {
            problematicas=instance.cargarProblematicasReportadas(idsesion);
        } catch (SQLException ex) {
            alertas.mostrarAlertaErrorConexionDB();
            log.warn(ex);
        }

        if(!problematicas.isEmpty()){
            this.cEE.setCellValueFactory(new PropertyValueFactory("experiencia"));
            this.cDocente.setCellValueFactory(new PropertyValueFactory("NombreDocente"));
            this.cProblematica.setCellValueFactory(new PropertyValueFactory("titulo"));
            ObservableList<ProblematicaReporte> tablaProblematicas = FXCollections.observableArrayList() ;
             for (ProblematicaReporte problematica1 : problematicas){
                 tablaProblematicas.add(problematica1); 
             }
              tblProblematicas.setItems(tablaProblematicas);
        }else{
            alertas.mostrarAlertaNohayProblematicasRegistradasPorTutor();
        }
    }

    private void cargarDatos(){
        this.cargarEncabezado();
        this.cargarListaAsistencia();
        this.cargarComentario();
        this.cargarProblematicas();
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
