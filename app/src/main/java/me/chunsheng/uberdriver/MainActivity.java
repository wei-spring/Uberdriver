package me.chunsheng.uberdriver;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.layout_bottom_sheet)
    View mLayoutBottomSheet;
    @BindView(R.id.activity_main)
    android.support.design.widget.CoordinatorLayout mainContain;
    @BindView(R.id.car_btn_call)
    Button car_btn_call;
    @BindView(R.id.checkbox1)
    CheckBox checkbox1;

    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mBottomSheetBehavior = BottomSheetBehavior.from(mLayoutBottomSheet);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("Tag", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.e("Tag", "STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("Tag", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("Tag", "STATE_COLLAPSED");
                        break;
                    default:
                        Log.e("Tag", "Default");
                        break;

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

    }


    @OnClick(R.id.map)
    public void showBottomSheet() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @OnClick(R.id.car_btn_call)
    public void callCar() {
        startActivity(new Intent(this, DetailActivity.class));
    }


    @OnClick(R.id.money)
    public void showMoney() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("预估价格:")
                .setMessage("预估价格15元,实际支付价格以具体路况为准...")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_map)
                .show();
    }

}
