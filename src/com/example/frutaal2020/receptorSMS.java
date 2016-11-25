package com.example.frutaal2020;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;


public class receptorSMS extends BroadcastReceiver{
	
	public void onReceive(Context context, Intent intent) 
	{
		//—get the SMS message passed in—
		Bundle bundle = intent.getExtras();
		SmsMessage[] msgs = null;
		String messages = "";
		if (bundle != null)
		{
		//—retrieve the SMS message received—
			Object[] smsExtra = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[smsExtra.length];

			for (int i=0; i<msgs.length; i++)
			{
				SmsMessage sms = SmsMessage.createFromPdu((byte[])smsExtra[i]);
				//take out content from sms
				String body = sms.getMessageBody().toString();
				String address = sms.getOriginatingAddress();
				messages += address + "\n";
				if (body.toLowerCase().equals("alta 2020"))
					InsertarUsuarioEnBD(address.toString(), context );
				else
					if (body.toLowerCase().equals("baja 2020"))
						EliminarUsuarioEnBD(address.toString(), context);
						
			}
		}
	}
		
		public void InsertarUsuarioEnBD(String nro, Context cont)
		{
			Intent x = new Intent("INTENT_DE_CARO");
			x.putExtra("extradata", nro);
			cont.startService(x);
		}
		
		public void EliminarUsuarioEnBD(String nro, Context cont)
		{
			Intent x = new Intent("INTENT_BAJA_USUARIOS");
			x.putExtra("extradata", nro);
			cont.startService(x);
		}
		
}
