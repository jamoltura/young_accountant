package financialStudio.young_accountant;

import android.view.View;
import android.widget.AdapterView;

public interface DoubleTapCallback {
    void onDoubleClick(AdapterView<?> parent, View view, int position, long id);
}
