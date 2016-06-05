package com.gforce.tome.SpellBook;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import java.io.InputStream;
import java.io.StringWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class SpellList
 *
 * A SpellList is a list of spells used by a single source (a character, a class, etc.)
 *
 * Created by GForce on 8/8/2015.
 */
public class SpellList {
    private ArrayList<Spell> spellList;

    public SpellList() {
        this.spellList = new ArrayList<>();
    }

    public SpellList(SpellList list){
        this.spellList = list.spellList;
    }

    public ArrayList<Spell> getSpellList() {
        return this.spellList;
    }

    public void setSpellList(ArrayList<Spell> spellList) {
        this.spellList = spellList;
    }

    public int getLength() {
        return spellList.size();
    }

    public void addSpell(Spell spell) {
        this.spellList.add(spell);
    }

    public void addSpellList(SpellList list) {
        this.spellList.addAll(list.getSpellList());
    }

    public void removeSpell(Spell spell) {
        this.spellList.remove(spell);
    }

    public void removeSpellList(SpellList list) {
        this.spellList.removeAll(list.getSpellList());
    }

    public Spell getSpellByName(String name) {
        for (int i = 0; i < this.spellList.size(); i++) {
            if (name.equalsIgnoreCase(this.spellList.get(i).getName())) {
                return this.spellList.get(i);
            }
        }

        return null;
    }

    public Spell getSpellByIndex(int index) {
        if (index < this.spellList.size()) {
            return this.spellList.get(index);
        } else return null;
    }

    public ArrayList<String> getSpellNames() {
        ArrayList<String> names = new ArrayList<>();

        for (Spell spell : this.spellList) {
            names.add(spell.getName());
        }

        return names;
    }

    /**
     * This function parses a JSON file and populates a spell list with the appropriate spells
     * @param context - the context of the current activity (needed to get res files)
     * @param list - SpellList object to populate; this needs to be created prior to calling this method
     * @param fileName - the name (plain, no file path or extension)
     * @param sourceName - a string of the source where the spell content comes from (ex. PHB, etc.)
     */
    public static void spellListFromJSON(Context context, SpellList list, String fileName, String sourceName) {
        // get the raw JSON ID
        int id = context.getResources().getIdentifier(fileName, "raw", context.getPackageName());
        if(id == 0) { // the resource wasn't found - error handling
            Log.e("SpellList Parse", "Error: resource ID not found for " + fileName);
        }
        else { // the resource was found - proceed to parse
            InputStream input = context.getResources().openRawResource(id);
            StringWriter writer = new StringWriter();
            char[] buffer = new char[1024];

            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
                int n;
                while((n = reader.read(buffer)) != -1){
                    writer.write(buffer, 0, n);
                }
            } catch (IOException ioe) {
                Log.e("SpellList Parse", "Error: IO Exception when reading input");
                ioe.printStackTrace();
            } finally {
                try { input.close(); } catch (IOException ioe) {
                    Log.e("SpellList Parse", "Error: IO Exception when closing stream");
                }
            }

            String jsonString = writer.toString();
            try {
                JSONArray jsonData = new JSONArray(jsonString);
                for(int i = 0; i < jsonData.length(); i++){
                    // get the current spell JSON object from the array
                    JSONObject spellData = jsonData.getJSONObject(i);

                    // create a temp spell
                    Spell curr = new Spell();

                    // set the spell's parameters from the JSON
                    curr.setName(spellData.getString("name"));
                    curr.setLevel(spellData.getInt("level"));
                    curr.setSchool(spellData.getString("school"));
                    curr.setCastTime(spellData.getString("casting_time"));
                    curr.setRange(spellData.getString("range"));
                    curr.setVerbal(spellData.getJSONObject("components").getBoolean("verbal"));
                    curr.setSomatic(spellData.getJSONObject("components").getBoolean("somatic"));
                    curr.setMaterial(spellData.getJSONObject("components").getBoolean("material"));
                    curr.setComponents(spellData.getJSONObject("components").getString("raw"));
                    curr.setDuration(spellData.getString("duration"));
                    curr.setConcentration(spellData.getBoolean("concentration"));
                    if(!spellData.has("higher_levels")){
                        curr.setDescription(spellData.getString("description"));
                    } else {
                        String desc = spellData.getString("description");
                        desc += "\n\nAt Higher Levels. " + spellData.getString("higher_levels");
                        curr.setDescription(desc);
                    }
                    curr.setSource(sourceName);

                    // add it to the list
                    list.addSpell(curr);
                }
            } catch (JSONException jsone) {
                Log.e("SpellList Parse", "Error: JSON Exception occurred!");
                jsone.printStackTrace();
            }
        }
    }

    /**
     * A method for merge-sorting a SpellList by spell name; sorts the list object its called on
     */
    public void spellSort() {
        int origLength = this.getLength();
        if (origLength > 1) {
            SpellList leftList = new SpellList();
            SpellList rightList = new SpellList();

            // Get the SpellList lengths
            int leftHalf = this.getLength() / 2;
            int rightHalf = this.getLength() - leftHalf;

            // Set each list to have the appropriate spells
            for (int i = 0; i < leftHalf; i++) {
                leftList.addSpell(this.getSpellByIndex(i));
            }
            for (int i = 0; i < rightHalf; i++) {
                rightList.addSpell(this.getSpellByIndex(i + leftHalf));
            }

            // Sort the lists recursively
            leftList.spellSort();
            rightList.spellSort();
            this.spellList = mergeSpellLists(leftList, rightList).spellList;
        }
    }

    /**
     * A method for merging two lists in sorted order
     * @param left - the left half of the list
     * @param right - the right half of the list
     * @return Returns the combined, sorted list
     */
    private SpellList mergeSpellLists(SpellList left, SpellList right) {
        int lIndex = 0;
        int rIndex = 0;
        SpellList result = new SpellList();

        while(lIndex < left.getLength() && rIndex < right.getLength()) {
            if(left.getSpellByIndex(lIndex).getName().compareTo(right.getSpellByIndex(rIndex).getName()) < 0) {
                // if the left spell name is 'less' than the right (-1), add it to the new list
                result.addSpell(left.getSpellByIndex(lIndex));
                lIndex++;
            }
            else {
                // if the right spell name is 'less' than the left (+1), add it to the new list;
                // NOTE: the names of two spells should NEVER be equivalent, so compareTo will be +-1
                result.addSpell(right.getSpellByIndex(rIndex));
                rIndex++;
            }
        }

        // add the remaining values - the remaining values should be in order at this point
        while(lIndex < left.getLength()) {
            result.addSpell(left.getSpellByIndex(lIndex));
            lIndex++;
        }
        while(rIndex < right.getLength()) {
            result.addSpell((right.getSpellByIndex(rIndex)));
            rIndex++;
        }

        return result;
    }
}