package pe.edu.upeu.sysalmacenfx.repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.modelo.Categoria;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MainX {
    @Autowired
    CategoriaRepository repository;
    Scanner cs = new Scanner(System.in);

    public void registroCategoria(){

        String categoria;
        Categoria ca = new Categoria();
        do {
            System.out.println("Introduzca categoría: ");
            categoria = cs.nextLine();
        } while(categoria == null);

        ca.setNombre(categoria);
        Categoria dd = repository.save(ca);
        System.out.println("Reporte: ");
        System.out.println(dd.getIdCategoria() + "  " + dd.getNombre());
    }

    public void listarCategoria(){
        List<Categoria> datos = repository.findAll();
        System.out.println("ID\tNombre ");
        for (Categoria ca: datos){
            System.out.println(ca.getIdCategoria() + "\t" + ca.getNombre());
        }
    }
    public void eliminarCategoria(){

        listarCategoria();
        System.out.println("Ingrese el ID de la categoría a borrar: ");
        long id = Integer.parseInt(cs.nextLine());

        Optional<Categoria> categoria = repository.findById(id);

        if (categoria.isPresent()) {
            repository.deleteById(id);
            System.out.println("Eliminado");
        } else {
            System.out.println("No existe ID");
        }


    }
    public void editarCategoria(){
        listarCategoria();
        System.out.println("Ingrese el ID de la categoría a editar: ");
        long id = Integer.parseInt(cs.nextLine());

        Optional<Categoria> categoria = repository.findById(id);
        if (categoria.isPresent()) {
            String nuevoNombre;
            Categoria ca = categoria.get();
            System.out.println("Categoría: " + ca.getNombre());
            System.out.println("Ingrese el nuevo nombre de la categoría: ");
            nuevoNombre = cs.nextLine();

            if (nuevoNombre != null) {
                ca.setNombre(nuevoNombre);
                repository.save(ca);
                System.out.println("Editado");
            } else {
                System.out.println("El nombre no puede estar vacío");
            }
        } else {
            System.out.println("No existe ID");
        }
    }


    public void menu(){

        int opc = 0;
        String mensaje = "Seleccione una opción: \n1: Crear\n2: Listar\n3: Eliminar\n4: Editar\n5: Salir";
        System.out.println(mensaje);

        do {
            System.out.print("Opción: ");
            opc = Integer.parseInt(cs.nextLine());
            switch (opc) {
                case 1:
                    registroCategoria();
                    break;
                case 2:
                    listarCategoria();
                    break;
                case 3:
                    eliminarCategoria();
                    break;
                case 4:
                    editarCategoria();
                    break;
            }
            if(opc != 5){
                System.out.println(mensaje);
            }
        } while (opc != 5);
    }

}
