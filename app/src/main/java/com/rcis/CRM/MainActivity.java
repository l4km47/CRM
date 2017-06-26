package com.rcis.CRM;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;


    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;

    private Receiver receiver;
    IntentFilter filter;
    TextView con;
    LinearLayout ll;
    LinearLayout btnl;

    Button add;
    Button search;
    Fragment[] f;
    FragmentTransaction[] transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            btnl = (LinearLayout) findViewById(R.id.btnl);

            btnl.setVisibility(View.VISIBLE);
            ll = (LinearLayout) findViewById(R.id.constus);
            con = (TextView) findViewById(R.id.con);
            search = (Button) findViewById(R.id.nav_search);
            add = (Button) findViewById(R.id.nav_add);
            filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            receiver = new Receiver();
            registerReceiver(receiver, filter);
            f = new Fragment[2];
            transaction = new FragmentTransaction[2];
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            //   listView = (ExpandableListView) findViewById(R.id.lvExp);
            // initData();
            //   listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
            //   listView.setAdapter(listAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("EDMTDev");
        listDataHeader.add("Android");
        listDataHeader.add("Xamarin");
        listDataHeader.add("UWP");

        List<String> edmtDev = new ArrayList<>();
        edmtDev.add("This is Expandable ListView");

        List<String> androidStudio = new ArrayList<>();
        androidStudio.add("Expandable ListView");
        androidStudio.add("Google Map");
        androidStudio.add("Chat Application");
        androidStudio.add("Firebase ");

        List<String> xamarin = new ArrayList<>();
        xamarin.add("Xamarin Expandable ListView");
        xamarin.add("Xamarin Google Map");
        xamarin.add("Xamarin Chat Application");
        xamarin.add("Xamarin Firebase ");

        List<String> uwp = new ArrayList<>();
        uwp.add("UWP Expandable ListView");
        uwp.add("UWP Google Map");
        uwp.add("UWP Chat Application");
        uwp.add("UWP Firebase ");

        listHash.put(listDataHeader.get(0), edmtDev);
        listHash.put(listDataHeader.get(1), androidStudio);
        listHash.put(listDataHeader.get(2), xamarin);
        listHash.put(listDataHeader.get(3), uwp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_add:
                transaction[0] = getFragmentManager().beginTransaction();
                f[0] = new AddFragment();

                if (f[0] != null) {
                    transaction[0].replace(R.id.container, f[0]);
                    transaction[0].addToBackStack(null);
                    transaction[0].commit();
//                        listView.setVisibility(View.GONE);
                    btnl.setVisibility(View.GONE);
                }
            case R.id.nav_search:
                transaction[1] = getFragmentManager().beginTransaction();
                f[1] = new SearchFragment();

                if (f[1] != null) {
                    transaction[1].replace(R.id.container, f[1]);
                    transaction[1].addToBackStack(null);
                    transaction[1].commit();
                    //                      listView.setVisibility(View.GONE);
                    btnl.setVisibility(View.GONE);
                }
        }

    }

    private class isconnected extends AsyncTask<Boolean, Boolean, Boolean> {

        @Override
        protected Boolean doInBackground(Boolean... booleen) {
            publishProgress(false);
            boolean exists = false;

            try {
                SocketAddress sockaddr = new InetSocketAddress("192.168.2.100", 80);
                // Create an unbound socket
                Socket sock = new Socket();

                // This method will block no more than timeoutMs.
                // If the timeout occurs, SocketTimeoutException is thrown.
                int timeoutMs = 2000;   // 2 seconds
                sock.connect(sockaddr, timeoutMs);
                sock.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;  // Handle exception
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

        }

        @Override
        protected void onProgressUpdate(Boolean... values) {
        }
    }

    @Override
    protected void onResume() {
        try {
            super.onResume();
            registerReceiver(receiver, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        try {
            super.onDestroy();
            unregisterReceiver(receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class Receiver extends BroadcastReceiver {
        private boolean isConnected;

        @Override
        public void onReceive(final Context context, final Intent intent) {
            if (isNetworkAvailable(context)) {
                con.setText("");
                add.setEnabled(true);
                search.setEnabled(true);
                ll.setVisibility(View.GONE);
            } else {
                con.setText("Error connecting to server");
                add.setEnabled(false);
                search.setEnabled(false);
                ll.setVisibility(View.VISIBLE);
            }

        }

        private boolean isNetworkAvailable(Context context) {
            ConnectivityManager connectivity = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            AsyncTask is = new isconnected().execute(true);
                            try {
                                if (is.get().toString().contains("true")) {
                                    isConnected = true;
                                    return true;
                                }
                            } catch (InterruptedException | ExecutionException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }
            isConnected = false;
            return false;
        }


    }

    @Override
    public void onBackPressed() {
        int i = getFragmentManager().getBackStackEntryCount();
        String j = "0";
        switch (i) {
            case 0:
                j = "0";
                break;
            case 1:
                j = "1";
                btnl.setVisibility(View.VISIBLE);
                //  listView.setVisibility(View.VISIBLE);
                break;
        }
        Log.i("back", j);
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }
}
