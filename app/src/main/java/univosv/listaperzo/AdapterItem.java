package univosv.listaperzo;

import android.app.Activity;
import android.content.Context;
import android.icu.util.ULocale;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 07-18-17.
 */

public class AdapterItem extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<items> items=new ArrayList<items>();

    public AdapterItem(Activity activity, ArrayList<items> items) {

        this.activity = activity;
        this.items = items;
    }
    public AdapterItem(Activity activity, ArrayList<String> titulos,ArrayList<String>descripciones) {
        //convertir array string a array de items
        this.activity=activity;
        for(int i=0;i<titulos.size();i++){

            items.add(new items(String.valueOf(i),titulos.get(i),descripciones.get(i)));
            Log.d(TAG, "titulo:"+titulos.get(i));
        }

    }


    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<items> items) {
        for (int i = 0; i < items.size(); i++) {
            items.add(items.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null ) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.listac, null);
        }

        items dir = items.get(position);

        TextView title = (TextView) v.findViewById(R.id.category);
        title.setText(dir.getTitle());

        TextView description = (TextView) v.findViewById(R.id.texto);
        description.setText(dir.getDescription());

        return v;
    }
}