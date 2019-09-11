package com.example.memoryskills;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    EditText enter_email;
    String Date_of_birth;
    EditText edt_show_calendar;
    DatePickerDialog.OnDateSetListener setListener;
    Button clk;

    public Button login;
    DatabaseOpenHelper db;
    private int counter;
    private Cursor c;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        enter_email = findViewById( R.id.enter_email );
        login = findViewById( R.id.login);

        /*login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/


        login.setOnClickListener( new View.OnClickListener() {
            private Object ThirdActivity;
            @Override
            public void onClick(View view) {
                validate(enter_email.getText().toString(),edt_show_calendar.getText().toString());
            }

        } );



        edt_show_calendar = findViewById(R.id.edt_show_calendar);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        edt_show_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog= new DatePickerDialog(
                        MainActivity.this,setListener,year,month,day);
                //datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

       /* setListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date = day+"/"+month+"/"+year;
                edt_show_calendar.setText(date);
            }
        };*/
        edt_show_calendar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date = day+"/"+month+"/"+year;
                        edt_show_calendar.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();
            }

        });

    }

    private void validate(String email, String dob) {

        if (TextUtils.isEmpty( email ) && TextUtils.isEmpty( dob )){
            Toast.makeText( this, "Please enter E-Mail and DOB", Toast.LENGTH_SHORT ).show();
            return;
        }

        if (TextUtils.isEmpty( email )){
            Toast.makeText( this, "Please enter E-Mail", Toast.LENGTH_SHORT ).show();
            return;
        }

        if (TextUtils.isEmpty( dob )){
            Toast.makeText( this, "Please select D.O.B", Toast.LENGTH_SHORT ).show();
            return;
        }

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance( getApplicationContext() );
        databaseAccess.open();
        int verify = databaseAccess.Verify(email);
        if(verify>0){
            Intent i = new Intent( MainActivity.this, ScrollingActivity .class );
            startActivity( i );
        }else{
            boolean insertData= databaseAccess.insertData(name,Date_of_birth );
            Log.d("debug","Bool value after data insertion == "+insertData);
            if(insertData){
                Intent i = new Intent( MainActivity.this, ScrollingActivity .class );
                startActivity( i );
            }
        }
        databaseAccess.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void activity_second(View view) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View mview = getLayoutInflater().inflate(R.layout.activity_second,null);

        alert.setView(mview);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alert.show();

        VideoView mVideoView2 = mview.findViewById(R.id.videoView1);
        Button buttonPlayVideo2 = mview.findViewById(R.id.button1);


        String uriPath2 = "android.resource://com.example.memoryskills/"+R.raw.intro;
        Uri uri2 = Uri.parse(uriPath2);
        mVideoView2.setVideoURI(uri2);
        mVideoView2.requestFocus();
        mVideoView2.start();

        buttonPlayVideo2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoView mVideoView2 = (VideoView) findViewById(R.id.videoView1);

                // VideoView mVideoView = new VideoView(this);
                String uriPath = "android.resource://com.example.memoryskills/" + R.raw.intro;
                Uri uri2 = Uri.parse(uriPath);
                mVideoView2.setVideoURI(uri2);
                mVideoView2.requestFocus();
                mVideoView2.start();
            }
        });

    }


}



