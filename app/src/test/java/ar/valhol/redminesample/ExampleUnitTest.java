package ar.valhol.redminesample;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ExampleUnitTest {
    private static TextView mTextTitle;

    @Test
    public void title_notNullOrEmpty() {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        mTextTitle = (TextView) activity.findViewById(R.id.title);
        Log.d("mText", mTextTitle.getText().toString());
        assertNotSame(null, mTextTitle.getText().toString());
        assertNotSame("REDMINE MINI", mTextTitle.getText().toString());
    }

    @Test
    public void textTitle_notNull() {
        assertNotSame(null, mTextTitle);
    }

    @Test
    public void fragment_isShowing() {
    }

}