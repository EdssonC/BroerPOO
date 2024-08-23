package pe.edu.upeu.sysgui.service;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysgui.modelo.Persona;

import java.util.ArrayList;
import java.util.List;
@Component
public class RegistroAsistencia {
    List<Persona> p= new ArrayList<>();
    ArrayList<Persona> pp= new ArrayList<>();
    public void registrarAsistencia(Persona px){
        System.out.println("Registrar Asistencia a Curso POO");
        p.add(px);
        pp.add(px);
    }
    public List<Persona> mostrarAsistenciaList(){
        System.out.println("Alamcenado en un LIST:");
        return p;
    }
    public ArrayList<Persona> mostrarAsistenciaArray(){
        System.out.println("Alamcenado en un ArraList:");
        return pp;
    }
}

