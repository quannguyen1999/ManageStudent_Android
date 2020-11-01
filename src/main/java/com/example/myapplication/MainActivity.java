package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.adapter.ListStudentAdapter;
import com.example.myapplication.model.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  ClickItemListener{
    EditText edtMa, edtHoTen;

    Spinner spLop;

    RadioButton rdName, rdNu;

    Button btnThem, btnXoa;

    private RecyclerView rclView;

    private ArrayList<Student> listStudents;

    private ArrayList<String> listLop;

    private ListStudentAdapter listStudentAdapter;

    private int itemSelcted = -1;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        metaData();

        initData();

        initSpinner();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mssv = edtMa.getText().toString();
                String hoTen = edtHoTen.getText().toString();
                String lop = spLop.getSelectedItem().toString();
                boolean gioiTinh = false;
                if (hoTen.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Mã sinh viên chưa nhập", Toast.LENGTH_LONG).show();
                    edtMa.requestFocus();
                    return;
                }
                if (hoTen.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Họ tên chưa nhập", Toast.LENGTH_LONG).show();
                    edtHoTen.requestFocus();
                    return;
                }

                Student student = new Student(mssv, hoTen, lop, gioiTinh);

                listStudents.add(student);

                listStudentAdapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();

                clearData();

                closeKeyBoard();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemSelcted == -1) {
                    Toast.makeText(MainActivity.this, "please choose", Toast.LENGTH_LONG);
                } else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Delete?")
                            .setMessage("Are you sure ? ")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    listStudents.remove(itemSelcted);
                                    listStudentAdapter.notifyDataSetChanged();
                                    clearData();
                                    itemSelcted = -1;
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }

            }
        });


    }

    private void closeKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void clearData() {
        edtHoTen.setText("");
        edtMa.setText("");
        rdName.setChecked(true);
    }

    private void initSpinner() {
        listLop = new ArrayList<>();

        listLop.add("DHKTPM13ATT");
        listLop.add("DHKTPM13BTT");
        listLop.add("DHKTPM13CTT");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,listLop);

        spLop.setAdapter(adapter);
    }

    public void metaData(){
        edtHoTen = findViewById(R.id.edtHoTen);
        edtMa = findViewById(R.id.edtMaSV);
        spLop = findViewById(R.id.sp);
        rdNu = findViewById(R.id.rdNu);
        rdName = findViewById(R.id.rdNam);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        rclView = findViewById(R.id.rclView);
    }

    public void initData(){
        listStudents = new ArrayList<>();

        listStudents.add(new Student("17008971","quân","KTPM13ATT",true));
        listStudents.add(new Student("16008971","bitch","KTPM13ATT",false));
        listStudents.add(new Student("15708974","eimi","KTPM13ATT",true));
        listStudents.add(new Student("14856977","slut","KTPM13ATT",false));


        listStudentAdapter = new ListStudentAdapter(this, listStudents,this);

        rclView.setAdapter(listStudentAdapter);

        rclView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemCLick(Student student, int position) {
        edtMa.setText(student.getMssv());
        edtHoTen.setText(student.getHoTen());
        for(int i=0; i<spLop.getCount();i++){
            if(spLop.getItemAtPosition(i).equals(student.getLop())){
                spLop.setSelection(i);
                break;
            }
        }
//        System.out.println(student.isGioiTinh());
//        rdNu.setChecked(true);
        if(student.isGioiTinh()==true){
            rdName.setChecked(true);
        }else{
            rdNu.setChecked(true);
        }
        itemSelcted = position;
    }
}