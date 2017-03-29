package jp.devisoft.griddecorationtest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import jp.devisoft.griddecorationtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        GridLayoutManager lm = new GridLayoutManager(this,
                getResources().getInteger(R.integer.recycler_span_count));
        adapter = new MyAdapter(this);
        lm.setSpanSizeLookup(new MySpanSizeLookup(adapter));

        int sideMargin = this.getResources().getDimensionPixelSize(R.dimen.recycler_side_margin);

        binding.recycler.addItemDecoration(new GridDecoration(sideMargin));
//        binding.recycler.addItemDecoration(new FailureDecoration1(sideMargin));
//        binding.recycler.addItemDecoration(new FailureDecoration2(sideMargin));
        binding.recycler.setLayoutManager(lm);
        binding.recycler.setAdapter(adapter);

        setData(createTestData2());
    }

    private void setData(List<MyData> list) {
        adapter.clear();
        adapter.addAll(list);
        adapter.notifyDataSetChanged();
    }

    private List<MyData> createTestData1() {
        List<MyData> list = new ArrayList<>();
        list.add(MyData.create(4));
        for (int i = 0; i < 16; i++) {
            list.add(MyData.create(1));
        }
        return list;
    }

    private List<MyData> createTestData2() {
        List<MyData> list = new ArrayList<>();
        list.add(MyData.create(4));

        list.add(MyData.create(1));
        list.add(MyData.create(1));
        list.add(MyData.create(1));
        list.add(MyData.create(1));

        list.add(MyData.create(2));
        list.add(MyData.create(2));

        list.add(MyData.create(3));
        list.add(MyData.create(1));

        list.add(MyData.create(3));

        list.add(MyData.create(2));
        return list;
    }
}
