package mobiili.ohjelmointi.lab4tehtava1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        LeagueEngine engine = LeagueApp.giveEngine();

        final LeagueQuery query = new LeagueQuery();
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        ListView leagueList = findViewById (R.id.leagueList);
        //final TextView vastaus = (TextView) findViewById (R.id.textView);

        String url = "https://api.football-data.org/v2/competitions?areas=2072";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (Request.Method.GET, url, null , new Response.Listener<JSONObject> ( ) {
            @Override
            public void onResponse(JSONObject response) {
                // vastaus.setText ("Response: " + response.toString ());
                query.parse(response);
                //updateList();
                Log.d ("VASTAUS", response.toString ( ));
            }
        }, new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "VIRHE!!!!");
            }
        });

        //MySingleton.getIsntace(this).addToRequestQueue(jsonObjectRequest);


        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    public void updateList(){

        ListView leagueList = findViewById (R.id.leagueList);
        ArrayList<String> leagueItems = new ArrayList<> ();

        LeagueEngine engine = LeagueApp.giveEngine ();
        for (int i = 0; i < engine.leagueAmmount(); i++){
            League league = engine.leagueIndex(i);
            leagueItems.add(String.valueOf (league.getName ()));
        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<> (this,
                android.R.layout.simple_list_item_1,
                leagueItems);

        leagueList.setAdapter(itemsAdapter);

    }
    public static MainActivity getInstance() {
        return instance;
    }

}
