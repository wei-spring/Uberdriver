package me.chunsheng.uberdriver;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.layout_bottom_sheet)
    View mLayoutBottomSheet;
    @BindView(R.id.layout_bottom_sheet_one)
    View mLayoutBottomSheetOne;
    @BindView(R.id.layout_bottom_sheet_two)
    View mLayoutBottomSheetTwo;


    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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
                        mLayoutBottomSheetTwo.setVisibility(View.VISIBLE);
                        mLayoutBottomSheetOne.setVisibility(View.GONE);
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("Tag", "STATE_COLLAPSED");
                        mLayoutBottomSheetTwo.setVisibility(View.GONE);
                        mLayoutBottomSheetOne.setVisibility(View.VISIBLE);
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

    @OnClick({R.id.help, R.id.help_call})
    public void showMoney() {
        new AlertDialog.Builder(DetailActivity.this)
                .setTitle("联系车主:")
                .setMessage("拨打:155-177-47859")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:15517747859"));
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(R.drawable.ub__ic_hop_car)
                .show();
    }
}
