package jp.devisoft.griddecorationtest.failures;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class FailureDecoration1 extends RecyclerView.ItemDecoration {

    private final int sideMargin;

    public FailureDecoration1(int sideMargin) {
        this.sideMargin = sideMargin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
            RecyclerView.State state) {
        if (!(parent.getLayoutManager() instanceof GridLayoutManager)) {
            // とりあえずGridLayoutManager以外は無視
            super.getItemOffsets(outRect, view, parent, state);
            return;
        }

        int adapterPosition = parent.getChildAdapterPosition(view);
        if (RecyclerView.NO_POSITION == adapterPosition) return;

        GridLayoutManager lm = (GridLayoutManager) parent.getLayoutManager();
        GridLayoutManager.SpanSizeLookup ssl = lm.getSpanSizeLookup();

        int spanCount = lm.getSpanCount();

        outRect.bottom = sideMargin;

        if (ssl.getSpanSize(adapterPosition) >= spanCount) return;

        if (isLeft(ssl, spanCount, adapterPosition)) {
            outRect.left = sideMargin;
            outRect.right = sideMargin / 2;
            return;
        }

        if (isRight(ssl, spanCount, adapterPosition)) {
            outRect.left = sideMargin / 2;
            outRect.right = sideMargin;
            return;
        }

        outRect.left = sideMargin / 2;
        outRect.right = sideMargin / 2;
    }

    private boolean isLeft(GridLayoutManager.SpanSizeLookup ssl, int spanCount,
            int adapterPosition) {
        int spanIndex = ssl.getSpanIndex(adapterPosition, spanCount);
        return spanIndex == 0;
    }

    private boolean isRight(GridLayoutManager.SpanSizeLookup ssl,
            int spanCount, int adapterPosition) {
        int groupIndex = ssl.getSpanGroupIndex(adapterPosition, spanCount);
        int nextGroupIndex = ssl.getSpanGroupIndex(adapterPosition + 1, spanCount);
        return groupIndex != nextGroupIndex;
    }
}
