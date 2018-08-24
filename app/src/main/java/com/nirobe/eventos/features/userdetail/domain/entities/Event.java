package com.nirobe.eventos.features.userdetail.domain.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "idEvento",
        "nombreEvento"
})
public class Event {

    @JsonProperty("idEvento")
    private Integer idEvento;
    @JsonProperty("nombreEvento")
    private String nombreEvento;

    @JsonProperty("idEvento")
    public Integer getIdEvento() {
        return idEvento;
    }

    @JsonProperty("documentoIdentidad")
    public void setIdEvento(Integer documentoIdentidad) {
        this.idEvento = documentoIdentidad;
    }

    @JsonProperty("nombreEvento")
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    @JsonProperty("nombreEvento")
    public String getNombreEvento() {
        return nombreEvento;
    }
}
