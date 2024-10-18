package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.CompCarrito;

import pe.edu.upeu.sysalmacenfx.repositorio.CompCarritoRepository;

import java.util.List;
@Service
public class CompCarritoService {
    @Autowired
    CompCarritoRepository repo;


    public CompCarrito saveCompCarrito(CompCarrito to) {
        return repo.save(to);
    }

    public List<CompCarrito> listCompCarrito() {
        return repo.findAll();
    }

    public CompCarrito updateCompCarrito(CompCarrito to, Long id) {
        try {
            CompCarrito toe = repo.findById(id).get();
            if (toe != null) {
                toe.setNombreProducto(to.getNombreProducto());
            }
            return repo.save(toe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    public CompCarrito updateCompCarrito(CompCarrito to) {
        return repo.save(to);
    }

    public void deleteCompCarrito(Long id) {
        repo.deleteById(id);
    }
    public CompCarrito searchByIdCompCarrito(Long id){
        return repo.findById(id).get();
    }
}
