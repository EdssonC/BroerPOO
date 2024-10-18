package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.Compra;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.repositorio.CompraRepository;

import java.util.List;
@Service
public class CompraService {
    @Autowired
    CompraRepository repo;


    public Compra saveCompra(Compra to) {
        return repo.save(to);
    }

    public List<Compra> listCompra() {
        return repo.findAll();
    }

    public Compra updateCompra(Compra to, Long id) {
        try {
            Compra toe = repo.findById(id).get();
            if (toe != null) {
                toe.setUsuario(to.getUsuario());
            }
            return repo.save(toe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    public Compra updateCompra(Compra to) {
        return repo.save(to);
    }

    public void deleteCompra(Long id) {
        repo.deleteById(id);
    }
    public Compra searchByIdCompra(Long id){
        return repo.findById(id).get();
    }
}
