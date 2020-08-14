package all.donordarah.app;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import static all.donordarah.app.R.id.email;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class Kuisioner extends AppCompatActivity {
    private LinearLayout papanKuisioner;
    private Button selanjutnya;

    private Dialog loading, gagal;
    private Button btnCoba;
    private TextView statusLoading;
    private int[] nilai;
    private String[] idKuisioner, dijawab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kuisioner);
        papanKuisioner = (LinearLayout) findViewById(R.id.papanKuisioner);
        selanjutnya = (Button) findViewById(R.id.selanjutnya);

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
                    dataKuisioner();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        try {
            dataKuisioner();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void kembali(View v) {
        finish();
    }
    public void dataKuisioner() throws Exception{
        RequestParams params = new RequestParams();
        params.put("aksi", "data");
        params.put("id", MenuUtama.idPendonor);

        ServerClass.post("android/kuisioner.php", params,
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
                                Toast.makeText(Kuisioner.this, "belum ada data", Toast.LENGTH_SHORT).show();
                            } else {
                                nilai = new int[response.length()];
                                idKuisioner = new String[response.length()];
                                dijawab = new String[response.length()];
                                for(int i=0; i<response.length(); i++){
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    idKuisioner[i] = jsonObject.getString("id_kuisioner");
                                    LinearLayout pertanyaan = new LinearLayout(Kuisioner.this);
                                    pertanyaan.setOrientation(LinearLayout.HORIZONTAL);
                                    pertanyaan.setWeightSum(5);
                                    LinearLayout.LayoutParams pt = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    pertanyaan.setLayoutParams(pt);

                                    TextView soal = new TextView(Kuisioner.this);
                                    soal.setText(String.valueOf(i+1)+". "+jsonObject.getString("soal"));
                                    LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    p.weight = 4;
                                    soal.setLayoutParams(p);
                                    pertanyaan.addView(soal);

                                    Spinner jawaban = new Spinner(Kuisioner.this);
                                    LinearLayout.LayoutParams s = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    s.weight = 1;
                                    jawaban.setLayoutParams(s);
                                    String[] jenisJawaban = {"...", "ya", "tidak"};
                                    ArrayAdapter adpJawaban = new ArrayAdapter(Kuisioner.this, R.layout.item_normal, jenisJawaban);
                                    jawaban.setAdapter(adpJawaban);;
                                    pertanyaan.addView(jawaban);

                                    final String kunciJawaban = jsonObject.getString("jawaban");
                                    final int j = i;

                                    jawaban.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                            if(position==0){//tidak memilih
                                                nilai[j] = 0;
                                                dijawab[j] = " ";
                                            }else{
                                                if(kunciJawaban.equals("Y")&&position==1){
                                                    nilai[j] = 1;
                                                    dijawab[j] = "Y";
                                                }else if(kunciJawaban.equals("T")&&position==2){
                                                    nilai[j] = 1;
                                                    dijawab[j] = "T";
                                                }else{
                                                    nilai[j] = 0;
                                                    if(position == 1){
                                                        dijawab[j] = "Y";
                                                    }else if(position == 2){
                                                        dijawab[j] = "T";
                                                    }else {
                                                        dijawab[j] = " ";
                                                    }
                                                }
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {
                                            nilai[j] = 0;
                                            dijawab[j] = " ";
                                        }
                                    });

                                    papanKuisioner.addView(pertanyaan);

                                }
                                selanjutnya.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        int jmlBenar = 0;
                                        for(int i = 0; i<nilai.length; i++){
                                            jmlBenar += nilai[i];
                                        }
                                        Toast.makeText(Kuisioner.this, jmlBenar+" benar", Toast.LENGTH_SHORT).show();
                                        KebutuhanDarahTerkait.idKuisioner = idKuisioner;
                                        KebutuhanDarahTerkait.jawaban = dijawab;
                                        startActivity(new Intent(Kuisioner.this, KebutuhanDarahTerkait.class));
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
                                    dataKuisioner();
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
                                    dataKuisioner();
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
                                    dataKuisioner();
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
