package com.example.ksdandroidocrreader;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CategoryFragment extends Fragment {

    private RecyclerView categoryView = null;

    private static String[] arr_string = {
            "발달 검사",
            "사회성 검사",
            "성격 검사",
            "언어 검사",
            "자폐 검사",
            "지능 검사",
            "집중력 검사",
            "학습 장애 검사",
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_main, container, false);
        categoryView = view.findViewById(R.id.categoryfragment_recyclerview);
        categoryView.setHasFixedSize(true);
        categoryView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        categoryView.setAdapter(new CategoryFragmentRecyclerViewAdapter(this, arr_string));

        return view;
    }

}

class CategoryFragmentRecyclerViewAdapter extends RecyclerView.Adapter<CategoryFragmentRecyclerViewAdapter.ViewHolder> {
    private String[] localStringDataSet;
    CategoryFragment fragment;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ResultsActivity.class);
                    intent.putExtra("code", getAdapterPosition());
                    intent.putExtra("name", localStringDataSet[getAdapterPosition()]);
                    v.getContext().startActivity(intent);
                }
            });
        }

        public TextView getTextView() {
            return textView;
        }
    }


    public CategoryFragmentRecyclerViewAdapter(CategoryFragment fragment, String[] dataSet) {
        this.fragment = fragment;
        localStringDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int width = fragment.getResources().getDisplayMetrics().widthPixels;

        TextView textView = new TextView((viewGroup.getContext()));
        textView.setTextSize(24);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.BLACK);
        textView.setLayoutParams(new LinearLayoutCompat.LayoutParams(width, width / 7));


        return new ViewHolder(textView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if(position % 2 == 0) {
            viewHolder.getTextView().setBackgroundColor(0xFFfcf8e8);
        }
        else {
            viewHolder.getTextView().setBackgroundColor(0xFFd4e2d4);
        }
        viewHolder.getTextView().setText(localStringDataSet[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localStringDataSet.length;
    }

}
