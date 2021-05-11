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

    EditText name, address, healths, phone, date, go_out, come_in, start_day, end_day, away;

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



        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String dateTXT = date.getText().toString();
                String phoneTXT = phone.getText().toString();
                String addressTXT = address.getText().toString();
                String outTXT = go_out.getText().toString();
                String comeTXT = come_in.getText().toString();
                String healthsTXT = healths.getText().toString();

                boolean checker1 = DB.insertuserdata(nameTXT, dateTXT, phoneTXT, addressTXT, outTXT, comeTXT, healthsTXT);
                if (checker1){
                    Toast.makeText(KhaiBaoYTe.this, "Đã Khai Báo", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(KhaiBaoYTe.this, "Khai báo không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String dateTXT = date.getText().toString();
                String phoneTXT = phone.getText().toString();
                String addressTXT = address.getText().toString();
                String outTXT = go_out.getText().toString();
                String comeTXT = come_in.getText().toString();
                String healthsTXT = healths.getText().toString();



                boolean checker2 = DB.updateuserdata(nameTXT, dateTXT, phoneTXT, addressTXT, outTXT, comeTXT, healthsTXT);
                if (checker2){
                    Toast.makeText(KhaiBaoYTe.this, "Đã Cập Nhật", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(KhaiBaoYTe.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                boolean checker3 = DB.deletedata(nameTXT);
                if (checker3){
                    Toast.makeText(KhaiBaoYTe.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(KhaiBaoYTe.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0){
                    Toast.makeText(KhaiBaoYTe.this, "Danh sách trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while (res.moveToNext()){
                    buffer.append("Họ & Tên: ").append(res.getString(0)).append("\n");
                    buffer.append("Ngày Sinh: ").append(res.getString(1)).append("\n");
                    buffer.append("SĐT: ").append(res.getString(2)).append("\n");
                    buffer.append("Địa Chỉ: ").append(res.getString(3)).append("\n");
                    buffer.append("Nơi Đi: ").append(res.getString(4)).append("\n");
                    buffer.append("Nơi Đến: ").append(res.getString(5)).append("\n");
                    buffer.append("Sức Khỏe: ").append(res.getString(6)).append("\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(KhaiBaoYTe.this);
                builder.setCancelable(true);
                builder.setTitle("Danh Sách Khai Báo Y Tế");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }




}