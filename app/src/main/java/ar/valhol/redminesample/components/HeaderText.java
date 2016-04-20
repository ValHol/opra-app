package ar.valhol.redminesample.components;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;
import ar.valhol.redminesample.R;

/**
 * Created by Valentín on 15-Apr-16.
 */
public class HeaderText extends TextView {

    public HeaderText(Context context) {
        super(context);
        init();
    }

    public HeaderText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeaderText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.background_grey));
    }
}
