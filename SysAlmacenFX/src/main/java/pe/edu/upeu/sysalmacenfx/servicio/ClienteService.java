package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.Cliente;
import pe.edu.upeu.sysalmacenfx.repositorio.ClienteRepository;

import java.util.List;
@Service
public class ClienteService {
    @Autowired
    ClienteRepository repo;


    public Cliente saveCliente(Cliente to) {
        return repo.save(to);
    }

    public List<Cliente> listCliente() {
        return repo.findAll();
    }

    public Cliente updateCliente(Cliente to, Long id) {
        try {
            Cliente toe = repo.findById(id).get();
            if (toe != null) {
                toe.setNombres(to.getNombres());
            }
            return repo.save(toe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    public Cliente updateCliente(Cliente to) {
        return repo.save(to);
    }

    public void deleteCliente(Long id) {
        repo.deleteById(id);
    }
    public Cliente searchByIdCliente(Long id){
        return repo.findById(id).get();
    }
}
