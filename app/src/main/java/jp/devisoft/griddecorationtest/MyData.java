package jp.devisoft.griddecorationtest;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class MyData extends BaseObservable {

    private int spanSize;

    public static MyData create(int spanSize) {
        return new MyData(spanSize);
    }

    private MyData(int spanSize) {
        this.spanSize = spanSize;
        notifyPropertyChanged(BR.strSpanSize);
    }

    public int getSpanSize() {
        return spanSize;
    }

    @Bindable
    public String getStrSpanSize() {
        return String.valueOf(spanSize);
    }
}
