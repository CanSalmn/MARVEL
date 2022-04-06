package com.cansalman.marvelbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cansalman.marvelbook.databinding.ParentRecyclerviewBinding;
import com.cansalman.marvelbook.model.DataModel;
import com.cansalman.marvelbook.model.HeroesModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ParentHolder> {
    ChildAdapter childAdapter;
    Context context;
    List<String> categoryList;

    ArrayList<HeroesModel> overHeroList;
    ArrayList<HeroesModel> underHeroList;




    public ParentAdapter(List<String> categoryList,ArrayList<HeroesModel> overHeroList,ArrayList<HeroesModel> underHeroList) {
        this.categoryList = categoryList;
        this.overHeroList= overHeroList;
        this.underHeroList= underHeroList;
    }

    @NonNull
    @Override
    public ParentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ParentRecyclerviewBinding binding = ParentRecyclerviewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        this.context = parent.getContext();
        return new ParentHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentHolder holder, int position) {
        holder.binding.categoryTitle.setText(categoryList.get(position));
        holder.binding.cardRecyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false  ));


        if(categoryList.get(position).equals("Comics Under 20")){
            childAdapter = new ChildAdapter(underHeroList);
            holder.binding.cardRecyclerView.setAdapter(childAdapter);
        }else{
            childAdapter = new ChildAdapter(overHeroList);
            holder.binding.cardRecyclerView.setAdapter(childAdapter);
        }










    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    protected class ParentHolder extends RecyclerView.ViewHolder {

        ParentRecyclerviewBinding binding;

        public ParentHolder(ParentRecyclerviewBinding binding) {
            super(binding.getRoot());
            this.binding= binding;
        }
    }

}
