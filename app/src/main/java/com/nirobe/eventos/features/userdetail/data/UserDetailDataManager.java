package com.nirobe.eventos.features.userdetail.data;

import android.content.Context;

import com.google.gson.Gson;
import com.nirobe.eventos.features.userdetail.data.service.EventoService;
import com.nirobe.eventos.features.userdetail.domain.entities.Assistance;
import com.nirobe.eventos.features.userdetail.domain.entities.Attendant;
import com.nirobe.eventos.features.userdetail.domain.entities.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDetailDataManager implements UserDetailRepository {


    private final static String NO_VALIDO = "305";
    private final static String NO_VALID_EVENT = "306";
    private final static String CONFIRMAR = "200";
    public final static String ASISTENCIA_EXISTENTE = "307";


    private UserDetailRepository.Callback userDetailRepositoryCallback;
    private Context context;
    private static String BASE_URL = "http://webappev.azurewebsites.net/";
    public UserDetailDataManager(Context context) {
       this.context = context;
    }

    @Override
    public void setCallback(UserDetailRepository.Callback userDetailRepositoryCallback) {
        this.userDetailRepositoryCallback =
                userDetailRepositoryCallback;
    }


    @Override
    public void searchUserHistory(String document) {
        if (!document.isEmpty()){

            Gson gson = new Gson();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
            EventoService restClient = retrofit.create(EventoService.class);
            Call<ArrayList<Event>> attendantCall = restClient.getUserHistoryById(document);
            attendantCall.enqueue(new retrofit2.Callback<ArrayList<Event>>() {
                @Override
                public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                    switch (response.code()) {
                        case 200:
                            if(response.body()==null){
                                userDetailRepositoryCallback.noEventPersonFound();

                            }else{
                                ArrayList<Event> data = response.body();
                                if(data!=null){
                                    userDetailRepositoryCallback.setInfoEventsPerson(data);
                                }
                                /*ArrayList<Event> events = new ArrayList<>();
                                Event event = new Event();
                                event.setIdEvento(1);
                                event.setNombreEvento("Primero");
                                events.add(event);
                                event = new Event();
                                event.setIdEvento(2);
                                event.setNombreEvento("Segundo");
                                events.add(event);
                                event = new Event();
                                event.setIdEvento(3);
                                event.setNombreEvento("Tercero");
                                events.add(event);
                                event = new Event();
                                event.setIdEvento(4);
                                event.setNombreEvento("Primero");
                                events.add(event);**/
                            }
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                    userDetailRepositoryCallback.errorCommunication();
                }
            });
        }else{
            userDetailRepositoryCallback.emptyDocumentNumber();
        }
    }

    @Override
    public void searchUserByDocument(String document) {
        if (!document.isEmpty()){

            Gson gson = new Gson();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
            EventoService restClient = retrofit.create(EventoService.class);
            Call<Attendant> attendantCall = restClient.getUserById(document);
            attendantCall.enqueue(new retrofit2.Callback<Attendant>() {
                @Override
                public void onResponse(Call<Attendant> call, Response<Attendant> response) {
                    switch (response.code()) {
                        case 200:
                            if(response.body()==null){
                                userDetailRepositoryCallback.noInfoPersonFound();
                            }else{
                                Attendant data = response.body();
                                if(data!=null){
                                    userDetailRepositoryCallback.setFieldsInfoPerson(data.getCiudad(), data.getCooperativa(),
                                            data.getCorreoElectronico(), data.getDepartamento(), data.getDireccionDeNotificacion(),
                                            data.getDocumentoIdentidad(), data.getNombreParticipante(), data.getTelefonoCelular(),
                                            data.getTelefonoFijo(), data.getObservaciones());
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onFailure(Call<Attendant> call, Throwable t) {
                    userDetailRepositoryCallback.errorCommunication();
                }
            });
        }else{
            userDetailRepositoryCallback.emptyDocumentNumber();
        }
    }

    @Override
    public void addAttendant(String ciudad, String cooperativa, String correoElectronico, String departamento,
                             String direccion, String documento, String nombre, String numeroCelular,
                             String numeroTelefonico, String observaciones) {

        if(documento.isEmpty()){
            userDetailRepositoryCallback.emptyDocumentNumberToAdd();
        }else if(cooperativa.isEmpty()||correoElectronico.isEmpty()||nombre.isEmpty()||numeroCelular.isEmpty()){

            String message = "Los siguientes campos son requeridos:"+"\n";
            if(cooperativa.isEmpty()){
                message+= "Cooperativa"+"\n";
            }
            if(correoElectronico.isEmpty()){
                message+= "Correo Electronico"+"\n";
            }
            if(nombre.isEmpty()){
                message+= "Nombre"+"\n";
            }
            if(numeroCelular.isEmpty()){
                message+= "Numero Celular"+"\n";
            }
            userDetailRepositoryCallback.emptyFieldsAdd(message);
        }else{

            Gson gson = new Gson();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();

            Attendant attendant = new Attendant();
            attendant.setCiudad(ciudad);
            attendant.setCooperativa(cooperativa);
            attendant.setCorreoElectronico(correoElectronico);
            attendant.setDepartamento(departamento);
            attendant.setDireccionDeNotificacion(direccion);
            attendant.setDocumentoIdentidad(Integer.parseInt(documento));
            attendant.setNombreParticipante(nombre);
            attendant.setTelefonoCelular(numeroCelular);
            attendant.setTelefonoFijo(numeroTelefonico);
            attendant.setObservaciones(observaciones);
            attendant.setMarcaTemporal(new Date().toString());

            EventoService restClient = retrofit.create(EventoService.class);
            Call<String> attendantCall = restClient.addAttendant(attendant);
            attendantCall.enqueue(new retrofit2.Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    switch (Objects.requireNonNull(response.body())) {
                        case CONFIRMAR:
                            userDetailRepositoryCallback.addAttendantConfirm(true);
                            break;
                        case NO_VALIDO:
                            userDetailRepositoryCallback.addAttendantConfirm(false);
                            break;
                        case NO_VALID_EVENT:
                            userDetailRepositoryCallback.addAttendantConfirm(false);
                            break;
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    userDetailRepositoryCallback.addAttendantConfirm(false);
                }
            });
        }
    }

    @Override
    public void checkAssitance(String documentNumber) {

        Gson gson = new Gson();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        EventoService restClient = retrofit.create(EventoService.class);
        Assistance assistance = new Assistance();
        GregorianCalendar date = new GregorianCalendar();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);

        assistance.setFecha(reportDate);
        assistance.setDocumentoIdentidad(Integer.parseInt(documentNumber));
        Call<String> assistanceCall = restClient.sendAssistance(assistance);
        assistanceCall.enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (Objects.requireNonNull(response.body())) {
                    case CONFIRMAR:
                        userDetailRepositoryCallback.assitanceConfirm(true, "0");
                        break;
                    case NO_VALIDO:
                        userDetailRepositoryCallback.assitanceConfirm(false, "0");
                        break;
                    case NO_VALID_EVENT:
                        userDetailRepositoryCallback.assitanceConfirm(false, "0");
                        break;
                    case ASISTENCIA_EXISTENTE:
                        userDetailRepositoryCallback.assitanceConfirm(false, ASISTENCIA_EXISTENTE);
                        break;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                userDetailRepositoryCallback.assitanceConfirm(false, ASISTENCIA_EXISTENTE);

            }
        });
    }

}