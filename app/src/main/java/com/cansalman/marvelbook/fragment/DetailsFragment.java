package com.cansalman.marvelbook.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cansalman.marvelbook.R;
import com.cansalman.marvelbook.databinding.FragmentDetailsBinding;
import com.squareup.picasso.Picasso;


public class DetailsFragment extends Fragment {
    private FragmentDetailsBinding binding;



    public DetailsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view ;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null ){
            String descriptionText = DetailsFragmentArgs.fromBundle(getArguments()).getDescription();
            String heroName = DetailsFragmentArgs.fromBundle(getArguments()).getName();
            String downloadUrl = DetailsFragmentArgs.fromBundle(getArguments()).getDownloadUrl();
            Picasso.get().load(downloadUrl).into(binding.imageView);
            binding.descriptionsText.setText(descriptionText);
            binding.nameText.setText(heroName);

        }
        binding.turnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnBack(v);
            }
        });



    }

    public void turnBack(View view ){
        DetailsFragmentDirections.ActionDetailsFragmentToMainFragment action = DetailsFragmentDirections.actionDetailsFragmentToMainFragment();
        Navigation.findNavController(requireView()).navigate(action);

    }
}