package com.nirobe.eventos.features.userdetail.data.service;

import com.nirobe.eventos.features.userdetail.domain.entities.Assistance;
import com.nirobe.eventos.features.userdetail.domain.entities.Attendant;
import com.nirobe.eventos.features.userdetail.domain.entities.Event;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by NIROBE on 6/02/2018.
 */

public interface EventoService {

    @Headers("Content-type: application/json")
    @GET("Guest/GetByDocumentoDeIdentidad")
    Call<Attendant> getUserById(@Query("documentoDeIdentidad") String userDocument);

    @Headers("Content-type: application/json")
    @POST("Guest/AddGuest")
    Call<String> addAttendant(@Body Attendant body);

    @Headers("Content-type: application/json")
    @POST("Event/AddGuestToEvent")
    Call<String> sendAssistance(@Body Assistance body);

    @Headers("Content-type: application/json")
    @GET("Guest/GetEventsByDocumentoDeIdentidad")
    Call<ArrayList<Event>> getUserHistoryById(@Query("documentoDeIdentidad") String userDocument);
}
