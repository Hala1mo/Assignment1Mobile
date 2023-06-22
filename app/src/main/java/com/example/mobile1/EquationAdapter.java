package com.example.mobile1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EquationAdapter extends RecyclerView.Adapter<EquationAdapter.EquationViewHolder> {


    private List<Solution> equationList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public EquationAdapter(List<Solution> equationList) {
        this.equationList = equationList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public EquationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equation, parent, false);
        return new EquationViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EquationViewHolder holder, int position) {
        Solution equation = equationList.get(position);
        holder.textViewEquation.setText(equation.getEqu());
        holder.textViewSolution.setText(equation.getSol());
    }

    @Override
    public int getItemCount() {
        return equationList.size();
    }

    public static class EquationViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewEquation;
        private TextView textViewSolution;

        public EquationViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewEquation = itemView.findViewById(R.id.textViewEquation);
            textViewSolution = itemView.findViewById(R.id.textViewSolution);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}