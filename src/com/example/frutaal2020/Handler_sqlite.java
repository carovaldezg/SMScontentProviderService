package com.example.frutaal2020;

import static android.provider.BaseColumns._ID;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class Handler_sqlite extends SQLiteOpenHelper{
    
    public Handler_sqlite(Context ctx)
    {
            super(ctx, "MiBase", null, 1);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db)
    {
           // String query = "CREATE TABLE usuarios ("+_ID+" INTEGER," +
             //               "telefono TEXT PRIMARY KEY);";
    	  String query = "CREATE TABLE usuarios (telefono TEXT PRIMARY KEY);";
            db.execSQL(query);
            query = "CREATE TABLE mensajes (frase TEXT);";
            db.execSQL(query);
            cargarMensajes();
         //   cargarUsuarios();
            
    }
    
    public void cargarMensajes()
	{
		String mensaje = "Eres el html de mi corazon.com";
		insertarRegMensaje(mensaje);
		mensaje = "Tus ojos brillan mas que mi torre de cd´s";
		insertarRegMensaje(mensaje);
		mensaje = "Cuando te veo mi ciclo de CPU se acelera";
		insertarRegMensaje(mensaje);
		mensaje = "Tengo el feed RSS de tus pensamientos";
		insertarRegMensaje(mensaje);
		mensaje = "Eres el linkador de mis objetos";
		insertarRegMensaje(mensaje);
		mensaje = "Sobrevuelo los gigas de tu ram y alucino con tu wireless LAN";
		insertarRegMensaje(mensaje);
		mensaje = "Eres el .gif que anima mi vida";
		insertarRegMensaje(mensaje);		
	}
    
    public void cargarUsuarios()
    {
    	String nro = "5556";
    	insertarRegUsuario(nro);
    	nro = "5554";
    	insertarRegUsuario(nro);
    	nro = "5558";
    	insertarRegUsuario(nro);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int version_ant, int version_nue)
    {
            db.execSQL("DROP TABLE IF EXISTS usuarios");
            db.execSQL("DROP TABLE IF EXISTS mensajes");
            onCreate(db);
    }
    
  
    
    public void insertarRegUsuario(String usr)
    {
            ContentValues valores = new ContentValues();
            valores.put("telefono", usr); //COLUMNA de usuario (user) un nombre de usuario
            this.getWritableDatabase().insert("usuarios",null, valores);
    }
    
    public void insertarRegMensaje(String msg)
    {
            ContentValues valores = new ContentValues();
            valores.put("frase", msg); //COLUMNA de mensaje (frase) 
            this.getWritableDatabase().insert("mensajes",null, valores);
    }
    
    public void eliminarRegUsuario(String nro)
    {
    	String query = "DELETE FROM usuarios WHERE telefono = "+nro;
    	this.getWritableDatabase().execSQL(query);
    }
    
    public String[] leer()
    {
            String result[] = new String [100];
            String columnas[]= {_ID,"telefono"};
            Cursor c = this.getReadableDatabase().query("usuarios", columnas, null, null,null, null, null);
            
            int id,iu,ip;
            id = c.getColumnIndex(_ID);
            iu = c.getColumnIndex("telefono");
          
            
            int contador=0;
            for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
            {
                    result[contador] = c.getString(iu)+"\n";
                    contador++;
            }
            
            return result;
    
    }
    
    public ArrayList<String> obtenerUsuarios()
    {
    	ArrayList <String> users = new ArrayList <String>();
    	String columnas[]= {"telefono"};
        Cursor c = this.getReadableDatabase().query("usuarios", columnas, null, null,null, null, null);
        
        int id,iu;
 //       id = c.getColumnIndex(_ID);
        iu = c.getColumnIndex("telefono");
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        	users.add(c.getString(iu));
        if (users.size()>0)
        	return users;
        else
        	return null;
        
    	
    }
    
    public String[] leerMensajes()
    {
            String result[] = new String [1000];
            String columnas[]= {"frase"};
            Cursor c = this.getReadableDatabase().query("mensajes", columnas, null, null,null, null, null);
            
            int iu;
            iu = c.getColumnIndex("frase");
          
            
            int contador=0;
            for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
            {
                    result[contador] = c.getString(iu)+"\n";
                    contador++;
            }
            if (contador == 0)
            {
            	result[contador] = "vacio";
            }
            return result;
    
    }
    
    
    public String obtenerMensajeRandom()
    {
         Vector<String> result = new Vector<String>();
         String columnas[]= {"frase"};
         Cursor c = this.getReadableDatabase().query("mensajes", columnas, null, null,null, null, null);
         int destino;
        
         destino = c.getColumnIndex("frase");
         for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
         {
        	 result.add(c.getString(destino));
        	 
         }
         c.close();
         if (result.size()>0)
         {	Random r = new Random();
         	int pos = r.nextInt(result.size()-1);
         	return result.elementAt(pos);
         }
         else
        	 return null;
    
    }
    
    
    public Vector<String> obtenerMensajes()
    {
         Vector<String> result = new Vector<String>();
         String columnas[]= {"frase"};
         Cursor c = this.getReadableDatabase().query("mensajes", columnas, null, null,null, null, null);
         int destino;
        
         destino = c.getColumnIndex("frase");
         for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
         {
        	 result.add(c.getString(destino));
        	 
         }
         c.close();
         if (result.size()>0)
        	 return null;
         else
        	 return result;
    }
        			
    
    public void reiniciarBD()
    {
    	this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS usuarios");
    	this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS mensajes");
    	onCreate(this.getWritableDatabase());
    }
    
    public void abrir()
    {
            this.getWritableDatabase();       
    }
    
    public void cerrar()
    {
            this.close();
    }
}

