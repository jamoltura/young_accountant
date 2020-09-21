package financialStudio.young_accountant;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

public class DoubleTapListener implements AdapterView.OnItemClickListener {

    private boolean isRunning= false;
    private int resetInTime =500;
    private int counter=0;

    private DoubleTapCallback listener;

    public DoubleTapListener(Context context)
    {
        listener = (DoubleTapCallback)context;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(isRunning)
        {
            if(counter==1) //<-- makes sure that the callback is triggered on double click
                listener.onDoubleClick(parent, view, position, id);
        }

        counter++;

        if(!isRunning)
        {
            isRunning=true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(resetInTime);
                        isRunning = false;
                        counter=0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
}
