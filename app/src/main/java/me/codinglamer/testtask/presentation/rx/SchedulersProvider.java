package me.codinglamer.testtask.presentation.rx;

import io.reactivex.rxjava3.core.Scheduler;

public interface SchedulersProvider {

    Scheduler ui();

    Scheduler io();

    Scheduler computation();
}
