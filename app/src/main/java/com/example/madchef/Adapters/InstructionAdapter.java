package com.example.madchef.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madchef.Models.InstructionsResponse;
import com.example.madchef.R;

import java.util.List;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionViewHolder> {

    Context context;
    List<InstructionsResponse> list;

    public InstructionAdapter(Context context, List<InstructionsResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionViewHolder holder, int position) {

        holder.view_instruction.setText(list.get(position).name);
        holder.recycler_step.setHasFixedSize(true);
        holder.recycler_step.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));
        InstructionStepAdapter stepAdapter = new InstructionStepAdapter(context,list.get(position).steps);
        holder.recycler_step.setAdapter(stepAdapter);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionViewHolder extends RecyclerView.ViewHolder{

    TextView view_instruction ;
    RecyclerView recycler_step;

    public InstructionViewHolder(@NonNull View itemView) {
        super(itemView);
        view_instruction = itemView.findViewById(R.id.view_instruction);
        recycler_step = itemView.findViewById(R.id.recycler_step);
    }
}