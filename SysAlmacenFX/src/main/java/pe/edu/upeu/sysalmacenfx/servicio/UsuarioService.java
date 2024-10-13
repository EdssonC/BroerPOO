package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.modelo.Usuario;
import pe.edu.upeu.sysalmacenfx.repositorio.ProductoRepository;
import pe.edu.upeu.sysalmacenfx.repositorio.UsuarioRepository;

import java.util.List;

public class UsuarioService {
    @Autowired
    UsuarioRepository repo;


    public Usuario saveUsuario(Usuario to) {
        return repo.save(to);
    }

    public List<Usuario> listUsuario() {
        return repo.findAll();
    }

    public Usuario updateUsuario(Usuario to, Long id) {
        try {
            Usuario toe = repo.findById(id).get();
            if (toe != null) {
                toe.setUser(to.getUser());
            }
            return repo.save(toe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    public Usuario updateUsuario(Usuario to) {
        return repo.save(to);
    }

    public void deleteUsuario(Long id) {
        repo.deleteById(id);
    }
    public Usuario searchByIdUsuario(Long id){
        return repo.findById(id).get();
    }
}
