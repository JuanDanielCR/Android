package com.reserva;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juan Daniel on 31/05/2015.
 */
public class SQLHelp extends SQLiteOpenHelper {
    String SQL_Tabla = "CREATE TABLE Reservacion ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Nombre TEXT, Pass TEXT)";

    public SQLHelp(Context context, String DBname,
                       SQLiteDatabase.CursorFactory factory,
                       int version) {
        super(context, DBname, factory, version);
    }

    @Override
    // La primera vez que se ejecute la app entra aqui
    // para crear la tabla
    public void onCreate(SQLiteDatabase baseDatos) {
        baseDatos.execSQL(SQL_Tabla);
    }

    @Override
    // Este se ejecuta cuando modificamos algo (agregamos algun
    // otro campo en alguna tabla)
    public void onUpgrade(SQLiteDatabase baseDatos,
                          int oldVersion, int newVersion) {
        baseDatos.execSQL(SQL_Tabla);
    }
}