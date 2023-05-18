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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.bussinesslogic.DocenteEEProgramasDAO;
import uv.fei.tutorias.bussinesslogic.TutorTutoradoDAO;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Docente;

import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.Periodo;
import uv.fei.tutorias.domain.ProgramaEducativo;


/**
 * FXML Controller class
 *
 * @author Valea
 */
public class CU46AsignarEEController implements Initializable {

    @FXML
    private TextField txtEE;
    @FXML
    private TableView<Docente>tabDocente;
    @FXML
    private TableColumn ColumnNomDocente;
    @FXML
    private TableColumn ColumnCuentaUv;
    @FXML
    private TableView<ExperienciaEducativa>tabEE;
    @FXML
    private TableColumn ColumnEE;
    @FXML
    private TableColumn ColumnNRC;
    @FXML
    private TableColumn<?, ?> Cmaterno;
     @FXML
    private TextField txtNP;
    ObservableList<String> opcionesCombo;
    ObservableList<String> opcionesComboPeriodo;
    
    final static Logger log = Logger.getLogger(CU46AsignarEEController.class); 
 
   private final ListChangeListener<ExperienciaEducativa> selectorTablaExperiencias=new ListChangeListener<ExperienciaEducativa> (){
            @Override
            public void onChanged (ListChangeListener.Change<? extends ExperienciaEducativa> c){
             getDatosExperienciaSeleccionada();
       }
    
    };
    @FXML
    private ComboBox  cbbProgramaE;
    @FXML
    private Button btnCerrar;
   
   
   
 public ExperienciaEducativa getTablaExperienciaSeleccionada (){
    ExperienciaEducativa ExperienciaSeleccionada=null;
    if (tabEE != null){
        List<ExperienciaEducativa> tabla=tabEE.getSelectionModel ().getSelectedItems ();
        if (tabla.size ()== 1){
            ExperienciaSeleccionada=tabla.get(0);
            
        } 
    
      }
    return ExperienciaSeleccionada;
   }
 
    public int getDatosExperienciaSeleccionada(){
      final ExperienciaEducativa experiencia =getTablaExperienciaSeleccionada ();
      int nrc=0;
      if (experiencia!=null){
     
        nrc= experiencia.getNrc();
      }
      return nrc;
    }
    
    private final ListChangeListener<Docente> selectorTablaDocentes=new ListChangeListener<Docente> (){
            @Override
            public void onChanged (ListChangeListener.Change<? extends Docente> c){
             getDatosDocenteSeleccionado();
       }
    
    };
   
   
 public Docente getTablaDocenteSeleccionado (){
    Docente DocenteSeleccionado=null;
    if (tabEE != null){
        List<Docente> tabla=tabDocente.getSelectionModel ().getSelectedItems ();
        if (tabla.size ()== 1){
            DocenteSeleccionado=tabla.get(0);
            
        } 
    
      }
    return DocenteSeleccionado;
   }
 
    public int getDatosDocenteSeleccionado(){
      final Docente docente =getTablaDocenteSeleccionado ();
      int numPersonal=0;
      if (docente!=null){numPersonal= docente.getNumPersonal();}
            
      return numPersonal;
      
    } 

   private void cargarDatosExperiencias(){ 
       try{
            ExperienciaEducativaDAO instance = new  ExperienciaEducativaDAO();
            ArrayList<ExperienciaEducativa> experienciasEducativas= new ArrayList<>();
            experienciasEducativas = instance.consultarExperienciasNoAsignadas();

            if (!experienciasEducativas.isEmpty()){
                this.ColumnEE.setCellValueFactory(new PropertyValueFactory("nombre"));
                this.ColumnNRC.setCellValueFactory(new PropertyValueFactory("nrc"));

                ObservableList<ExperienciaEducativa> experiencias = FXCollections.observableArrayList() ;

                for (ExperienciaEducativa experienciaEducativa1 : experienciasEducativas){
                    experiencias.add(experienciaEducativa1); 
                }

                 tabEE.setItems(experiencias); 
           }else{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("No se han encontrado registros de Experiencias");
                alert.showAndWait(); 
             }
          }catch (SQLException ex){
              this.avisoSinConexion();
              log.warn(ex);
         }  
          
   }
   
    private void cargarDatosDocentes(){
       try{
            TutorTutoradoDAO instance = new  TutorTutoradoDAO();
             ArrayList<Docente> docentes= new ArrayList<>();
             docentes = instance.consultarTodosDocentes();

             if (!docentes.isEmpty()){
             this.ColumnNomDocente.setCellValueFactory(new PropertyValueFactory("nombre"));
             this.ColumnCuentaUv.setCellValueFactory(new PropertyValueFactory("NumPersonal"));


             ObservableList<Docente> docentesTabla = FXCollections.observableArrayList() ;

             for (Docente docente1 : docentes){
                 docentesTabla.add(docente1); 
             }

              tabDocente.setItems(docentesTabla); 

             }else{
                 Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText("No se han encontrado registros de Docentes");
                 alert.showAndWait(); 
             } 
        }catch(SQLException ex){
             this.avisoSinConexion();
             log.warn(ex);
        }
   }
    private void cargarTablas(){
          this.cargarDatosExperiencias();
          this.cargarDatosDocentes();

    }
    private void cargarProgramas(){
        
       opcionesCombo = FXCollections.observableArrayList();
       try{ 
        DocenteEEProgramasDAO instance=new  DocenteEEProgramasDAO();
        ArrayList<ProgramaEducativo> programas= instance.consultarProgramas();
        //opcionesCombo.add("--");
       
         if (!programas.isEmpty()){
            for (ProgramaEducativo programa1 : programas){
                 opcionesCombo.add(programa1.getNombre()); 
            }
             cbbProgramaE.setItems(opcionesCombo);
             cbbProgramaE.getSelectionModel().select(0);
         }
       }catch(SQLException ex){
        log.warn(ex);
       }
    }
    
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         this.cargarProgramas();
        this.cargarTablas();
       
        
        final ObservableList<ExperienciaEducativa> tablaExperienciaSeleccionada=tabEE.getSelectionModel().getSelectedItems();
        tablaExperienciaSeleccionada.addListener (selectorTablaExperiencias);
        
        final ObservableList<Docente> tablaDocenteSeleccionado=tabDocente.getSelectionModel().getSelectedItems();
        tablaDocenteSeleccionado.addListener (selectorTablaDocentes); 
       
   
    }

    @FXML
    private void guardar(ActionEvent event) {
            int resultado=0;
       try{
            
            int nrc= getDatosExperienciaSeleccionada();
            int numpersonal= getDatosDocenteSeleccionado();
            String programa=cbbProgramaE.getSelectionModel().getSelectedItem().toString();
            
            ExperienciaEducativa experiencia= this.getTablaExperienciaSeleccionada();
            Docente docente= this.getTablaDocenteSeleccionado();
           
            
            if (experiencia != null && docente!=null){
                DocenteEEProgramasDAO instance = new  DocenteEEProgramasDAO();
                int idprogramaeducativo=instance.consultarProgramaSeleccionado(programa);
                resultado=instance.asignarEEProfesor(nrc, numpersonal,idprogramaeducativo);
             } else{
            
             JOptionPane.showMessageDialog(null, "Seleccione al menos una Experiencia y un Docente");
            }
            
            if (resultado==1){
            
            ExperienciaEducativa experiencia1= getTablaExperienciaSeleccionada ();    
            tabEE.getItems().remove(experiencia1);
            this.tabEE.refresh();
            JOptionPane.showMessageDialog(null, "Asignación realizada correctamente");
            }
       }catch(SQLException ex){
           log.warn(ex);
           this.avisoSinConexion();
       }
       
        
       
    }  

    @FXML
    private void filtrarNumPersonal(KeyEvent event) {
        try{
            TutorTutoradoDAO instance = new  TutorTutoradoDAO();
            ArrayList<Docente> docentes= new ArrayList<>();
            docentes = instance.consultarTodosDocentes();
            if(!docentes.isEmpty()){
                ObservableList<Docente> docentesTabla = FXCollections.observableArrayList() ;
                String filtroDocente =  this.txtNP.getText();


                if (!(filtroDocente.isEmpty())){
                  for (Docente docente1 : docentes){
                     if (docente1.getNombre().toLowerCase().contains(filtroDocente.toLowerCase())) 
                         docentesTabla.add(docente1); 
                }

                     tabDocente.setItems(docentesTabla);  
                }
                else{
                  for (Docente docente1 : docentes){

                      docentesTabla.add(docente1);   
                   }
                }
                tabDocente.setItems(docentesTabla); 
            }
       }catch (SQLException ex) {
             this.avisoSinConexion(); 
             log.warn(ex);
        } 
    }
    @FXML
    private void filtrarNRC(KeyEvent event) {
        
        try{
            ExperienciaEducativaDAO instance = new  ExperienciaEducativaDAO();
            ArrayList<ExperienciaEducativa> experienciasEducativas= new ArrayList<>();
            experienciasEducativas = instance.consultarExperienciasNoAsignadas();
            if(!experienciasEducativas.isEmpty()){
                ObservableList<ExperienciaEducativa> experiencias = FXCollections.observableArrayList() ;
                String filtroExperiencia =  this.txtEE.getText();
                
                if (!(filtroExperiencia.isEmpty())){
                  for (ExperienciaEducativa experienciaEducativa1 : experienciasEducativas){
                     if (experienciaEducativa1.getNombre().toLowerCase().contains(filtroExperiencia.toLowerCase())) 
                        experiencias.add(experienciaEducativa1); 
                   }


                     tabEE.setItems(experiencias);  
                }
                else{
                   for (ExperienciaEducativa experienciaEducativa1 : experienciasEducativas){
                      experiencias.add(experienciaEducativa1); 
                   }
                   tabEE.setItems(experiencias);  
                }
                 
            }
       }catch (SQLException ex) {
            this.avisoSinConexion();
            log.warn(ex);
        } 
    }

    private void buscarEE(ActionEvent event) {
        try{  
            ExperienciaEducativaDAO instance = new  ExperienciaEducativaDAO();
            ArrayList<ExperienciaEducativa> experienciasEducativas= new ArrayList<>();
            experienciasEducativas = instance.consultarExperienciasNoAsignadas();

           ObservableList<Docente> docentesTabla = FXCollections.observableArrayList() ;
           String filtroEE =  this.txtEE.getText();

            ObservableList<ExperienciaEducativa> experiencias = FXCollections.observableArrayList() ;

            for (ExperienciaEducativa experienciaEducativa1 : experienciasEducativas){
                experiencias.add(experienciaEducativa1); 
            }


           if (!(filtroEE.isEmpty())){
            for (ExperienciaEducativa experienciaEducativa1 : experienciasEducativas){
                if (experienciaEducativa1.getNombre().toLowerCase().contains(filtroEE.toLowerCase())) 
                        experiencias.add(experienciaEducativa1); 
           }

             tabEE.setItems(experiencias); 
           }
           else{

            for (ExperienciaEducativa experienciaEducativa1 : experienciasEducativas){
                experiencias.add(experienciaEducativa1); 
            }
                 tabEE.setItems(experiencias); 

           }
         }catch (SQLException ex) {
            this.avisoSinConexion();
            log.warn(ex);
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
