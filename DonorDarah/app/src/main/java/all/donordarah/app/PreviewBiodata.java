package all.donordarah.app;
//PreviewBiodata.java muncul ketika memilih daftar donor darah. Ini adalah menu pertama saat daftar donor
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.widget.DatePicker;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class PreviewBiodata extends AppCompatActivity { //pembuatan variable
    private EditText nama, email, password, noHp, tglLahir, pekerjaan, golDarah, bb;
    private RadioButton L, P, A, B, AB, O;

    private Dialog loading, gagal;
    private Button btnCoba, btnTglLahir1;
    private TextView statusLoading;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_biodata);
        nama = (EditText) findViewById(R.id.nama);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        noHp = (EditText) findViewById(R.id.noHp);
        tglLahir = (EditText) findViewById(R.id.tglLahir);
        pekerjaan = (EditText) findViewById(R.id.pekerjaan);
        bb = (EditText) findViewById(R.id.bb);
        L = (RadioButton) findViewById(R.id.L);
        P = (RadioButton) findViewById(R.id.P);
        A = (RadioButton) findViewById(R.id.A);
        B = (RadioButton) findViewById(R.id.B);
        AB = (RadioButton) findViewById(R.id.AB);
        O = (RadioButton) findViewById(R.id.O);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        loading = new Dialog(this);
        loading.setContentView(R.layout.dialog_loading);
        statusLoading = (TextView) loading.findViewById(R.id.statusLoading);
        loading.setTitle("loading");
        loading.setCancelable(false);
        loading.setCanceledOnTouchOutside(false);
        gagal = new Dialog(this);
        gagal.setContentView(R.layout.dialog_gagal);
        gagal.setTitle("koneksi error");
        //gagal.setCancelable(false);
        //gagal.setCanceledOnTouchOutside(false);
        btnCoba = (Button) gagal.findViewById(R.id.btnCoba);

        btnCoba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dataPendonor();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        btnTglLahir1 = (Button) findViewById(R.id.btnTglLahir);
        btnTglLahir1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
    }
    public void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tglLahir.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            dataPendonor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void selanjutnya(View v){
        startActivity(new Intent(this, Kuisioner.class));
    } //menuju ke menu Kuisioner
    public void editBiodata(View v){
        startActivity(new Intent(this, Biodata.class));
    } //menuju ke menu Biodata untuk edit biodata
    public void kembali(View v) {
        finish();
    } //kembali ke menu utama atau home

    public void dataPendonor() throws Exception{
        RequestParams params = new RequestParams();
        params.put("aksi", "data"); //parameter yang akan dihubungkan ke daftardonor.php
        params.put("id", MenuUtama.idPendonor); //parameter id

        ServerClass.post("android/daftaruser.php", params,
                new JsonHttpResponseHandler() { //penggunaan JSON untuk respon ke daftaruser.php
                    @Override
                    public void onStart() {
                        // TODO Auto-generated method stub
                        super.onStart();
                        loading.show();
                        statusLoading.setText("mengambil data");
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONArray response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(statusCode, headers, response);
                        try {
                            loading.dismiss();
                            gagal.dismiss();
                            if (response.isNull(0)) { //jika biodata belum terisi
                                Toast.makeText(PreviewBiodata.this, "belum ada data", Toast.LENGTH_SHORT).show();
                            } else {
                                JSONObject jsonObject = response
                                            .getJSONObject(0);
                                if (!jsonObject.isNull("selisih"))
                                { //untuk melihat selisih antara donor selanjutnya dengan donor terakhir
                                    if (jsonObject.getInt("selisih") < 0) { //jika belum waktunya mendonor, akan langsung didirect menuju menu utama
                                        Toast.makeText(PreviewBiodata.this, "Belum saatnya mendonor!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                } //ambil data pendonor dari database dengan parameter dari daftaruser.php
                                nama.setText(jsonObject.getString("nama"));
                                noHp.setText(jsonObject.getString("no_hp"));
                                email.setText(jsonObject.getString("email"));
                                password.setText(jsonObject.getString("password"));
                                tglLahir.setText(jsonObject.getString("tgl_lhr"));
                                pekerjaan.setText(jsonObject.getString("pekerjaan"));
                                bb.setText(jsonObject.getString("berat_badan"));
                                if(jsonObject.getString("gol_dar").equals("A")){
                                    A.setChecked(true);
                                }else if(jsonObject.getString("gol_dar").equals("B")){
                                    B.setChecked(true);
                                }else if(jsonObject.getString("gol_dar").equals("AB")){
                                    AB.setChecked(true);
                                }else{
                                    O.setChecked(true);
                                }
                                if(jsonObject.getString("jk").equals("Pria")){
                                    L.setChecked(true);
                                }else{
                                    P.setChecked(true);
                                }
                            }
                        } catch (Exception e) {
                            // TODOhandle exception
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          String responseString, Throwable throwable) {
                        // TODO Auto-generated method stub
                        super.onFailure(statusCode, headers, responseString,
                                throwable);
                        loading.dismiss();
                        gagal.show();
                        btnCoba.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                try {
                                    dataPendonor();
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          Throwable throwable, JSONArray errorResponse) {
                        // TODO Auto-generated method stub
                        super.onFailure(statusCode, headers, throwable,
                                errorResponse);
                        loading.dismiss();
                        gagal.show();
                        btnCoba.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                try {
                                    dataPendonor();
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          Throwable throwable, JSONObject errorResponse) {
                        // TODO Auto-generated method stub
                        super.onFailure(statusCode, headers, throwable,
                                errorResponse);
                        loading.dismiss();
                        gagal.show();
                        btnCoba.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                try {
                                    dataPendonor();
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
    }

}
