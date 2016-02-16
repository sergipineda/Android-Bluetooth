package com.iesebre.dam2.spineda.bluetooth;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    private static final String TAG = "bluetooth MainActivity";
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBluetoothAdapter  = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Bluetooth is available", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled, so set up a chat session
                    Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
                } else {
                    // User did not enable Bluetooth or an error occurred
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(this, "Bluetooth no s'ha activat correctament",
                            Toast.LENGTH_SHORT).show();
                    this.finish();
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_discover:
                               Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                               discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                              startActivity(discoverableIntent);
                               return true;

        }
        return super.onOptionsItemSelected(item);
    }
}

