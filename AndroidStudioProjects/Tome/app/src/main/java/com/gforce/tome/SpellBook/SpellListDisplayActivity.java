package com.gforce.tome.SpellBook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gforce.tome.R;

public class SpellListDisplayActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    SpellList list;
    SpellList mDisplayList;

    ListView mSpellListDisplay;
    ArrayAdapter mSpells;

    String mFilter;

    private static String EXTRA_SPELL = "com.gforce.tome.SPELL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_list_display);

        list = new SpellList();

        // add spells from each file
        SpellList.spellListFromJSON(getApplicationContext(), list, "phb_spells", "Player's Handbook");
        SpellList.spellListFromJSON(getApplicationContext(), list, "eepc_spells", "Elemental Evil Player's Companion");
        SpellList.spellListFromJSON(getApplicationContext(), list, "scag_spells", "Sword Coast Adventurer's Guide");

        // sort spells alphabetically
        list.spellSort();

        // copy full spell list into the displayed list
        mDisplayList = new SpellList(list);

        // attach the list to the view components
        mSpellListDisplay = (ListView)findViewById(R.id.spellList);
        mSpells = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, mDisplayList.getSpellNames());
        mSpellListDisplay.setAdapter(mSpells);
        mSpellListDisplay.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spell_list_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.action_filter:
                createFilterDialog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, SpellDisplayActivity.class);
        intent.putExtra(EXTRA_SPELL, list.getSpellByIndex(position));
        startActivity(intent);
    }

    public void createFilterDialog(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Spell List Filter");

        // Dialog button functionality
        builder.setCancelable(true);
        builder.setPositiveButton("Filter", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Set the desired options
                mFilter = "something";
                // Filter the list and set the new list to the view

                // Dismiss the dialog
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Clear the filter
                mFilter = "";
                // Reset the list and the view; refresh the display

                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        android.app.AlertDialog alertdialog = builder.create();
        alertdialog.show();
    }
}
