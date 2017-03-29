package jp.devisoft.griddecorationtest;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import jp.devisoft.griddecorationtest.databinding.ViewItemBinding;

public class MyHolder extends RecyclerView.ViewHolder {

    private final ViewItemBinding binding;

    public MyHolder(@NonNull Context context, @Nullable ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.view_item, parent, false));
        binding = DataBindingUtil.bind(itemView);
    }

    public void setData(MyData data) {
        binding.setData(data);
        binding.executePendingBindings();
    }
}
