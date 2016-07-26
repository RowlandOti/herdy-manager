package co.herdy.manager.presentation.dashboardfeature.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.herdy.manager.BuildConfig;
import co.herdy.manager.R;
import co.herdy.manager.domain.dashboardfeature.model.Animal;
import co.herdy.manager.domain.repository.model.ModelLoader;
import co.herdy.manager.presentation.dashboardfeature.view.adapter.AnimalAdapter;
import co.herdy.manager.presentation.view.fragment.ABaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimalFragment extends ABaseFragment {

    // The class Log identifier
    private static final String LOG_TAG = AnimalFragment.class.getSimpleName();

    // ButterKnife injected Views
    @Bind(R.id.sw_refresh_layout)
    SwipeRefreshLayout mSwRefreshLayout;
    @Bind(R.id.recycle_view)
    RecyclerView mRecycleView;
    @Bind(R.id.empty_text_view_container)
    LinearLayout mEmptyTextViewContainer;

    private List<Animal> mAnimalList;
    private AnimalAdapter mDueAdapter;
    private LoaderManager.LoaderCallbacks mAnimalsLoaderCallBack;


    public AnimalFragment() {
        // Required empty public constructor
    }


    public static AnimalFragment newInstance(Bundle args) {
        AnimalFragment fragmentInstance = new AnimalFragment();
        if (args != null) {
            fragmentInstance.setArguments(args);
        }
        return fragmentInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animal, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwRefreshLayout.setColorSchemeResources(R.color.app_color_accent_teal);
        final LinearLayoutManager mLinearLayoutManger = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        mRecycleView.setLayoutManager(mLinearLayoutManger);
        mRecycleView.setHasFixedSize(false);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());


        mDueAdapter = new AnimalAdapter(mAnimalList);

        mRecycleView.setAdapter(mDueAdapter);

        mAnimalsLoaderCallBack = new LoaderManager.LoaderCallbacks<List<Animal>>() {
            @Override
            public Loader<List<Animal>> onCreateLoader(int id, Bundle args) {
                ModelLoader productLoader = new ModelLoader<Animal>(getActivity(), Animal.class, true);
                return productLoader;
            }

            @Override
            public void onLoadFinished(Loader<List<Animal>> loader, List<Animal> dataList) {
                mAnimalList = dataList;
                mDueAdapter.addAll(mAnimalList);
                mDueAdapter.notifyDataSetChanged();
                updateEmptyView();
                // Check whether we are in debug mode
                if (BuildConfig.IS_DEBUG_MODE) {
                    Log.d(LOG_TAG, "Animal: " + mDueAdapter.getItemCount());
                }
            }

            @Override
            public void onLoaderReset(Loader<List<Animal>> loader) {
                mDueAdapter.addAll(null);
            }
        };

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAnimalList = new ArrayList<>();
        // Change Title
        getActivity().setTitle("Animals");
        // Initialize the Loader
        getLoaderManager().initLoader(0, null, mAnimalsLoaderCallBack);
        updateEmptyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void updateEmptyView() {
        if (mDueAdapter.getItemCount() == 0) {
            mSwRefreshLayout.setVisibility(View.GONE);
            mEmptyTextViewContainer.setVisibility(View.VISIBLE);
        } else {
            mSwRefreshLayout.setVisibility(View.VISIBLE);
            mEmptyTextViewContainer.setVisibility(View.GONE);
        }
    }

}
