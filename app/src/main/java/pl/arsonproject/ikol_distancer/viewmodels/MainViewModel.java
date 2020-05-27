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

    public MutableLiveData<String> firstPoint = new MutableLiveData<>();
    public MutableLiveData<String> secondPoint = new MutableLiveData<>();
    public MutableLiveData<Integer> loadingState = new MutableLiveData<>(View.GONE);
    public MutableLiveData<Boolean> permissionRequest = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<Location> location = new MutableLiveData<>();

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Location> getLocation() {
        return location;
    }

    private DistanceApi api = ApiFactory.getInstance();

    public void doCalculate(){
        permissionRequest.setValue(false);
    }

    private boolean validateControl() {
        boolean result = false;
        Location location = new Location();
        if ((firstPoint.getValue() != null && (secondPoint.getValue() != null))
                && (!firstPoint.getValue().trim().isEmpty() && !secondPoint.getValue().trim().isEmpty())) {
            result = location.checkPoints(firstPoint.getValue()) && location.checkPoints(secondPoint.getValue());
        }
        return result;
    }

    public void onPermissionGranted() {
        loadingState.postValue(View.VISIBLE);
        if (validateControl()) {
            try {
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
                            errorMessage.setValue("Nie można połączyc wybranych punktów");
                        }
                        loadingState.postValue(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<Location> call, Throwable t) {
                        location.setValue(null);
                        loadingState.postValue(View.GONE);
                    }
                });
            } catch (Exception e) {
                errorMessage.setValue(e.getMessage());
            }
        } else {
            errorMessage.setValue("Wprowadzone punkty są nieprawidłowe");
            loadingState.postValue(View.GONE);
        }
    }
}
