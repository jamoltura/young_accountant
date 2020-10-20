package financialStudio.young_accountant;

public class ThreadSearch extends Thread{

    private String value;
    private Activity_dopol activity_dopol;
    private DokDopol dokDopol;
    private StatusDopol statusDopol;


    public ThreadSearch(Activity_dopol activity_dopol, DokDopol dokDopol, String value, StatusDopol statusDopol) {
        this.activity_dopol = activity_dopol;
        this.dokDopol = dokDopol;
        this.value = value;
        this.statusDopol = statusDopol;
    }

    public ThreadSearch(Activity_dopol activity_dopol, DokDopol dokDopol, StatusDopol statusDopol) {
        this.activity_dopol = activity_dopol;
        this.dokDopol = dokDopol;
        this.statusDopol = statusDopol;
    }


    @Override
    public void run() {

        switch (statusDopol){
            case SearchStart:{
                boolean result = dokDopol.search(value);
                activity_dopol.runOnUiThread(() -> activity_dopol.stopSearch(dokDopol, result));
            }
            break;
            case SearchNext:{
                boolean result = dokDopol.nextCollection();
                activity_dopol.runOnUiThread(() -> activity_dopol.next_and_preed_Search(dokDopol, result));
            }
            break;
            case SearchPreed:{
                boolean result = dokDopol.preedCollection();
                activity_dopol.runOnUiThread(() -> activity_dopol.next_and_preed_Search(dokDopol, result));
            }
        }
    }
}
