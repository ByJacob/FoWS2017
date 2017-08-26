package pl.edu.pwr.fows.fows2017.facebookPost;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.parser.JsonParserFacebookPosts;
import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesAPIProvider;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

class FacebookProvider extends OkHttpProvider {

    private final List<FacebookPost> posts = new ArrayList<>();
    private final String url;
    private final SharedPreferencesDataInterface gatewaySharedPref;

    public FacebookProvider(String url, SharedPreferencesDataInterface gatewaySharedPref) {
        this.url = url;
        this.gatewaySharedPref = gatewaySharedPref;
    }

    public List<FacebookPost> getPosts() throws Exception {
        String response;
        response = run(url);
        gatewaySharedPref.save(SharedPreferencesAPIProvider.TAG_FACEBOOK_POSTS, response);
        List<FacebookPost> tempPosts = JsonParserFacebookPosts.parseJson(response);
        posts.clear();
        for (int i = 0; i < tempPosts.size(); i++) {
            posts.add(tempPosts.get(i));
        }
        return posts;
    }
}
