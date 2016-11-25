package com.example.frutaal2020;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;



public class prueba extends IntentService{

	Handler_sqlite db = MainActivity.obtenerBD();
	
	public prueba() {
		super("myIntentServiceName");
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void onHandleIntent(Intent nuevo) {
	    Bundle b = nuevo.getExtras();
		db.abrir();
		db.insertarRegUsuario(b.getString("numero"));
		db.cerrar();
	}

}
