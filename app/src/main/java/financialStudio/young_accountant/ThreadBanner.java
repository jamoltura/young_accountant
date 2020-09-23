package financialStudio.young_accountant;

import androidx.appcompat.app.AppCompatActivity;

public class ThreadBanner extends Thread{

    final private BaseActivite activity;

    public ThreadBanner(BaseActivite activity, int value) {
        BaseActivite activity1;


        switch (value){
            case 0: activity1 = activity;
            break;
            case 1: activity1 = activity;
                break;
            case 2: activity1 = activity;
                break;
            case 3: activity1 = activity;
                break;
            case 4: activity1 = activity;
                break;
            default:
                activity1 = activity;
        }
        this.activity = activity1;
    }

    @Override
    public void run() {
        super.run();

    }

    public AppCompatActivity getActivity() {
        return activity;
    }
}
