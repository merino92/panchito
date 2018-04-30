package univosv.listaperzo.Notificaciones;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

/**
 * Created by root on 01-05-18.
 */

public class AcercaDe extends android.support.v4.app.DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage("\t\tJose Adolfo Herrera \n\t\tRafael ALfredo Zelaya\n\t\tJose Alfredo Merino")
                .setTitle("\t\tDesarrolladores")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }







}
