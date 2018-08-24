
package com.nirobe.eventos.features.userdetail.domain.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ciudad",
        "cooperativa",
        "correoElectronico",
        "departamento",
        "direccionDeNotificacion",
        "documentoIdentidad",
        "nombreParticipante",
        "telefonoFijo",
        "telefonoCelular",
        "observaciones"
})
public class Attendant {

    @JsonProperty("documentoIdentidad")
    private Integer documentoIdentidad;
    @JsonProperty("nombreParticipante")
    private String nombreParticipante;
    @JsonProperty("cooperativa")
    private String cooperativa;
    @JsonProperty("ciudad")
    private String ciudad;
    @JsonProperty("departamento")
    private String departamento;
    @JsonProperty("direccionDeNotificacion")
    private String direccionDeNotificacion;
    @JsonProperty("correoElectronico")
    private String correoElectronico;
    @JsonProperty("telefonoFijo")
    private String telefonoFijo;
    @JsonProperty("telefonoCelular")
    private String telefonoCelular;
    @JsonProperty("observaciones")
    private String observaciones;
    @JsonProperty("marcaTemporal")
    private String marcaTemporal;


    @JsonProperty("marcaTemporal")
    public String getMarcaTemporal() {
        return marcaTemporal;
    }

    @JsonProperty("marcaTemporal")
    public void setMarcaTemporal(String marcaTemporal) {
        this.marcaTemporal = marcaTemporal;
    }

    @JsonProperty("documentoIdentidad")
    public Integer getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    @JsonProperty("documentoIdentidad")
    public void setDocumentoIdentidad(Integer documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    @JsonProperty("nombreParticipante")
    public String getNombreParticipante() {
        return nombreParticipante;
    }

    @JsonProperty("nombreParticipante")
    public void setNombreParticipante(String nombreParticipante) {
        this.nombreParticipante = nombreParticipante;
    }

    @JsonProperty("cooperativa")
    public String getCooperativa() {
        return cooperativa;
    }

    @JsonProperty("cooperativa")
    public void setCooperativa(String cooperativa) {
        this.cooperativa = cooperativa;
    }

    @JsonProperty("ciudad")
    public String getCiudad() {
        return ciudad;
    }

    @JsonProperty("ciudad")
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @JsonProperty("departamento")
    public String getDepartamento() {
        return departamento;
    }

    @JsonProperty("departamento")
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @JsonProperty("direccionDeNotificacion")
    public String getDireccionDeNotificacion() {
        return direccionDeNotificacion;
    }

    @JsonProperty("direccionDeNotificacion")
    public void setDireccionDeNotificacion(String direccionDeNotificacion) {
        this.direccionDeNotificacion = direccionDeNotificacion;
    }

    @JsonProperty("correoElectronico")
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    @JsonProperty("correoElectronico")
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @JsonProperty("telefonoFijo")
    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    @JsonProperty("telefonoFijo")
    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    @JsonProperty("telefonoCelular")
    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    @JsonProperty("telefonoCelular")
    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    @JsonProperty("observaciones")
    public String getObservaciones() {
        return observaciones;
    }

    @JsonProperty("observaciones")
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
