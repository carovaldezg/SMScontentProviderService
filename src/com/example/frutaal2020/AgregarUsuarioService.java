package com.example.frutaal2020;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class AgregarUsuarioService extends IntentService {
	
	Handler_sqlite db;
	
	public AgregarUsuarioService() {
		super("myIntentServiceName");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		db = MainActivity.obtenerBD();
		String num = intent.getStringExtra("extradata");
	    db.abrir();
		db.insertarRegUsuario(num);
		db.cerrar();
		
	}

	
}
