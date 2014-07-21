package com.pcsalt.sharedprefdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public static final String KEY_USERNAME = "user";
	public static final String KEY_PASSWORD = "pass";
	
	EditText etUser, etPassword;
	
	SharedPreferences preference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etUser = (EditText) findViewById(R.id.etUser);
		etPassword = (EditText) findViewById(R.id.etPassword);
		
		// Get default shared preference
		preference = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
		
		// Get values from SharedPreference and set it to EditText
		// If there is no value (for instance, at first launch)
		// then set blank string as default value
		etUser.setText(preference.getString(KEY_USERNAME, ""));
		etPassword.setText(preference.getString(KEY_PASSWORD, ""));

		findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String user = etUser.getText().toString();
				String pass = etPassword.getText().toString();
				
				// Get editor to save value in SharedPreference
				// values are stored in <key, value> pair
				SharedPreferences.Editor editor = preference.edit();
				editor
					.putString(KEY_USERNAME, user)
					.putString(KEY_PASSWORD, pass)
					.commit(); // commit is mandatory, otherwise nothing will be saved in it
				Toast.makeText(MainActivity.this, "Information saved", Toast.LENGTH_LONG).show();
			}
		});
		
		findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// this will clear the values from Shared preference
				preference.edit().clear().commit();
				
				// clear EditText
				etUser.setText("");
				etPassword.setText("");
			}
		});
		
	}

}
