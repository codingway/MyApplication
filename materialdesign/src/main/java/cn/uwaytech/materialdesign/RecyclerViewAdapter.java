package cn.uwaytech.materialdesign;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private List<Integer> integers;

    public RecyclerViewAdapter(List<Integer> integers) {
        this.integers = integers;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.info_text);
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        // set the view's size, margin, padding and layout parameters
        // ...

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int position) {
        recyclerViewHolder.textView.setText(integers.get(position) + "");
    }

    @Override
    public int getItemCount() {
        return integers.size();
    }
}
