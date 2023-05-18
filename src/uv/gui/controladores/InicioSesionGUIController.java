package uv.gui.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import uv.fei.tutorias.domain.Usuario;
import uv.fei.tutorias.bussinesslogic.UsuarioDAO;
import uv.mensajes.Alertas;

public class InicioSesionGUIController implements Initializable {
    
    @FXML
    private Label lblContrasena;
    @FXML
    private PasswordField txtContrasenia;
    @FXML
    private Label lblUsuario;
    @FXML
    private TextField txtUsuario;
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private Label txtTitulo;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Label lblUsuarioInvalido;
    @FXML
    private Label lblContrasenaInavlida;
    @FXML
    private Button btnSalir;

    final static Logger log = Logger.getLogger(InicioSesionGUIController.class);

    private void validarUsuarioBD(String cuentaUV, String contrasena) throws IOException {
        Alertas alertas = new Alertas();
        try{
            UsuarioDAO userDAO = new UsuarioDAO();
            Usuario usuarioRecuperado = userDAO.recuperarSesion(cuentaUV, contrasena);
            if(usuarioRecuperado != null){
                switch(usuarioRecuperado.getRol()) {
                    case 1:
                        mostrarMenuAdministrador();
                        break;
                    case 2:
                        mostrarMenuJefedeCarrera(usuarioRecuperado);
                        break;
                    case 3:
                        mostrarMenuCoordinaro(usuarioRecuperado);
                        break;
                    case 4:
                        mostrarMenuTutor(usuarioRecuperado);
                        break;
                    default:
                        alertas.mostrarAlertaUsuarioIncorrecto();
                        break;
                }
            }
        }catch (SQLException exception) {
          log.warn(exception);
          alertas.mostrarAlertaErrorConexionDB();
        }              
    }

    private void mostrarMenuTutor(Usuario usuarioRecuperado) throws IOException{
        Stage stageMenuTutor = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/MenuTutorGUI.fxml").openStream());
        MenuTutorGUIController menuTutorGUIController = (MenuTutorGUIController) loader.getController();
        menuTutorGUIController.recibirParametros(usuarioRecuperado);
        Scene scene = new Scene(root);
        stageMenuTutor.setScene(scene);
        stageMenuTutor.setTitle("Menu de tutores");
        stageMenuTutor.alwaysOnTopProperty();
        stageMenuTutor.initModality(Modality.APPLICATION_MODAL);
        stageMenuTutor.show();
        cerrarVentanaAux();
    }

    private void mostrarMenuAdministrador() throws IOException{
        Stage stageMenuTutor = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/MenuAdministradorGUI.fxml").openStream());
        Scene scene = new Scene(root);
        stageMenuTutor.setScene(scene);
        stageMenuTutor.setTitle("Menu de administradores");
        stageMenuTutor.alwaysOnTopProperty();
        stageMenuTutor.initModality(Modality.APPLICATION_MODAL);
        stageMenuTutor.show();
        cerrarVentanaAux();
    }

    private void mostrarMenuCoordinaro(Usuario usuarioRecuperado) throws IOException{
        Stage stageMenuTutor = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/MenuCoordinadorGUI.fxml").openStream());
        MenuCoordinadorGUIController menuCoordinadorGUIController = (MenuCoordinadorGUIController) loader.getController();
        menuCoordinadorGUIController.recibirParametros(usuarioRecuperado);
        Scene scene = new Scene(root);
        stageMenuTutor.setScene(scene);
        stageMenuTutor.setTitle("Menu de coordinadores");
        stageMenuTutor.alwaysOnTopProperty();
        stageMenuTutor.initModality(Modality.APPLICATION_MODAL);
        stageMenuTutor.show();
    }

    private void mostrarMenuJefedeCarrera(Usuario usuarioRecuperado) throws IOException {
        Stage stageMenuTutor = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/uv/gui/interfaces/MenuJefedeCarreraGUI.fxml").openStream());
        MenuJefedeCarreraGUIController menuJefedeCarreraGUIController = (MenuJefedeCarreraGUIController) loader.getController();
        menuJefedeCarreraGUIController.recibirParametros(usuarioRecuperado);
        Scene scene = new Scene(root);
        stageMenuTutor.setScene(scene);
        stageMenuTutor.setTitle("Menu de Jefe de carrera");
        stageMenuTutor.alwaysOnTopProperty();
        stageMenuTutor.initModality(Modality.APPLICATION_MODAL);
        stageMenuTutor.show();
        cerrarVentanaAux();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        URL linkLogo = getClass().getResource("/uv/fei/tutorias/img/logoUV.png");
        Image imagePdf = new Image(linkLogo.toString());
        imgLogo.setImage(imagePdf);

    }    

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException{
        String cuentaUV = txtUsuario.getText();
        String contrasena = txtContrasenia.getText();
        if(cuentaUV.isEmpty()){
            lblUsuarioInvalido.setVisible(true);
        }else if(contrasena.isEmpty()){
            lblContrasenaInavlida.setVisible(true);
        }else {
            lblContrasenaInavlida.setVisible(false);
            lblUsuarioInvalido.setVisible(false);
            validarUsuarioBD(cuentaUV, contrasena);
        }
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void cerrarVentanaAux(){
        Stage stageActual = (Stage)lblUsuario.getScene().getWindow();
        Stage stage = (Stage) stageActual.getScene().getWindow();
        stage.close();

    }
}