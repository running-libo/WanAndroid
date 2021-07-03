package com.libo.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * create by libo
 * create on 2021/7/3
 * description 流布局viewGroup
 */
public class FlowLayoutView extends ViewGroup {
    private List<Row> rows = new ArrayList<>();
    private int usedWidth;
    /**
     * 当前需要操作的行
     */
    private Row curRow;
    private int verticalPadding = 30;
    private int horizontalPadding = 40;

    public FlowLayoutView(Context context) {
        super(context);
    }

    public FlowLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        restoreLine();  //每次重新布局，属性要初始化，避免onMeasure重复调用混乱问题

        //子view设置宽高为父view大小减去padding值
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //设置每个子view宽高，并且将每个子View归到自己的行
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);

            //设置子view设置AT_MOST模式，即布局属性为wrap_content
            int childWidthSpec = MeasureSpec.makeMeasureSpec(width, widthMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : widthMode);
            int childHeightSpec = MeasureSpec.makeMeasureSpec(height, heightMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : heightMode);
            childView.measure(childWidthSpec, childHeightSpec);

            if (curRow == null) {
                curRow = new Row();
            }

            //根据当前childview宽度和剩余宽度判断是否能放进当前行，放不了就要换行
            if (childView.getMeasuredWidth() + horizontalPadding > width - usedWidth) {
                //先换行，再放入
                nextLine();
            }

            usedWidth += childView.getMeasuredWidth() + horizontalPadding;
            curRow.addView(childView);
        }

        //将最后一个row加入到rows中
        rows.add(curRow);

        //根据子view组成的高度重设自己高度
        int finalHeight = 0;
        for (Row row : rows) {
            finalHeight += row.height + verticalPadding;
        }

        setMeasuredDimension(width, finalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int top = 0;
        //遍历每一行，将每一行子view布局
        for (Row row : rows) {
            row.layout(top);
            top = top + row.height + verticalPadding;
        }
    }

    /**
     * 换行，需要将当前row存储，并且创建新的row，新的行使用空间置0
     */
    private void nextLine() {
        rows.add(curRow);
        curRow = new Row();
        usedWidth = 0;
    }

    /**
     * 每次onmeasure需要重置信息
     */
    private void restoreLine() {
        rows.clear();
        curRow = new Row();
        usedWidth = 0;
    }

    /**
     * 用于记录每一行放置子View的信息
     */
    class Row {
        /**
         * 该行放置的子view
         */
        private List<View> childViews = new ArrayList<>();
        private int height;

        public void addView(View view) {
            childViews.add(view);
            height = view.getMeasuredHeight() > height ? view.getMeasuredHeight() : height;  //高度取最高子view的高度
        }

        public int getSize() {
            return childViews.size();
        }

        /**
         * 将当前childViews进行布局
         * top 当前hang处于的顶部高度
         */
        public void layout(int top) {
            int leftMargin = 0;
            for (int i = 0; i < childViews.size(); i++) {
                View view = childViews.get(i);
                view.layout(leftMargin, top, leftMargin + view.getMeasuredWidth(), top + view.getMeasuredHeight());
                leftMargin = leftMargin + view.getMeasuredWidth() + horizontalPadding;
            }
        }
    }

}
