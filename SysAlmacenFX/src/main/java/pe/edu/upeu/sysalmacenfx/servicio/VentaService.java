package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.modelo.Venta;
import pe.edu.upeu.sysalmacenfx.repositorio.ProductoRepository;
import pe.edu.upeu.sysalmacenfx.repositorio.VentaRepository;

import java.util.List;

public class VentaService {
    @Autowired
    VentaRepository repo;


    public Venta saveVenta(Venta to) {
        return repo.save(to);
    }

    public List<Venta> listVenta() {
        return repo.findAll();
    }

    public Venta updateVenta(Venta to, Long id) {
        try {
            Venta toe = repo.findById(id).get();
            if (toe != null) {
                toe.setUsuario(to.getUsuario());
            }
            return repo.save(toe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    public Venta updateVenta(Venta to) {
        return repo.save(to);
    }

    public void deleteVenta(Long id) {
        repo.deleteById(id);
    }
    public Venta searchByIdVenta(Long id){
        return repo.findById(id).get();
    }
}
