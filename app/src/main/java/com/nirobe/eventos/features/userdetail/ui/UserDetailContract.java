package com.nirobe.eventos.features.userdetail.ui;

import android.support.v7.widget.RecyclerView;

import com.nirobe.eventos.features.userdetail.ui.adapter.EventsAdapter;

public interface UserDetailContract{

    interface View{

        void emptyDocumentName();

        void noInfoPersonFound();

        void setFieldsInfoPerson(String ciudad, String cooperativa, String correoElectronico, String departamento, String direccion, Integer documento, String nombre, String numeroCelular, String numeroTelefonico, String observaciones);

        void errorCommunication();

        void emptyDocumentNumberToAdd();

        void assitanceConfirm(boolean isConfirmed, String asistenciaExistente);

        void addAttendantConfirm(boolean isAdded);

        void emptyFieldsAdd(String message);

        void noEventPersonFound();

        void eventsListShow();
    }

    interface Presenter{
        void onDestroy();

        void searchUserByDocument(String document);

        void addAttendant(String ciudad, String cooperativa, String correoElectronico,
                          String departamento, String direccion, String documento, String nombre,
                          String numeroCelular, String numeroTelefonico, String observaciones);

        void checkAssitance(String documentNumber);

        void searchUserHistory(String document);

        void setEventsAdapter(EventsAdapter eventsAdapter);
    }

}