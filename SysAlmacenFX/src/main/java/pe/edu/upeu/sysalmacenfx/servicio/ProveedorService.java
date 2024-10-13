package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.modelo.Proveedor;
import pe.edu.upeu.sysalmacenfx.repositorio.ProductoRepository;
import pe.edu.upeu.sysalmacenfx.repositorio.ProveedorRepository;

import java.util.List;

public class ProveedorService {
    @Autowired
    ProveedorRepository repo;


    public Proveedor saveProveedor(Proveedor to) {
        return repo.save(to);
    }

    public List<Proveedor> listProveedor() {
        return repo.findAll();
    }

    public Proveedor updateProveedor(Proveedor to, Long id) {
        try {
            Proveedor toe = repo.findById(id).get();
            if (toe != null) {
                toe.setNombresRaso(to.getNombresRaso());
            }
            return repo.save(toe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    public Proveedor updateProveedor(Proveedor to) {
        return repo.save(to);
    }

    public void deleteProveedor(Long id) {
        repo.deleteById(id);
    }
    public Proveedor searchByIdProveedor(Long id){
        return repo.findById(id).get();
    }
}
