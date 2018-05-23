
package de.j4velin.pedometer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import de.j4velin.pedometer.util.Logger;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (BuildConfig.DEBUG) Logger.log("booted");

        SharedPreferences prefs = context.getSharedPreferences("pedometer", Context.MODE_PRIVATE);

        Database db = Database.getInstance(context);

        if (!prefs.getBoolean("correctShutdown", false)) {
            if (BuildConfig.DEBUG) Logger.log("Incorrect shutdown");
            // can we at least recover some steps?
            int steps = db.getCurrentSteps();
            if (BuildConfig.DEBUG) Logger.log("Trying to recover " + steps + " steps");
            db.addToLastEntry(steps);
        }
        // last entry might still have a negative step value, so remove that
        // row if that's the case
        db.removeNegativeEntries();
        db.saveCurrentSteps(0);
        db.close();
        prefs.edit().remove("correctShutdown").apply();

        context.startService(new Intent(context, SensorListener.class));
    }
}
