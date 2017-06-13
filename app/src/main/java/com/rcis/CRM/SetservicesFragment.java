package com.rcis.CRM;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/**
 * Created by lakmal 2017-06-06
 */
public class SetservicesFragment extends Fragment {
    String name;
    InputStream is = null;
    String result = null;
    String line = null;
    boolean swdetails[];
    String cdata[];
    GridLayout grdm;
    private EditText finalResult;

    public SetservicesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)

    {
        final View rootView = inflater.inflate(R.layout.fragment_setservice, container, false);

        GridLayout grdcctv = (GridLayout) rootView.findViewById(R.id.grdcctv);

        swdetails = getArguments().getBooleanArray("service");
        final String s = getArguments().getString("snote");
        cdata = getArguments().getStringArray("cdata");

        syncgetid runner;


     /*   ProgressDialog progressDialog = ProgressDialog.show(getActivity(),
                "Info",
                "Wait for fatching data..");*/
        int count;
        grdm = (GridLayout) rootView.findViewById(R.id.setmain);
        if (swdetails[0]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdcctv, null);
            runner = new syncgetid();
            runner.execute("cctvid", "rcisl_clients_cctv");
            try {

                Integer cn;
                cn = Integer.parseInt(runner.get().substring(runner.get().length() - 4, runner.get().length()));
                cn++;
                String cn_ = cn.toString();
                if (cn.toString().length() == 1) {
                    cn_ = "000" + cn;
                }

                if (cn.toString().length() == 2) {
                    cn_ = "00" + cn;
                }

                if (cn.toString().length() == 3) {
                    cn_ = "0" + cn;
                }

                ((EditText) grdv
                        .findViewById(R.id.txtcctv)).setText("cc" + cn_);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            grdm.addView(grdv);
            count = 1;
        }
        if (swdetails[1]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdsolar, null);
            runner = new syncgetid();
            runner.execute("solarid", "rcisl_clients_solar");
            try {

                Integer cn;
                cn = Integer.parseInt(runner.get().substring(runner.get().length() - 4, runner.get().length()));
                cn++;
                String cn_ = cn.toString();
                if (cn.toString().length() == 1) {
                    cn_ = "000" + cn;
                }

                if (cn.toString().length() == 2) {
                    cn_ = "00" + cn;
                }

                if (cn.toString().length() == 3) {
                    cn_ = "0" + cn;
                }

                ((EditText) grdv
                        .findViewById(R.id.txtsolarid)).setText("sp" + cn_);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            grdm.addView(grdv);
            count = 2;
        }

        if (swdetails[2]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdintercom, null);

            runner = new syncgetid();
            runner.execute("intercomid", "rcisl_clients_intercom");
            try {

                Integer cn;
                cn = Integer.parseInt(runner.get().substring(runner.get().length() - 4, runner.get().length()));
                cn++;
                String cn_ = cn.toString();
                if (cn.toString().length() == 1) {
                    cn_ = "000" + cn;
                }

                if (cn.toString().length() == 2) {
                    cn_ = "00" + cn;
                }

                if (cn.toString().length() == 3) {
                    cn_ = "0" + cn;
                }

                ((EditText) grdv
                        .findViewById(R.id.txtintercomid)).setText("ic" + cn_);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            grdm.addView(grdv);
            count = 3;
        }

        if (swdetails[3] && !swdetails[4]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdnetwork, null);

            runner = new syncgetid();
            runner.execute("netid", "rcisl_clients_network");
            try {

                Integer cn;
                cn = Integer.parseInt(runner.get().substring(runner.get().length() - 4, runner.get().length()));
                cn++;
                String cn_ = cn.toString();
                if (cn.toString().length() == 1) {
                    cn_ = "000" + cn;
                }

                if (cn.toString().length() == 2) {
                    cn_ = "00" + cn;
                }

                if (cn.toString().length() == 3) {
                    cn_ = "0" + cn;
                }

                ((EditText) grdv
                        .findViewById(R.id.txtnetid)).setText("nw" + cn_);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            EditText editText = (EditText) grdv.findViewById(R.id.txtaccesspoints);
            editText.setEnabled(false);
            EditText editText1 = (EditText) grdv.findViewById(R.id.txtrepeaters);
            editText1.setEnabled(false);
            grdm.addView(grdv);
            count = 4;
        }
        if (swdetails[4] && !swdetails[3]) {
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
            runner = new syncgetid();
            runner.execute("netid", "rcisl_clients_network");
            try {

                Integer cn;
                cn = Integer.parseInt(runner.get().substring(runner.get().length() - 4, runner.get().length()));
                cn++;
                String cn_ = cn.toString();
                if (cn.toString().length() == 1) {
                    cn_ = "000" + cn;
                }

                if (cn.toString().length() == 2) {
                    cn_ = "00" + cn;
                }

                if (cn.toString().length() == 3) {
                    cn_ = "0" + cn;
                }

                ((EditText) grdv
                        .findViewById(R.id.txtnetid)).setText("nw" + cn_);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            grdm.addView(grdv);

            count = 5;
        }

        if (swdetails[4] && swdetails[3]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdnetwork, null);
            runner = new syncgetid();
            runner.execute("netid", "rcisl_clients_network");
            try {

                Integer cn;
                cn = Integer.parseInt(runner.get().substring(runner.get().length() - 4, runner.get().length()));
                cn++;
                String cn_ = cn.toString();
                if (cn.toString().length() == 1) {
                    cn_ = "000" + cn;
                }

                if (cn.toString().length() == 2) {
                    cn_ = "00" + cn;
                }

                if (cn.toString().length() == 3) {
                    cn_ = "0" + cn;
                }
                ((EditText) grdv
                        .findViewById(R.id.txtnetid)).setText("nw" + cn_);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            grdm.addView(grdv);
            count = 6;
        }


        if (swdetails[5]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdsoftware, null);

            runner = new syncgetid();
            runner.execute("softid", "rcisl_clients_softwares");
            try {

                Integer cn;
                cn = Integer.parseInt(runner.get().substring(runner.get().length() - 4, runner.get().length()));
                cn++;
                String cn_ = cn.toString();
                if (cn.toString().length() == 1) {
                    cn_ = "000" + cn;
                }

                if (cn.toString().length() == 2) {
                    cn_ = "00" + cn;
                }

                if (cn.toString().length() == 3) {
                    cn_ = "0" + cn;
                }
                ((EditText) grdv
                        .findViewById(R.id.txtsoftid)).setText("sw" + cn_);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            grdm.addView(grdv);
            count = 7;
        }
        if (swdetails[6]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdweb, null);

            runner = new syncgetid();
            runner.execute("webid", "rcisl_clients_web");
            try {

                Integer cn;
                cn = Integer.parseInt(runner.get().substring(runner.get().length() - 4, runner.get().length()));
                cn++;
                String cn_ = cn.toString();
                if (cn.toString().length() == 1) {
                    cn_ = "000" + cn;
                }

                if (cn.toString().length() == 2) {
                    cn_ = "00" + cn;
                }

                if (cn.toString().length() == 3) {
                    cn_ = "0" + cn;
                }
                ((EditText) grdv
                        .findViewById(R.id.txtWebid)).setText("sw" + cn_);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            grdm.addView(grdv);
            count = 8;
        }
        if (swdetails[7]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdcomputers, null);

            runner = new syncgetid();
            runner.execute("comid", "rcisl_clients_computers");
            try {

                Integer cn;
                cn = Integer.parseInt(runner.get().substring(runner.get().length() - 4, runner.get().length()));
                cn++;
                String cn_ = cn.toString();
                if (cn.toString().length() == 1) {
                    cn_ = "000" + cn;
                }

                if (cn.toString().length() == 2) {
                    cn_ = "00" + cn;
                }

                if (cn.toString().length() == 3) {
                    cn_ = "0" + cn;
                }
                ((EditText) grdv
                        .findViewById(R.id.txtcomid)).setText("co" + cn_);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            grdm.addView(grdv);
            count = 9;
        }
        if (swdetails[8]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdprinters, null);

            final EditText printid = (EditText) grdv
                    .findViewById(R.id.txtprinterid);
            final EditText model = (EditText) grdv.findViewById(R.id.txtprintermodel);
            final Spinner type = (Spinner) grdv
                    .findViewById(R.id.spinprintermettype);
            final EditText typemodel = (EditText) grdv
                    .findViewById(R.id.txtprintmetmodel);
            final EditText notes = (EditText) grdv
                    .findViewById(R.id.txtprintnotes);

            runner = new syncgetid();
            runner.execute("printerid", "rcisl_clients_printers");

            try {

                Integer cn;
                cn = Integer.parseInt(runner.get().substring(runner.get().length() - 4, runner.get().length()));
                cn++;
                String cn_ = cn.toString();
                if (cn.toString().length() == 1) {
                    cn_ = "000" + cn;
                }

                if (cn.toString().length() == 2) {
                    cn_ = "00" + cn;
                }

                if (cn.toString().length() == 3) {
                    cn_ = "0" + cn;
                }
                printid.setText("pr" + cn_);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            if (swdetails[9]) {
                type.setSelection(0);
            }

            if (swdetails[10]) {
                type.setSelection(1);
            }

            if (swdetails[11]) {
                type.setSelection(2);
            }
            grdm.addView(grdv);
            count = 10;
        }
       /* if (count == swdetails.length-1) {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
        }*/

        final Button finishbtn = (Button) rootView.findViewById(R.id.btnfinish);
        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "cloud not found", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }

        });
        return rootView;

    }


}


