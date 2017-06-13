package com.rcis.CRM;

import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

/**
 * Created by Rohitha on 6/11/2017.
 */
public class getServices {

    /*
    grdm = (GridLayout) rootView.findViewById(R.id.setmain);
        if (swdetails[0]) {
            GridLayout grdv = (GridLayout) View.inflate(
                    rootView.getContext(),
                    R.layout.grdcctv, null);

            ((EditText) grdv
                    .findViewById(R.id.txtcctv))
                    .setText("cc001");
            grdm.addView(grdv);
        }*/

    public static void getAvalibles(View rootView, int editid,int layout){
        GridLayout grdm = (GridLayout) rootView.findViewById(R.id.setmain);

        GridLayout grdv = (GridLayout) View.inflate(
                rootView.getContext(),
                layout, null);

        ((EditText) grdv
                .findViewById(editid))
                .setText("cc001");
        grdm.addView(grdv);
    }
}
