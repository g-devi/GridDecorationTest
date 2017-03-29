package jp.devisoft.griddecorationtest;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class GridDecoration extends RecyclerView.ItemDecoration {

    private final int sideMargin;
    private final int halfMargin;
    private final int halfMinusMargin;
    private boolean initializedRecycler = false;

    public GridDecoration(int sideMargin) {
        this.sideMargin = sideMargin;
        halfMargin = sideMargin / 2;
        halfMinusMargin = -1 * halfMargin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
            RecyclerView.State state) {

        if (!(parent.getLayoutManager() instanceof GridLayoutManager)) {
            // とりあえずGridLayoutManager以外は無視
            super.getItemOffsets(outRect, view, parent, state);
            return;
        }

        setRecyclerAttr(parent);

        int adapterPosition = parent.getChildAdapterPosition(view);
        if (RecyclerView.NO_POSITION == adapterPosition) return;

        GridLayoutManager lm = (GridLayoutManager) parent.getLayoutManager();
        GridLayoutManager.SpanSizeLookup ssl = lm.getSpanSizeLookup();

        int spanCount = lm.getSpanCount();

        if (ssl.getSpanSize(adapterPosition) >= spanCount) {
            // 全幅以上の場合は余白をとらないようにマイナスマージンをつける
            ViewGroup.MarginLayoutParams params =
                    (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            params.setMargins(halfMinusMargin, params.topMargin, halfMinusMargin, sideMargin);
            view.setLayoutParams(params);
            super.getItemOffsets(outRect, view, parent, state);
            return;
        }

        outRect.left = halfMargin;
        outRect.right = halfMargin;
        // 見やすさのためにbottomにも設定
        outRect.bottom = sideMargin;
    }

    private void setRecyclerAttr(RecyclerView parent) {
        // Decoration内で設定するものでもないが、これ単体で完結させるためにとりあえずここに記載
        if (initializedRecycler) return;
        initializedRecycler = true;
        parent.setClipToPadding(false);
        parent.setPadding(
                halfMargin
                , parent.getPaddingTop()
                , halfMargin
                , parent.getPaddingBottom()
        );
    }
}
