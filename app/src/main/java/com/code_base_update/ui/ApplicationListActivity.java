package com.code_base_update.ui;

import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.code_base_update.beans.ApplicationBean;
import com.code_base_update.models.ApplicationListModel;
import com.code_base_update.presenters.IApplicationListPresenter;
import com.code_base_update.ui.adapters.ApplicationListAdapter;
import com.code_base_update.view.IApplicationListView;
import com.medeveloper.ayaz.hostelutility.R;

import java.util.ArrayList;

public class ApplicationListActivity extends BaseRecyclerActivity<IApplicationListView, IApplicationListPresenter, ApplicationListAdapter> implements IApplicationListView {

    private ArrayList<ApplicationBean> list;
    @Override
    public RecyclerView getRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        return recyclerView;
    }

    @Override
    public ApplicationListAdapter getAdapter() {
        return new ApplicationListAdapter(this, R.layout.new_card_application,list);
    }

    @Override
    public void initViews() {
        setupToolbar("Your Application");
        enableNavigation();
        list = new ArrayList<>();
        mPresenter.loadData(this);
    }

    @Override
    public void refreshLayout() {

    }

    @Override
    protected IApplicationListPresenter createPresenter() {
        return new ApplicationListModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.simple_recycler_activity;
    }

    @Override
    public void onListLoaded(ArrayList<ApplicationBean> complaintBean) {
        adapter.update(complaintBean);
    }

    @Override
    public void onFailure(String msg) {
        toastMsg(msg);
    }
}
