package com.example.sqlite_begin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtName, txtPhone,txtDate;
    Button btnInsert,btnSelect,btnUpdate,btnDelete;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnInsert.setOnClickListener(v -> {
            Boolean checkInsertData = databaseHelper.insert(txtName.getText().toString(),txtPhone.getText().toString(),txtDate.getText().toString());
            if (checkInsertData)
                Toast.makeText(getApplicationContext(),"Данные успешно добавлены", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"Произошла ошибка", Toast.LENGTH_SHORT).show();

        });
        btnUpdate.setOnClickListener(v -> {
            Boolean checkUpdateData = databaseHelper.update(txtName.getText().toString(),txtPhone.getText().toString(),txtDate.getText().toString());
            if (checkUpdateData)
                Toast.makeText(getApplicationContext(),"Данные успешно изменены", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"Произошла ошибка", Toast.LENGTH_SHORT).show();
        });

        btnDelete.setOnClickListener(v -> {
            Boolean checkDeleteData = databaseHelper.delete(txtName.getText().toString(),txtPhone.getText().toString(),txtDate.getText().toString());
            if (checkDeleteData)
                Toast.makeText(getApplicationContext(),"Данные успешно удалены", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"Произошла ошибка", Toast.LENGTH_SHORT).show();
        });

        btnSelect.setOnClickListener(v -> {
            Cursor res = databaseHelper.getData();
            if(res.getCount() == 0){
                Toast.makeText(this,"Нет данных", Toast.LENGTH_SHORT).show();
                return;
            }

            StringBuilder buffer = new StringBuilder();
            while (res.moveToNext()){
                buffer.append("Имя: ").append(res.getString(0)).append("\n");
                buffer.append("Тел. номер: ").append(res.getString(1)).append("\n");
                buffer.append("Дата рождения: ").append(res.getString(2)).append("\n\n");
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Данные пользователей");
            builder.setMessage(buffer.toString());
            builder.show();
        });
    }
    private void init(){
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtDate = findViewById(R.id.txtDate);
        btnSelect = findViewById(R.id.btnSelect);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        databaseHelper = new DatabaseHelper(this);
    }
}