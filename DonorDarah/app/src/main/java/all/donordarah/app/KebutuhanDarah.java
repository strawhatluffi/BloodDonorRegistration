package all.donordarah.app;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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


public class KebutuhanDarah extends AppCompatActivity {
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
        params.put("aksi", "semua"); //parameter untuk menampilkan data kebutuhan dari dari kebutuhan_darah.php

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
                                Toast.makeText(KebutuhanDarah.this, "belum ada data", Toast.LENGTH_SHORT).show();
                            } else { //menampilkan list data kebutuhan darah pada listview
                                idList = new String[response.length()];
                                dataList = new String[response.length()];
                                for(int i = 0; i<response.length(); i++){ //menampilkan jumlah data kebutuhan darah
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
                                adpData = new ArrayAdapter(KebutuhanDarah.this, R.layout.item_normal, dataList);
                                listData.setAdapter(adpData);
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
                        loading.dismiss(); //menampilkan dialog gagal jika respon ke database gagal
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

}
