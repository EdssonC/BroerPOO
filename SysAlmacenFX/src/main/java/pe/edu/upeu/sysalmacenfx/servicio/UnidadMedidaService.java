package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Marca;
import pe.edu.upeu.sysalmacenfx.modelo.UnidadMedida;
import pe.edu.upeu.sysalmacenfx.repositorio.MarcaRepository;
import pe.edu.upeu.sysalmacenfx.repositorio.UnidadMedidaRepository;

import java.util.ArrayList;
import java.util.List;

public class UnidadMedidaService {
    @Autowired
    UnidadMedidaRepository repo;


    public UnidadMedida saveUnidadMedida(UnidadMedida to) {
        return repo.save(to);
    }

    public List<UnidadMedida> listUnidadMedida() {
        return repo.findAll();
    }

    public UnidadMedida updateUnidadMedida(UnidadMedida to, Long id) {
        try {
            UnidadMedida toe = repo.findById(id).get();
            if (toe != null) {
                toe.setNombreMedida(to.getNombreMedida());
            }
            return repo.save(toe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    public UnidadMedida updateUnidadMedida(UnidadMedida to) {
        return repo.save(to);
    }

    public void deleteUnidadMedida(Long id) {
        repo.deleteById(id);
    }
    public UnidadMedida searchByIdUnidadMedida(Long id){
        return repo.findById(id).get();
    }
    public List<ComboBoxOption> listaUnidadMedidaCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();
        for (UnidadMedida cate : repo.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdUnidad()),
                    cate.getNombreMedida()
            ));
        }
        return listar;
    }
}
