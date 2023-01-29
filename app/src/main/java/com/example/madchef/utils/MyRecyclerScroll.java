package com.example.madchef.utils;

import androidx.recyclerview.widget.RecyclerView;

public abstract class MyRecyclerScroll extends RecyclerView.OnScrollListener {
    int scrollDist = 0;
    boolean isVisible = true;
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int MINIMUM=1;
        if (isVisible && scrollDist > MINIMUM) {
            show();
            scrollDist = 0;
            isVisible = true;
        }
        else if (!isVisible && scrollDist < -MINIMUM) {

            hide();
            scrollDist = 0;
            isVisible = false;
        }

        if ((isVisible && dy > 0) || (!isVisible && dy < 0)) {
            scrollDist += dy;
        }

    }
    public abstract void show();
    public abstract void hide();
}
