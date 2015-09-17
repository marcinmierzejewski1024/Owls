package marcinmierzejewski.com.owls;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.collections.Collection;
import com.googlecode.flickrjandroid.photos.PhotoList;
import com.googlecode.flickrjandroid.photos.SearchParameters;
import com.googlecode.flickrjandroid.photos.licenses.License;
import com.googlecode.flickrjandroid.test.TestInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dom on 17/09/15.
 */
public class Owl {

    String name;
    String description;
    ArrayList<String> photos;
    ArrayList<String> sounds;


    public Owl(String name, String description) {
        this.name = name;
        this.description = description;


        Flickr f = new Flickr(Utils.FLICKR_API_KEY,Utils.FLICKR_API_SECRET);
        SearchParameters searchParams = new SearchParameters();
        searchParams.setText(name);

        try {
            PhotoList results = f.getPhotosInterface().search(searchParams, 1, 0);
            results.get(0).getSmall320Url();
        } catch (Exception e) {
            e.printStackTrace();
        }


        this.photos = photos;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public ArrayList<String> getSounds() {
        return sounds;
    }

    public void setSounds(ArrayList<String> sounds) {
        this.sounds = sounds;
    }

    public String getThumbnail()
    {
        if(photos != null && photos.size() > 0)
            return photos.get(0);

        return null;
    }
}
