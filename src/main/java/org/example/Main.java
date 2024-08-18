package org.example;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome \n");
        Persona nomVec2[] = {new Persona("Pablo"," ",'M'),
                new Persona("Juan"," ",'M')};
        System.out.println("Registro de asistencia: ");
        Scanner leer = new Scanner(System.in);
        for (Persona p : nomVec2) {
            System.out.print("Está presente " + p.nombre+": ");
            // p.estadoAsis= leer.nextLine();
            p.setEstadoAsis(leer.nextLine());
        }
        System.out.println("Mostrar registro de asitencia: ");
        for (Persona p : nomVec2) {
            //System.out.println(p.nombre+"-"+p.estadoAsis);
            System.out.println(p.getNombre() + " - "+p.getEstadoAsis() + " - " + p.getFechaReg());
        }
    }
}