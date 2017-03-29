package jp.devisoft.griddecorationtest;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    public static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_CARD = 1;

    private Context context;
    private final List<MyData> list;

    public MyAdapter(@NonNull Context context) {
        this(context, new ArrayList<MyData>());
    }

    public MyAdapter(@NonNull Context context, List<MyData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, @LayoutRes int viewType) {
        return new MyHolder(context, parent);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Context getContext() {
        return context;
    }

    public MyData getItem(int position) {
        return list.get(position);
    }

    public void add(MyData data) {
        list.add(data);
    }

    public void addAll(Collection<MyData> collection) {
        list.addAll(collection);
    }

    public void clear() {
        list.clear();
    }
}
