package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.Perfil;
import pe.edu.upeu.sysalmacenfx.repositorio.PerfilRepository;

import java.util.List;
@Service
public class PerfilService {
    @Autowired
    PerfilRepository repo;


    public Perfil savePerfil(Perfil to) {
        return repo.save(to);
    }

    public List<Perfil> listPerfil() {
        return repo.findAll();
    }

    public Perfil updatePerfil(Perfil to, Long id) {
        try {
            Perfil toe = repo.findById(id).get();
            if (toe != null) {
                toe.setNombre(to.getNombre());
            }
            return repo.save(toe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    public Perfil updatePerfil(Perfil to) {
        return repo.save(to);
    }

    public void deletePerfil(Long id) {
        repo.deleteById(id);
    }
    public Perfil searchByIdPerfil(Long id){
        return repo.findById(id).get();
    }
}
