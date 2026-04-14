package com.deviceshop.customer;

import org.platformlambda.core.annotations.MainApplication;
import org.platformlambda.core.models.EntryPoint;
import org.platformlambda.core.system.AutoStart;
import org.platformlambda.core.system.Platform;
import org.platformlambda.core.system.ServerPersonality;

@MainApplication
public class MainApp implements EntryPoint {

    public static void main(String[] args) {
        AutoStart.main(args);
    }

    @Override
    public void start(String[] args) {
        ServerPersonality.getInstance().setType(ServerPersonality.Type.REST);
        Platform.getInstance().connectToCloud();
        System.out.println("Customer Mercury app started");
    }
}
