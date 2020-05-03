package com.manitos.dev.gpcendpoint.ui.content;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.manitos.dev.gpcendpoint.R;
import com.manitos.dev.jokedetail.JokeDetailActivity;

/**
 * Activity result, for more detail on
 * https://stackoverflow.com/questions/6147884/onactivityresult-is-not-being-called-in-fragment
 */
public class ContentFragment extends Fragment {

    private static String TAG = ContentFragment.class.getSimpleName() + "_ADS";

    private InterstitialAd mInterstitialAd;

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInterstitial();
    }

    private void initializeInterstitial() {
        mInterstitialAd = new InterstitialAd(requireContext());
        mInterstitialAd.setAdUnitId(requireContext().getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build());
        interstitialAdListeners();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_content, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == JokeDetailActivity.KEY_ACTIVITY_RESULT) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d(TAG, "The interstitial wasn't loaded yet.");
            }
        }
    }

    private void interstitialAdListeners() {
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "onAdLoaded(Code to be executed when an ad finishes loading.)");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d(TAG, "onAdFailedToLoad(Code to be executed when an ad request fails.)");
            }

            @Override
            public void onAdOpened() {
                Log.d(TAG, "onAdOpened(Code to be executed when the ad is displayed.)");
            }

            @Override
            public void onAdClicked() {
                Log.d(TAG, "onAdClicked(Code to be executed when the user clicks on an ad.)");
            }

            @Override
            public void onAdLeftApplication() {
                Log.d(TAG, "onAdLeftApplication(Code to be executed when the user has left the app.)");
            }

            @Override
            public void onAdClosed() {
                Log.d(TAG, "onAdClosed(Code to be executed when the interstitial ad is closed.)");
            }
        });
    }
}
