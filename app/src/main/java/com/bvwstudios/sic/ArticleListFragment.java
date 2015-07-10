package com.bvwstudios.sic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

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
                        Intent intent = new Intent(activity, ArticleActivity.class);
                        NewsObject returnedSource = mAdapter.getArticle(position);
                        intent.putExtra("newsObject", returnedSource);

                        startActivity(intent);
                    }
                })
        );

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        mAdapter = new ArticleAdapter(new ArrayList<NewsObject>(), getActivity());
        recyclerView.setAdapter(mAdapter);

        if (activity.selectedPosition == 0) {
            activity.pullBreakingNewsArticles(mAdapter);
        } else {
            activity.pullArticles(mAdapter, (1 << (activity.selectedPosition - 1)));
        }


        return view;
    }

}
