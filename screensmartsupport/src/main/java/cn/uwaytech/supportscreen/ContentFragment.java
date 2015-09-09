package cn.uwaytech.supportscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment {
    private TextView content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        content = (TextView) view.findViewById(R.id.content);

        if (getArguments() != null) {
            showContent(getArguments().getInt("position"));
        }

        return view;
    }

    public void showContent(int position) {
        content.setText("content " + position);
    }
}
