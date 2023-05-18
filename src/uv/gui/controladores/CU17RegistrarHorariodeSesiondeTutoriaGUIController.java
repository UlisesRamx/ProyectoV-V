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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import uv.fei.tutorias.bussinesslogic.*;
import uv.fei.tutorias.domain.*;
import uv.fei.tutorias.domain.SesionTutoria;
import uv.fei.tutorias.bussinesslogic.SesionTutoriaDAO;
import uv.mensajes.Alertas;


public class CU17RegistrarHorariodeSesiondeTutoriaGUIController implements Initializable {

    @FXML
    private TableColumn colMatricula;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colHorario;
    @FXML
    private ComboBox cbbFechaTutoria;
    @FXML
    private TextField lblPeriodo;
    @FXML
    private Button btnCancelar;
    @FXML
    private TableView tblTutoradosHorario;
    @FXML
    private Button btnGuardar;
    @FXML
    private TableView<Horario> tblTutorados;
    @FXML
    private TableColumn colMatriculaTutorados;
    @FXML
    private TableColumn colNombreTutorados;

    private ObservableList<Horario> horarioExistenteObservableList;
    private ObservableList<Horario> horarioNuevoObservableList;
    private Alertas alertas = new Alertas();
    private Usuario usuarioActivo;
    private Horario horarioSeleccionado;
    private SesionTutoria sesionTutoria;
    private ProgramaEducativo programaEducativoActivo;

    final static Logger log = Logger.getLogger(CU17RegistrarHorariodeSesiondeTutoriaGUIController.class);
    @FXML
    private TextField txtHora;
    @FXML
    private TextField txtMinuto;

    public void recibirParametros(Usuario usuarioRecibido, ProgramaEducativo programaEducativo) throws SQLException{
        usuarioActivo = usuarioRecibido;
        programaEducativoActivo = programaEducativo;
        establecerPeriodoFechasTutoria();
        configurarTabla();
    }

    private void configurarTabla(){
        colMatricula.setCellValueFactory(new PropertyValueFactory<Horario, String>("matricula"));
        colNombre.setCellValueFactory(new PropertyValueFactory <Horario, String>("nombre"));
        colHorario.setCellValueFactory(new PropertyValueFactory <Horario, String>("hora"));
        colMatriculaTutorados.setCellValueFactory(new PropertyValueFactory<Horario, String>("matricula"));
        colNombreTutorados.setCellValueFactory(new PropertyValueFactory<Horario, String>("nombre"));
    }

    private void establecerPeriodoFechasTutoria() {
        Periodo periodo;
        PeriodoDAO periodoDAO = new PeriodoDAO();
        try {
            periodo = periodoDAO.consultarPeriodoActivo();
            if (periodo.getFechaFin() != null){
                lblPeriodo.setText(periodo.toString());
                mostrarFechasDeTutoria(periodo);
            }else {
                alertas.mostrarAlertaNoHayPeriodosRegistrados();
            }
        } catch (SQLException exception) {
            log.warn(exception);
        }
    }

    private void mostrarFechasDeTutoria(Periodo periodo) throws SQLException{
        ObservableList<SesionTutoria> opcionesCombo = FXCollections.observableArrayList();
        SesionTutoriaDAO sesionTutoriaDAO = new SesionTutoriaDAO();
        ArrayList<SesionTutoria> sesionesTutorias = sesionTutoriaDAO.consultarTutoriaPorPeriodo(periodo.getIdPeriodo());
        if(!sesionesTutorias.isEmpty()) {
            for (SesionTutoria sesionTutoriaciclo : sesionesTutorias) {
                opcionesCombo.add(sesionTutoriaciclo);
            }
            cbbFechaTutoria.setItems(opcionesCombo);
        } else {
            alertas.mostrarAlertaNoHayFechasDeTutoriaRegistradas();
        }
        cbbFechaTutoria.valueProperty().addListener((ov, valorAntiguo, valorNuevo) -> {
            tblTutoradosHorario.getItems().clear();
            tblTutorados.getItems().clear();
            sesionTutoria = (SesionTutoria) valorNuevo;
            mostrarHorario();
        });
    }

    private void mostrarHorario() {
        HorarioDAO horarioDAO = new HorarioDAO();
        ArrayList<Horario> horarioArrayList = horarioDAO.consultarHorariosporIdTutoria(usuarioActivo.getCuentaUV(), sesionTutoria.getIdSesionTutoria(), programaEducativoActivo.getIdProgramaEducativo());
        if (!horarioArrayList.isEmpty()){
            horarioExistenteObservableList = FXCollections.observableArrayList();
            for (Horario horario : horarioArrayList) {
                horarioExistenteObservableList.add(horario);
            }
            tblTutoradosHorario.setItems(horarioExistenteObservableList);
            tblTutoradosHorario.setDisable(true);
            tblTutorados.setDisable(true);
            alertas.mostrarAlertaHorarioExistente();
        }else{
            HorarioDAO horarioDAO1 = new HorarioDAO();
            ArrayList<Horario> tutoradosdeTutor;
            tutoradosdeTutor = horarioDAO1.obtenerTutoradosParaRegistrodeHorario(usuarioActivo.getCuentaUV(), 2);
            horarioNuevoObservableList = FXCollections.observableArrayList();
            for (Horario horariosNuevo : tutoradosdeTutor) {
                horarioNuevoObservableList.add(horariosNuevo);
            }
            tblTutorados.setItems(horarioNuevoObservableList);
            tblTutoradosHorario.setDisable(false);
            tblTutorados.setDisable(false);
            cbbFechaTutoria.setDisable(true);
            horarioExistenteObservableList = FXCollections.observableArrayList();
            tblTutoradosHorario.setItems(horarioExistenteObservableList);
        }
    }

    private final ListChangeListener<Horario> selectorTablaTutoradosRegistroHorario =
            new ListChangeListener<Horario>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends Horario> c) {
                    ponerHorarioSeleccionado();
                }
            };

    public Horario obtenerNuevoTutoradoHorario() {
        Horario horarioseleccionado = new Horario();
        if (tblTutorados != null) {
            List<Horario> tabla = tblTutorados.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                horarioseleccionado = tabla.get(0);
            }
        }
        return horarioseleccionado;
    }

    private void ponerHorarioSeleccionado() {
        horarioSeleccionado = obtenerNuevoTutoradoHorario();
        if (horarioSeleccionado != null) {
            btnGuardar.setDisable(false);
            txtHora.setDisable(false);
            txtMinuto.setDisable(false);
            btnCancelar.setDisable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ObservableList<Horario> horariosNuevos = tblTutorados.getSelectionModel().getSelectedItems();
        horariosNuevos.addListener(selectorTablaTutoradosRegistroHorario);
        btnGuardar.setDisable(true);
        txtHora.setDisable(true);
        txtMinuto.setDisable(true);
    }

    @FXML
    private void guardarHorario(ActionEvent event) {
        if(!txtHora.getText().isEmpty() && !txtMinuto.getText().isEmpty()){
            String hora = txtHora.getText();
            String minuto = txtMinuto.getText();
            boolean esHora = true;
            for (int i = 0; i < hora.length(); i++) {
                if (!Character.isDigit(hora.charAt(i))) {
                    esHora = false;
                }
            }
            boolean esMinuto = true;
            for (int i = 0; i < minuto.length(); i++) {
                if (!Character.isDigit(minuto.charAt(i))) {
                    esMinuto = false;
                }
            }
            if(esHora && esMinuto){
                int horas = Integer.valueOf(hora);
                int minutos = Integer.valueOf(minuto);
                if(horas >=0 && horas <24 && minutos < 60 && minutos >= 0){
                    String horarioEscrito = (horas + ":" + minutos + ":00");
                    horarioSeleccionado.setCuentauv(usuarioActivo.getCuentaUV());
                    horarioSeleccionado.setHora(horarioEscrito);
                    horarioSeleccionado.setIdTutoria(sesionTutoria.getIdSesionTutoria());
                    horarioSeleccionado.setIdProgramaEducativo(programaEducativoActivo.getIdProgramaEducativo());
                    HorarioDAO horarioDAO = new HorarioDAO();
                    horarioDAO.registrarHorario(horarioSeleccionado);
                    horarioExistenteObservableList.add(horarioSeleccionado);
                    horarioNuevoObservableList.remove(horarioNuevoObservableList.indexOf(horarioSeleccionado));
                    if(horarioNuevoObservableList.isEmpty()){
                        btnCancelar.setDisable(false);
                        btnGuardar.setDisable(true);
                        cbbFechaTutoria.setDisable(false);
                    }
                } else {
                    alertas.mostrarAlertarHorarioNoValido();
                }
            }else {
                alertas.mostrarAlertarHorarioNoValido();
            }
        }else {
            alertas.mostrarAlertaCamposVacios();
        }
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
