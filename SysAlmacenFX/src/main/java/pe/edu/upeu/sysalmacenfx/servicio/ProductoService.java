package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.modelo.UnidadMedida;
import pe.edu.upeu.sysalmacenfx.repositorio.ProductoRepository;
import pe.edu.upeu.sysalmacenfx.repositorio.UnidadMedidaRepository;

import java.util.List;

public class ProductoService {
    @Autowired
    ProductoRepository repo;


    public Producto saveProducto(Producto to) {
        return repo.save(to);
    }

    public List<Producto> listProducto() {
        return repo.findAll();
    }

    public Producto updateProducto(Producto to, Long id) {
        try {
            Producto toe = repo.findById(id).get();
            if (toe != null) {
                toe.setNombre(to.getNombre());
            }
            return repo.save(toe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    public Producto updateProducto(Producto to) {
        return repo.save(to);
    }

    public void deleteProducto(Long id) {
        repo.deleteById(id);
    }
    public Producto searchByIdProducto(Long id){
        return repo.findById(id).get();
    }
}
