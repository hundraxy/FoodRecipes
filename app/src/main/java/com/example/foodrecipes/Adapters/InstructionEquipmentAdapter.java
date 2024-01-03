package com.example.foodrecipes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipes.Models.Equipment;
import com.example.foodrecipes.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionEquipmentAdapter extends RecyclerView.Adapter<InstructionEquipmentViewHolder> {
   Context context;
   List<Equipment> list;

    public InstructionEquipmentAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionEquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionEquipmentViewHolder(LayoutInflater.from(context).inflate(R.layout.list_intsruction_step_items,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionEquipmentViewHolder holder, int position) {
         holder.textView_instructions_steps_items.setText(list.get(position).name);
         holder.textView_instructions_steps_items.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/equipment_250x250/"+list.get(position).image).into(holder.imageView_instructions_steps_items);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionEquipmentViewHolder extends RecyclerView.ViewHolder {


    ImageView imageView_instructions_steps_items;
    TextView textView_instructions_steps_items;

    public InstructionEquipmentViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_instructions_steps_items = itemView.findViewById(R.id.imageView_instructions_steps_items);
        textView_instructions_steps_items = itemView.findViewById(R.id.textView_instructions_steps_items);
    }
}

