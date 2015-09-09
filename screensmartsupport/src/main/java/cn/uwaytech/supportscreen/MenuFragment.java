package cn.uwaytech.supportscreen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MenuFragment extends Fragment implements AdapterView.OnItemClickListener {
    private Context context;
    private OnMenuItemSelectedListener onMenuItemSelectedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        onMenuItemSelectedListener = (OnMenuItemSelectedListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ListView listView = (ListView) view.findViewById(android.R.id.list);
        listView.setOnItemClickListener(this);
        listView.setAdapter(new MenuAdapter(context));
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (onMenuItemSelectedListener != null) {
            onMenuItemSelectedListener.onMenuSelect(position);
        }
    }

    class MenuAdapter extends BaseAdapter {
        private Context context;

        public MenuAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            ((TextView)convertView.findViewById(android.R.id.text1)).setText(position + "");

            return convertView;
        }
    }

    public interface OnMenuItemSelectedListener {
        void onMenuSelect(int position);
    }
}
