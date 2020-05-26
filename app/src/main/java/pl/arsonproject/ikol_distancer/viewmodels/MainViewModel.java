package pl.arsonproject.ikol_distancer.viewmodels;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import pl.arsonproject.ikol_distancer.models.Location;
import pl.arsonproject.ikol_distancer.repository.ApiFactory;
import pl.arsonproject.ikol_distancer.repository.DistanceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    public MutableLiveData<Integer> loadingState;
    private MutableLiveData<String> errorMessage;
    private MutableLiveData<Location> location;
    private DistanceApi api;
    public MutableLiveData<String> firstPoint;
    public MutableLiveData<String> secondPoint;

    public MainViewModel(){
        location = new MutableLiveData<>();
        firstPoint = new MutableLiveData<>();
        secondPoint = new MutableLiveData<>();
        loadingState = new MutableLiveData<>(View.GONE);
        errorMessage = new MutableLiveData<>();
        api = ApiFactory.getInstance();
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Location> getLocation() {
        return location;
    }

    public void doCalculate(){
        loadingState.postValue(View.VISIBLE);
        if (validateControl()) {
            api.calculateDistance(firstPoint.getValue(), secondPoint.getValue()).enqueue(new Callback<Location>() {
                @Override
                public void onResponse(Call<Location> call, Response<Location> response) {
                    if (response.isSuccessful() &&
                            response.body().getStatus().equals("OK") &&
                            response.body().getRows().size() > 0 &&
                            response.body().getRows().get(0).getElements().size() > 0 &&
                            response.body().getRows().get(0).getElements().get(0).getStatus().equals("OK"))
                        location.setValue(response.body());
                    else {
                        location.setValue(null);
                        errorMessage.setValue("Wprowadzone punkty są nieprawidłowe");
                    }
                }

                @Override
                public void onFailure(Call<Location> call, Throwable t) {
                    location.setValue(null);
                }
            });
        } else {
            errorMessage.setValue("Wprowadzone punkty są nieprawidłowe");
        }
        loadingState.postValue(View.GONE);
    }

    private boolean validateControl() {
        return (firstPoint.getValue() != null && (secondPoint.getValue() != null)) &&
                (!firstPoint.getValue().trim().isEmpty() && !secondPoint.getValue().trim().isEmpty());
    }
}
