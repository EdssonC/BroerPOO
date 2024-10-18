package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.modelo.VentaDetalle;
import pe.edu.upeu.sysalmacenfx.repositorio.ProductoRepository;
import pe.edu.upeu.sysalmacenfx.repositorio.VentaDetalleRepository;

import java.util.List;
@Service
public class VentaDetalleService {
    @Autowired
    VentaDetalleRepository repo;


    public VentaDetalle saveVentaDetalle(VentaDetalle to) {
        return repo.save(to);
    }

    public List<VentaDetalle> listVentaDetalle() {
        return repo.findAll();
    }

    public VentaDetalle updateVentaDetalle(VentaDetalle to, Long id) {
        try {
            VentaDetalle toe = repo.findById(id).get();
            if (toe != null) {
                toe.setProducto(to.getProducto());
            }
            return repo.save(toe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    public VentaDetalle updateVentaDetalle(VentaDetalle to) {
        return repo.save(to);
    }

    public void deleteVentaDetalle(Long id) {
        repo.deleteById(id);
    }
    public VentaDetalle searchByIdVentaDetalle(Long id){
        return repo.findById(id).get();
    }
}
