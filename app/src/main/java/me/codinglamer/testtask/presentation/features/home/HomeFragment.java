package me.codinglamer.testtask.presentation.features.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import me.codinglamer.testtask.R;
import me.codinglamer.testtask.core.BaseListTypeConverter;
import me.codinglamer.testtask.databinding.FragmentHomeBinding;
import me.codinglamer.testtask.domain.model.GoodsPreviewEntity;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Context context;

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;
    private HomeViewModel viewModel;

    public HomeFragment() {

    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        context = view.getContext();

        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.defaultCollapsingToolbar.toolbar);
        binding.defaultCollapsingToolbar.toolbarLayout.setTitle(getString(R.string.title_home));

//        viewModel = new ViewModelProvider(this, viewModelFactory).get(HomeViewModel.class);
        viewModel = viewModelFactory.create(HomeViewModel.class);
//        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(HomeViewModel.class);
        viewModel.getGoodsList();
        viewModel.getGoodsListLiveData()
                .observe(getViewLifecycleOwner(), goodsPreviewEntities ->
                        Toast.makeText(context, new BaseListTypeConverter<GoodsPreviewEntity>()
                                .listToString(goodsPreviewEntities), Toast.LENGTH_LONG).show()
                );

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_cart)
                .build();
        NavController navController = NavHostFragment.findNavController(this);
        NavigationUI.setupWithNavController(binding.defaultCollapsingToolbar.toolbar, navController, appBarConfiguration);

//        NavigationUI.setupWithNavController(binding.toolbar, NavHostFragment.findNavController(this));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        context = null;
    }
}
