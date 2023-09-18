package org.acme.bindy.ftp;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@RegisterForReflection
@FixedLengthRecord(length=18, paddingChar=' ')
public class Persona {

    @DataField(pos = 1, length=8)
    public String dni;

    @DataField(pos = 9, length=10)
    public String fechaNacimiento;


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setAuthor(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
        public String toString() {
            return "";
        }

}
