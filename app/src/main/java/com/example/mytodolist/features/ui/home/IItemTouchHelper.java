package com.example.mytodolist.features.ui.home;

public interface IItemTouchHelper {
    boolean onItemMove(int from_position, int to_position);

    void onItemSwipeLeftModify(int position);

    void onItemSwipeRightDelete(int position);
}
