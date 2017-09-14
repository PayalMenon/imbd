package imdb.android.example.com.imdb.dragger.component;

import javax.inject.Singleton;

import dagger.Component;
import imdb.android.example.com.imdb.dragger.module.ApplicationModule;
import imdb.android.example.com.imdb.dragger.module.NetworkModule;
import imdb.android.example.com.imdb.ui.DetailsFragment;
import imdb.android.example.com.imdb.ui.ListFragment;
import imdb.android.example.com.imdb.ui.MainActivity;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface NetworkComponent {

    void inject(MainActivity activity);
    void inject(ListFragment fragment);
    void inject(DetailsFragment fragment);
}
