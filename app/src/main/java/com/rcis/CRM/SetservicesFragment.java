package com.rcis.CRM;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by lakmal 2017-06-06
 */
public class SetservicesFragment extends Fragment {
    public SetservicesFragment() {
    }

    private EditText finalResult;
    String name;
    InputStream is = null;
    String result = null;
    String line = null;
    boolean swdetails[];
    String cdata[];
    GridLayout grdm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)

    {
        final View rootView = inflater.inflate(R.layout.fragment_setservice, container, false);

        GridLayout grdcctv = (GridLayout) rootView.findViewById(R.id.grdcctv);

        swdetails = getArguments().getBooleanArray("service");
        final String s = getArguments().getString("snote");
        cdata = getArguments().getStringArray("cdata");

        grdm = (GridLayout) rootView.findViewById(R.id.setmain);
        if (swdetails[0]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdcctv, null);
            AsyncTaskRunner runner = new AsyncTaskRunner();
            runner.execute("cctvid", "rcisl_clients_cctv");
            finalResult = (EditText) grdv
                    .findViewById(R.id.txtcctv);
            grdm.addView(grdv);
        }
        if (swdetails[1]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdsolar, null);
            AsyncTaskRunner runner = new AsyncTaskRunner();
            runner.execute("solarid", "rcisl_clients_solar");
            finalResult = (EditText) grdv
                    .findViewById(R.id.txtsolarid);
            grdm.addView(grdv);
        }

        if (swdetails[2]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdintercom, null);

            AsyncTaskRunner runner = new AsyncTaskRunner();
            runner.execute("intercomid", "rcisl_clients_intercom");
            finalResult = (EditText) grdv
                    .findViewById(R.id.txtintercomid);
            grdm.addView(grdv);
        }

        if (swdetails[3] && swdetails[4] == false) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdnetwork, null);

            ((EditText) grdv
                    .findViewById(R.id.txtnetid))
                    .setText("nw001");
            EditText editText = (EditText) grdv.findViewById(R.id.txtaccesspoints);
            editText.setEnabled(false);
            EditText editText1 = (EditText) grdv.findViewById(R.id.txtrepeaters);
            editText1.setEnabled(false);
            grdm.addView(grdv);
        }
        if (swdetails[4] && swdetails[3] == false) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdnetwork, null);
            EditText editText = (EditText) grdv.findViewById(R.id.txtswitchs);
            editText.setEnabled(false);
            EditText editText1 = (EditText) grdv.findViewById(R.id.txtpatchpanels);
            editText1.setEnabled(false);
            EditText editText2 = (EditText) grdv.findViewById(R.id.txtnetpoints);
            editText2.setEnabled(false);
            EditText editText3 = (EditText) grdv.findViewById(R.id.txtrouters);
            editText3.setEnabled(false);
            ((EditText) grdv
                    .findViewById(R.id.txtnetid))
                    .setText("nw001");

            grdm.addView(grdv);
        }

        if (swdetails[4] && swdetails[3]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdnetwork, null);
            ((EditText) grdv
                    .findViewById(R.id.txtnetid))
                    .setText("nw001");

            grdm.addView(grdv);
        }


        if (swdetails[5]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdsoftware, null);

            ((EditText) grdv
                    .findViewById(R.id.txtsoftid))
                    .setText("sp001");
            grdm.addView(grdv);
        }
        if (swdetails[6]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdweb, null);

            ((EditText) grdv
                    .findViewById(R.id.txtWebid))
                    .setText("wb001");
            grdm.addView(grdv);
        }
        if (swdetails[7]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdcomputers, null);

            ((EditText) grdv
                    .findViewById(R.id.txtcomid))
                    .setText("com001");
            grdm.addView(grdv);
        }
        if (swdetails[8]) {

        }
        final Button finishbtn = (Button) rootView.findViewById(R.id.btnfinish);
        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swdetails[0]) {
/* }
                    else {
                        Snackbar.make(view, "cloud not found", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }*/
                    //   txtcctvid.setText("cc0001");
                }
            }

        });
        return rootView;

    }


    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... arg0) {
            publishProgress("Wait..");
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            String column = arg0[0];
            String table = arg0[1];
            nameValuePairs.add(new BasicNameValuePair("column", column));
            nameValuePairs.add(new BasicNameValuePair("table", table));
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://192.168.1.100/crm/select/selectlastid.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
                Log.e("pass 1", "connection success ");


                try {
                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(is, "iso-8859-1"), 8);
                    StringBuilder sb = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    is.close();
                    result = sb.toString();
                    Log.e("pass 2", "connection success ");
                } catch (Exception e) {
                    Log.e("Fail 2", e.toString());
                }

                try {
                    JSONObject json_data = new JSONObject(result);
                    Log.e("pass 3", "connection success ");
                    result = json_data.getString(column);

                } catch (Exception e) {
                    Log.e("Fail 3", e.toString());
                }
            } catch (Exception e) {
                Log.e("Fail 0", " error connecting to server " + e.toString());
            }

            return result;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();

            finalResult.setText(result);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(),
                    "Info",
                    "Wait for fetching data..");
        }


        @Override
        protected void onCancelled() {
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(String... text) {
            finalResult.setText(text[0]);

        }
    }
}


