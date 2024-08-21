package org.example;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class AsistList {
    static List<Persona> p=new ArrayList<>();
    public static void main(String[] args) {
        registrarAsistencia();
        imprimirAsistencia();

    }
    public static void registrarAsistencia(){
        Scanner entrada = new Scanner(System.in);
        String exisAlumnos= "";
        System.out.println("Registrar Asistencia curso POO");
        do {
            System.out.print("Ingrese el nombre del alumno: ");
            String nombre = entrada.nextLine();
            System.out.println("Ingrese P:Presente, F:Falta");
            String estado = entrada.nextLine();
            p.add(new Persona(nombre, estado,new Date() ));
            System.out.println("¿Existen más Alumnos? S=SI, N=NO");
            exisAlumnos = entrada.nextLine();
        }while (exisAlumnos.toUpperCase().equals("S"));

    }
    public static void imprimirAsistencia(){
        for(Persona p:p){
            System.out.println(p.toString());
  }

}

}
