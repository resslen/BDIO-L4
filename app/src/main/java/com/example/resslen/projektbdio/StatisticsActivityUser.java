package com.example.resslen.projektbdio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;

public class StatisticsActivityUser extends AppCompatActivity {



    Spinner spinner1;
    Spinner spinner2;
    Button button1;

    TextView wyniki;
    TextView tytul;


    ArrayAdapter arrayAdapter2;
    ArrayAdapter arrayAdapter3;
    ArrayAdapter arrayAdapter4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_user);




        wyniki= (TextView) findViewById(R.id.textwyniki);
        tytul=  (TextView) findViewById(R.id.textView2);

        button1= (Button)findViewById(R.id.button1);



        button1.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {


                String zawartosc1 = spinner1.getSelectedItem().toString();
                String zawartosc2 = spinner2.getSelectedItem().toString();

                wyniki.setVisibility(ViewGroup.VISIBLE);
                wyniki.setText("");

                if (zawartosc1.equals("2017") && zawartosc2.equals("1 termin"))
                {
                    String egzamin="Pierwszy termin 2017r.";

                    double ocena=2.0;
                    int punkty=9;
                    String spunkty=Integer.toString(punkty);
                    String socena=Double.toString(ocena);
                    String zal;
                    if(ocena>=3.0) zal="TAK";
                    else zal="NIE";


                    wyniki.setText(egzamin + "\n \nIlość punktów: "+spunkty+"\nOtrzymana ocena: "+socena+"\n Zaliczenie: "+zal);


                }


                if (zawartosc1.equals("2017") && zawartosc2.equals("2 termin"))
                {
                    String egzamin="Drugi termin 2017r.";

                    double ocena=3.5;
                    int punkty=13;
                    String spunkty=Integer.toString(punkty);
                    String socena=Double.toString(ocena);
                    String zal;
                    if(ocena>=3.0) zal="TAK";
                    else zal="NIE";


                    wyniki.setText(egzamin + "\n \nIlość punktów: "+spunkty+"\nOtrzymana ocena: "+socena+"\n Zaliczenie: "+zal);

                }


                if (zawartosc1.equals("2016") && zawartosc2.equals("2 termin"))
                {
                    String egzamin="Drugi termin 2016r.";

                    double ocena=2.0;
                    int punkty=7;
                    String spunkty=Integer.toString(punkty);
                    String socena=Double.toString(ocena);
                    String zal;
                    if(ocena>=3.0) zal="TAK";
                    else zal="NIE";


                    wyniki.setText(egzamin + "\n \nIlość punktów: "+spunkty+"\nOtrzymana ocena: "+socena+"\n Zaliczenie: "+zal);


                }




                if (zawartosc1.equals("2016") && zawartosc2.equals("zerówka"))
                {
                    String egzamin="Zerowy termin 2016r.";

                    double ocena=2.0;
                    int punkty=8;
                    String spunkty=Integer.toString(punkty);
                    String socena=Double.toString(ocena);
                    String zal;
                    if(ocena>=3.0) zal="TAK";
                    else zal="NIE";


                    wyniki.setText(egzamin + "\n \nIlość punktów: "+spunkty+"\nOtrzymana ocena: "+socena+"\n Zaliczenie: "+zal);

                }


                if (zawartosc1.equals("2016") && zawartosc2.equals("1 termin"))
                {
                    String egzamin="Pierwszy termin 2016r.";

                    double ocena=2.0;
                    int punkty=6;
                    String spunkty=Integer.toString(punkty);
                    String socena=Double.toString(ocena);
                    String zal;
                    if(ocena>=3.0) zal="TAK";
                    else zal="NIE";


                    wyniki.setText(egzamin + "\n \nIlość punktów: "+spunkty+"\nOtrzymana ocena: "+socena+"\n Zaliczenie: "+zal);


                }



            }
        });









        spinner1=(Spinner) findViewById(R.id.spinner1);
        spinner2=(Spinner) findViewById(R.id.spinner2);


        String[] myItems1=getResources().getStringArray(R.array.WYBIERZ_ROK);
        ArrayAdapter arrayAdapter1= new ArrayAdapter<>(this, android.R.layout.select_dialog_item,myItems1);



        String[] myItems2=getResources().getStringArray(R.array.rok_1);
        String[] myItems3=getResources().getStringArray(R.array.rok_2);
        String[] myItems4=getResources().getStringArray(R.array.rok_3);


        arrayAdapter2= new ArrayAdapter<>(this, android.R.layout.select_dialog_item,myItems2);
        arrayAdapter3= new ArrayAdapter<>(this, android.R.layout.select_dialog_item,myItems3);
        arrayAdapter4= new ArrayAdapter<>(this, android.R.layout.select_dialog_item,myItems4);



        spinner1.setAdapter(arrayAdapter1);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        spinner2.setAdapter(arrayAdapter2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);





        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                String zawartosc1 = spinner1.getSelectedItem().toString();
                String zawartosc2 = spinner2.getSelectedItem().toString();

                if (zawartosc1.equals("2016"))
                {


                    spinner2.setAdapter(arrayAdapter2);
                    arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                }






                if (zawartosc1.equals("2017"))
                {



                    spinner2.setAdapter(arrayAdapter4);
                    arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );









    }








}


