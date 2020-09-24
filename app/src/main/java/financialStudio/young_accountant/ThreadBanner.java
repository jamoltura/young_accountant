package financialStudio.young_accountant;

import androidx.appcompat.app.AppCompatActivity;

public class ThreadBanner extends Thread{

    final private BaseActivite activity;

    public ThreadBanner(BaseActivite activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        super.run();

        try {
            Thread.sleep(1000);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    getActivity().startBanner();
                }
            });


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public BaseActivite getActivity() {
        return activity;
    }
}
