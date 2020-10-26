package financialStudio.young_accountant;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PdfFragment extends BottomSheetDialogFragment {


    private static final String TAG = "myLogs";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public PdfFragment() {
        super();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void dismissAllowingStateLoss() {
        super.dismissAllowingStateLoss();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_pdf, null);


        return v;
    }




    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();

        if (dialog != null) {
            View bottomSheet = dialog.findViewById(R.id.design_bottom_sheet);
            bottomSheet.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        View view = getView();
        view.post(() -> {
            View parent = (View) view.getParent();
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) (parent).getLayoutParams();
            CoordinatorLayout.Behavior behavior = params.getBehavior();
            BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
            bottomSheetBehavior.setPeekHeight(view.getMeasuredHeight());

            parent.setBackgroundColor(Color.TRANSPARENT);

            FloatingActionButton fab = (FloatingActionButton) parent.findViewById(R.id.fab);
            fab.setImageResource(R.drawable.ic_downbutton);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        });
    }

    protected int getColorPrimaryDark(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(R.color.colorPrimaryDark, getActivity().getTheme());
        }else {
            return getResources().getColor(R.color.colorPrimaryDark);
        }
    }

    protected int getColor_white(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(R.color.color_white, getActivity().getTheme());
        }else {
            return getResources().getColor(R.color.color_white);
        }
    }

    protected int getColor_colorlisthead(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(R.color.colorlisthead, getActivity().getTheme());
        }else {
            return getResources().getColor(R.color.colorlisthead);
        }
    }

    protected int getColor_colorfonchild(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(R.color.colorfonchild, getActivity().getTheme());
        }else {
            return getResources().getColor(R.color.colorfonchild);
        }
    }

    protected int getColor_colortransparent(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(R.color.color_transparent, getActivity().getTheme());
        }else {
            return getResources().getColor(R.color.color_transparent);
        }
    }
}
