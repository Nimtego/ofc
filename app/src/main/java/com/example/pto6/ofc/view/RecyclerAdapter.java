package com.example.pto6.ofc.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.model.BaseUserFinance;
import com.example.pto6.ofc.model.Debit;

import java.util.List;

public class RecyclerAdapter<T extends BaseUserFinance> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<T> mDataset;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewName;
        public TextView mTextViewDate;
        public TextView mTextViewDateChange;
        public TextView mTextViewAmount;

        public ViewHolder(View v) {
            super(v);
            mTextViewName = (TextView) v.findViewById(R.id.name);
            mTextViewDate = (TextView) v.findViewById(R.id.create_date);
            mTextViewDateChange = (TextView) v.findViewById(R.id.change_date);
            mTextViewAmount = (TextView) v.findViewById(R.id.amount);
        }
    }

    public RecyclerAdapter( List<T> dataset, Context context) {
        mDataset = dataset;
        mContext = context;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.credit_card_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextViewName.setText(mDataset.get(position).name());
        holder.mTextViewDate.setText(mDataset.get(position).createDate().toString());
        holder.mTextViewDateChange.setText(mDataset.get(position).changeDate().toString());
        holder.mTextViewAmount.setText(String.valueOf(((Debit)mDataset.get(position)).arrivalSize()));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}