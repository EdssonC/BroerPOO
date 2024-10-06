package pe.edu.upeu.calcfx.service;

import pe.edu.upeu.calcfx.modelo.CalcTO;

import java.util.List;

public interface CalcServiceI {

    public List<CalcTO> obtenerResultados();
    public void guardarResultados(CalcTO to);
    public void actualizarResultados(CalcTO to, long index);
    public void eliminarResultados(long index);
}
