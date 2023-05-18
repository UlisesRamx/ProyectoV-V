package uv.fei.tutorias.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import LogerDataBase.UsuarioDB;
import LogerDataBase.UsuarioDataBase;

public class DataBaseConnection {
    
    private Connection connection;
    private final String DB="jdbc:mysql://sistema-tutorias.mysql.database.azure.com:3306/sistematutoriasfei?useSSL=false";
    private UsuarioDB usuarioDB;
    UsuarioDataBase usuarioDataBase = new UsuarioDataBase();

    public Connection getConnection() throws SQLException{
        connect();
        return connection;
    }

    private void connect() throws SQLException{
        usuarioDB = usuarioDataBase.obtenerUsuario();
        connection=DriverManager.getConnection(DB,usuarioDB.getUser(),usuarioDB.getPassword());
    }

    public void closeConection(){
        if(connection!=null){
            try {
                if(!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
