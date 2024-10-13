package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.modelo.VentCarrito;
import pe.edu.upeu.sysalmacenfx.repositorio.ProductoRepository;
import pe.edu.upeu.sysalmacenfx.repositorio.VentCarritoRepository;

import java.util.List;

public class VentCarritoService {
    @Autowired
    VentCarritoRepository repo;


    public VentCarrito saveVentCarrito(VentCarrito to) {
        return repo.save(to);
    }

    public List<VentCarrito> listVentCarrito() {
        return repo.findAll();
    }

    public VentCarrito updateVentCarrito(VentCarrito to, Long id) {
        try {
            VentCarrito toe = repo.findById(id).get();
            if (toe != null) {
                toe.setUsuario(to.getUsuario());
            }
            return repo.save(toe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    public VentCarrito updateVentCarrito(VentCarrito to) {
        return repo.save(to);
    }

    public void deleteVentCarrito(Long id) {
        repo.deleteById(id);
    }
    public VentCarrito searchByIdVentCarrito(Long id){
        return repo.findById(id).get();
    }
}
