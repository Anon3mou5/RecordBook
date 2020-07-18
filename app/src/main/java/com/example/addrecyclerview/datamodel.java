package com.example.addrecyclerview;

import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.addrecyclerview.MainActivity.adaptermodel;
import static com.example.addrecyclerview.MainActivity.glist;
import static com.example.addrecyclerview.MainActivity.count;

public class datamodel extends RecyclerView.Adapter<viewholder> {

    List<data> modelview ;
    String lname,fname,age,usn,gender;


    public datamodel(List<data> model) {
        this.modelview = model;

    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
lname = modelview.get(position).getLname();
fname = modelview.get(position).getFname();
age = modelview.get(position).getAge();
usn = modelview.get(position).getUsn();
gender = modelview.get(position).getGender();
holder.setdata(fname,lname,usn,gender,age,modelview.get(position).count,position);
    }



    @Override
    public int getItemCount() {
        return modelview.size();
    }
}
 class viewholder extends RecyclerView.ViewHolder{

     TextView fnameedit ;
     TextView lnameedit ;
     TextView ageedit ;
     TextView usnedit ;
     TextView genderedit,num;

     Button edit,delete;


    public viewholder(@NonNull View itemView) {
        super(itemView);
      fnameedit = itemView.findViewById(R.id.fnameedit);
      lnameedit = itemView.findViewById(R.id.lnameedit);
       ageedit = itemView.findViewById(R.id.ageedit);
      usnedit  = itemView.findViewById(R.id.usnedit);
      genderedit = itemView.findViewById(R.id.toggle);
      edit = itemView.findViewById(R.id.edit);
      delete = itemView.findViewById(R.id.delete);
      num = itemView.findViewById(R.id.textView);
    }
    public void setdata(final String fname, final String lname, final String usn, final String gender, final String age, final int counter, final int position)
    {
        fnameedit.setText(fname);
        lnameedit.setText(lname);
        usnedit.setText(usn);
        genderedit.setText(gender);
        ageedit.setText(age);
        int z = counter;
        String t = "#" + Integer.toString(z);
        num.setText(t);

       edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
               LayoutInflater lf = (LayoutInflater) itemView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               View v2 = lf.inflate(R.layout.dialog, null);
               final TextView cancel = v2.findViewById(R.id.cancel);
               final TextView b = v2.findViewById(R.id.add);
               final EditText fnme = v2.findViewById(R.id.fnameedit);
               final EditText lnme = v2.findViewById(R.id.lnameedit);
               final EditText agem = v2.findViewById(R.id.ageedit);
               final EditText usnm = v2.findViewById(R.id.usnedit);
               final ToggleButton genderm = v2.findViewById(R.id.toggleButton);
               builder.setView(v2);
               final AlertDialog dialog = builder.create();
               dialog.show();
               b.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       if(fnme.getText().toString().equals("") || lnme.getText().toString().equals("") || agem.getText().toString().equals("") || usnm.getText().toString().equals("") )
                       {
                           Toast.makeText(itemView.getContext(),"Fields Empty",Toast.LENGTH_SHORT).show();
                       }
                       else
                       {
                           glist.remove(position);
                           data g = new data(lnme.getText().toString(),usnm.getText().toString(),genderm.getText().toString(),agem.getText().toString(),fnme.getText().toString(),counter);
                           glist.add(position,g);
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

       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               glist.remove(position);
               adaptermodel.notifyDataSetChanged();
               count--;
           }
       });


    }
}