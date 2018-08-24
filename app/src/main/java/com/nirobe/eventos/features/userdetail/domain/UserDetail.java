package com.nirobe.eventos.features.userdetail.domain;

import com.nirobe.eventos.features.userdetail.data.UserDetailRepository;



public class UserDetail implements UserDetailInteractor {

   private UserDetailRepository userDetailRepository;

   public UserDetail(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
   }

   @Override
   public void setCallback(UserDetailRepository.Callback userDetailCallback) {
           userDetailRepository.setCallback(userDetailCallback);
   }

    @Override
    public void searchUserByDocument(String document) {
        userDetailRepository.searchUserByDocument( document);
    }

    @Override
    public void addAttendant(String ciudad, String cooperativa, String correoElectronico,
                             String departamento, String direccion, String documento, String nombre,
                             String numeroCelular, String numeroTelefonico, String observaciones) {
        userDetailRepository.addAttendant( ciudad,  cooperativa,  correoElectronico,  departamento,
                direccion,  documento,  nombre,  numeroCelular,
                numeroTelefonico,  observaciones);

    }

    @Override
    public void checkAssitance(String documentNumber) {
        userDetailRepository.checkAssitance(documentNumber);
    }

    @Override
    public void searchUserHistory(String document) {
        userDetailRepository.searchUserHistory( document);

    }

}