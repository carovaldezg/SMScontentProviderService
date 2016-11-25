package com.example.frutaal2020;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AgregarMensajeActivity extends Activity {

	EditText textomsg;
	Handler_sqlite bd;
	Button boton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar_mensaje);
		boton = (Button)findViewById(R.id.botonAddMsg);
		textomsg = (EditText)findViewById(R.id.editTextmensaje);
		bd = new Handler_sqlite(this);
		boton.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if (textomsg.getText().toString().length()>0)
				{	
					bd.abrir();
					bd.insertarRegMensaje(textomsg.getText().toString());
					bd.cerrar();
					Toast.makeText(getApplicationContext(), "Mensaje guardado en la bd", Toast.LENGTH_LONG).show();
				}
				else
					{
						Toast.makeText(getApplicationContext(), "No puede ingresar un mensaje en blanco", Toast.LENGTH_LONG).show();
					}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agregar_mensaje, menu);
		return true;
	}

}
