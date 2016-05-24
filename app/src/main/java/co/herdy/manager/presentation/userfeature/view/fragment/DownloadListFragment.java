package co.herdy.manager.presentation.userfeature.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import co.herdy.manager.presentation.userfeature.presenter.DownloadListPresenter;
import co.herdy.manager.presentation.userfeature.view.adapter.DownloadAdapter;
import co.herdy.manager.presentation.userfeature.view.layoutmanager.DownloadLinearLayoutManager;
import co.herdy.manager.presentation.internal.di.coponents.DownloadComponent;
import co.herdy.manager.presentation.view.fragment.ABaseFragment;

import co.herdy.manager.R;
import co.herdy.manager.presentation.userfeature.view.IDownloadListView;
import co.herdy.manager.presentation.userfeature.view.activity.DownloadListActivity;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows a list of Downloads.
 */
public class DownloadListFragment extends ABaseFragment implements IDownloadListView {

    // Class log identifier
    public final static String LOG_TAG = DownloadListFragment.class.getSimpleName();
    /**
     * Interface for listening user list events.
     */
    public interface DownloadListListener {
        void onDownloadClicked(final String userModel);
    }

    @Inject
    DownloadListPresenter userListPresenter;
    @Inject
    DownloadAdapter usersAdapter;

    @Nullable
    @Bind(R.id.tb_user)
    Toolbar mToolbar;
    @Bind(R.id.rv_users)
    RecyclerView rv_users;
    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;
    @Bind(R.id.rl_retry)
    RelativeLayout rl_retry;
    @Bind(R.id.bt_retry)
    Button bt_retry;

    private DownloadListListener userListListener;

    public DownloadListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof DownloadListListener) {
            this.userListListener = (DownloadListListener) activity;
        }
        // Set the ToolBar
        ((DownloadListActivity) getActivity()).setToolbar(mToolbar, false, true, 0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(DownloadComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.userListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadDownloadList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.userListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.userListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rv_users.setAdapter(null);
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.userListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.userListListener = null;
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void renderDownloadList(Collection<String> userModelCollection) {
        if (userModelCollection != null) {
            this.usersAdapter.setDownloadsCollection(userModelCollection);
        }
    }

    @Override
    public void viewDownload(String userModel) {
        if (this.userListListener != null) {
            this.userListListener.onDownloadClicked(userModel);
        }
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {
        this.usersAdapter.setOnItemClickListener(onItemClickListener);
        this.rv_users.setLayoutManager(new DownloadLinearLayoutManager(context()));
        this.rv_users.setAdapter(usersAdapter);
    }

    /**
     * Loads all users.
     */
    private void loadDownloadList() {
        this.userListPresenter.initialize();
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        DownloadListFragment.this.loadDownloadList();
    }

    private DownloadAdapter.OnItemClickListener onItemClickListener =
            new DownloadAdapter.OnItemClickListener() {
                @Override
                public void onDownloadItemClicked(String userModel) {
                    if (DownloadListFragment.this.userListPresenter != null && userModel != null) {
                        DownloadListFragment.this.userListPresenter.onDownloadClicked(userModel);
                    }
                }
            };
}
