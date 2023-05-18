/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uv.fei.tutorias.domain;

import java.util.Objects;

/**
 *
 * @author Valea
 */
public class TutorTutorado {
    
    private int  numTutorados;
    private String CuentaUv;
    private String nombre;

    public void setNumTutorados(int numTutorados) {
        this.numTutorados = numTutorados;
    }

    public void setCuentaUv(String CuentaUv) {
        this.CuentaUv = CuentaUv;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumTutorados() {
        return numTutorados;
    }

    public String getCuentaUv() {
        return CuentaUv;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "TutorTutorado{" + "numTutorados=" + numTutorados + ", CuentaUv=" + CuentaUv + ", nombre=" + nombre + '}';
    }
  

   
}
