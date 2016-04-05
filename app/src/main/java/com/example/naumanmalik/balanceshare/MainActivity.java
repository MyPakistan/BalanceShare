package com.example.naumanmalik.balanceshare;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.net.Uri;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final Button b = (Button) findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String baseUssd ="";

                // builder.aSppend("73");
                // builder.append(Uri.encode("#"));
                TelephonyManager manager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                String carrierName = manager.getNetworkOperator();
                String  code=null;
                if(carrierName.equals("41006")){//telenor
                    code=Uri.encode("*")+"1"+Uri.encode("*")+"1"+Uri.encode("*");
                    baseUssd = Uri.encode("*") + "444" + Uri.encode("#");
                }else if(carrierName.equals("41001")){//jazz
                    baseUssd = Uri.encode("*") + "111" + Uri.encode("#");
                    code = Uri.encode("*") + "100" + Uri.encode("*");
                }else if(carrierName.equals("41003")){//ufone
                    baseUssd = Uri.encode("*") + "124" + Uri.encode("#");
                    code = Uri.encode("*") + "828" + Uri.encode("*");
                }else if(carrierName.equals("41007")){//warid
                    baseUssd = Uri.encode("*") + "100" + Uri.encode("#");
                }else if(carrierName.equals("41004")){//zong
                    baseUssd = Uri.encode("*") + "222" + Uri.encode("#");
                }

                StringBuilder builder = new StringBuilder();
                builder.append(baseUssd);
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + builder.toString()));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }
                }

                //String s=manager.getSimOperatorName();

                b.setText(carrierName);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
