package pe.edu.upeu.sysalmacenfx.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ModeloDataAutocomplet;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.repositorio.ProductoRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductoService {
    @Autowired
    ProductoRepository repo;

    Logger logger= LoggerFactory.getLogger(ProductoService.class);

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
        return repo.findById(id).orElse(null);
    }

    public List<ModeloDataAutocomplet> listAutoCompletProducto() {
        List<ModeloDataAutocomplet> listarProducto = new ArrayList<>();
        try {
            for (Producto producto : repo.findAll()) {
                ModeloDataAutocomplet data = new ModeloDataAutocomplet();
                data.setIdx(String.valueOf(producto.getIdProducto()));
                data.setNameDysplay(producto.getNombre());
                data.setOtherData(producto.getPu() + ":" + producto.getStock());
                listarProducto.add(data);
            }
        } catch (Exception e) {
            logger.error("Error al realizar la busqueda", e);
        }
        return listarProducto;
    }

}
