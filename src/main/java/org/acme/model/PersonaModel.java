package org.acme.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

//import javax.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "TBL_PERSONA_MA")
public class PersonaModel extends PanacheEntityBase {
    @Id
    public Long npersona_id;
    public String npersona_idtipdoc;
    public String cpersona_nrodoc;
    public String cpersona_apepaterno;
    public String cpersona_apematerno;
    public String cpersona_nombres;
    public LocalDate dpersona_fnacimiento;
    public String cpersona_razonsocial;
    public String cpersona_dscreferencia;
    public String cpersona_dscdireccion;
    public String cpersona_dscreprelegal;
    public String cpersona_cdepartdomic;
    public String cpersona_cprovdomic;
    public String cpersona_cdistdomic;
    public String cpersona_dsctelef;
    public String cpersona_dscmovil;
    public String cpersona_dsccorreo;
    public String caudi_usr_ins;
    public LocalDate daudi_fec_ins;
    public String caudi_usr_upd;
    public LocalDate daudi_fec_upd;
    public String cpersona_prinommater;
    public LocalDate dpersona_femidoc;
    public String cpersona_codsexo;

}