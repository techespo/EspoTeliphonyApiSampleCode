package com.espoir.telephony;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	   TextView textView1;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView1=(TextView)findViewById(R.id.textView1);  
        
        //Get the instance of TelephonyManager  
        TelephonyManager  tm =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
         
        //Calling the methods of TelephonyManager the returns the information  
        String IMEINumber=tm.getDeviceId();  
        String subscriberID=tm.getDeviceId();  
        String SIMSerialNumber=tm.getSimSerialNumber();  
        String networkCountryISO=tm.getNetworkCountryIso();  
        String SIMCountryISO=tm.getSimCountryIso();  
        String softwareVersion=tm.getDeviceSoftwareVersion();  
        String voiceMailNumber=tm.getVoiceMailNumber();  
          
        //Get the phone type  
        String strphoneType="";  
          
        int phoneType=tm.getPhoneType();  
  
        switch (phoneType)   
        {  
                case (TelephonyManager.PHONE_TYPE_CDMA):  
                           strphoneType="CDMA";  
                               break;  
                case (TelephonyManager.PHONE_TYPE_GSM):   
                           strphoneType="GSM";                
                               break;  
                case (TelephonyManager.PHONE_TYPE_NONE):  
                            strphoneType="NONE";                
                                break;  
         }  
          
        //getting information if phone is in roaming  
        boolean isRoaming=tm.isNetworkRoaming();  
          
        String info="Phone Details:\n";  
        info+="\n IMEI Number:"+IMEINumber;  
        info+="\n SubscriberID:"+subscriberID;  
        info+="\n Sim Serial Number:"+SIMSerialNumber;  
        info+="\n Network Country ISO:"+networkCountryISO;  
        info+="\n SIM Country ISO:"+SIMCountryISO;  
        info+="\n Software Version:"+softwareVersion;  
        info+="\n Voice Mail Number:"+voiceMailNumber;  
        info+="\n Phone Network Type:"+strphoneType;  
        info+="\n In Roaming? :"+isRoaming;  
          
        textView1.setText(info);//displaying the information in the textView  
		
		
		
		
		
		TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		TelephonyMgr.listen(new TeleListener(),
				PhoneStateListener.LISTEN_CALL_STATE);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	class TeleListener extends PhoneStateListener {
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				// CALL_STATE_IDLE;
				Toast.makeText(getApplicationContext(), "CALL_STATE_IDLE",
						Toast.LENGTH_LONG).show();
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				// CALL_STATE_OFFHOOK;
				Toast.makeText(getApplicationContext(), "CALL_STATE_OFFHOOK",
						Toast.LENGTH_LONG).show();
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				// CALL_STATE_RINGING
				Toast.makeText(getApplicationContext(), incomingNumber,
						Toast.LENGTH_LONG).show();
				Toast.makeText(getApplicationContext(), "CALL_STATE_RINGING",
						Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
		}

	}
}