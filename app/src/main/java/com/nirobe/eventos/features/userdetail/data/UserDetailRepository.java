package com.nirobe.eventos.features.userdetail.data;


import com.nirobe.eventos.features.userdetail.domain.entities.Event;

import java.util.ArrayList;

public interface UserDetailRepository {

    void setCallback(UserDetailRepository.Callback userDetailRepositoryCallback);

    void searchUserByDocument(String document);

    void addAttendant(String ciudad, String cooperativa, String correoElectronico, String departamento, String direccion, String documento, String nombre, String numeroCelular, String numeroTelefonico, String observaciones);

    void checkAssitance(String documentNumber);

    void searchUserHistory(String document);


    interface Callback{

        void emptyDocumentNumber();

        void setFieldsInfoPerson(String ciudad, String cooperativa, String correoElectronico, String departamento, String direccion, Integer documento, String nombre, String numeroCelular, String numeroTelefonico, String observaciones);

        void noInfoPersonFound();

        void errorCommunication();

        void emptyDocumentNumberToAdd();

        void assitanceConfirm(boolean isConfirmed, String asistenciaExistente);

        void addAttendantConfirm(boolean isAdded);

        void emptyFieldsAdd(String message);

        void noEventPersonFound();

        void setInfoEventsPerson(ArrayList<Event> data);
    }

}