package pl.arsonproject.ikol_distancer.viewmodels;

import android.os.AsyncTask;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import pl.arsonproject.ikol_distancer.models.MyResponse;
import pl.arsonproject.ikol_distancer.repository.ApiFactory;
import pl.arsonproject.ikol_distancer.repository.DistanceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> distance;
    public MutableLiveData<String> destination;
    public MutableLiveData<String> source;

    public MutableLiveData<String> firstPoint;
    public MutableLiveData<String> secondPoint;

    public MutableLiveData<Integer> loading;

    public MainViewModel(){
        distance = new MutableLiveData("0 km");
        destination = new MutableLiveData("Brak celu");
        source = new MutableLiveData<>("");
        firstPoint = new MutableLiveData<String>();
        secondPoint = new MutableLiveData<String>();
        loading = new MutableLiveData<Integer>(View.GONE);
    }

    public void doCalculate(){
        AsyncTask<String, Void, Void> loadingTask = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... strings) {
                loading.postValue(View.VISIBLE);
                try {
                    Thread.currentThread();
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                DistanceApi api = ApiFactory.api;
                api.calculateDistance(firstPoint.getValue(), secondPoint.getValue()).enqueue(new Callback<MyResponse>() {
                    @Override
                    public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                        if (response.isSuccessful()) {

                            MyResponse myResponse = response.body();
                            if (myResponse.getRows().size() > 0) {
                                distance.postValue(myResponse.getRows().get(0).getElements().get(0).getDistance().getText());
                                destination.postValue(myResponse.getDestinationAddresses().get(0));
                                source.postValue(myResponse.getOriginAddresses().get(0));
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<MyResponse> call, Throwable t) {

                    }
                });

                loading.postValue(View.GONE);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                loading.setValue(View.GONE);
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
                loading.setValue(View.VISIBLE);
            }
        };

        loadingTask.execute();
    }
}
