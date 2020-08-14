package all.donordarah.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static all.donordarah.app.R.id.email;

public class MenuUtama extends AppCompatActivity {
    public static String idPendonor = ""; //definisi variabel idPendonor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utama);
    }
    public void daftar(View v){
        startActivity(new Intent(this, PreviewBiodata.class));
    } //button Daftar donor darah yang diarahkan ke menu PreviewBiodata
    public void biodata(View v){
        startActivity(new Intent(this, Biodata.class));
    } //button menuju menu biodata
    public void kebutuhan(View v){
        startActivity(new Intent(this, KebutuhanDarah.class));
    } //Button menuju menu Kebutuhan Darah
    public void help(View v){
        startActivity(new Intent(this, Help.class));
    } //Button menuju menu Help
    public void about(View v){
        startActivity(new Intent(this, About.class));
    } //Button menuju menu about
}
