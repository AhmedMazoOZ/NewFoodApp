package com.example.foodplanner.SearchFeature.Model;

import static io.reactivex.rxjava3.schedulers.Schedulers.io;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.foodplanner.HomeScreen.View.Model.Area;
import com.example.foodplanner.HomeScreen.View.Model.AreaResponse;
import com.example.foodplanner.HomeScreen.View.Model.Category;
import com.example.foodplanner.HomeScreen.View.Model.CategoryResponse;
import com.example.foodplanner.HomeScreen.View.Model.Recipe;
import com.example.foodplanner.NetworkLayer.ApiClient;
import com.example.foodplanner.NetworkLayer.ApiInterface;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchModel {

    String category;
    private CompositeDisposable disposables = new CompositeDisposable();

    public void GetCategories(OnHomeListener onhomeListener) {
        ApiInterface services = ApiClient.getClient().create(ApiInterface.class);
        Call<CategoryResponse> api = services.getCategories();
        api.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onhomeListener.onSuccessCategory(response.body().categories);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
                onhomeListener.onFailure(t.getMessage());
            }
        });
    }

    public void GetCountries(OnHomeListener onhomeListener) {
        ApiInterface services = ApiClient.getClient().create(ApiInterface.class);
        Call<AreaResponse> api = services.getAreaes();
        api.enqueue(new Callback<AreaResponse>() {
            @Override
            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onhomeListener.onSuccessCountries(response.body().meals);
                } else {
                    Log.e("API_ERROR", "Response error: " + response.errorBody());
                    onhomeListener.onFailure("Response error");
                }
            }

            @Override
            public void onFailure(Call<AreaResponse> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
                onhomeListener.onFailure(t.getMessage());
            }
        });
    }

    public void GetIngrediants(OnHomeListener onhomeListener) {
        ApiInterface services = ApiClient.getClient().create(ApiInterface.class);
        Call<IngrediantResponce> api = services.getIngrediant();
        api.enqueue(new Callback<IngrediantResponce>() {
            @Override
            public void onResponse(Call<IngrediantResponce> call, Response<IngrediantResponce> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onhomeListener.onSuccessIngrediants(response.body().meals);
                } else {
                    Log.e("API_ERROR", "Response error: " + response.errorBody());
                    onhomeListener.onFailure("Response error");
                }
            }

            @Override
            public void onFailure(Call<IngrediantResponce> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
                onhomeListener.onFailure(t.getMessage());
            }
        });
    }

    public void SearchByCategory(String str, EditText searchEditText, OnHomeListener onhomeListener) {
        ApiInterface services = ApiClient.getClient().create(ApiInterface.class);
        Call<IngrediantResponce> api;
        if (str != null && str.equalsIgnoreCase("c")) {
            disposables.add(
                    Observable.create(emitter -> {
                                searchEditText.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        emitter.onNext(s.toString());
                                        category = s.toString();
                                        Log.d("asdfdsfsdfsdf", "onTextChanged: " + category);
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                    }
                                });
                            })
                            .debounce(300, TimeUnit.MILLISECONDS) // Wait 300ms after the user stops typing
                            .switchMap(query ->
                                    services.getRecipesByCategory(category)
                                            .subscribeOn(io())
                            )
                            .subscribeOn(io()) // Perform network call on IO thread
                            .observeOn(AndroidSchedulers.mainThread()) // Observe result on main thread
                            .subscribe(
                                    searchResponse -> {
                                        onhomeListener.onSuccessSearchByCategory(searchResponse.meals);
                                    },
                                    throwable -> {
                                        onhomeListener.onFailure(throwable.getMessage());
                                    }
                            )
            );
        } else if (str != null && str.equalsIgnoreCase("a")) {
            disposables.add(
                    Observable.create(emitter -> {
                                searchEditText.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        emitter.onNext(s.toString());
                                        category = s.toString();
                                        Log.d("asdfdsfsdfsdf", "onTextChanged: " + category);
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                    }
                                });
                            })
                            .debounce(300, TimeUnit.MILLISECONDS) // Wait 300ms after the user stops typing
                            .switchMap(query ->
                                    services.getRecipesByArea(category)
                                            .subscribeOn(io())
                            )
                            .subscribeOn(io()) // Perform network call on IO thread
                            .observeOn(AndroidSchedulers.mainThread()) // Observe result on main thread
                            .subscribe(
                                    searchResponse -> {
                                        onhomeListener.onSuccessSearchByCategory(searchResponse.meals);
                                    },
                                    throwable -> {
                                        onhomeListener.onFailure(throwable.getMessage());
                                    }
                            )
            );
        } else if (str != null && str.equalsIgnoreCase("i")) {
            disposables.add(
                    Observable.create(emitter -> {
                                searchEditText.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        emitter.onNext(s.toString());
                                        category = s.toString();
                                        Log.d("asdfdsfsdfsdf", "onTextChanged: " + category);
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                    }
                                });
                            })
                            .debounce(300, TimeUnit.MILLISECONDS) // Wait 300ms after the user stops typing
                            .switchMap(query ->
                                    services.getRecipesByingrediant(category)
                                            .subscribeOn(io())
                            )
                            .subscribeOn(io()) // Perform network call on IO thread
                            .observeOn(AndroidSchedulers.mainThread()) // Observe result on main thread
                            .subscribe(
                                    searchResponse -> {
                                        onhomeListener.onSuccessSearchByCategory(searchResponse.meals);
                                    },
                                    throwable -> {
                                        onhomeListener.onFailure(throwable.getMessage());
                                    }
                            )
            );
        } else {
            disposables.add(
                    Observable.create(emitter -> {
                                searchEditText.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        emitter.onNext(s.toString());
                                        category = s.toString();
                                        Log.d("asdfdsfsdfsdf", "onTextChanged: " + category);
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                    }
                                });
                            })
                            .debounce(300, TimeUnit.MILLISECONDS) // Wait 300ms after the user stops typing
                            .switchMap(query ->
                                    services.getRecipesByCategory(category)
                                            .subscribeOn(io())
                            )
                            .subscribeOn(io()) // Perform network call on IO thread
                            .observeOn(AndroidSchedulers.mainThread()) // Observe result on main thread
                            .subscribe(
                                    searchResponse -> {
                                        onhomeListener.onSuccessSearchByCategory(searchResponse.meals);
                                    },
                                    throwable -> {
                                        onhomeListener.onFailure(throwable.getMessage());
                                    }
                            )
            );
        }


    }

    public interface OnHomeListener {

        void onSuccessCategory(List<Category> CategoriesList);

        void onSuccessCountries(List<Area> AreaList);

        void onSuccessIngrediants(List<Iingrediants> ingrediantsList);

        void onSuccessSearchByCategory(List<Recipe> RecipeList);

        void onFailure(String errorMessage);
    }
}
