package imdb.android.example.com.imdb;

import imdb.android.example.com.imdb.dragger.component.DaggerNetworkComponent;
import imdb.android.example.com.imdb.dragger.component.NetworkComponent;
import imdb.android.example.com.imdb.dragger.module.ApplicationModule;
import imdb.android.example.com.imdb.dragger.module.NetworkModule;

public class Application extends android.app.Application {

    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        networkComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule("https://api.themoviedb.org/3/"))
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
