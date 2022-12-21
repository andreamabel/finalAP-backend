
package com.portfolio.amba.Interface;

import com.portfolio.amba.Entity.Persona;
import java.util.List;

public interface IPersonaService {
    //Traer persona
    public List<Persona> getPersona();
    
    //Guardar objeto de tipo Persona
    public void savePersona(Persona persona);
    
    //Eliminar un objeto por ID
    public void deletePersona(Long id);
    
    //Buscar una Persona por ID
    public Persona findPersona(Long id);
}
