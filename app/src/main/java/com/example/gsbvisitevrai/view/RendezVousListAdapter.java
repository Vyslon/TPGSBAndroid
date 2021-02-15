package com.example.gsbvisitevrai.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.gsbvisitevrai.R;
import com.example.gsbvisitevrai.model.Medicament;
import com.example.gsbvisitevrai.model.RendezVous;

import java.util.ArrayList;

/**
 * Aide à l'affichage de la liste dans un listview
 */
public class RendezVousListAdapter extends BaseAdapter {

    private ArrayList<RendezVous> lesRDV;
    private LayoutInflater inflater;

    public RendezVousListAdapter(Context context, ArrayList<RendezVous> lesRendezVous){
        this.lesRDV = lesRendezVous;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return lesRDV.size();
    }

    @Override
    public Object getItem(int position) {
        return lesRDV.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_liste_rendezvous, null);
            holder.txtHeure = (TextView) convertView.findViewById(R.id.txtListHeure);
            holder.txtIdPraticien = (TextView) convertView.findViewById(R.id.txtListIdPraticien);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtHeure.setText(lesRDV.get(position).getHeure());
        holder.txtIdPraticien.setText(Integer.toString((int)lesRDV.get(position).getPraticien().getNumero()));
        return convertView;
    }
    private class ViewHolder{
        TextView txtHeure;
        TextView txtIdPraticien;

    }
}
