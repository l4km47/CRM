package com.rcis.CRM;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by lakmal 2017-06-06
 */
public class SetservicesFragment extends Fragment {

    boolean swdetails[];
    String cdata[];
    String ServiceNote;
    GridLayout grdm;

    public SetservicesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)

    {
        final View rootView = inflater.inflate(R.layout.fragment_setservice, container, false);


        swdetails = getArguments().getBooleanArray("service");
        ServiceNote = getArguments().getString("snote");
        cdata = getArguments().getStringArray("cdata");
        grdm = (GridLayout) rootView.findViewById(R.id.setmain);

        if (swdetails[0]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdcctv, null);

            ((TextView) grdv
                    .findViewById(R.id.txtcctv)).setText(ServerTasks.get.SetId("CC", "cctvid", "rcisl_clients_cctv"));
            grdm.addView(grdv);

        }
        if (swdetails[1]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdsolar, null);

            ((TextView) grdv
                    .findViewById(R.id.txtsolarid)).setText(ServerTasks.get.SetId("SP", "solarid", "rcisl_clients_solar"));
            grdm.addView(grdv);

        }
        if (swdetails[2]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdintercom, null);

            ((TextView) grdv
                    .findViewById(R.id.txtintercomid)).setText(ServerTasks.get.SetId("IC", "intercomid", "rcisl_clients_intercom"));
            grdm.addView(grdv);

        }
        if (swdetails[3] && !swdetails[4]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdnetwork, null);
            CustomEditText editText = (CustomEditText) grdv.findViewById(R.id.txtaccesspoints);
            editText.setEnabled(false);
            CustomEditText editText1 = (CustomEditText) grdv.findViewById(R.id.txtrepeaters);
            editText1.setEnabled(false);
            ((TextView) grdv
                    .findViewById(R.id.txtnetid)).setText(ServerTasks.get.SetId("NW", "netid", "rcisl_clients_network"));
            grdm.addView(grdv);
        }

        if (swdetails[4] && !swdetails[3]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdnetwork, null);
            CustomEditText editText = (CustomEditText) grdv.findViewById(R.id.txtswitchs);
            editText.setEnabled(false);
            CustomEditText editText1 = (CustomEditText) grdv.findViewById(R.id.txtpatchpanels);
            editText1.setEnabled(false);
            CustomEditText editText2 = (CustomEditText) grdv.findViewById(R.id.txtnetpoints);
            editText2.setEnabled(false);
            CustomEditText editText3 = (CustomEditText) grdv.findViewById(R.id.txtrouters);
            editText3.setEnabled(false);
            ((TextView) grdv
                    .findViewById(R.id.txtnetid)).setText(ServerTasks.get.SetId("NW", "netid", "rcisl_clients_network"));
            grdm.addView(grdv);
        }

        if (swdetails[4] && swdetails[3]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdnetwork, null);
            ((TextView) grdv
                    .findViewById(R.id.txtnetid)).setText(ServerTasks.get.SetId("NW", "netid", "rcisl_clients_network"));
            grdm.addView(grdv);

        }


        if (swdetails[5]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdsoftware, null);
            ((TextView) grdv
                    .findViewById(R.id.txtsoftid)).setText(ServerTasks.get.SetId("SW", "softid", "rcisl_clients_softwares"));
            grdm.addView(grdv);
        }

        if (swdetails[6]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdweb, null);
            ((TextView) grdv
                    .findViewById(R.id.txtWebid)).setText(ServerTasks.get.SetId("SW", "webid", "rcisl_clients_web"));
            grdm.addView(grdv);
        }
        if (swdetails[7]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdcomputers, null);
            ((TextView) grdv
                    .findViewById(R.id.txtcomid)).setText(ServerTasks.get.SetId("CO", "comid", "rcisl_clients_computers"));
            grdm.addView(grdv);
        }

        if (swdetails[8]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdprinters, null);
            final TextView printid = (TextView) grdv
                    .findViewById(R.id.txtprinterid);
            final CustomEditText model = (CustomEditText) grdv.findViewById(R.id.txtprintermodel);
            final Spinner type = (Spinner) grdv
                    .findViewById(R.id.spinprintermettype);
            final CustomEditText typemodel = (CustomEditText) grdv
                    .findViewById(R.id.txtprintmetmodel);
            final CustomEditText notes = (CustomEditText) grdv
                    .findViewById(R.id.txtprintnotes);
            if (swdetails[9]) {
                type.setSelection(0);
            }

            if (swdetails[10]) {
                type.setSelection(1);
            }

            if (swdetails[11]) {
                type.setSelection(2);
            }
            printid.setText(ServerTasks.get.SetId("PR", "printerid", "rcisl_clients_printers"));
            grdm.addView(grdv);
        }

        final Button finishbtn = (Button) rootView.findViewById(R.id.btnfinish);
        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ServerTasks.Save.AsyncSaveCustomer(cdata)) {
                    String id = ServerTasks.get.SetId("SI", "sno", "rcisl_clients_solutions");
                    if (ServerTasks.Save.AsyncSaveService(id, cdata[0], swdetails, ServiceNote)) {

                        if (swdetails[0]) {
                            final TextView txtcctvid = (TextView) rootView.findViewById(R.id.txtcctv);
                            final Spinner spnchannels = (Spinner) rootView.findViewById(R.id.spincctvchanels);
                            final Spinner spnmodel = (Spinner) rootView.findViewById(R.id.spincctvmodel);
                            final EditText camcount = (EditText) rootView.findViewById(R.id.txtcctvcamcount);
                            final EditText note = (EditText) rootView.findViewById(R.id.txtcctvnotes);

                            if (camcount.getText().length() > 0) {
                                String cctvdata[] = new String[6];
                                cctvdata[0] = txtcctvid.getText().toString();
                                cctvdata[1] = cdata[0];
                                cctvdata[2] = spnchannels.getSelectedItem().toString();
                                cctvdata[3] = spnmodel.getSelectedItem().toString();
                                cctvdata[4] = camcount.getText().toString();
                                cctvdata[5] = note.getText().toString();

                                ServerTasks.Save.AsyncSaveCCTV(cctvdata);
                            }
                        }
                        if (swdetails[1]) {
                            final TextView solarid = (TextView) rootView.findViewById(R.id.txtsolarid);
                            final EditText w = (EditText) rootView.findViewById(R.id.txtwatt);
                            final Spinner type = (Spinner) rootView.findViewById(R.id.spninvtype);
                            final EditText cap = (EditText) rootView.findViewById(R.id.txtinvcap);
                            final EditText note = (EditText) rootView.findViewById(R.id.txtsolarnotes);

                            if (w.getText().length() > 0 && cap.getText().length() > 0) {
                                String solardata[] = new String[6];
                                solardata[0] = solarid.getText().toString();
                                solardata[1] = cdata[0];
                                solardata[2] = w.getText().toString();
                                solardata[3] = type.getSelectedItem().toString();
                                solardata[4] = cap.getText().toString();
                                solardata[5] = note.getText().toString();

                                ServerTasks.Save.AsyncSaveSOLAR(solardata);
                            }
                        }

                        if (swdetails[2]) {
                            final TextView icid = (TextView) rootView.findViewById(R.id.txtintercomid);
                            final EditText analog = (EditText) rootView.findViewById(R.id.txtanalog);
                            final EditText degital = (EditText) rootView.findViewById(R.id.txtdegital);
                            final EditText dlines = (EditText) rootView.findViewById(R.id.txtdirectlines);
                            final EditText notes = (EditText) rootView.findViewById(R.id.txtintercomnotes);

                            if (analog.getText().length() > 0 && degital.getText().length() > 0 && dlines.getText().length() > 0) {
                                String intercomdata[] = new String[6];
                                intercomdata[0] = icid.getText().toString();
                                intercomdata[1] = cdata[0];
                                intercomdata[2] = analog.getText().toString();
                                intercomdata[3] = degital.getText().toString();
                                intercomdata[4] = dlines.getText().toString();
                                intercomdata[5] = notes.getText().toString();

                                ServerTasks.Save.AsyncSaveINTERCOM(intercomdata);
                            }
                        }


                        if (swdetails[3] || swdetails[4] || (swdetails[3] && swdetails[4])) {
                            final TextView netid = (TextView) rootView.findViewById(R.id.txtnetid);
                            final EditText switchs = (EditText) rootView.findViewById(R.id.txtswitchs);
                            final EditText patchp = (EditText) rootView.findViewById(R.id.txtpatchpanels);
                            final EditText netpoints = (EditText) rootView.findViewById(R.id.txtnetpoints);
                            final EditText routers = (EditText) rootView.findViewById(R.id.txtrouters);
                            final EditText ap = (EditText) rootView.findViewById(R.id.txtaccesspoints);
                            final EditText reapeaters = (EditText) rootView.findViewById(R.id.txtrepeaters);

                            final EditText notes = (EditText) rootView.findViewById(R.id.txtintercomnotes);

                            String netdata[] = new String[9];
                            netdata[0] = netid.getText().toString();
                            netdata[1] = cdata[0];
                            netdata[2] = switchs.getText().toString();
                            netdata[3] = patchp.getText().toString();
                            netdata[4] = netpoints.getText().toString();
                            netdata[5] = routers.getText().toString();
                            netdata[6] = ap.getText().toString();
                            netdata[7] = reapeaters.getText().toString();
                            netdata[8] = notes.getText().toString();

                            ServerTasks.Save.AsyncSaveNETWORK(netdata);
                        }
                    }
                }
            }
        });
        return rootView;
    }
}




