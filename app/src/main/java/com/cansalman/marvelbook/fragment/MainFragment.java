package com.cansalman.marvelbook.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cansalman.marvelbook.adapter.ChildAdapter;
import com.cansalman.marvelbook.model.CharacterModel;
import com.cansalman.marvelbook.model.DataModel;
import com.cansalman.marvelbook.model.HeroesModel;
import com.cansalman.marvelbook.service.MarvelAPI;
import com.cansalman.marvelbook.adapter.ParentAdapter;
import com.cansalman.marvelbook.databinding.FragmentMainBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    ArrayList<DataModel> dataModelArrayList;
    ParentAdapter parentAdapter;
    List<String> categoryList;




    ArrayList<HeroesModel> overHeroList;
    ArrayList<HeroesModel> underHeroList;

    String BASE_URL = "http://gateway.marvel.com/v1/public/";
    Retrofit retrofit ;
    CompositeDisposable compositeDisposable;

    public MainFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= FragmentMainBinding.inflate(inflater,container,false) ;
        View view = binding.getRoot();
        return  view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoryList = new ArrayList<>();
        categoryList.add("Comics Under 20");
        categoryList.add("Comics Over 20 ");

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit= new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();


        loadData();



    }
    public  void loadData(){
        MarvelAPI marvelAPI = retrofit.create(MarvelAPI.class);
        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(marvelAPI.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handlerResponse)
        );

    }

    public void handlerResponse(DataModel dataModel ){
        dataModelArrayList = new ArrayList<>();
        dataModelArrayList.add(dataModel);
        overHeroList = new ArrayList<>();
        underHeroList = new ArrayList<>();

        int  size = dataModelArrayList.get(0).characterModel.heroesModel.size();

            for(int i =0; i<size;i++){
                int comicsSize= dataModelArrayList.get(0).characterModel.heroesModel.get(i).comicsModel.comicsSize;
                if(comicsSize>=20 ){
                    overHeroList.add(dataModelArrayList.get(0).characterModel.heroesModel.get(i));

                }else{
                    underHeroList.add(dataModelArrayList.get(0).characterModel.heroesModel.get(i));

                }


            }

        binding.mainRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        parentAdapter = new ParentAdapter(categoryList, overHeroList,underHeroList);
        binding.mainRecyclerView.setAdapter(parentAdapter);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}