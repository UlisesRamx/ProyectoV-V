/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uv.fei.tutorias.bussinesslogic;
import uv.fei.tutorias.domain.Problematica;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ISolucionesProblematicaAcademicaDAO {
  
    public int registrarSolucion(String solucion, int idProblematicaA)throws SQLException;

    public ArrayList<Problematica> consultarProblematicasSinSolucion(int idProgramaEducativo) throws SQLException;

    public ArrayList<Problematica> consultarSoluciones(int idProgramaEducativo) throws SQLException;
    
}
