package br.com.sti.gtf.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Phelipe Melanias
 */
public enum VehicleStatus implements Serializable {
    EM_USO("Em uso"), DISPONIVEL_USO("Disponível para uso"), DISPONIVEL_VENDA("Disponível para venda"), VENDIDO("Vendido");

    private final String status;

    private VehicleStatus(String status) {
        this.status = status;
    }

    //getters
    public String getStatus() { return status; }
    public static List<VehicleStatus> getAll() { return Arrays.asList(values()); }
}