package com.example.frutaal2020;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

public class EliminarUsuarioService extends IntentService{

	Handler_sqlite db;
	public EliminarUsuarioService() 
	{
		super("nuevoIntentService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) 
	{
		db = MainActivity.obtenerBD();
		String num = intent.getStringExtra("extradata");
	    db.abrir();
		db.eliminarRegUsuario(num);
		db.cerrar();
	}

}

	

