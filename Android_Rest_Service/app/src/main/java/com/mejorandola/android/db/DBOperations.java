package com.mejorandola.android.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mejorandola.android.models.Tweet;

import java.util.ArrayList;

/**
 * Created by Juan Daniel on 14/05/2015.
 */
public class DBOperations {
    //Instancia de mi otra clase para aprovechar su metodo onCreate y onUpgrade
    private DBHelper dbHelper;
    //Cosntructor
    public DBOperations(Context context){
        dbHelper= new DBHelper(context);
    }
    //ContentVlaues es una estrcutura de información, esta dentro de android
    //trae las referencias de columan y valor
    public void insertOrIgnore(ContentValues values){
        //bdHelper conoce la estructura de mi base de datos

        //Objeto de SQLite, con permisos de esritura
        SQLiteDatabase dataBase = dbHelper.getWritableDatabase();
        //Metodo para insertar un dato
        try {
            //Este metodo permite cachar muchas execpetions
            //Recibe nombre de lBD y todos los paraertos en orden
            dataBase.insertWithOnConflict(DBHelper.TABLE,null,values,SQLiteDatabase.CONFLICT_IGNORE);
        }//try sin catch por que la constante de CONFLICT_IGNORE
        finally{
            dataBase.close();
        }

    }
    //Metodo de tipo ArrayList
    public ArrayList<Tweet> consulta(){
        //Obejto Array
        ArrayList<Tweet> tweets= new ArrayList<Tweet>();
        //Creo un objeto de SQL DB
        SQLiteDatabase bd= dbHelper.getReadableDatabase();
        //Los datos viene en formato Cursor que es un cals e de Android
        //Accede a su fila y luegoo a sus valores de columna
        Cursor cursor=bd.query(DBHelper.TABLE, null,null,null,null,null,null);

        //Valido que la db tenga algo
        if(cursor.moveToFirst()){
            while(cursor.isAfterLast()==false){
                Tweet tw= new Tweet();
                //Usanod los getters y setters del objeto tweet
                //Hago un objeto tweet sy set le mando cursor.getTIpo(posicion de la columna);
                tw.setId(String.valueOf(cursor.getInt(DBHelper.C_ID_INDEX)));
                tw.setName(cursor.getString(DBHelper.C_SCREEN_NAME_INDEX));
                tw.setScreenName(cursor.getString(DBHelper.C_SCREEN_NAME_INDEX));
                tw.setProfileImageUrl(cursor.getString(DBHelper.C_IMAGE_PROFILE_URL_INDEX));
                tw.setText(cursor.getString(DBHelper.C_TEXT_INDEX));
                tw.setCreatedAt(cursor.getString(DBHelper.C_CREATED_AT_INDEX));
                //Agrego al Array el objeto tweet creado
                tweets.add(tw);
                //Siguiente iteracion
                cursor.moveToNext();
            }
        }

        //Regresa un arreglo de tweets
        return tweets;
    }
}
