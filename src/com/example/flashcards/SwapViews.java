package com.example.flashcards;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

public final class SwapViews implements Runnable {
	private boolean mIsFirstView;
	Button image1;
	Button image2;

	public SwapViews(boolean isFirstView, Button image1, Button image2) {
		mIsFirstView = isFirstView;
		this.image1 = image1;
		this.image2 = image2;
	}

	public void run() {
		final float centerX = image1.getWidth() / 2.0f;
		final float centerY = image1.getHeight() / 2.0f;
		Flip3DAnimation rotation;

		if (mIsFirstView) {
			image1.setVisibility(View.GONE);
			image2.setVisibility(View.VISIBLE);
			image2.requestFocus();

			rotation = new Flip3DAnimation(-90, 0, centerX, centerY);
		} else {
			image2.setVisibility(View.GONE);
			image1.setVisibility(View.VISIBLE);
			image1.requestFocus();

			rotation = new Flip3DAnimation(90, 0, centerX, centerY);
		}

		rotation.setDuration(1000);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new DecelerateInterpolator());

		if (mIsFirstView) {
			image2.startAnimation(rotation);
		} else {
			image1.startAnimation(rotation);
		}
	}
}