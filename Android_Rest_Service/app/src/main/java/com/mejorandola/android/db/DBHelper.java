package com.mejorandola.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Juan Daniel on 14/05/2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    //Nombre de la base de datos que se guarda de ntro del archivo de archivos del telefono
    public static final String DB_NAME = "timeline.db";
    //Version de la BD
    public static final int DB_VERSION=1;
    //Nombre de la tabla
    public static final String TABLE = "timeline";
    //Columna 1
    public static final String C_ID= BaseColumns._ID;
    //Columna 2...
    public static final String C_NAME="name";
    public static final String C_SCREEN_NAME="screen_name";
    public static final String C_IMAGE_PROFILE_URL= "image_profile_url";
    public static final String C_TEXT="text";
    public static final String C_CREATED_AT="created_at";

    //Para consultar deo usar un objeto cursor
    //El orden de los parametro de cada fila afecta como queire acceder a ellos, es como un arreglo
    public static final int C_ID_INDEX=0;
    public static final int C_NAME_INDEX=1;
    public static final int C_SCREEN_NAME_INDEX=2;
    public static final int C_IMAGE_PROFILE_URL_INDEX=3;
    public static final int C_TEXT_INDEX=4;
    public static final int C_CREATED_AT_INDEX=5;

    //Debo agregar un constructor
    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creo mi tabla
        String query="create table "+ TABLE +"("
                + C_ID + "int primary key, "
                + C_NAME + "text, "
                + C_SCREEN_NAME + "text, "
                + C_IMAGE_PROFILE_URL +"text, "
                + C_TEXT + "text, "
                + C_CREATED_AT +"text"
                +")";
        //Ejecuto mi query
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        //para tirar los tweets viejos y meterle nuevos
        db.execSQL("drop table if exists "+TABLE);
        //Mando llamar de nuevo al oncreate para crear otra vez la db limpia
        onCreate(db);
    }
}
