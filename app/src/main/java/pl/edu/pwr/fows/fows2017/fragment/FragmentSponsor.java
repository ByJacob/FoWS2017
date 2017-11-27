package pl.edu.pwr.fows.fows2017.fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.LinearLayout;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.adapter.FragmentSponsorAdapter;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentSponsorModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.presenter.FragmentSponsorPresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentSponsorView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

public class FragmentSponsor extends BaseFragment implements FragmentSponsorView {

    @SuppressWarnings("CanBeFinal")
    @Inject
    FragmentSponsorPresenter presenter;
    private LinearLayout parent;

    @Override
    protected Integer getLayoutId() {
        return R.layout.fragment_sponsor;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parent = getView().findViewById(R.id.fragment_sponsor);
        presenter.onViewTaken(this);
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new FragmentSponsorModule()).inject(this);
    }

    @Override
    public void continueLoading() {
        Point point = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(point);
        FragmentSponsorAdapter adapter = new FragmentSponsorAdapter(getActivity(), point.x);
        adapter.setPresenter(presenter);
        adapter.setParent(parent);
        adapter.display();
    }
}
