package br.com.sti.gtf.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Phelipe Melanias
 */
public enum EmployeeStatus implements Serializable {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String status;

    private EmployeeStatus(String status) {
        this.status = status;
    }

    //getters
    public String getStatus() { return status; }
    public static List<EmployeeStatus> getAll() { return Arrays.asList(values()); }
}