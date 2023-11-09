package com.hoangtuan.nhht.w08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.PackageManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends FragmentActivity implements MainCallbacks {

    FragmentTransaction ft;
    FragmentBlue blueFragment;
    FragmentRed redFragment;

    private SQLiteDatabase database;
    String myDataBasePath;

    Student[] students;
    Class[] classes;

    void setStudents(Student[] students){
        this.students = students;
    }

    void setClasses(Class[] classes){
        this.classes = classes;
    }


    private void buildFragment(){
        // create a new BLUE fragment - show it
        ft = getSupportFragmentManager().beginTransaction();
        blueFragment = FragmentBlue.newInstance("first-blue");
        ft.replace(R.id.main_holder_blue, blueFragment);
        ft.commit();

        // create a new RED fragment - show it
        ft = getSupportFragmentManager().beginTransaction();
        redFragment = FragmentRed.newInstance("");
        ft.replace(R.id.main_holder_red, redFragment);
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File storagePath = getApplication().getFilesDir();
        myDataBasePath = storagePath + "/" + "myDataBase";

        /*createDataBase();
        buildFragment();*/

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                createDataBase();
                buildFragment();
            } else {
                Toast.makeText(MainActivity.this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        // Database will stay opened all the time, only close it on app destroy
        if (database.isOpen()) {
            database.close();
        }
        super.onDestroy();
    }


    @Override
    public void onMsgFromFragToMain(Student student, String msg) {
        redFragment.onMsgFromMainToFragment(student, msg);
    }


    @Override
    public void onCommandFromFragToMain(String str) {
        blueFragment.onCommandFromMainToFragment(str);
    }

    private void createDataBase(){
        try {
            database = SQLiteDatabase.openDatabase(myDataBasePath,null,SQLiteDatabase.CREATE_IF_NECESSARY);
            Toast.makeText(MainActivity.this, "Database created successfully", Toast.LENGTH_SHORT).show();
            createTables();
            insertTables();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        };
    }


    private void createTables(){
        try {
            database.execSQL("DROP TABLE IF EXISTS HOCSINH;");
            database.execSQL("DROP TABLE IF EXISTS LOPHOC;");
            database.execSQL("CREATE TABLE HOCSINH ( " +
                    "STUDENTID TEXT PRIMARY KEY, " +
                    "NAME TEXT, " +
                    "CLASSID INTEGER, " +
                    "AVG FLOAT); ");
            database.execSQL("CREATE TABLE LOPHOC (" +
                    "CLASSID INTEGER PRIMARY KEY," +
                    "CLASSNAME TEXT);");
            Toast.makeText(this, "Create Tables Completed", Toast.LENGTH_SHORT).show();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private void insertTables(){
        try {
            database.execSQL("INSERT INTO LOPHOC VALUES (1, '21_2')");
            database.execSQL("INSERT INTO HOCSINH VALUES ('21120053', 'Lại Đức Dũng', 1, 10.0)");
            database.execSQL("INSERT INTO HOCSINH VALUES ('21120072', 'Nguyễn Xuân Hòa', 1, 10.0)");
            database.execSQL("INSERT INTO HOCSINH VALUES ('21120088', 'Nguyễn Nhật Khoa', 1, 10.0)");
            database.execSQL("INSERT INTO HOCSINH VALUES ('21120148', 'Trần Tiến', 1, 10.0)");
            database.execSQL("INSERT INTO HOCSINH VALUES ('21120187', 'Nguyễn Hà Hoàng Tuấn', 1, 10.0)");

            Toast.makeText(this, "Insert Tables Completed", Toast.LENGTH_SHORT).show();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    SQLiteDatabase getDatabase(){
        return database;
    }
}