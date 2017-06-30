package com.example.resslen.projektbdio;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.view.ViewGroup;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {
    private RelativeLayout statLayout;
    private PieChart mChart;
    private BarChart bChart;
    //wyświetlanie wykresu ciastkowego na telefonach
    private float[] yData={5,10,15,30,30,10};
    private String[] xData = {"2", "3", "3.5", "4", "4.5","5"};
    ArrayList<BarEntry> entries  = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<String>();
    BarDataSet dataset = new BarDataSet(entries, "Poprawne odpowiedzi");
    BarData data = new BarData(labels, dataset);


    Spinner spinner1;
    Spinner spinner2;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView wyniki;
    TextView tytul;


    ArrayAdapter arrayAdapter2;
    ArrayAdapter arrayAdapter3;
    ArrayAdapter arrayAdapter4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        bChart = (BarChart) findViewById(R.id.chart);


        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12, 3));
        entries.add(new BarEntry(30, 4));



        labels.add("Zad 1");
        labels.add("Zad 2");
        labels.add("Zad 3");
        labels.add("Zad 4");
        labels.add("Zad 5");
        data = new BarData(labels, dataset);
        bChart.setData(data); // set the data and list of lables into chart
        bChart.setDescription("Poprawne odpowiedzi na zadania");


        //nowe
        statLayout=(RelativeLayout) findViewById(R.id.statLayout);
        mChart = new PieChart(this);
        //dodaj wykres ciastkowy do layout
        statLayout.addView(mChart);
        statLayout.setBackgroundColor(Color.WHITE);

        bChart.setVisibility(View.INVISIBLE);
        mChart.setVisibility(View.INVISIBLE);

        ViewGroup.LayoutParams params = mChart.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;


        //ustawienia wykresu ciastkowego
        mChart.setUsePercentValues(true);
        mChart.setDescription("Wykres ocen");

        //odblokuje hole i konfiguracja
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.TRANSPARENT);
        mChart.setHoleRadius(20);
        mChart.setTransparentCircleRadius(30);






        //uruchomienie rotacji wykresu po obróceniu
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        //ustawienie wartości wykresu listner?
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                //wyświetl wiadomość kiedy zaznaczona wartość
                if(e==null)
                    return;
                Toast.makeText(StatisticsActivity.this, xData[e.getXIndex()]+" = "+ e.getVal() + "%", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        //dodawanie danych
        addData();

        //ustawienia legendy
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);




        wyniki= (TextView) findViewById(R.id.textwyniki);
        tytul=  (TextView) findViewById(R.id.textView2);

        button1= (Button)findViewById(R.id.button1);
        button2= (Button)findViewById(R.id.button2);
        button3= (Button)findViewById(R.id.button3);
        button4= (Button)findViewById(R.id.button4);


        button1.setOnClickListener(new View.OnClickListener(){

            void wyswietlanie(int podeszli, int zdali, int niezdali, double srednia, String egzamin)
            {
                String spodeszli=Integer.toString(podeszli);
                String szdali=Integer.toString(zdali);
                String sniezdali=Integer.toString(niezdali);
                String ssrednia=Double.toString(srednia);
                wyniki.setText(egzamin+"\n \n Do egzaminu podeszło osób: "+ spodeszli +
                        "\nZdało: "+ szdali +" \n Niezdało: "+ sniezdali+"\nŚrednia ocen: "+ssrednia);
            }





            @Override
            public void onClick(View view) {

                button2.setVisibility(ViewGroup.VISIBLE);
                button3.setVisibility(ViewGroup.VISIBLE);

                String zawartosc1 = spinner1.getSelectedItem().toString();
                String zawartosc2 = spinner2.getSelectedItem().toString();

                wyniki.setVisibility(ViewGroup.VISIBLE);
                wyniki.setText("");

                if (zawartosc1.equals("1 EF-DI") && zawartosc2.equals("zerówka"))
                {
                    String egzamin="Zerowy termin 1 EF-DI";
                    int podeszli=20;
                    int zdali=15;
                    int niezdali=5;
                    double srednia=3.78;

                    wyswietlanie(podeszli,zdali,niezdali,srednia,egzamin);
                }


                if (zawartosc1.equals("1 EF-DI") && zawartosc2.equals("1 termin"))
                {
                    String egzamin="Pierwszy termin 1 EF-DI";
                    int podeszli=25;
                    int zdali=21;
                    int niezdali=4;
                    double srednia=4.02;



                    wyswietlanie(podeszli,zdali,niezdali,srednia,egzamin);

                }


                if (zawartosc1.equals("1 EF-DI") && zawartosc2.equals("2 termin"))
                {
                    String egzamin="Drugi termin 1 EF-DI";
                    int podeszli=30;
                    int zdali=21;
                    int niezdali=9;
                    double srednia=3.47;
                    wyswietlanie(podeszli,zdali,niezdali,srednia,egzamin);

                }




                if (zawartosc1.equals("2 EF-DI") && zawartosc2.equals("zerówka"))
                {
                    String egzamin="Zerowy termin 2 EF-DI";
                    int podeszli=30;
                    int zdali=21;
                    int niezdali=9;
                    double srednia=3.47;
                    wyswietlanie(podeszli,zdali,niezdali,srednia,egzamin);
                }


                if (zawartosc1.equals("2 EF-DI") && zawartosc2.equals("1 termin"))
                {
                    String egzamin="Pierwszy termin 2 EF-DI";
                    int podeszli=24;
                    int zdali=21;
                    int niezdali=3;
                    double srednia=3.95;
                    wyswietlanie(podeszli,zdali,niezdali,srednia,egzamin);

                }


                if (zawartosc1.equals("3 EF-DI") && zawartosc2.equals("1 termin"))
                {
                    String egzamin="Pierwszy termin 3 EF-DI";
                    int podeszli=25;
                    int zdali=21;
                    int niezdali=4;
                    double srednia=3.45;
                    wyswietlanie(podeszli,zdali,niezdali,srednia,egzamin);

                }


                if (zawartosc1.equals("3 EF-DI") && zawartosc2.equals("2 termin"))
                {
                    String egzamin="Drugi termin 3 EF-DI";
                    int podeszli=12;
                    int zdali=8;
                    int niezdali=4;
                    double srednia=3.24;
                    wyswietlanie(podeszli,zdali,niezdali,srednia,egzamin);

                }


            }
        });





        button2.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                button4.setVisibility(ViewGroup.VISIBLE);
                button1.setVisibility(ViewGroup.INVISIBLE);
                button2.setVisibility(ViewGroup.INVISIBLE);
                button3.setVisibility(ViewGroup.INVISIBLE);
                tytul.setVisibility(ViewGroup.INVISIBLE);
                wyniki.setVisibility(ViewGroup.INVISIBLE);
                spinner1.setVisibility(ViewGroup.INVISIBLE);
                spinner2.setVisibility(ViewGroup.INVISIBLE);

                mChart.setVisibility(View.VISIBLE);



            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button4.setVisibility(ViewGroup.VISIBLE);
                button1.setVisibility(ViewGroup.INVISIBLE);
                button2.setVisibility(ViewGroup.INVISIBLE);
                button3.setVisibility(ViewGroup.INVISIBLE);
                tytul.setVisibility(ViewGroup.INVISIBLE);
                wyniki.setVisibility(ViewGroup.INVISIBLE);
                spinner1.setVisibility(ViewGroup.INVISIBLE);
                spinner2.setVisibility(ViewGroup.INVISIBLE);
                bChart.setVisibility(View.VISIBLE);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button4.setVisibility(ViewGroup.INVISIBLE);
                button1.setVisibility(ViewGroup.VISIBLE);
                button2.setVisibility(ViewGroup.VISIBLE);
                button3.setVisibility(ViewGroup.VISIBLE);
                tytul.setVisibility(ViewGroup.VISIBLE);
                wyniki.setVisibility(ViewGroup.VISIBLE);
                spinner1.setVisibility(ViewGroup.VISIBLE);
                spinner2.setVisibility(ViewGroup.VISIBLE);
                bChart.setVisibility(View.INVISIBLE);
                mChart.setVisibility(View.INVISIBLE);


            }
        });



        spinner1=(Spinner) findViewById(R.id.spinner1);
        spinner2=(Spinner) findViewById(R.id.spinner2);


        String[] myItems1=getResources().getStringArray(R.array.WYBIERZ_ROCZNIK);
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

                if (zawartosc1.equals("1 EF-DI"))
                {


                    spinner2.setAdapter(arrayAdapter2);
                    arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                }



                if (zawartosc1.equals("2 EF-DI"))
                {
                    spinner2.setAdapter(arrayAdapter3);
                    arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }


                if (zawartosc1.equals("3 EF-DI"))
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








    private void addData()
    {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i=0; i < yData.length; i++)
            yVals1.add(new Entry(yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for(int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        //tworzenie pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "LEGENDA OCEN");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for(int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for(int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for(int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for(int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        //inicjalizacja obiektu ciastka
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);



        mChart.setData(data);

        //undo all highlights
        mChart.highlightValues(null);

        //update pie char
        mChart.invalidate();



    }




}
