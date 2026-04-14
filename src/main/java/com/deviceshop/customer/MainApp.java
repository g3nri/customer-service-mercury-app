package com.deviceshop.customer;

import org.platformlambda.core.models.EntryPoint;
import org.platformlambda.core.system.AutoStart;

import org.platformlambda.core.annotations.MainApplication;

@MainApplication
public class MainApp implements EntryPoint {

    public static void main(String[] args) {
        AutoStart.main(args);
    }

    @Override
    public void start(String[] args) {
        System.out.println("Customer Mercury app started");
    }
}

