package com.android.recyclerviewtest.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2019-06-09   17:34
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public abstract class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
    protected static final int DEFAULT_DIVIDER_HEIGHT = 1; // 默认宽度
    protected static final int DEFAULT_DIVIDER_COLOR = 0xFFDDDDDD; // 默认分割线颜色
    protected int mOrientation;
    protected Paint mPaint; // 定义画笔

    protected int mHorizontalDividerHeight = DEFAULT_DIVIDER_HEIGHT; // 水平方向上的宽度
    protected int mHorizontalDividerColor = DEFAULT_DIVIDER_COLOR; // 水平分割线颜色
    protected int mCrossPointColor = mHorizontalDividerColor;   // 交叉点的分割线颜色，默认使用水平分割线的颜色

    protected boolean mIsDrawLastLow = false; // 最后一行之后是否绘制分割线
    protected boolean mIsDrawLastCol = false; // 最后一列之后是否绘制分割线
    protected boolean mIsDrawFirstLow = false; // 第一行之前是否绘制分割线
    protected boolean mIsDrawFirstCol = false; // 第一列之前是否绘制分割线

    protected int mFirstLowHeight = DEFAULT_DIVIDER_HEIGHT; // 第一行之前的分割线高度
    protected int mFirstColHeight = DEFAULT_DIVIDER_HEIGHT;   // 第一列之前的分割线宽度
    protected int mLastLowHeight = DEFAULT_DIVIDER_HEIGHT;  // 最后一行之后的分割线高度
    protected int mLastColHeight = DEFAULT_DIVIDER_HEIGHT;    // 最后一列之后的分割线宽度

    protected int mLastLowColor = DEFAULT_DIVIDER_COLOR; // 最后一行之后分割线颜色
    protected int mLastColColor = DEFAULT_DIVIDER_COLOR; // 最后一列之后分割线颜色
    protected int mFirstLowColor = DEFAULT_DIVIDER_COLOR; // 第一行之前分割线颜色
    protected int mFirstColColor = DEFAULT_DIVIDER_COLOR; // 第一列之前分割线颜色
    protected int mBorderCrossPointColor = DEFAULT_DIVIDER_COLOR; // 四周分隔线交叉点颜色


    public RecyclerItemDecoration(@RecyclerView.Orientation int orientation) {
        this.mOrientation = orientation;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(DEFAULT_DIVIDER_COLOR);
    }

    /**
     * 指定分割线高度，单位 px ；默认 1px
     *
     * @param dividerHeight 水平方向和垂直方向上的宽度相同
     * @return
     */
    public <T extends RecyclerItemDecoration> T dividerHeight(int dividerHeight) {
        this.mHorizontalDividerHeight = dividerHeight;
        setLowAndColHeight(dividerHeight, dividerHeight);
        return (T) this;
    }

    /**
     * 指定分割线高度，单位 px ；默认 1px
     *
     * @param horizontalDividerHeight 水平方向上的宽度
     * @param verticalDividerHeight   垂直方向的宽度
     * @return
     */
    public <T extends RecyclerItemDecoration> T dividerHeight(int horizontalDividerHeight, int verticalDividerHeight) {
        this.mHorizontalDividerHeight = horizontalDividerHeight;
        setLowAndColHeight(horizontalDividerHeight, verticalDividerHeight);
        return (T) this;
    }

    /**
     * 这个方法主要用于在设置水平方向和垂直方向时对第一行之前和最后一行之后、第一列之前和最后一列之后的高度进行设置，<br/>
     * 但是在设置之前需要先判断有没有单独为这几个位置设置过高度，因为单独设置的优先级更高
     *
     * @param lowHeight 行高度
     * @param colHeight 列高度
     */
    protected void setLowAndColHeight(int lowHeight, int colHeight) {
        if (DEFAULT_DIVIDER_HEIGHT == mFirstLowHeight) mFirstLowHeight = lowHeight;
        if (DEFAULT_DIVIDER_HEIGHT == mLastLowHeight) mLastLowHeight = lowHeight;
        if (DEFAULT_DIVIDER_HEIGHT == mFirstColHeight) mFirstColHeight = colHeight;
        if (DEFAULT_DIVIDER_HEIGHT == mLastColHeight) mLastColHeight = colHeight;
    }

    /**
     * 设置在第一行之前是否需要绘制分割线
     *
     * @param isDrawFirstLow 是否绘制第一行之前分割线  默认 false
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawFirstLowBefore(boolean isDrawFirstLow) {
        this.mIsDrawFirstLow = isDrawFirstLow;
        return (T) this;
    }

    /**
     * 设置在第一行之前是否需要绘制分割线，并指定颜色
     *
     * @param isDrawFirstLow 是否绘制第一行之前分割线  默认 false
     * @param firstLowColor  分割线颜色
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawFirstLowBeforeColor(boolean isDrawFirstLow, int firstLowColor) {
        this.mIsDrawFirstLow = isDrawFirstLow;
        this.mFirstLowColor = firstLowColor;
        return (T) this;
    }

    /**
     * 设置在第一行之前是否需要绘制分割线，并指定高度
     *
     * @param isDrawFirstLow 是否绘制第一行之前分割线  默认 false
     * @param firstLowHeight 分割线高度
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawFirstLowBeforeHeight(boolean isDrawFirstLow, int firstLowHeight) {
        this.mIsDrawFirstLow = isDrawFirstLow;
        this.mFirstLowHeight = firstLowHeight;
        return (T) this;
    }

    /**
     * 设置在第一行之前是否需要绘制分割线，并指定颜色和高度
     *
     * @param isDrawFirstLow 是否绘制第一行之前分割线  默认 false
     * @param firstLowColor  分割线颜色
     * @param firstLowHeight 分割线高度
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawFirstLowBefore(boolean isDrawFirstLow, int firstLowColor, int firstLowHeight) {
        this.mIsDrawFirstLow = isDrawFirstLow;
        this.mFirstLowColor = firstLowColor;
        this.mFirstLowHeight = firstLowHeight;
        return (T) this;
    }

    /**
     * 设置在第一列之前是否需要绘制分割线
     *
     * @param isDrawFirstCol 是否绘制第一列之前分割线  默认 false
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawFirstColBefore(boolean isDrawFirstCol) {
        this.mIsDrawFirstCol = isDrawFirstCol;
        return (T) this;
    }

    /**
     * 设置在第一列之前是否需要绘制分割线，并指定颜色
     *
     * @param isDrawFirstCol 是否绘制第一列之前分割线  默认 false
     * @param firstColColor  分割线颜色
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawFirstColBeforeColor(boolean isDrawFirstCol, int firstColColor) {
        this.mIsDrawFirstCol = isDrawFirstCol;
        this.mFirstColColor = firstColColor;
        return (T) this;
    }

    /**
     * 设置在第一列之前是否需要绘制分割线，并指定高度
     *
     * @param isDrawFirstCol 是否绘制第一列之前分割线  默认 false
     * @param firstColHeight 分割线高度
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawFirstColBeforeHeight(boolean isDrawFirstCol, int firstColHeight) {
        this.mIsDrawFirstCol = isDrawFirstCol;
        this.mFirstColHeight = firstColHeight;
        return (T) this;
    }

    /**
     * 设置在第一列之前是否需要绘制分割线，并指定颜色和高度
     *
     * @param isDrawFirstCol 是否绘制第一列之前分割线  默认 false
     * @param firstColColor  分割线颜色
     * @param firstColHeight 分割线高度
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawFirstColBefore(boolean isDrawFirstCol, int firstColColor, int firstColHeight) {
        this.mIsDrawFirstCol = isDrawFirstCol;
        this.mFirstColColor = firstColColor;
        this.mFirstColHeight = firstColHeight;
        return (T) this;
    }

    /**
     * 设置在最后一行之后是否需要绘制分割线
     *
     * @param isDrawLastLow 是否绘制最后一行之后分割线  默认 false
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawLastLowAfter(boolean isDrawLastLow) {
        this.mIsDrawLastLow = isDrawLastLow;
        return (T) this;
    }

    /**
     * 设置在最后一行之后是否需要绘制分割线，并指定颜色
     *
     * @param isDrawLastLow 是否绘制最后一行之后分割线  默认 false
     * @param lastLowColor  分割线颜色
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawLastLowAfterColor(boolean isDrawLastLow, int lastLowColor) {
        this.mIsDrawLastLow = isDrawLastLow;
        this.mLastLowColor = lastLowColor;
        return (T) this;
    }

    /**
     * 设置在最后一行之后是否需要绘制分割线，并指定高度
     *
     * @param isDrawLastLow 是否绘制最后一行之后分割线  默认 false
     * @param lastLowHeight 分割线高度
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawLastLowAfterHeight(boolean isDrawLastLow, int lastLowHeight) {
        this.mIsDrawLastLow = isDrawLastLow;
        this.mLastLowHeight = lastLowHeight;
        return (T) this;
    }

    /**
     * 设置在最后一行之后是否需要绘制分割线，并指定颜色和高度
     *
     * @param isDrawLastLow 是否绘制最后一行之后分割线  默认 false
     * @param lastLowColor  分割线颜色
     * @param lastLowHeight 分割线高度
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawLastLowAfter(boolean isDrawLastLow, int lastLowColor, int lastLowHeight) {
        this.mIsDrawLastLow = isDrawLastLow;
        this.mLastLowColor = lastLowColor;
        this.mLastLowHeight = lastLowHeight;
        return (T) this;
    }

    /**
     * 设置在最后一列之后是否需要绘制分割线
     *
     * @param isDrawLastCol 是否绘制最后一列之后分割线  默认 false
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawLastColAfter(boolean isDrawLastCol) {
        this.mIsDrawLastCol = isDrawLastCol;
        return (T) this;
    }

    /**
     * 设置在最后一列之后是否需要绘制分割线，并指定颜色
     *
     * @param isDrawLastCol 是否绘制最后一列之后分割线  默认 false
     * @param lastColColor  分割线颜色
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawLastColAfterColor(boolean isDrawLastCol, int lastColColor) {
        this.mIsDrawLastCol = isDrawLastCol;
        this.mLastColColor = lastColColor;
        return (T) this;
    }

    /**
     * 设置在最后一列之后是否需要绘制分割线，并指定颜色和高度
     *
     * @param isDrawLastCol 是否绘制最后一列之后分割线  默认 false
     * @param lastColHeight 分割线高度
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawLastColAfterHeight(boolean isDrawLastCol, int lastColHeight) {
        this.mIsDrawLastCol = isDrawLastCol;
        this.mLastColHeight = lastColHeight;
        return (T) this;
    }

    /**
     * 设置在最后一列之后是否需要绘制分割线，并指定颜色和高度
     *
     * @param isDrawLastCol 是否绘制最后一列之后分割线  默认 false
     * @param lastColColor  分割线颜色
     * @param lastColHeight 分割线高度
     * @return
     */
    public <T extends RecyclerItemDecoration> T isDrawLastColAfter(boolean isDrawLastCol, int lastColColor, int lastColHeight) {
        this.mIsDrawLastCol = isDrawLastCol;
        this.mLastColColor = lastColColor;
        this.mLastColHeight = lastColHeight;
        return (T) this;
    }

    /**
     * 设置边界交叉点颜色(第一行之前、第一列之前、最后一行之后、最后一列之后组成的框的交叉点颜色)，<br/>
     * 如果没有指定，那么行的交叉点使用水平方向上的颜色，列的交叉点使用垂直方向上的颜色
     *
     * @param borderCrossPointColor 边界交叉点颜色
     * @return
     */
    public <T extends RecyclerItemDecoration> T borderCrossPointColor(int borderCrossPointColor) {
        this.mBorderCrossPointColor = borderCrossPointColor;
        return (T) this;
    }

    /**
     * 指定分割线显色，默认 0xFFDDDDDD
     *
     * @param dividerColor 水平方向、垂直方向上和交叉点的颜色相同  交叉点默认使用水平方向上的颜色
     * @return
     */
    public <T extends RecyclerItemDecoration> T dividerColor(int dividerColor) {
        this.mHorizontalDividerColor = dividerColor;
        this.mCrossPointColor = dividerColor;
        setLowAndColColor(dividerColor, dividerColor);
        return (T) this;
    }

    /**
     * 分别指定水平方向上和垂直方向上的分割线显色，交叉点的分割线的颜色与水平方向上的颜色一致  默认 0xFFDDDDDD
     *
     * @param horizontalDividerColor 水平方向上分割线的颜色，交叉点的分割线的颜色与水平方向上的颜色一致
     * @param verticalDividerColor   垂直方向上分割线的颜色
     * @return
     */
    public <T extends RecyclerItemDecoration> T dividerColor(int horizontalDividerColor, int verticalDividerColor) {
        this.mHorizontalDividerColor = horizontalDividerColor;
        this.mCrossPointColor = horizontalDividerColor;
        setLowAndColColor(horizontalDividerColor, verticalDividerColor);
        return (T) this;
    }

    /**
     * 分别指定水平方向上、垂直方向上以及交叉点的分割线显色，默认 0xFFDDDDDD
     *
     * @param horizontalDividerColor 水平方向上分割线的颜色
     * @param verticalDividerColor   垂直方向上分割线的颜色
     * @param crossPointColor        交叉点的分割线的颜色
     * @return
     */
    public <T extends RecyclerItemDecoration> T dividerColor(int horizontalDividerColor, int verticalDividerColor, int crossPointColor) {
        this.mHorizontalDividerColor = horizontalDividerColor;
        this.mCrossPointColor = crossPointColor;
        setLowAndColColor(horizontalDividerColor, verticalDividerColor);
        return (T) this;
    }

    /**
     * 这个方法主要用于在设置水平方向和垂直方向时对第一行之前和最后一行之后、第一列之前和最后一列之后的颜色值进行设置，<br/>
     * 但是在设置之前需要先判断有没有单独为这几个位置设置过颜色，因为单独设置的优先级更高
     *
     * @param lowColor 行颜色
     * @param colColor 列颜色
     */
    protected void setLowAndColColor(int lowColor, int colColor) {
        if (DEFAULT_DIVIDER_COLOR == mFirstLowColor) mFirstLowColor = lowColor;
        if (DEFAULT_DIVIDER_COLOR == mLastLowColor) mLastLowColor = lowColor;
        if (DEFAULT_DIVIDER_COLOR == mFirstColColor) mFirstColColor = colColor;
        if (DEFAULT_DIVIDER_COLOR == mLastColColor) mLastColColor = colColor;
    }

    // 设置偏移量
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        itemOffsets(outRect, view, parent, state);
    }


    // 在 item 绘制之前调用(就是绘制在 item 的底层) [和 onDrawOver() 方法二选一即可]
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        draw(c, parent, state);
    }

    // 在 item 绘制之后调用(就是绘制在 item 的上层) [和 onDraw() 方法二选一即可]
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    /**
     * 设置 item 偏移量
     */
    protected abstract void itemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state);

    /**
     * 在 item 绘制之前调用(就是绘制在 item 的底层)
     */
    protected abstract void draw(Canvas c, RecyclerView parent, RecyclerView.State state);
}
