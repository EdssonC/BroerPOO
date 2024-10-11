package pe.edu.upeu.sysalmacenfx.pruebas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.modelo.Categoria;
import pe.edu.upeu.sysalmacenfx.servicio.CategoriaService;

import java.util.List;
import java.util.Scanner;

@Component
public class MainX2 {
    @Autowired
    CategoriaService sC;
    Scanner cs = new Scanner(System.in);

    public void registroCategoria(){

        String categoria;
        Categoria ca = new Categoria();
        do {
            System.out.println("Introduzca categoría: ");
            categoria = cs.nextLine();
        } while(categoria == null);

        ca.setNombre(categoria);
        Categoria dd = sC.save(ca);
        System.out.println("Reporte: ");
        System.out.println(dd.getIdCategoria() + "  " + dd.getNombre());
    }

    public void listarCategoria(){
        List<Categoria> datos = sC.list();
        System.out.println("ID\tNombre ");
        for (Categoria ca: datos){
            System.out.println(ca.getIdCategoria() + "\t" + ca.getNombre());
        }
    }
    public void eliminarCategoria(){

        listarCategoria();
        System.out.println("Ingrese el ID de la categoría a borrar: ");
        long id = Integer.parseInt(cs.nextLine());

        sC.delete(id);
        System.out.println("Eliminado");
    }
    public void editarCategoria() {
        listarCategoria();
        System.out.println("Ingrese el ID de la categoria a editar: ");
        long id = Integer.parseInt(cs.nextLine());

        Categoria ca = sC.update(new Categoria(), id);
        if (ca != null) {
            System.out.println("Categoria: " + ca.getNombre());
            System.out.println("Ingrese el nuevo nombre de la categoria: ");
            String nuevoNombre = cs.nextLine();

            if (!nuevoNombre.isEmpty()) {
                ca.setNombre(nuevoNombre);
                sC.save(ca);
                System.out.println("Editado");
            } else {
                System.out.println("El nombre no puede estar vacío");
            }
        } else {
            System.out.println("No se encontró la categoría con el ID proporcionado.");
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
