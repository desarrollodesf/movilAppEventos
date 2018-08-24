package com.nirobe.eventos.features.userdetail.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nirobe.eventos.R;
import com.nirobe.eventos.features.userdetail.UserDetailPresenter;
import com.nirobe.eventos.features.userdetail.data.UserDetailDataManager;
import com.nirobe.eventos.features.userdetail.domain.UserDetail;
import com.nirobe.eventos.features.userdetail.ui.adapter.EventsAdapter;
import com.nirobe.eventos.features.userdetail.ui.adapter.EventsListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserDetailActivity extends AppCompatActivity implements UserDetailContract.View{

    private static final int BUSQUEDA = 0;
    private static final int REGISTRO = 1;
    private static final int AGREGAR = 2;
    private static final int HISTORY = 3;

    @BindView(R.id.search)
    FloatingActionButton mSearch;
    @BindView(R.id.add)
    FloatingActionButton mAdd;
    @BindView(R.id.clear)
    FloatingActionButton mClear;
    @BindView(R.id.save)
    FloatingActionButton mSave;
    @BindView(R.id.refresh)
    FloatingActionButton mRefresh;
    @BindView(R.id.check)
    FloatingActionButton mCheck;
    @BindView(R.id.history)
    FloatingActionButton mHistory;
    @BindView(R.id.numeroDocumentoTIL)
    TextInputLayout mNumeroDocumentoTIL;
    @BindView(R.id.numeroDocumentoEditText)
    EditText mNumeroDocumentoEditText;

    @BindView(R.id.nombreTIL)
    TextInputLayout nombreTIL;
    @BindView(R.id.nombreEditText)
    EditText nombreEditText;
    @BindView(R.id.cooperativaTIL)
    TextInputLayout cooperativaTIL;
    @BindView(R.id.cooperativaEditText)
    EditText cooperativaEditText;
    @BindView(R.id.departamentoIL)
    TextInputLayout departamentoIL;
    @BindView(R.id.departamentoEditText)
    AutoCompleteTextView departamentoEditText;
    @BindView(R.id.ciudadTIL)
    TextInputLayout ciudadTIL;
    @BindView(R.id.ciudadEditText)
    AutoCompleteTextView ciudadEditText;
    @BindView(R.id.direccionIL)
    TextInputLayout direccionIL;
    @BindView(R.id.direccionEditText)
    EditText direccionEditText;
    @BindView(R.id.numeroTelefonicoTIL)
    TextInputLayout numeroTelefonicoTIL;
    @BindView(R.id.numeroTelefonicoEditText)
    EditText numeroTelefonicoEditText;
    @BindView(R.id.numeroCelularTIL)
    TextInputLayout numeroCelularTIL;
    @BindView(R.id.numeroCelularEditText)
    EditText numeroCelularEditText;
    @BindView(R.id.correoElectronicoTIL)
    TextInputLayout correoElectronicoTIL;
    @BindView(R.id.correoElectronicoEditText)
    EditText correoElectronicoEditText;
    @BindView(R.id.observacionesTIL)
    TextInputLayout observacionesTIL;
    @BindView(R.id.observacionesEditText)
    EditText observacionesEditText;

    @BindView(R.id.userInformation)
    RelativeLayout userInformation;
    @BindView(R.id.eventsRecyclerView)
    RecyclerView mEventsRecyclerView;
    @BindView(R.id.eventsList)
    RelativeLayout eventsList;

    private EventsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Context context;
    private UserDetailContract.Presenter userDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);
        context=this;
        visibleFields(View.GONE);
        userDetailPresenter = new UserDetailPresenter(this,
                new UserDetail(new UserDetailDataManager(context)));

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mEventsRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mEventsRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventsAdapter();
        mEventsRecyclerView.setAdapter(mAdapter);

        userDetailPresenter.setEventsAdapter(mAdapter);

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(UserDetailActivity.this,
                    new String[]{
                            Manifest.permission.INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    public void visibleFields(int isVisible){
        nombreTIL.setVisibility(isVisible);
        ciudadTIL.setVisibility(isVisible);
        direccionIL.setVisibility(isVisible);
        numeroTelefonicoTIL.setVisibility(isVisible);
        numeroCelularTIL.setVisibility(isVisible);
        correoElectronicoTIL.setVisibility(isVisible);
        cooperativaTIL.setVisibility(isVisible);
        observacionesTIL.setVisibility(isVisible);
        departamentoIL.setVisibility(isVisible);
    }

    public void cleanFields(){
        mNumeroDocumentoEditText.setText("");
        nombreEditText.setText("");
        cooperativaEditText.setText("");
        ciudadEditText.setText("");
        direccionEditText.setText("");
        numeroTelefonicoEditText.setText("");
        numeroCelularEditText.setText("");
        correoElectronicoEditText.setText("");
        observacionesEditText.setText("");
        departamentoEditText.setText("");
        mNumeroDocumentoTIL.setErrorEnabled(false);
    }

    public void setFocusableFields(boolean isFocus){
        nombreEditText.setFocusable(isFocus);
        cooperativaEditText.setFocusable(isFocus);
        ciudadEditText.setFocusable(isFocus);
        direccionEditText.setFocusable(isFocus);
        numeroTelefonicoEditText.setFocusable(isFocus);
        numeroCelularEditText.setFocusable(isFocus);
        correoElectronicoEditText.setFocusable(isFocus);
        observacionesEditText.setFocusable(isFocus);
        departamentoEditText.setFocusable(isFocus);
        setTouchFields(isFocus);
    }

    public void setTouchFields(boolean isFocus){
        nombreEditText.setFocusableInTouchMode(isFocus);
        cooperativaEditText.setFocusableInTouchMode(isFocus);
        ciudadEditText.setFocusableInTouchMode(isFocus);
        direccionEditText.setFocusableInTouchMode(isFocus);
        numeroTelefonicoEditText.setFocusableInTouchMode(isFocus);
        numeroCelularEditText.setFocusableInTouchMode(isFocus);
        correoElectronicoEditText.setFocusableInTouchMode(isFocus);
        observacionesEditText.setFocusableInTouchMode(isFocus);
        departamentoEditText.setFocusableInTouchMode(isFocus);
    }

    public void showSearchButtons(int searchMode){
        switch (searchMode) {
            case BUSQUEDA:
                mHistory.setVisibility(View.VISIBLE);
                mAdd.setVisibility(View.VISIBLE);
                mSearch.setVisibility(View.VISIBLE);
                mSave.setVisibility(View.INVISIBLE);
                mClear.setVisibility(View.INVISIBLE);
                mRefresh.setVisibility(View.INVISIBLE);
                mCheck.setVisibility(View.INVISIBLE);
                break;
            case AGREGAR:
                mHistory.setVisibility(View.INVISIBLE);
                mAdd.setVisibility(View.INVISIBLE);
                mSearch.setVisibility(View.INVISIBLE);
                mSave.setVisibility(View.VISIBLE);
                mClear.setVisibility(View.VISIBLE);
                mRefresh.setVisibility(View.INVISIBLE);
                mCheck.setVisibility(View.INVISIBLE);
                break;
            case REGISTRO:
                mHistory.setVisibility(View.INVISIBLE);
                mAdd.setVisibility(View.INVISIBLE);
                mSearch.setVisibility(View.INVISIBLE);
                mSave.setVisibility(View.INVISIBLE);
                mClear.setVisibility(View.INVISIBLE);
                mRefresh.setVisibility(View.VISIBLE);
                mCheck.setVisibility(View.VISIBLE);
                break;
            case  HISTORY:

                unblockButtons(false);
                userDetailPresenter.searchUserHistory(mNumeroDocumentoEditText.getText().toString());
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDetailPresenter.onDestroy();
    }


    @OnClick(R.id.check)
    public void onCheckClick(View v){
        unblockButtons(false);
        userDetailPresenter.checkAssitance(mNumeroDocumentoEditText.getText().toString());
    }

    @OnClick(R.id.refresh)
    public void onRefreshClick(View v){
        setFocusableFields(false);
        visibleFields(View.GONE);
        cleanFields();
        showSearchButtons(BUSQUEDA);
    }

    @OnClick(R.id.search)
    public void onSearchClick(View v){
        unblockButtons(false);
        userDetailPresenter.searchUserByDocument(mNumeroDocumentoEditText.getText().toString());
    }

    @OnClick(R.id.add)
    public void onAddClick(View v){
        visibleFields(View.VISIBLE);
        cleanFields();
        setFocusableFields(true);
        showSearchButtons(AGREGAR);
    }

    @OnClick(R.id.history)
    public void onHistoryClick(View v){
        showSearchButtons(HISTORY);
    }

    @OnClick(R.id.clear)
    public void onClearClick(View v){
        userInformation.setVisibility(View.VISIBLE);
        eventsList.setVisibility(View.GONE);
        setFocusableFields(false);
        visibleFields(View.GONE);
        cleanFields();
        showSearchButtons(BUSQUEDA);
    }

    @OnClick(R.id.save)
    public void onSaveClick(View v){
        setTouchFields(false);
        unblockButtons(false);

        userDetailPresenter.addAttendant(
                ciudadEditText.getText().toString(),
                cooperativaEditText.getText().toString(),
                correoElectronicoEditText.getText().toString(),
                departamentoEditText.getText().toString(),
                direccionEditText.getText().toString(),
                mNumeroDocumentoEditText.getText().toString(),
                nombreEditText.getText().toString(),
                numeroCelularEditText.getText().toString(),
                numeroTelefonicoEditText.getText().toString(),
                observacionesEditText.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.readQR:
                if(mSearch.isFocusable()){
                    IntentIntegrator integrator = new IntentIntegrator(this);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                    integrator.setPrompt("Scan");
                    integrator.setCameraId(0);
                    integrator.setBeepEnabled(false);
                    integrator.setBarcodeImageEnabled(false);
                    integrator.initiateScan();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_SHORT).show();
            }else{
                mNumeroDocumentoEditText.setText(result.getContents());
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setFieldsInfoPerson(String ciudad, String cooperativa,String correoElectronico,
                                    String departamento, String direccion, Integer documento, String nombre,
                                    String numeroCelular, String numeroTelefonico, String observaciones) {

        visibleFields(View.VISIBLE);
        mNumeroDocumentoTIL.setErrorEnabled(false);
        ciudadEditText.setText(ciudad);
        cooperativaEditText.setText(cooperativa);
        correoElectronicoEditText.setText(correoElectronico);
        departamentoEditText.setText(departamento);
        direccionEditText.setText(direccion);
        mNumeroDocumentoEditText.setText(String.valueOf(documento));
        nombreEditText.setText(nombre);
        numeroCelularEditText.setText(numeroCelular);
        numeroTelefonicoEditText.setText(numeroTelefonico);
        observacionesEditText.setText(observaciones);
        unblockButtons(true);
        showSearchButtons(REGISTRO);
    }

    @Override
    public void errorCommunication() {
        visibleFields(View.GONE);
        mNumeroDocumentoTIL.setErrorEnabled(true);
        mNumeroDocumentoTIL.setError("Error en la comunicación");
        unblockButtons(true);
    }

    @Override
    public void emptyDocumentNumberToAdd() {
        mNumeroDocumentoTIL.setErrorEnabled(true);
        mNumeroDocumentoTIL.setError("Debe ingresar un numero de documento");
        unblockButtons(true);
        setFocusableFields(true);
    }

    @Override
    public void assitanceConfirm(boolean isConfirmed, String asistenciaExistente) {
        unblockButtons(true);

        if(isConfirmed){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            alertDialogBuilder.setTitle("Aviso");
            alertDialogBuilder
                    .setMessage("Se realizó la confirmacion de asistencia exitosamente.")
                    .setCancelable(false)
                    .setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.dismiss();
                            setFocusableFields(false);
                            visibleFields(View.GONE);
                            cleanFields();
                            showSearchButtons(BUSQUEDA);
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }else{

            if(asistenciaExistente.equals(UserDetailDataManager.ASISTENCIA_EXISTENTE)){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                alertDialogBuilder.setTitle("Advertencia!");
                alertDialogBuilder
                        .setMessage("Ya se encuentra registrada la persona al evento.")
                        .setCancelable(false)
                        .setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                alertDialogBuilder.setTitle("Advertencia!");
                alertDialogBuilder
                        .setMessage("No se realizó la confirmacion de asistencia.")
                        .setCancelable(false)
                        .setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }
        setFocusableFields(false);
        visibleFields(View.GONE);
        cleanFields();
        showSearchButtons(BUSQUEDA);
    }

    @Override
    public void addAttendantConfirm(boolean isAdded) {
        unblockButtons(true);
        if(isAdded){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            alertDialogBuilder.setTitle("Aviso");
            alertDialogBuilder
                    .setMessage("Se agrego la confirmacion de asistencia exitosamente.")
                    .setCancelable(false)
                    .setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.dismiss();
                            setFocusableFields(false);
                            visibleFields(View.GONE);
                            cleanFields();
                            showSearchButtons(BUSQUEDA);
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }else{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            alertDialogBuilder.setTitle("Advertencia!");
            alertDialogBuilder
                    .setMessage("No se agrego al evento la persona.")
                    .setCancelable(false)
                    .setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.dismiss();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    @Override
    public void emptyFieldsAdd(String message) {
        unblockButtons(true);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        alertDialogBuilder.setTitle("Advertencia!");
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        setFocusableFields(true);
    }

    @Override
    public void noEventPersonFound() {
        visibleFields(View.GONE);
        mNumeroDocumentoTIL.setErrorEnabled(true);
        mNumeroDocumentoTIL.setError("No se encontró información de eventos"+"\n"
                +"registrados para este numero de documento");
        unblockButtons(true);
    }

    @Override
    public void eventsListShow() {
        mAdd.setVisibility(View.INVISIBLE);
        mSearch.setVisibility(View.INVISIBLE);
        mSave.setVisibility(View.INVISIBLE);
        mClear.setVisibility(View.VISIBLE);
        mRefresh.setVisibility(View.INVISIBLE);
        mCheck.setVisibility(View.INVISIBLE);
        mHistory.setVisibility(View.INVISIBLE);

        userInformation.setVisibility(View.GONE);
        eventsList.setVisibility(View.VISIBLE);
        unblockButtons(true);
    }

    @Override
    public void emptyDocumentName() {
        visibleFields(View.GONE);
        mNumeroDocumentoTIL.setErrorEnabled(true);
        mNumeroDocumentoTIL.setError("Debe ingresar un numero de documento");
        unblockButtons(true);
    }

    @Override
    public void noInfoPersonFound(){
        visibleFields(View.GONE);
        mNumeroDocumentoTIL.setErrorEnabled(true);
        mNumeroDocumentoTIL.setError("No se encontró información de "+"\n"
                +"este numero de documento");
        unblockButtons(true);
    }

    public void unblockButtons(boolean block){
        mSearch.setClickable(block);
        mAdd.setClickable(block);
        mHistory.setClickable(block);
        mClear.setClickable(block);
        mSave.setClickable(block);
        if(!block){
            mNumeroDocumentoEditText.setFocusable(false);
            mNumeroDocumentoEditText.setFocusableInTouchMode(false);
        }else{
            mNumeroDocumentoEditText.setFocusable(true);
            mNumeroDocumentoEditText.setFocusableInTouchMode(true);
        }
    }

}