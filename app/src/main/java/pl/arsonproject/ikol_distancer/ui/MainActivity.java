package pl.arsonproject.ikol_distancer.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.arsonproject.ikol_distancer.R;
import pl.arsonproject.ikol_distancer.databinding.ActivityMainBinding;
import pl.arsonproject.ikol_distancer.models.Location;
import pl.arsonproject.ikol_distancer.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.inputFirstPoint)
    TextInputEditText inputFirstPoint;

    @BindView(R.id.inputSecondPoint)
    TextInputEditText inputSecondPoint;

    @BindView(R.id.container)
    ConstraintLayout container;

    private MainViewModel viewModel;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setVm(viewModel);

        ButterKnife.bind(this);
        setObservable();
    }

    private void setObservable() {
        viewModel.firstPoint.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.trim().isEmpty())
                    inputFirstPoint.setError("Uzupełnij pole");
                else
                    inputFirstPoint.setError(null);
            }
        });

        viewModel.secondPoint.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.trim().isEmpty())
                    inputSecondPoint.setError("Uzupełnij pole");
                else
                    inputSecondPoint.setError(null);
            }
        });

        viewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.isEmpty())
                    Snackbar.make(container, s, Snackbar.LENGTH_SHORT).show();
            }
        });

        viewModel.getLocation().observe(this, new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                if (location == null) {
                    inputFirstPoint.setText("");
                    inputSecondPoint.setText("");
                    inputFirstPoint.setError(null);
                    inputSecondPoint.setError(null);,,
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.INTERNET) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.INTERNET},
                    1001);
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.INTERNET)) {

        }
    }
}
