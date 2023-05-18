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
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import uv.fei.tutorias.bussinesslogic.*;
import uv.fei.tutorias.domain.*;
import uv.mensajes.Alertas;


public class CU03RegistrarProblematicaAcademicaGUIController implements Initializable {

    @FXML
    private ComboBox cbbDocente;
    @FXML
    private ComboBox cbbExperienciaEducativa;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextArea txtDescrpcion;
    @FXML
    private TextField txtCantidadTutorados;

    int idSesionActiva;
    Usuario usuarioActivo;
    private Alertas alertas = new Alertas();
    ProgramaEducativo programaEducativoActivo;
    Docente docenteSeleccionado;
    ExperienciaEducativa experienciaEducativaSeleccionada;
    
    final static Logger log = Logger.getLogger(CU03RegistrarProblematicaAcademicaGUIController.class);

    public void recibirParametros(Usuario usuario, ProgramaEducativo programaEducativo, int idsesion) throws SQLException {
        usuarioActivo = usuario;
        programaEducativoActivo = programaEducativo;
        idSesionActiva = idsesion;
        establecerDocentes();
    }

    private void establecerDocentes() {
        ArrayList<Docente> docentes;
        DocenteDAO docenteDAO = new DocenteDAO();
        try {
            docentes = docenteDAO.recuperarDocentesPorProgramaEducativo(programaEducativoActivo.getIdProgramaEducativo());
            ObservableList<Docente> docentesObservableList = FXCollections.observableArrayList();
            if (!docentes.isEmpty()) {
                for (Docente docente : docentes) {
                    docentesObservableList.add(docente);
                }
            } else {
                alertas.mostrarAlertarNoHayDocentesRegistrados();
            }
            cbbDocente.setItems(docentesObservableList);
            cbbDocente.valueProperty().addListener((ov, valorAntiguo, valorNuevo) -> {
                docenteSeleccionado = (Docente) valorNuevo;
                establecerExperienciasEducativas(docenteSeleccionado.getNumPersonal());
            });
        } catch (SQLException exception){
            alertas.mostrarAlertaErrorConexionDB();
            log.warn(exception);
        }
    }

    private void establecerExperienciasEducativas(int numPersonal) {
        ArrayList<ExperienciaEducativa> experienciasEducativas;
        ObservableList<ExperienciaEducativa> experienciaEducativaObservableList = FXCollections.observableArrayList();
        try {
            ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
            experienciasEducativas = experienciaEducativaDAO.consultarExperienciasPorDocente(numPersonal);
            if (!experienciasEducativas.isEmpty()) {
                for (ExperienciaEducativa experienciaEducativa : experienciasEducativas) {
                    experienciaEducativaObservableList.add(experienciaEducativa);
                }
            } else {
                alertas.mostrarAlertaNoHayExperienciasRegistradas();
            }
            cbbExperienciaEducativa.setItems(experienciaEducativaObservableList);
            cbbExperienciaEducativa.valueProperty().addListener((ov, valorAntiguo, valorNuevo) -> {
                experienciaEducativaSeleccionada = (ExperienciaEducativa) valorNuevo;
            });
        } catch (SQLException exception){
            alertas.mostrarAlertaErrorConexionDB();
            log.warn(exception);
        }
    }

    @FXML
    private void comprobarInformacionCompleta(ActionEvent event) throws SQLException {
        Alertas alertas = new Alertas();
        if(txtCantidadTutorados.getText().isEmpty()){
            alertas.mostrarAlertaCamposVacios();
        }else if(txtTitulo.getText().isEmpty()){
            alertas.mostrarAlertaCamposVacios();
        }else if(txtDescrpcion.getText().isEmpty()){
            alertas.mostrarAlertaCamposVacios();
        }else if(docenteSeleccionado == null){
            alertas.mostrarAlertaCamposVacios();
        }else if(experienciaEducativaSeleccionada == null){
            alertas.mostrarAlertaCamposVacios();
        }else {
            if(comprobarDatosValidos() == 3){
                guardarProblematica();
            } else {
                alertas.mostrarCamposInvalidos();
            }
        }
    }

    private int comprobarDatosValidos() {
        int datosValidos = 0;
        String cantidadTutorados = txtCantidadTutorados.getText();
        if(cantidadTutorados.matches("[+-]?\\d*(\\.\\d+)?")) {
            int catidad = Integer.parseInt(cantidadTutorados);
            if(catidad > 0 && catidad < 30) {
                ++datosValidos;
            }
        }
        if(txtTitulo.getText().length() < 100) {
            ++datosValidos;
        }
        if(txtDescrpcion.getText().length() < 500){
            ++datosValidos;
        }
        return datosValidos;
    }

    private void guardarProblematica() {
        ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica();
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        int cantidadTutorados = Integer.parseInt(txtCantidadTutorados.getText());
        String titulo = txtTitulo.getText();
        String descripcion = txtDescrpcion.getText();
        int numPersonal = docenteSeleccionado.getNumPersonal();
        int nrc = experienciaEducativaSeleccionada.getNrc();
        int idDocenteEE = obtenerIdDocenteEEPrograma();
        if (idDocenteEE != 0) {
            problematicaAcademica.setIdDocenteEePrograma(idDocenteEE);
        }
        problematicaAcademica.setCantidadTutorados(cantidadTutorados);
        problematicaAcademica.setDescripcion(descripcion);
        problematicaAcademica.setTitulo(titulo);
        try {
            int resultado = problematicaAcademicaDAO.registrarProblematicaAcademica(problematicaAcademica, idSesionActiva);
            int idProblematicaNueva = problematicaAcademicaDAO.obtenerIdProblematica(problematicaAcademica.getTitulo(), cantidadTutorados);
            int resultadoDos = problematicaAcademicaDAO.vincularProblematicaSesion(idProblematicaNueva, idSesionActiva);
            if ((resultado + resultadoDos) == 2) {
                Alertas alertas = new Alertas();
                alertas.mostrarAlertaRegistroExitoso();
            } else {
                alertas.mostrarAlertaRegistroNoCompletado();
            }
        }catch (SQLException exception){
            alertas.mostrarAlertaErrorConexionDB();
            log.warn(exception);
        }
    }

    private int obtenerIdDocenteEEPrograma() {
        DocenteEEProgramasDAO docenteEEProgramasDAO = new DocenteEEProgramasDAO();
        int idDocenteEE = 0;
        try {
            idDocenteEE = docenteEEProgramasDAO.obtenerIdDocenteEEPrograma(docenteSeleccionado.getNumPersonal(), experienciaEducativaSeleccionada.getNrc());
        } catch (SQLException exception) {
            alertas.mostrarAlertaErrorConexionDB();
            log.warn(exception);
        }
        return idDocenteEE;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void limpiarCampos(ActionEvent event) {
        txtDescrpcion.setText("");
        txtTitulo.setText("");
        txtCantidadTutorados.setText("");
        cbbDocente.getSelectionModel().clearSelection();
        cbbExperienciaEducativa.getSelectionModel().clearSelection();
    }
}
