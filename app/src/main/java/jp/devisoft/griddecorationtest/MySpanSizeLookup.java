package jp.devisoft.griddecorationtest;

import android.support.v7.widget.GridLayoutManager;

public class MySpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private MyAdapter adapter;

    public MySpanSizeLookup(MyAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getSpanSize(int position) {
        return adapter.getItem(position).getSpanSize();
    }
}
