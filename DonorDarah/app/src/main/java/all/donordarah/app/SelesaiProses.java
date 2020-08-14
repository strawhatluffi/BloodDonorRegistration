package all.donordarah.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class SelesaiProses extends AppCompatActivity { //menu untuk menampilkan hasil donor darah setelah semua proses selesai
    public static String idReg;
    private TextView txtIdReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) { //menampilkan id_registrasi utk proses selanjutnya. Proses mendapatkan registrasi ada pada daftar_donor.php
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selesai_proses);
        txtIdReg = (TextView) findViewById(R.id.idReg);
        txtIdReg.setText(idReg);
    }
}
