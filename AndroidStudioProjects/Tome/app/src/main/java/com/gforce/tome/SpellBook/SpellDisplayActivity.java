package com.gforce.tome.SpellBook;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.gforce.tome.R;

public class SpellDisplayActivity extends AppCompatActivity {

    private static String EXTRA_SPELL = "com.gforce.tome.SPELL";

    Spell toDisplay;

    TextView mNameDisplay;
    TextView mSourceDisplay;
    TextView mSchoolDisplay;
    TextView mCastTimeDisplay;
    TextView mRangeDisplay;
    TextView mComponentsDisplay;
    TextView mDurationDisplay;
    TextView mDescriptionDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_display);

        Bundle intentData = getIntent().getExtras();
        toDisplay = intentData.getParcelable(EXTRA_SPELL);

        mNameDisplay = (TextView) findViewById(R.id.textViewName);
        // Set font for name display
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/vtks_blank.ttf");
        mNameDisplay.setTypeface(font);
        mNameDisplay.setText(toDisplay.getName());
        mSourceDisplay = (TextView) findViewById(R.id.textViewSource);
        String source = "(from the " + toDisplay.getSource() + ")";
        mSourceDisplay.setText(source);
        mSchoolDisplay = (TextView) findViewById(R.id.textViewLevelSchool);
        // Compose the string that displays the spell's school and level
        String levelAndSchool = "";
        String school = toDisplay.getSchool().substring(0, 1).toUpperCase() + toDisplay.getSchool().substring(1);
        if(toDisplay.getLevel() == 0){
            levelAndSchool += school + " Cantrip";
        }
        else {
            if (toDisplay.getLevel() == 1) {
                levelAndSchool += "1st";
            } else if (toDisplay.getLevel() == 2) {
                levelAndSchool += "2nd";
            } else if (toDisplay.getLevel() == 3) {
                levelAndSchool += "3rd";
            } else {
                levelAndSchool += toDisplay.getLevel() + "th";
            }
            levelAndSchool += "-Level " + school + " Spell";
        }
        mSchoolDisplay.setText(levelAndSchool);
        mCastTimeDisplay = (TextView) findViewById(R.id.textViewCastTime);
        mCastTimeDisplay.setText(toDisplay.getCastTime());
        mRangeDisplay = (TextView) findViewById(R.id.textViewRange);
        mRangeDisplay.setText(toDisplay.getRange());
        mComponentsDisplay = (TextView) findViewById(R.id.textViewComponents);
        mComponentsDisplay.setText(toDisplay.getComponents());
        mDurationDisplay = (TextView) findViewById(R.id.textViewDuration);
        mDurationDisplay.setText(toDisplay.getDuration());
        mDescriptionDisplay = (TextView) findViewById(R.id.textViewDescription);
        mDescriptionDisplay.setText(toDisplay.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spell_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
