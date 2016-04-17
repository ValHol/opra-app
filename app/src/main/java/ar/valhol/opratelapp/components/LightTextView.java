package ar.valhol.opratelapp.components;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class LightTextView extends TextView {

    private static Typeface lightTypeFace;

    public LightTextView(Context context) {
        super(context);
        init();
    }

    public LightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LightTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (lightTypeFace == null) {
            lightTypeFace = Typeface.createFromAsset(getContext().getAssets(), "OpenSans-CondLight.ttf");
        }
        setTypeface(lightTypeFace);
    }

}
