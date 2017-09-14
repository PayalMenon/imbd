package imdb.android.example.com.imdb.dragger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import imdb.android.example.com.imdb.Service.MovieService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    @Provides
    @Singleton
    MovieService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

}
