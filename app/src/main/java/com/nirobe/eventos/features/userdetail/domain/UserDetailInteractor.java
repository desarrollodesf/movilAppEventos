package com.nirobe.eventos.features.userdetail.domain;

import com.nirobe.eventos.features.userdetail.data.UserDetailRepository;

public interface UserDetailInteractor {

    void setCallback(UserDetailRepository.Callback userDetailCallback);

    void searchUserByDocument(String document);

    void addAttendant(String ciudad, String cooperativa, String correoElectronico, String departamento,
                      String direccion, String documento, String nombre, String numeroCelular,
                      String numeroTelefonico, String observaciones);

    void checkAssitance(String documentNumber);

    void searchUserHistory(String document);
}