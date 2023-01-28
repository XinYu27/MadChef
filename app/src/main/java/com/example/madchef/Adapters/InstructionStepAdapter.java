package com.example.madchef.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madchef.Models.Step;
import com.example.madchef.R;

import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepViewHolder>{
    Context context;
    List<Step> list;

    public InstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_instruction_step,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {

        holder.instruction_number.setText(String.valueOf(list.get(position).number));
        holder.instruction_title.setText(list.get(position).step);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionStepViewHolder extends RecyclerView.ViewHolder{
    TextView instruction_number, instruction_title;

    public InstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);

        instruction_number = itemView.findViewById(R.id.instruction_number);
        instruction_title = itemView.findViewById(R.id.instruction_title);
    }
}
