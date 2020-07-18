package com.example.addrecyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static List<data> glist = new ArrayList<data>();
    static int count;
    static RecyclerView recycle;
    static datamodel adaptermodel ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        FloatingActionButton addmem = findViewById(R.id.addmem);
        recycle = findViewById(R.id.recycle);
        adaptermodel = new datamodel(glist);
        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(lm);
        recycle.setAdapter(adaptermodel);
        recycle.getLayoutManager().scrollToPosition(glist.size() - 1);
        addmem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater lf = (LayoutInflater) getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v2 = lf.inflate(R.layout.dialog, null);
                final TextView cancel = v2.findViewById(R.id.cancel);
                final TextView b = v2.findViewById(R.id.add);
                final EditText fname = v2.findViewById(R.id.fnameedit);
                final EditText lname = v2.findViewById(R.id.lnameedit);
                final EditText age = v2.findViewById(R.id.ageedit);
                final EditText usn = v2.findViewById(R.id.usnedit);
                final ToggleButton gender = v2.findViewById(R.id.toggleButton);
                builder.setView(v2);
                final AlertDialog dialog = builder.create();
                dialog.show();
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(fname.getText().toString().equals("") || lname.getText().toString().equals("") || age.getText().toString().equals("") || usn.getText().toString().equals("") )
                        {
                            Toast.makeText(getApplicationContext(),"Fields Empty",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            count++;
                            data g = new data(lname.getText().toString(),usn.getText().toString(),gender.getText().toString(),age.getText().toString(),fname.getText().toString(),count);
                            glist.add(g);
                            adaptermodel.notifyDataSetChanged();
                        }
                        dialog.cancel();
                    }
                });
cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dialog.cancel();
    }
});
            }
        });
    }
}