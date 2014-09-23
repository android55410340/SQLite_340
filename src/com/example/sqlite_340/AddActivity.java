package com.example.sqlite_340;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class AddActivity extends Activity {
	private EditText edt_name, edt_tel;
	private Button btn_add;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		btn_add = (Button) findViewById(R.id.btnsave);
		btn_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v == btn_add) {
					SaveData();
				}
			}
		});
	}
	public boolean SaveData() {
		edt_name = (EditText) findViewById(R.id.edit_name);
		edt_tel = (EditText) findViewById(R.id.edit_tel);
		
		
		
		
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		AlertDialog ad = adb.create();
		if (edt_name.getText().length() == 0) {
			ad.setMessage("Please input Name");
			edt_name.requestFocus();
			return false;
		}
		if (edt_tel.getText().length() == 0) {
			ad.setMessage("Please input Tel");
			edt_tel.requestFocus();
			return false;
		}
		control340DB dbClass = new control340DB(this);
		long savedata = dbClass.InsertData(edt_name.getText().toString(), 
				edt_tel.getText().toString());
		if (savedata <= 0) {
			Log.d("Error Save" ,"Error !!!!");
			return false;
		}
		Toast.makeText(getApplicationContext(), "Add Data Successfully",
				Toast.LENGTH_SHORT).show();
		return true;
	}
}