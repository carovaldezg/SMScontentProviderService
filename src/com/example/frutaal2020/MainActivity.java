package com.example.frutaal2020;

import java.util.ArrayList;
import java.util.Vector;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText textomsg;
	TextView texto;
	static Handler_sqlite db;
	emisorSMS emisor;
	Bundle datos;
    static Context contexto;
  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contexto = this.getApplicationContext();
		
		db = new Handler_sqlite (this);
		emisor = new emisorSMS(this, db);
		texto = (TextView)findViewById(R.id.mensaje);
		Button botonAddMsg = (Button)findViewById(R.id.botonAddMsg);
		Button botonVerMsg = (Button)findViewById(R.id.botonVerMsg);
		Button botonVerUsers = (Button)findViewById(R.id.botonVerUsers);
		Button botonEnviarSms = (Button)findViewById(R.id.botonEnviarMsg);
		Button botonReiniciarBD = (Button)findViewById(R.id.botonreiniciardb);
		textomsg = (EditText)findViewById(R.id.editTextmensaje);
		
		
		botonAddMsg.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent addNuevoMensaje = new Intent (MainActivity.this, AgregarMensajeActivity.class);
				startActivity(addNuevoMensaje);
			}
		});
		
		botonEnviarSms.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				emisor.enviarSMS();
			}
	    });
		
		botonReiniciarBD.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				db.abrir();
				db.reiniciarBD();
				db.cerrar();
				Toast.makeText(MainActivity.this, "Se ha reiniciado la BD", Toast.LENGTH_LONG).show();
			}
		});
		
		botonVerUsers.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				db.abrir();
				ArrayList <String> usuarios = db.obtenerUsuarios();
				db.cerrar();
				Bundle datos = new Bundle();
				datos.putStringArrayList("arreglo", usuarios);
				Intent prueba = new Intent (MainActivity.this, MostrarUsuariosActivity.class);
				prueba.putExtras(datos);
				startActivity(prueba);
			}
		});
		
		
		botonVerMsg.setOnClickListener(new View.OnClickListener() 
		{@Override
			public void onClick(View v) 
			{
			
			Intent nuevo = new Intent(MainActivity.this, MostrarMensajesActivity.class);
			startActivity(nuevo);
			}
			
		});
			
	
		
	}
	
	public void insertarEnBD()
	{
		datos = this.getIntent().getExtras();
		db.abrir();
		db.insertarRegUsuario(datos.getString("numero"));
		db.cerrar();
		
	}
	
	public static Handler_sqlite obtenerBD()
	{
		return db;
	}

	public static Context getContexto()
	{
		return contexto;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
