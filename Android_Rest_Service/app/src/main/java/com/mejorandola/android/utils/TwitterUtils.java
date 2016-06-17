package com.mejorandola.android.utils;

import android.util.Base64;
import android.util.Log;

import com.mejorandola.android.models.Tweet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TwitterUtils {
	
	public static final String TAG = "TwitterUtils";
	
	public static String appAuthentication(){

		HttpURLConnection httpConnection = null;
		OutputStream outputStream = null;
		BufferedReader bufferedReader = null;
		StringBuilder response = null;

		try {
			URL url = new URL(ConstantsUtils.URL_AUTHENTICATION);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("POST");
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);

			String accessCredential = ConstantsUtils.CONSUMER_KEY + ":" + ConstantsUtils.CONSUMER_SECRET;
			String authorization = "Basic " + Base64.encodeToString(accessCredential.getBytes(), Base64.NO_WRAP);
			String param = "grant_type=client_credentials";

			httpConnection.addRequestProperty("Authorization", authorization);
			httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			httpConnection.connect();
			
			outputStream = httpConnection.getOutputStream();
			outputStream.write(param.getBytes());
			outputStream.flush();
			outputStream.close();

			bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
			String line;
			response = new StringBuilder();

			while ((line = bufferedReader.readLine()) != null){            
				response.append(line);	
			}

			Log.d(TAG, "POST response code: " + String.valueOf(httpConnection.getResponseCode()));
			Log.d(TAG, "JSON response: " + response.toString());

		} catch (Exception e) {
			Log.e(TAG, "POST error: " + Log.getStackTraceString(e));
			
		}finally{
			if (httpConnection != null) {
				httpConnection.disconnect();
			}
		}
		return response.toString();
	}
	
	public static ArrayList<Tweet> getTimelineForSearchTerm(String searchTerm){
		//Creo un objeto de array list
        ArrayList<Tweet> tweet= new ArrayList<Tweet>();
		HttpURLConnection httpConnection = null;
		BufferedReader bufferedReader = null;
		StringBuilder response = new StringBuilder();

		try {
			URL url = new URL(ConstantsUtils.URL_SEARCH + searchTerm + "&count=20");
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("GET");

			String jsonString = appAuthentication();
			JSONObject jsonObjectDocument = new JSONObject(jsonString);
			String token = jsonObjectDocument.getString("token_type") + " " + 
					jsonObjectDocument.getString("access_token");

			httpConnection.setRequestProperty("Authorization", token);
			httpConnection.setRequestProperty("Content-Type", "application/json");
			httpConnection.connect();

			bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));

			String line;
			while ((line = bufferedReader.readLine()) != null){            
				response.append(line);	
			}
			
			Log.d(TAG, "GET response code: " + String.valueOf(httpConnection.getResponseCode()));
			Log.d(TAG, "JSON response: " + response.toString());

            //ME TRAIGO CODIGO
            //Objeto que Convierte a json un string
            JSONObject respuesta= new JSONObject(response.toString());
            //Objeto que obtendra un arreglo de un json, con el metodo getJSONArray("recibe un nombre de arreglo- ver documentacion")
            JSONArray arreglo_respuesta= respuesta.getJSONArray("statuses");

            //Creo un miniobjeto JSON
            JSONObject jsonsito;
            //El jsonsito se llenara con un for que sacara solo la info importante del JSON general
            //El arreglo va reccoriendo los tweets dentro del array status
            for (int i=0; i<arreglo_respuesta.length(); i++){
                //Jsonsito se va transformando en un objeto distinto de todos os que estan dentro del array
                jsonsito=(JSONObject) arreglo_respuesta.get(i);
                //Creo un objeto tweet
                Tweet ntweet= new Tweet();

                ntweet.setId(jsonsito.getString("id_str"));
                //jsonsito esta en i, ies un array, obtengo user(un objeto dentro del array) y su valor
                ntweet.setName(jsonsito.getJSONObject("user").getString("name"));
                ntweet.setScreenName(jsonsito.getJSONObject(("user")).getString("screen_name"));
                ntweet.setProfileImageUrl(jsonsito.getJSONObject("user").getString("profile_image_url"));

                ntweet.setText(jsonsito.getString("text"));
                ntweet.setCreatedAt(jsonsito.getString("created_at"));

                //Agrgero el tweet al arraylist
                tweet.add(i,ntweet);
            }
		} catch (Exception e) {
			Log.e(TAG, "GET error: " + Log.getStackTraceString(e));
		}finally {
			if(httpConnection != null){
				httpConnection.disconnect();
			}
		}
        return tweet;
	}

}
