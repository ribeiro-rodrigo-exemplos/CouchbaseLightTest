package br.com.m2msolutions.couchbaselighttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.couchbase.lite.Array;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Document;
import com.couchbase.lite.Expression;
import com.couchbase.lite.Meta;
import com.couchbase.lite.MutableArray;
import com.couchbase.lite.MutableDocument;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* try{

            DatabaseConfiguration config = new DatabaseConfiguration(getApplicationContext());
            Database database = new Database("mydb",config);

            List<String> list = new ArrayList<>();

            list.add("Teste1");
            list.add("Teste2");

            MutableArray array = new MutableArray();


            MutableDocument mutableDoc = new MutableDocument()
                                                .setFloat("version",2.0F)
                                                .setString("type","SDK");


            database.save(mutableDoc);

            mutableDoc = database.getDocument(mutableDoc.getId()).toMutable();
            mutableDoc.setString("language","Java");
            database.save(mutableDoc);

            Document document = database.getDocument(mutableDoc.getId());

            Toast.makeText(this,"Document ID :: "+document.getId(),Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Learning :: "+document.getString("language"),Toast.LENGTH_SHORT).show();

            Query query = QueryBuilder.select(SelectResult.all())
                                        .from(DataSource.database(database))
                                        .where(Expression.property("type").equalTo(Expression.string("SDK")));

            ResultSet result = query.execute();
            Toast.makeText(this,"Number of rows :: "+result.allResults().size(),Toast.LENGTH_SHORT).show();

        }
        catch (CouchbaseLiteException e){
            e.printStackTrace();
        } */

       float[][] pontos = new float[1][2];
       pontos[0][0] = 12;
       pontos[0][1] = 14;

       GeoPoint geoPoint = new GeoPoint("LineString", pontos);
       Trajeto trajeto = new Trajeto("trajeto 1",geoPoint);

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = mapper.convertValue(trajeto, Map.class);

        try{

            DatabaseConfiguration config = new DatabaseConfiguration(getApplicationContext());
            Database database = new Database("mydb",config);

            MutableDocument document = new MutableDocument(map);
            database.save(document);

            Document document1 = database.getDocument(document.getId());

            Map<String,Object> objetoCadastrado = document1.toMap();

            Trajeto trajetoCadastrado = mapper.convertValue(objetoCadastrado,Trajeto.class);

            Toast.makeText(this,"Number of rows :: "+trajetoCadastrado.getLinestring().getCoordinates()[0][1],Toast.LENGTH_LONG).show();

            Query query = QueryBuilder
                                .select(
                                        //SelectResult.expression(Meta.id),
                                        SelectResult.property("linestring")
                                ).from(DataSource.database(database))
                                 .where(Expression.property("nome").equalTo(Expression.string("trajeto 1")));

            ResultSet rs = query.execute();



            for(Result result:rs){
                Map<String,Object> ob = result.toMap();
                Object linestring = ob.get("linestring");
                GeoPoint geo = mapper.convertValue(linestring,GeoPoint.class);
                Toast.makeText(this,"geolocation :: "+geo.getCoordinates()[0][1],Toast.LENGTH_LONG).show();
            }



        }catch (CouchbaseLiteException e){
            e.printStackTrace();
        }

    }
}
