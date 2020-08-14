package all.donordarah.app;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

public class DaftarUser extends AppCompatActivity {
    private EditText nama, email, password, noHp, tglLahir, pekerjaan, golDarah, bb;
    private RadioButton L, P, A, B, AB, O;

    private Dialog loading, gagal;
    private Button btnCoba, btnTglLahir1;
    private TextView statusLoading;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) { //pembuatan variabel
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftaruser);
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
                    prosesDaftar();
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
    public void kembali(View v) {
        finish();
    }

    public void daftar(View v){ //biodata harus diisi semua
        if(nama.getText().length()==0){
            Toast.makeText(this, "nama harus diisi", Toast.LENGTH_SHORT).show();
        }else if(noHp.getText().length()==0){
            Toast.makeText(this, "no hp harus diisi", Toast.LENGTH_SHORT).show();
        }else if(email.getText().length()==0){
            Toast.makeText(this, "email harus diisi", Toast.LENGTH_SHORT).show();
        }else if(password.getText().length()==0){
            Toast.makeText(this, "password harus diisi", Toast.LENGTH_SHORT).show();
        }else if(tglLahir.getText().length()==0){
            Toast.makeText(this, "tgl lahir harus diisi", Toast.LENGTH_SHORT).show();
        }else if(pekerjaan.getText().length()==0){
            Toast.makeText(this, "pekerjaan harus diisi", Toast.LENGTH_SHORT).show();
        }else if(bb.getText().length()==0){
            Toast.makeText(this, "bb harus diisi", Toast.LENGTH_SHORT).show();
        }else{
            try {
                prosesDaftar(); // method proses untuk mendaftar
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void prosesDaftar() throws Exception{ //pembuatan method proses mendaftar
        RequestParams params = new RequestParams(); //parameter untuk daftar user
        params.put("aksi", "daftar"); //parameter untuk melakukan aksi mendaftar user.
        params.put("nama", nama.getText().toString()); //membuat parameter dari input biodata yang dilakukan pendonor
        params.put("no_hp", noHp.getText().toString());
        params.put("email", email.getText().toString());
        params.put("password", password.getText().toString());
        if(L.isChecked()){
            params.put("jk", "Pria");
        }else{
            params.put("jk", "Wanita");
        }
        params.put("tgl_lahir", tglLahir.getText().toString());
        params.put("pekerjaan", pekerjaan.getText().toString());
        if(A.isChecked()){
            params.put("gol_darah", "A");
        }else if(B.isChecked()){
            params.put("gol_darah", "B");
        }else if(AB.isChecked()){
            params.put("gol_darah", "AB");
        }else{
            params.put("gol_darah", "O");
        }
        params.put("bb", bb.getText().toString());

        ServerClass.post("android/daftaruser.php", params, //kode untuk mengirim parameter agar terhubung dengan file daftaruser.php di server
                new JsonHttpResponseHandler() {
                    @Override
                    public void onStart() { //menunggu respon dari server
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
                            if (response.isNull(0)) { //baris untuk mengecek ketika biodata registrasi belum diinput
                                Toast.makeText(DaftarUser.this, "belum ada data", Toast.LENGTH_SHORT).show();
                            } else {
                                JSONObject jsonObject = response
                                            .getJSONObject(0);
                                if(jsonObject.getString("status").equals("berhasil")){ //jika registrasi berhasil
                                    Toast.makeText(DaftarUser.this, "berhasil mendaftar", Toast.LENGTH_SHORT).show();
                                    String id_pendonor = jsonObject.getString("id_pendonor");
                                    MenuUtama.idPendonor = id_pendonor;
                                    startActivity(new Intent(DaftarUser.this, MenuUtama.class));
                                }else{ //jika registrasi gagal
                                    Toast.makeText(DaftarUser.this, "gagal mendaftar, silahkan coba kembali", Toast.LENGTH_SHORT).show();
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
                                    prosesDaftar();
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
                                    prosesDaftar();
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
                                    prosesDaftar();
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
