package com.rcis.CRM;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class AddFragment extends Fragment {
    public AddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);

        final Button next;
        final String cdetails[] = new String[14];

        final EditText id = (EditText) rootView.findViewById(R.id.txtid);
        try {
            syncgetid runner = new syncgetid();
            ProgressDialog pd = new ProgressDialog(getActivity());
            pd.setMessage("Fatching data..");
            pd.setTitle("Info");
            pd.show();
            runner.execute("c_no", "rcisl_clients_details");

            if (runner.get() != null) {
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
                id.setText("c" + runner.get().substring(1, 2) + cn_);
                Thread.sleep(100);
                pd.dismiss();

            } else {
                pd.dismiss();

                Toast.makeText(getActivity(), "Error fetching information", Toast.LENGTH_LONG).show();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        final EditText fname = (EditText) rootView.findViewById(R.id.txtfname);
        final EditText lname = (EditText) rootView.findViewById(R.id.txtlname);

        final EditText address1 = (EditText) rootView.findViewById(R.id.txtaddress1);
        final EditText address2 = (EditText) rootView.findViewById(R.id.txtaddress2);
        final EditText address3 = (EditText) rootView.findViewById(R.id.txtaddress3);

        final EditText mobilep = (EditText) rootView.findViewById(R.id.txtp1);
        final EditText mobilew = (EditText) rootView.findViewById(R.id.txtp2);
        final EditText fax = (EditText) rootView.findViewById(R.id.txtfax);

        final EditText email = (EditText) rootView.findViewById(R.id.txtemail);

        final EditText company = (EditText) rootView.findViewById(R.id.txtcompany);
        final Spinner section = (Spinner) rootView.findViewById(R.id.spingp);
        final EditText title = (EditText) rootView.findViewById(R.id.txttitle);

        final EditText zone = (EditText) rootView.findViewById(R.id.txtzone);
        final EditText rout = (EditText) rootView.findViewById(R.id.txtrout);

        final EditText notes = (EditText) rootView.findViewById(R.id.txtNotes);


        final Fragment[] f = new Fragment[1];
        final FragmentTransaction[] transaction = new FragmentTransaction[1];
        next = (Button) rootView.findViewById(R.id.btnnext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction[0] = getFragmentManager().beginTransaction();
                f[0] = new ServiceFragment();
                cdetails[0] = id.getText().toString().trim().isEmpty() ? "no data" : id.getText().toString();
                cdetails[1] = fname.getText().toString().trim().isEmpty() ? "no data" : fname.getText().toString();
                cdetails[2] = lname.getText().toString().trim().isEmpty() ? "no data" : lname.getText().toString();
                cdetails[3] = address1.getText().toString().trim() + " ," + address2.getText().toString().trim() + " ," + address3.getText().toString().trim();
                cdetails[4] = mobilep.getText().toString().trim();
                cdetails[5] = mobilew.getText().toString().trim().isEmpty() ? "" : mobilew.getText().toString();
                cdetails[6] = fax.getText().toString().trim();
                cdetails[7] = email.getText().toString().trim();
                cdetails[8] = company.getText().toString().trim();
                cdetails[9] = section.getSelectedItem().toString();
                cdetails[10] = title.getText().toString().trim();
                cdetails[11] = zone.getText().toString().trim();
                cdetails[12] = rout.getText().toString().trim();
                cdetails[13] = notes.getText().toString().trim();


                if (f[0] != null) {

                    setRetainInstance(true);
                    Bundle bundle = new Bundle();
                    if (cdetails.length > 0) {
                        bundle.putStringArray("cdata", cdetails);
                        f[0].setArguments(bundle);

                    }

                    transaction[0].replace(R.id.container, f[0]);
                    transaction[0].addToBackStack(null);
                    transaction[0].commit();
                }
            }
        });
        return rootView;
    }
}
