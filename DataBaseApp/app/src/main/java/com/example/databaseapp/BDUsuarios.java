package com.example.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BDUsuarios extends SQLiteOpenHelper {
    private Context contexto;
    private static final String DATABASE_NAME = "usuarios.db";
    private static final int DATABASE_VERSION = 1;
    private final String SQLCREATE = "CREATE TABLE Usuarios (telefono INTEGER, nombre TEXT)";
    private final String SQLDROP = "DROP TABLE IF EXISTS Usuarios";
    private SQLiteDatabase bd = null;

    public BDUsuarios(Context contexto){
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQLCREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQLDROP);
        db.execSQL(SQLCREATE);
    }
    public void cerrarBD(){
        if(bd != null){
            bd.close();
        }
    }

    public void insertarUsuario(int telefono, String nombre) throws Exception{
        //obtengo los datos en modos escritura
        bd = getWritableDatabase();
        //si hemos abierto correctamente la BD
        if(bd != null){
            long newRowId;
            try{
                ContentValues values = new ContentValues();
                values.put("telefono", telefono);
                values.put("nombre", nombre);
                newRowId = bd.insert("Usuarios", null, values);
                cerrarBD();
            }catch (Exception e){
                throw new Exception(e.toString());
            }
        }
    }
    public void eliminarUsuario() throws Exception{
        //obtengo los datos en modos escritura
        bd = getWritableDatabase();
        //si hemos abierto correctamente la BD
        try{
            //defino la parte del where
            String selection = "nombre = ?";
            String[] whereArgs = {"Juan"};
            //ejecuto la sentencia
            bd.delete("Usuarios", selection, whereArgs);
            cerrarBD();
        }catch (Exception e){
            throw new Exception(e.toString());

        }
    }
    public void actualizarUsuario(String nombreAntiguo, String nombreNuevo,
                                   int telefonoNuevo) throws Exception {
        //obtengo los datos en modos escritura
        bd = getWritableDatabase();
        //si hemos abierto correctamente la BD
        try {
            //Creo un ContentValues con los valores a insertar
            ContentValues values = new ContentValues();
            values.put("nombre", nombreNuevo);
            values.put("telefono", telefonoNuevo);
            String selection = "nombre = ?";
            String[] whereArgs = {nombreAntiguo};
            bd.update("Usuarios", values, selection, whereArgs);
            cerrarBD();

        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    public ArrayList<ElementoUsuario> mostrarUsuarios() throws Exception {
        ArrayList<ElementoUsuario> usuario = new ArrayList<>();
        //obtengo los datos en modo lectura
        bd = getReadableDatabase();
        //si hemos abierto correctamente la BD
        try {
            //Creo el cursor de la consulta
            Cursor c = bd.query("Usuarios", null, null,
                    null, null, null, null);
            //Muestro los datos
            c.moveToFirst();
            if (c.getCount() > 0) {
                do{
                    int telefono = c.getInt(0);
                    String nombre = c.getString(1);
                    usuario.add(new ElementoUsuario(telefono, nombre));
                }while (c.moveToNext());

            }
            c.close();
            bd.close();
            return usuario;
            }catch (Exception e){
            throw new Exception(e.toString());
        }
        }

    }

