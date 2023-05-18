
package uv.gui.controladores;

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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import uv.fei.tutorias.bussinesslogic.TutorTutoradoDAO;
import uv.fei.tutorias.bussinesslogic.TutoradoDAO;
import uv.fei.tutorias.domain.ProgramaEducativo;
import uv.fei.tutorias.domain.TutorTutorado;
import uv.fei.tutorias.domain.Tutorado;
import uv.fei.tutorias.domain.Usuario;

/**
 * FXML Controller class
 *
 * @author Valea
 */
public class CU35AsignarTutorController implements Initializable {

    @FXML
    private TextField txtNombreTutor;
    @FXML
    private TextField txtNomTutorado;
    @FXML
    private TableView tblTutorados;
    @FXML
    private TableColumn<?, ?> cMatricula;
    @FXML
    private TableColumn<?, ?> cTutorado;
    @FXML
    private TableView tblTutores;
    @FXML
    private TableColumn<?, ?> cTutor;
    @FXML
    private TableColumn<?, ?> cNumTutorados;
    @FXML
    private Button btnAsignar;
      @FXML
    private Button btnCerrar;
    
    ProgramaEducativo programaEducativo;

    public void recibirParametros(ProgramaEducativo programaRecibido) throws SQLException {
       programaEducativo = programaRecibido;
       cargarTablas();
    }

    final static Logger log = Logger.getLogger(CU35AsignarTutorController.class); 
    
    private final ListChangeListener<Tutorado> selectorTablaTutorados=new ListChangeListener<Tutorado> (){
          @Override
          public void onChanged (ListChangeListener.Change<? extends Tutorado> c){
          getDatosTutoradoSeleccionada();
         }    
     };
  
            
  public Tutorado getTablaTutoradoSeleccionada (){
     Tutorado tutoradoSeleccionado=null;
    if (tblTutorados != null){
        List<Tutorado> tabla=tblTutorados.getSelectionModel ().getSelectedItems ();
        if (tabla.size ()== 1){
            tutoradoSeleccionado=tabla.get(0);
        }
      }
    return tutoradoSeleccionado;
   }
  
   public String getDatosTutoradoSeleccionada(){
      final Tutorado tutorado = getTablaTutoradoSeleccionada ();
      String matricula="";
      if (tutorado !=null){
        matricula= tutorado.getMatricula();
      }
      return matricula;
    }
    private final ListChangeListener<TutorTutorado> selectorTablaTutores=new ListChangeListener<TutorTutorado> (){
          @Override
          public void onChanged (ListChangeListener.Change<? extends TutorTutorado> c){
          getDatosTutorSeleccionado();
         }    
     };
    public TutorTutorado getTablaTutorSeleccionado (){
     TutorTutorado TutorSeleccionado=null;    
    if (tblTutores != null){
        List<TutorTutorado> tabla=tblTutores.getSelectionModel ().getSelectedItems ();
        if (tabla.size ()== 1){
            TutorSeleccionado=tabla.get(0);
           
        } 
    
      }
    return TutorSeleccionado;
   }
    public String getDatosTutorSeleccionado(){
      final TutorTutorado tutor =getTablaTutorSeleccionado ();
      String cuentauv="";
           if (tutor !=null)
           cuentauv= tutor.getCuentaUv();
      return cuentauv;
    }
  
    
    private void cargarDatosTutorados(){
     int idprograma=programaEducativo.getIdProgramaEducativo();

     try{
        TutoradoDAO instance= new TutoradoDAO();
        ArrayList<Tutorado> tutorados= new ArrayList<>();
        tutorados = instance.consultarTutoradosNoAsignados(idprograma);
        if (!tutorados.isEmpty()){
           this.cMatricula.setCellValueFactory(new PropertyValueFactory("matricula"));
           this.cTutorado.setCellValueFactory(new PropertyValueFactory("nombre"));

           ObservableList<Tutorado> tablaTutorados = FXCollections.observableArrayList() ;

           for (Tutorado tutorado : tutorados){
              tablaTutorados.add(tutorado);
           }
              tblTutorados.setItems(tablaTutorados); 
        }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sin datos que mostrar");
                alert.setHeaderText("No se han encontrado registros de Tutorados sin asignación de Tutor");
                alert.showAndWait(); 
        }
    }catch (SQLException ex) {
        this.avisoSinConexion();
        log.warn(ex);
    }     
    }
    
     private void cargarDatosTutores() {
     try{
        TutorTutoradoDAO instance= new TutorTutoradoDAO();
        ArrayList<TutorTutorado> tutores= new ArrayList<>();
        tutores = instance.consultarTutoradosporTutor();
        if (!tutores.isEmpty()){
           this.cTutor.setCellValueFactory(new PropertyValueFactory("Nombre"));
           this.cNumTutorados.setCellValueFactory(new PropertyValueFactory("NumTutorados"));

           ObservableList<TutorTutorado> tablaTutores = FXCollections.observableArrayList() ;

           for (TutorTutorado tutor1 : tutores){
              tablaTutores.add(tutor1); 
           }
           tblTutores.setItems(tablaTutores); 
        }else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Error");
           alert.setHeaderText("No se han encontrado registros de Tutores");
           alert.showAndWait(); 
        }
       }catch (SQLException ex) {
             this.avisoSinConexion();
             log.warn(ex);
     }
     }
      private void cargarTablas(){
          this.cargarDatosTutores();
          this.cargarDatosTutorados();
      }
            
        private void avisoSinConexion(){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Sin conexión con la Base de Datos");
           alert.setHeaderText("Intente más tarde. Por favor.");
           alert.showAndWait();
    }  
        
     
     
    private void avisoLimiteTutorados(TutorTutorado tutor){
      if( tutor != null) {
        int numTutorados= tutor.getNumTutorados();
        if (numTutorados>29){
             JOptionPane.showMessageDialog(null, "El Tutor seleccionado ha llegado al limite recomendable de tutorados asignados");
        }
      }
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ObservableList<Tutorado> tablaTutoradoSeleccionada=tblTutorados.getSelectionModel().getSelectedItems();
        tablaTutoradoSeleccionada.addListener(selectorTablaTutorados);
        final ObservableList<TutorTutorado> tablaTutoresSeleccionada=tblTutores.getSelectionModel().getSelectedItems();
        tablaTutoresSeleccionada.addListener(selectorTablaTutores);
        
    }    

    @FXML
    private void asignacionTutores(ActionEvent event) {
        
       int resultado=0;
       
       try{
            String matricula=this.getDatosTutoradoSeleccionada();
            String cuentauv=this.getDatosTutorSeleccionado();
            TutorTutorado tutor= this.getTablaTutorSeleccionado();
            this.avisoLimiteTutorados(tutor);
            
            if (matricula !="" && cuentauv !=""){
                 TutorTutoradoDAO instance= new TutorTutoradoDAO();
                 
                resultado= instance.asignarTutorTutorado(cuentauv, matricula);
                 
            } else{
              JOptionPane.showMessageDialog(null, "Seleccione al menos un Tutorado y un Tutor");
            }
             
            if (resultado==1){
                Tutorado estudiante= getTablaTutoradoSeleccionada ();    
                tblTutorados.getItems().remove(estudiante);
                this.cargarDatosTutores();
                JOptionPane.showMessageDialog(null, "Asignación realizada correctamente");
            }
            
        }catch (SQLException ex) {
             this.avisoSinConexion();
             log.warn(ex);
        }
        
    }

    @FXML
    private void filtrarNombreTutor(KeyEvent event) {
        
       try{
            TutorTutoradoDAO instance= new TutorTutoradoDAO();
            ArrayList<TutorTutorado> tutores= new ArrayList<>();
            tutores = instance.consultarTutoradosporTutor();
            if(!tutores.isEmpty()){
                ObservableList<TutorTutorado> tablaTutores = FXCollections.observableArrayList() ;
                String filtroTutores =  this.txtNombreTutor.getText();
                if (!(filtroTutores.isEmpty())){
                  for (TutorTutorado tutor1 : tutores){
                     if (tutor1.getNombre().toLowerCase().contains(filtroTutores.toLowerCase())) 
                         tablaTutores.add(tutor1); 
                }

                     tblTutores.setItems(tablaTutores);  
                }
                else{
                    for (TutorTutorado tutor1 : tutores){
                       tablaTutores.add(tutor1); 
                    }
                    tblTutores.setItems(tablaTutores);  
                }
            }
         }catch (SQLException ex) { 
             this.avisoSinConexion();
             log.warn(ex);
         }
    }

    @FXML
    private void filtrarNombreTutorado(KeyEvent event) {
        try{
            TutoradoDAO instance= new TutoradoDAO();
            ArrayList<Tutorado> estudiantes= new ArrayList<>();
            estudiantes = instance.consultarTutoradosNoAsignados(2);
            if(!estudiantes.isEmpty()){
                ObservableList<Tutorado> tablaTutorados = FXCollections.observableArrayList() ;

                String filtroTutorados =  this.txtNomTutorado.getText();

                if (!(filtroTutorados.isEmpty())){
                 for (Tutorado estudiante1 : estudiantes){
                     if (estudiante1.getNombre().toLowerCase().contains(filtroTutorados.toLowerCase())) 
                        tablaTutorados.add(estudiante1); 
                }

                     tblTutorados.setItems(tablaTutorados);  
                }
                else{
                    for (Tutorado estudiante1 : estudiantes){
                      tablaTutorados.add(estudiante1); 
                    }
                     tblTutorados.setItems(tablaTutorados); 
                }
            }
        }catch (SQLException ex) {   
            this.avisoSinConexion();
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
