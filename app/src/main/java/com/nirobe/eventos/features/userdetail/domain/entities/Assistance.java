package com.nirobe.eventos.features.userdetail.domain.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by NIROBE on 13/02/2018.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "documentoIdentidad",
        "fecha"
})
public class Assistance {

    @JsonProperty("documentoIdentidad")
    private Integer documentoIdentidad;
    @JsonProperty("fecha")
    private String fecha;

    @JsonProperty("documentoIdentidad")
    public Integer getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    @JsonProperty("documentoIdentidad")
    public void setDocumentoIdentidad(Integer documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    @JsonProperty("fecha")
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @JsonProperty("fecha")
    public String getFecha() {
        return fecha;
    }

}
