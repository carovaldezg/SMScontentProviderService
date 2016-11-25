package com.example.frutaal2020;

import java.util.ArrayList;
import java.util.Vector;



import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class emisorSMS {
	
	Handler_sqlite bd;
	ArrayList<String> usuarios;
	Context contexto;
	
	public emisorSMS(Context cont, Handler_sqlite base)
	{
		contexto = cont;
		bd = base;
		bd.abrir();
		usuarios = bd.obtenerUsuarios();
		bd.cerrar();
	}
	
	public void enviarSMS()
	{   bd.abrir();
		String mensajeAleatorio = bd.obtenerMensajeRandom();
		if (mensajeAleatorio!=null)
		{
			usuarios = bd.obtenerUsuarios();
			if((usuarios!=null)&&(usuarios.size()> 0))
			{
				for (int i=0; i<usuarios.size(); i++)
					enviarSMS( usuarios.get(i),mensajeAleatorio);
				Toast.makeText(contexto, "Mensajes enviados a todos los usuarios", Toast.LENGTH_LONG).show();
			}
			else
				Toast.makeText(contexto, "No hay usuarios en la BD", Toast.LENGTH_LONG).show();
		}
		else
			Toast.makeText(contexto, "No se encontro mensaje", Toast.LENGTH_LONG).show();
		bd.cerrar();
	}
	
	 private void enviarSMS(String numero, String message){
	    	SmsManager sms = SmsManager.getDefault();
	    	sms.sendTextMessage(numero, null, message, null , null);
	    	}
	
	

}
