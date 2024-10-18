package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.CompraDetalle;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.repositorio.CompraDetalleRepository;
import pe.edu.upeu.sysalmacenfx.repositorio.ProductoRepository;

import java.util.List;
@Service
public class CompraDetalleService {
    @Autowired
    CompraDetalleRepository repo;


    public CompraDetalle saveCompraDetalle(CompraDetalle to) {
        return repo.save(to);
    }

    public List<CompraDetalle> listCompraDetalle() {
        return repo.findAll();
    }

    public CompraDetalle updateCompraDetalle(CompraDetalle to, Long id) {
        try {
            CompraDetalle toe = repo.findById(id).get();
            if (toe != null) {
                toe.setProducto(to.getProducto());
            }
            return repo.save(toe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    public CompraDetalle updateCompraDetalle(CompraDetalle to) {
        return repo.save(to);
    }

    public void deleteCompraDetalle(Long id) {
        repo.deleteById(id);
    }
    public CompraDetalle searchByIdCompraDetalle(Long id){
        return repo.findById(id).get();
    }
}
