package com.example.frutaal2020;

import java.util.Vector;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MostrarMensajesActivity extends Activity {
	Handler_sqlite db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_mensajes);
		TextView texto = (TextView)findViewById(R.id.textoMsg);
		db = MainActivity.obtenerBD();
		Vector<String> mensajes = db.obtenerMensajes();
		String msg = db.obtenerMensajeRandom();
		if (msg!= null)
			texto.setText(msg);
		else
			texto.setText("null");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostrar_mensajes, menu);
		return true;
	}

}
