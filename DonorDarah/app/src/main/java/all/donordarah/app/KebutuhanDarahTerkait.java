package all.donordarah.app;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class KebutuhanDarahTerkait extends AppCompatActivity { //kebutuhan darah terkait digunakan sbg source code untuk menampilkan kebutuhan darah pada kebutuhan_darah.xml
    public static String[] idKuisioner, jawaban;
    private ListView listData;
    private ArrayAdapter adpData;
    private String[] idList;
    private String[] dataList;

    private Dialog loading, gagal;
    private Button btnCoba;
    private TextView statusLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kebutuhan_darah);
        listData = (ListView) findViewById(R.id.listData);

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
                    dataKebutuhan();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        try {
            dataKebutuhan();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void dataKebutuhan() throws Exception{
        RequestParams params = new RequestParams();
        params.put("aksi", "terkait"); //parameter untuk menampilkan kebutuhan darah yang sama dengan golongan darah pendonor
        params.put("id", MenuUtama.idPendonor);

        ServerClass.post("android/kebutuhan_darah.php", params,
                new JsonHttpResponseHandler() {
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
                            if (response.isNull(0)) {
                                Toast.makeText(KebutuhanDarahTerkait.this, "belum ada data", Toast.LENGTH_SHORT).show();
                            } else { //data kebutuhan yang ditampilkan sesuai dengan golongan darah dari pendonor
                                idList = new String[response.length()];
                                dataList = new String[response.length()];
                                for(int i = 0; i<response.length(); i++){
                                    JSONObject jsonObject = response
                                            .getJSONObject(i);
                                    idList[i] = jsonObject.getString("id_kebutuhan");
                                    dataList[i] = jsonObject.getString("nama_resipien");
                                    dataList[i] += "\ngol darah : "+jsonObject.getString("goldar");
                                    dataList[i] += "\njk : "+jsonObject.getString("jenis_kel");
                                    dataList[i] += "\nkebutuhan : "+jsonObject.getString("kebutuhan_kantung")+" kantung";
                                    dataList[i] += "\nkontak : "+jsonObject.getString("kontak");
                                    dataList[i] += "\nwali : "+jsonObject.getString("wali_pasien");
                                }
                                adpData = new ArrayAdapter(KebutuhanDarahTerkait.this, R.layout.item_normal, dataList);
                                listData.setAdapter(adpData);
                                listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int posisi, long l) {
                                        try {
                                            prosesSimpan(idList[posisi]);
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                });
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
                                    dataKebutuhan();
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
                                    dataKebutuhan();
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
                                    dataKebutuhan();
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
    }
    public void prosesSimpan(final String idKebutuhan) throws Exception{ //proses menyimpan pendaftaran donor darah
        RequestParams params = new RequestParams();
        params.put("aksi", "daftar"); //parameter untuk pendaftaran donor darah
        params.put("id_pendonor", MenuUtama.idPendonor); //parameter untuk mengambil data id_pendonor di database
        params.put("id_kebutuhan", idKebutuhan); //parameter untuk mengambil data idkebutuhan di database
        /*
        List<String> idKuisioners = new ArrayList<>();
        List<String> jawabans = new ArrayList<>();
        for(int x=0; x<idKuisioner.length; x++){
            idKuisioners.add(idKuisioner[x]);
            jawabans.add(jawaban[x]);
        }
        */

        //Toast.makeText(this, idKuisioner[2], Toast.LENGTH_SHORT).show();
        params.put("id_kuisioner", idKuisioner); //parameter untuk mengambil data idkuisioner di database
        params.put("jawaban", jawaban); //parameter jawaban

        ServerClass.post("android/daftar_donor.php", params, //paramater JSON untuk mengakses daftar_donor.php
                new JsonHttpResponseHandler() {
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
                            if (response.isNull(0)) { //ambil data pada database
                                Toast.makeText(KebutuhanDarahTerkait.this, "belum ada data", Toast.LENGTH_SHORT).show();
                            } else {
                                JSONObject jsonObject = response
                                            .getJSONObject(0);
                                SelesaiProses.idReg = jsonObject.getString("id_reg"); //proses utk menentukan id_registrasi ada pada daftar_donor.php
                                startActivity(new Intent(KebutuhanDarahTerkait.this, SelesaiProses.class));
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
                                    prosesSimpan(idKebutuhan);
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
                                    prosesSimpan(idKebutuhan);
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
                                    prosesSimpan(idKebutuhan);
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
