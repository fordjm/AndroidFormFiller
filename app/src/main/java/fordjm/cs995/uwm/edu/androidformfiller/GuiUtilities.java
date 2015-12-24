package fordjm.cs995.uwm.edu.androidformfiller;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

// TODO:	Change from display size to container size
public class GuiUtilities {
		
	public static Button decorateButton(final Button inputButton, Activity activity, String buttonText, int buttonColor, 
			LinearLayout.LayoutParams layoutParams, OnClickListener onClickListener){
		inputButton.setText(buttonText);
		inputButton.setTextSize(GuiUtilities.getScaledFontSize(activity, 22.0f));
		inputButton.setBackgroundColor(buttonColor);
		inputButton.setLayoutParams(layoutParams);
		inputButton.setOnClickListener(onClickListener);
		return inputButton;
	}
	
	// From http://stackoverflow.com/questions/2596452/how-to-scale-resize-text-to-fit-a-textview
	// Answer by Mark-1688382
	// Retrieved 01/31/2015
	public static int getScaledFontSize(Activity activity, Float size) {
		DisplayMetrics dMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dMetrics);

		// lets try to get them back a font size realtive to the pixel width of the screen
		final float WIDE = activity.getResources().getDisplayMetrics().widthPixels;
		int valueWide = (int)(WIDE / size / (dMetrics.scaledDensity));
		return valueWide;
	}
	
	// TODO:	Handle arbitrarily large button count
	public static int[] getButtonColors(){
		int[] result = new int[]{ -16711936, -65536, -256, -16776961, -65281, -7829368, -16711681 };
		
		return result;
	}
	
}

