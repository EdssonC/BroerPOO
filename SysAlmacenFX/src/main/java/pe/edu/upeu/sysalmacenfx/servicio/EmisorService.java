package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.Emisor;
import pe.edu.upeu.sysalmacenfx.repositorio.EmisorRepository;

import java.util.List;
@Service
public class EmisorService {
    @Autowired
    EmisorRepository repo;


    public Emisor saveEmisor(Emisor to) {
        return repo.save(to);
    }

    public List<Emisor> listEmisor() {
        return repo.findAll();
    }

    public Emisor updateEmisor(Emisor to, Long id) {
        try {
            Emisor toe = repo.findById(id).get();
            if (toe != null) {
                toe.setNombreComercial(to.getNombreComercial());
            }
            return repo.save(toe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    public Emisor updateEmisor(Emisor to) {
        return repo.save(to);
    }

    public void deleteEmisor(Long id) {
        repo.deleteById(id);
    }
    public Emisor searchByIdEmisor(Long id){
        return repo.findById(id).get();
    }
}
