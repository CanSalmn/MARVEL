package com.cansalman.marvelbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.cansalman.marvelbook.databinding.ChildRecyclerviewBinding;
import com.cansalman.marvelbook.fragment.MainFragment;
import com.cansalman.marvelbook.fragment.MainFragmentDirections;
import com.cansalman.marvelbook.model.DataModel;
import com.cansalman.marvelbook.model.HeroesModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildHolder> {
        ArrayList<HeroesModel> dataModelArrayList;

    public ChildAdapter(ArrayList<HeroesModel> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
    }

    @NonNull
    @Override
    public ChildHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChildRecyclerviewBinding binding = ChildRecyclerviewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ChildHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildHolder holder, int position) {
        String downloadUrl = dataModelArrayList.get(position).characterThumNail.path+"/portrait_incredible."+dataModelArrayList.get(position).characterThumNail.extension;
        boolean imageBoolean = dataModelArrayList.get(position).characterThumNail.path.endsWith("image_not_available");

        if(!imageBoolean){
            Picasso.get().load(downloadUrl).into(holder.binding.heroImage);
            holder.binding.nameTextView.setText(dataModelArrayList.get(position).name);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainFragmentDirections.ActionMainFragmentToDetailsFragment action = MainFragmentDirections.actionMainFragmentToDetailsFragment(
                            downloadUrl,dataModelArrayList.get(position).description,dataModelArrayList.get(position).name);
                Navigation.findNavController(v).navigate(action);




            }
        });



    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    protected class ChildHolder extends RecyclerView.ViewHolder{
        ChildRecyclerviewBinding binding;

        public ChildHolder(ChildRecyclerviewBinding binding) {
            super(binding.getRoot());
            this.binding= binding;
        }
    }
}
