package com.rcis.CRM;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class ServiceFragment extends Fragment {
    public ServiceFragment() {
    }

    String cde[];
    boolean sdetails[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_service, container, false);

        final Button next;
        final Switch swcctv = (Switch) rootView.findViewById(R.id.swcctv);
        final Switch swsolarp = (Switch) rootView.findViewById(R.id.swSolar);
        final Switch swintercomm = (Switch) rootView.findViewById(R.id.swintercomm);
        final Switch swnetwork = (Switch) rootView.findViewById(R.id.switnetwork);
        final Switch swwifi = (Switch) rootView.findViewById(R.id.swwifi);
        final Switch swsoft = (Switch) rootView.findViewById(R.id.swsoft);
        final Switch swweb = (Switch) rootView.findViewById(R.id.swweb);
        final Switch swcomputers = (Switch) rootView.findViewById(R.id.swcom);
        final Switch swprinters = (Switch) rootView.findViewById(R.id.swprinter);
        final Switch swtoner = (Switch) rootView.findViewById(R.id.swtoner);
        final Switch swribbon = (Switch) rootView.findViewById(R.id.swribbon);
        final Switch swink = (Switch) rootView.findViewById(R.id.swinks);
        final EditText notes = (EditText) rootView.findViewById(R.id.txtservicenotes);

        sdetails = new boolean[12];
        final Fragment[] f = new Fragment[1];
        final FragmentTransaction[] transaction = new FragmentTransaction[1];
        next = (Button) rootView.findViewById(R.id.btnnext2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction[0] = getFragmentManager().beginTransaction();
                f[0] = new SetservicesFragment();
                try {
                    sdetails[0] = swcctv.isChecked();
                    sdetails[1] = swsolarp.isChecked();
                    sdetails[2] = swintercomm.isChecked();
                    sdetails[3] = swnetwork.isChecked();
                    sdetails[4] = swwifi.isChecked();
                    sdetails[5] = swsoft.isChecked();
                    sdetails[6] = swweb.isChecked();
                    sdetails[7] = swcomputers.isChecked();
                    sdetails[8] = swprinters.isChecked();
                    sdetails[9] = swtoner.isChecked();
                    sdetails[10] = swribbon.isChecked();
                    sdetails[11] = swink.isChecked();

                    if (f[0] != null) {
                        setRetainInstance(true);
                        cde = getArguments().getStringArray("cdata");
                        Bundle bundle = new Bundle();

                        if (sdetails.length > 0) {
                            bundle.putString("snote", notes.getText().toString().isEmpty() ? "no data" : notes.getText().toString());
                            bundle.putBooleanArray("service", sdetails);
                            bundle.putStringArray("cdata", cde);
                            f[0].setArguments(bundle);

                        }
                        transaction[0].replace(R.id.container, f[0]);
                        transaction[0].addToBackStack(null);
                        transaction[0].commit();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }
}