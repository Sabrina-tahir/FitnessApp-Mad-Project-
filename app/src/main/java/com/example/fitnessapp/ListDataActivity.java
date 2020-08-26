package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import java.util.ArrayList;


public class ListDataActivity extends AppCompatActivity {

    StepsRecord rec;
    ArrayList<Stepdata> stepdatalist;
    ListView listView;
    Stepdata Stepdata;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        rec= new StepsRecord(this);

        stepdatalist = new ArrayList<>();
        Cursor data = rec.getData();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(ListDataActivity.this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
        }else{
            int i=0;
            while(data.moveToNext()){
                Stepdata = new Stepdata(data.getString(0),data.getString(1),data.getString(2));
                stepdatalist.add(i,Stepdata);
                System.out.println(data.getString(0)+" "+data.getString(1)+" "+data.getString(2));
                System.out.println(stepdatalist.get(i).getGoal());
                i++;
            }
            ThreeColumn_ListAdapter adapter =  new ThreeColumn_ListAdapter(this,R.layout.list_adapter_view, stepdatalist);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }
}

//    private static final String TAG = "ListDataActivity";
//
//    StepsRecord rec;
//
//    private ListView mListView;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_data);
////        mListView = (ListView) findViewById(R.id.listView);
//        rec = new StepsRecord(this);
//        populateListView();
//    }
//
//    private void populateListView() {
//        Cursor rs1 = rec.getData();
//        StringBuilder sb = new StringBuilder();
//        while (rs1.moveToNext()) {
//            String script = rs1.getString(0);
//            String call = rs1.getString(1);
//
//            sb.append(script).append(";").append(call).append(";").append("_");
//
//        }
//        String str = sb.toString();
//        String st = new String(str);
//        Log.e("Main", st);
//        String[] rows = st.split("_");
//        TableLayout tableLayout = (TableLayout) findViewById(R.id.tab);
//        tableLayout.removeAllViews();
//
//
//        for (int i = 0; i < rows.length; i++) {
//            String row = rows[i];
//            TableRow tableRow = new TableRow(getApplicationContext());
//            final String[] cols = row.split(";");
//
//            Handler handler = null;
//
//            for (int j = 0; j < cols.length; j++) {
//
//                final String col = cols[j];
//                final TextView columsView = new TextView(getApplicationContext());
//                columsView.setText(String.format("%7s", col));
//                tableRow.addView(columsView);
//            }
//        }
//    }
//}