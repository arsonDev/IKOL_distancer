package pl.arsonproject.ikol_distancer.viewmodels;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;

import pl.arsonproject.ikol_distancer.models.Distance;
import pl.arsonproject.ikol_distancer.repository.ApiFactory;
import pl.arsonproject.ikol_distancer.repository.DistanceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    // wprowadzenie danych do modeli

    public LiveData<String> distance;


    public MainViewModel(){

    }

    public void doCalculate(){
        DistanceApi api = ApiFactory.api;
        api.calculateDistance("kilometers",41,74,8,42).enqueue(new Callback<Distance>() {
            @Override
            public void onResponse(Call<Distance> call, Response<Distance> response) {
                Distance distance = response.body();
            }

            @Override
            public void onFailure(Call<Distance> call, Throwable t) {

            }
        });
    }
}
