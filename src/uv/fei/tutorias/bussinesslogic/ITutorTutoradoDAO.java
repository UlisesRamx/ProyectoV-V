/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import java.util.List;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.TutorTutorado;
import uv.fei.tutorias.domain.Docente;
import uv.fei.tutorias.domain.Tutor;
import java.sql.SQLException;


/**
 *
 * @author Valea
 */
public interface ITutorTutoradoDAO {
 
    public int asignarTutorTutorado(String cuentauv, String Matricula) throws SQLException;
  //  public List<TutorTutorado> consultarTutoresTutoradosporId (int idTutorTutorado)throws SQLException ;
    public ArrayList<Docente> consultarTodosDocentes()throws SQLException;
    public ArrayList<TutorTutorado> consultarTutoradosporTutor()throws SQLException;

    
}
