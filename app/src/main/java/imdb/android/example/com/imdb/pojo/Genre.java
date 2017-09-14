package imdb.android.example.com.imdb.pojo;

import com.google.gson.annotations.Expose;

public class Genre {

    @Expose
    int id;

    @Expose
    String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
