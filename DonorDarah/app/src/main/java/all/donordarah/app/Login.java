package all.donordarah.app;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import static all.donordarah.app.R.id.bb;
//import static all.donordarah.app.R.id.golDarah;
import static all.donordarah.app.R.id.nama;
import static all.donordarah.app.R.id.noHp;

public class Login extends AppCompatActivity {
    private EditText email, password;

    private Dialog loading, gagal;
    private Button btnCoba;
    private TextView statusLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

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
                    prosesLogin();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void login(View v){
        try {
            prosesLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void daftar(View v){
        startActivity(new Intent(this, DaftarUser.class)); //masuk ke activity daftaruser.
    }
    public void prosesLogin() throws Exception{
        RequestParams params = new RequestParams();
        params.put("aksi", "cek"); //kirim parameter ke server untuk cek proses login
        params.put("email", email.getText().toString()); //kirim parameter ke server untuk email user
        params.put("password", password.getText().toString()); //kirim parameter ke server untuk password user

        ServerClass.post("android/login.php", params, //kode untuk mengirim parameter agar terhubung dengan file login.php di server
                new JsonHttpResponseHandler() {
                    @Override
                    public void onStart() { //method untuk mengambil data. Proses loading
                        // TODO Auto-generated method stub
                        super.onStart();
                        loading.show();
                        statusLoading.setText("mengambil data");
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONArray response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(statusCode, headers, response); //proses login
                        try {
                            loading.dismiss();
                            gagal.dismiss();
                            if (response.isNull(0)) { //baris untuk mengecek ketika email dan password belum diinput
                                Toast.makeText(Login.this, "belum ada data", Toast.LENGTH_SHORT).show();
                            } else { //baris untuk mengecek ketika email dan password diinput
                                JSONObject jsonObject = response
                                        .getJSONObject(0);
                                if(jsonObject.getString("status").equals("berhasil")){ //mengambil parameter dari login.php
                                    String id_pendonor = jsonObject.getString("id_pendonor"); //data email dan password sesuai dengan database
                                    MenuUtama.idPendonor = id_pendonor;
                                    startActivity(new Intent(Login.this, MenuUtama.class)); //menuju ke menu utama
                                }else{ //email dan password salah
                                    Toast.makeText(Login.this, "username atau password salah", Toast.LENGTH_SHORT).show();
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
                                    prosesLogin();
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          Throwable throwable, JSONArray errorResponse) { //proses login gagal
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
                                    prosesLogin();
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
                                    prosesLogin();
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
