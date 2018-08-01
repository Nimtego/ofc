package com.example.pto6.ofc.view;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.model.BaseUserFinance;
import com.example.pto6.ofc.model.Debit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CardAdapter<T extends BaseUserFinance, V extends AbstractView>
        extends RecyclerView.Adapter<CardAdapter.PersonViewHolder> implements AdapterView.OnItemClickListener{

    private List<T> dataSet;
    private V viewParent;

    public CardAdapter(List<T> persons, V viewParent){
        this.dataSet = persons;
        this.viewParent = viewParent;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView mTextViewName;
        TextView mTextViewDate;
        TextView mTextViewDateChange;
        TextView mTextViewAmount;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            mTextViewName =  itemView.findViewById(R.id.name);
            mTextViewDate =  itemView.findViewById(R.id.create_date);
            mTextViewDateChange =  itemView.findViewById(R.id.change_date);
            mTextViewAmount =  itemView.findViewById(R.id.amount);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_cd, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        Date create  = dataSet.get(i).createDate();
        Date change  = dataSet.get(i).changeDate();

        DateFormat df = new SimpleDateFormat("MMM d, yyyy");

        personViewHolder.mTextViewName.setText(dataSet.get(i).name());
        personViewHolder.mTextViewDate.setText(df.format(create));
        personViewHolder.mTextViewDateChange.setText(df.format(change));
        personViewHolder.mTextViewAmount.setText(String.valueOf(((Debit)dataSet.get(i)).arrivalSize()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
