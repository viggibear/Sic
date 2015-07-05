package com.bvwstudios.sic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bradley on 25/6/15.
 */
public class ArticleListFragment extends Fragment {
    ArticleAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final MainActivity activity = (MainActivity) getActivity();
        final View view = inflater.inflate(R.layout.fragment_article_list, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.article_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        // assume its for breaking news

        mAdapter = new ArticleAdapter(new ArrayList<NewsObject>());
        recyclerView.setAdapter(mAdapter);
        activity.pullBreakingNewsArticles(mAdapter);

        return view;
    }

}
