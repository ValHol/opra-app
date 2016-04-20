package ar.valhol.redminesample.components;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class BoldTextView extends TextView {

    private static Typeface boldTypeFace;

    public BoldTextView(Context context) {
        super(context);
        init();
    }

    public BoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (boldTypeFace == null) {
            boldTypeFace = Typeface.createFromAsset(getContext().getAssets(), "OpenSans-CondBold.ttf");
        }
        setTypeface(boldTypeFace);
    }
}
