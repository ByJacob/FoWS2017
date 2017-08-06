package pl.edu.pwr.fows.fows2017.presenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentNewsRowView;
import pl.edu.pwr.fows.fows2017.view.FragmentNewsView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public class FragmentNewsPresenter extends BasePresenter<FragmentNewsView>{

    private FragmentNewsView view;
    private List<FacebookPost> posts;

    public FragmentNewsPresenter(UseCaseFactory factory) {
        super(factory);
    }

    public void onViewTaken(FragmentNewsView view){
        this.view = view;
        super.factory.getFacebookPosts().execute().subscribe(this::onFacebookPostsFetchSuccess);
    }

    private void onFacebookPostsFetchSuccess(List<FacebookPost> posts){
        this.posts = posts;
        view.disableLoading();
    }
    public int getPostsCount() {
        return posts.size();
    }

    public void configureNewsRow(FragmentNewsRowView holder, int position, Locale locale) {
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy HH:mm", locale);
        holder.setDate(df.format(posts.get(position).getCreatedTime()));
        holder.setMessage(posts.get(position).getMessage());
        holder.setPicture(posts.get(position).getPicture());
    }
}
