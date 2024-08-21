package org.example;
import java.util.ArrayList;
import java.util.List;

public class ListMain {
    public static void main(String[] args) {
        List<Persona> per = new ArrayList<>();
        per.add(new Persona("Pablito", "P", 'M'));
        per.add(new Persona("Pablito", "P", 'M'));
        per.add(new Persona("Pedro", "M", 'F'));
        System.out.println(per.get(2).getNombre());

        Persona[] pp = new Persona[10];
        pp[0] = new Persona("Pablito", "P", 'M');
        System.out.println(pp[0].getNombre());
    }
}