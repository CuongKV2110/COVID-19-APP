package com.developer.arsltech.covid_19tracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KhaiBaoYTe extends AppCompatActivity {

    EditText name, address, healths, phone, date, go_out, come_in, start_day, end_day, away, card;

    Button insert, view, update, delete;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khai_bao_y_te);
        insert = findViewById(R.id.idinsert);
        delete = findViewById(R.id.iddelete);
        update = findViewById(R.id.idupdate);
        view = findViewById(R.id.idview);

        name = findViewById(R.id.idname);
        address = findViewById(R.id.idaddress);
        healths = findViewById(R.id.idhealths);
        date = findViewById(R.id.iddateofbirth);
        phone = findViewById(R.id.idphone);
        go_out = findViewById(R.id.idfrom);
        come_in = findViewById(R.id.idcomein);
        start_day = findViewById(R.id.idstartday);
        end_day = findViewById(R.id.idendday);
        away = findViewById(R.id.idaway);
        card = findViewById(R.id.idcccd);



        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String cardTXT = card.getText().toString();
                String dateTXT = date.getText().toString();
                String phoneTXT = phone.getText().toString();
                String addressTXT = address.getText().toString();
                String outTXT = go_out.getText().toString();
                String comeTXT = come_in.getText().toString();
                String healthsTXT = healths.getText().toString();

                boolean checker1 = DB.insertuserdata(nameTXT, cardTXT, dateTXT, phoneTXT, addressTXT, outTXT, comeTXT, healthsTXT);
                if (checker1){
                    Toast.makeText(KhaiBaoYTe.this, "???? Khai B??o", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(KhaiBaoYTe.this, "Khai b??o kh??ng th??nh c??ng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String cardTXT = card.getText().toString();
                String dateTXT = date.getText().toString();
                String phoneTXT = phone.getText().toString();
                String addressTXT = address.getText().toString();
                String outTXT = go_out.getText().toString();
                String comeTXT = come_in.getText().toString();
                String healthsTXT = healths.getText().toString();

                boolean checker2 = DB.updateuserdata(nameTXT, cardTXT, dateTXT, phoneTXT, addressTXT, outTXT, comeTXT, healthsTXT);
                if (checker2){
                    Toast.makeText(KhaiBaoYTe.this, "???? C???p Nh???t", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(KhaiBaoYTe.this, "C???p nh???t kh??ng th??nh c??ng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardTXT = card.getText().toString();
                boolean checker3 = DB.deletedata(cardTXT);
                if (checker3){
                    Toast.makeText(KhaiBaoYTe.this, "???? x??a", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(KhaiBaoYTe.this, "X??a kh??ng th??nh c??ng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0){
                    Toast.makeText(KhaiBaoYTe.this, "Danh s??ch tr???ng", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while (res.moveToNext()){
                    buffer.append("H??? & T??n: ").append(res.getString(0)).append("\n");
                    buffer.append("CCCD: ").append(res.getString(1)).append("\n");
                    buffer.append("Ng??y Sinh: ").append(res.getString(2)).append("\n");
                    buffer.append("S??T: ").append(res.getString(3)).append("\n");
                    buffer.append("?????a Ch???: ").append(res.getString(4)).append("\n");
                    buffer.append("N??i ??i: ").append(res.getString(5)).append("\n");
                    buffer.append("N??i ?????n: ").append(res.getString(6)).append("\n");
                    buffer.append("S???c Kh???e: ").append(res.getString(7)).append("\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(KhaiBaoYTe.this);
                builder.setCancelable(true);
                builder.setTitle("Danh S??ch Khai B??o Y T???");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }




}