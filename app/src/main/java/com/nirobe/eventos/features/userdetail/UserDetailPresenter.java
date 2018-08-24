package com.nirobe.eventos.features.userdetail;

import android.support.v7.widget.RecyclerView;

import com.nirobe.eventos.features.userdetail.data.UserDetailRepository;
import com.nirobe.eventos.features.userdetail.domain.UserDetailInteractor;
import com.nirobe.eventos.features.userdetail.domain.entities.Event;
import com.nirobe.eventos.features.userdetail.ui.UserDetailContract;
import com.nirobe.eventos.features.userdetail.ui.adapter.EventsAdapter;

import java.util.ArrayList;

public class UserDetailPresenter implements UserDetailContract.Presenter,
                UserDetailRepository.Callback {

   private UserDetailContract.View userDetailView;
   private UserDetailInteractor userDetailInteractor;
    private EventsAdapter eventsAdapter;

    public UserDetailPresenter(UserDetailContract.View userDetailView,
    UserDetailInteractor userDetailInteractor ) {
       this.userDetailView = userDetailView;
       this.userDetailInteractor = userDetailInteractor;
       userDetailInteractor.setCallback(this);
   }

    @Override
    public void onDestroy() {
        userDetailView=null;
    }

    @Override
    public void searchUserByDocument(String document) {
        userDetailInteractor.searchUserByDocument( document);
    }

    @Override
    public void addAttendant(String ciudad, String cooperativa, String correoElectronico, String departamento,
                             String direccion, String documento, String nombre, String numeroCelular,
                             String numeroTelefonico, String observaciones) {
        userDetailInteractor.addAttendant( ciudad,  cooperativa,  correoElectronico,  departamento,
                 direccion,  documento,  nombre,  numeroCelular,
                 numeroTelefonico,  observaciones);

    }

    @Override
    public void checkAssitance(String documentNumber) {
        userDetailInteractor.checkAssitance( documentNumber);
    }

    @Override
    public void searchUserHistory(String document) {
        userDetailInteractor.searchUserHistory( document);

    }

    @Override
    public void setEventsAdapter(EventsAdapter eventsAdapter) {
        this.eventsAdapter = eventsAdapter;
    }


    @Override
    public void emptyDocumentNumber() {
        userDetailView.emptyDocumentName();
    }

    @Override
    public void setFieldsInfoPerson(String ciudad, String cooperativa, String correoElectronico, String departamento,
                                    String direccion, Integer documento, String nombre, String numeroCelular,
                                    String numeroTelefonico, String observaciones) {
        userDetailView.setFieldsInfoPerson( ciudad,  cooperativa,  correoElectronico,  departamento,
                 direccion,  documento,  nombre,  numeroCelular,
                 numeroTelefonico,  observaciones);

    }

    @Override
    public void noInfoPersonFound() {
        userDetailView.noInfoPersonFound();
    }

    @Override
    public void errorCommunication() {
        userDetailView.errorCommunication();
    }

    @Override
    public void emptyDocumentNumberToAdd() {
        userDetailView.emptyDocumentNumberToAdd();
    }

    @Override
    public void assitanceConfirm(boolean isConfirmed, String asistenciaExistente) {
        userDetailView.assitanceConfirm( isConfirmed, asistenciaExistente);
    }

    @Override
    public void addAttendantConfirm(boolean isAdded) {
        userDetailView.addAttendantConfirm( isAdded);

    }

    @Override
    public void emptyFieldsAdd(String message) {
        userDetailView.emptyFieldsAdd(message);
    }

    @Override
    public void noEventPersonFound() {
        userDetailView.noEventPersonFound();
    }

    @Override
    public void setInfoEventsPerson(ArrayList<Event> data) {
        eventsAdapter.setEventsList(data);
        userDetailView.eventsListShow();
    }
}