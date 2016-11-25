package com.example.frutaal2020;

import java.util.ArrayList;
import java.util.Vector;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class MostrarUsuariosActivity extends Activity {

	Bundle datos = new Bundle();
	ArrayList<String> arre;
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_usuarios);
		lv = (ListView) findViewById(R.id.listView1);
	    TextView texto1 = (TextView)findViewById(R.id.textView1);
	    TextView texto2 = (TextView)findViewById(R.id.textView3);
	    datos = this.getIntent().getExtras();
	    arre = new ArrayList<String>();
	    arre = datos.getStringArrayList("arreglo");
	  
	    if ((arre == null) || (arre.size()==0))
	    	texto1.setText("null");
	    else
	    {   
	    	String texto = arre.get(arre.size()-1);
	    	texto1.setText(texto);
	    }
	 
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostrar_usuarios, menu);
		return true;
	}

}
