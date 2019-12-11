package com.team.noty.event.ui.activities;

public interface LoadIndicator {
    void hideLoad();
    void showLoad();

    LoadIndicator empty = new LoadIndicator() {
        @Override
        public void hideLoad() {

        }

        @Override
        public void showLoad() {

        }
    };
}
