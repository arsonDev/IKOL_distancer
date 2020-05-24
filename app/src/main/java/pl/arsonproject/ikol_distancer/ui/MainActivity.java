package pl.arsonproject.ikol_distancer.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import pl.arsonproject.ikol_distancer.R;
import pl.arsonproject.ikol_distancer.databinding.ActivityMainBinding;
import pl.arsonproject.ikol_distancer.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.setVm(viewModel);

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.INTERNET) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.INTERNET)) {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
        } else {
            // You can directly ask for the permission.
            requestPermissions(new String[]{Manifest.permission.INTERNET},1000);
        }
    }
}
