package ar.valhol.opratelapp.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;
import ar.valhol.opratelapp.R;

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
