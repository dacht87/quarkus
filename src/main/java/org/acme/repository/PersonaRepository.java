package org.acme.repository;

import org.acme.model.PersonaModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PersonaRepository implements PanacheRepository<PersonaModel> {

    @Transactional
    public List<PersonaModel> findByTipoYNumeroDocumento(String tipoDocumento, String numeroDocumento) {
        System.out.println("npersona_idtipdoc = "+tipoDocumento+" and cpersona_nrodoc = "+numeroDocumento);
        return find("npersona_idtipdoc = ?1 and cpersona_nrodoc = ?2", tipoDocumento.trim(), numeroDocumento.trim()).list();
    }
}