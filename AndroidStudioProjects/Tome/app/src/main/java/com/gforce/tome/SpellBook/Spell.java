package com.gforce.tome.SpellBook;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class Spell
 *
 * This class describes a spell in D&D 5e.
 *
 * Created by GForce on 7/31/2015.
 */
public class Spell implements Parcelable {
    private String name;
    private int level;                  // the spell's caster level; 0 = cantrip
    private String school;
    private String castTime;
    private String range;

    // Spell Components
    private boolean somatic;            // has somatic component? T/F
    private boolean verbal;             // has verbal component? T/F
    private boolean material;           // has material component(s)? T/F
    private String components;          // the spell's material components; if there are none, this
                                        // string will be empty

    private String duration;
    private boolean concentration;      // requires concentration? T/F
    private String description;         // includes higher level casting info

    // Extra Info
    private String source;              // The spell's source material, for reference

    public Spell() {
        this.name = "";
        this.level = -1;
        this.school = "";
        this.castTime = "";
        this.range = "";
        this.somatic = false;
        this.verbal = false;
        this.material = false;
        this.components = "";
        this.duration = "";
        this.concentration = false;
        this.description = "";
        this.source = "UNKNOWN";
    }

    public Spell(String name, int level, String school, String castTime, String range,
                 boolean somatic, boolean verbal, boolean material, String components,
                 String duration, boolean concentration, String description, String source) {
        this.name = name;
        this.level = level;
        this.school = school;
        this.castTime = castTime;
        this.range = range;
        this.somatic = somatic;
        this.verbal = verbal;
        this.material = material;
        this.components = components;
        this.duration = duration;
        this.concentration = concentration;
        this.description = description;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCastTime() {
        return castTime;
    }

    public void setCastTime(String castTime) {
        this.castTime = castTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public boolean hasSomatic() {
        return somatic;
    }

    public void setSomatic(boolean somatic) {
        this.somatic = somatic;
    }

    public boolean hasVerbal() {
        return verbal;
    }

    public void setVerbal(boolean verbal) {
        this.verbal = verbal;
    }

    public boolean hasMaterial() {
        return this.material;
    }

    public void setMaterial(boolean material) {
        this.material = material;
    }

    public String getComponents() {
        return this.components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isConcentration() {
        return concentration;
    }

    public void setConcentration(boolean concentration) {
        this.concentration = concentration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return this.source;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // get the string value of the enum
        dest.writeString(this.castTime);
        dest.writeString(this.components);
        // handle bool value as int
        dest.writeInt(this.concentration ? 1 : 0);
        dest.writeString(this.description);
        dest.writeString(this.duration);
        dest.writeInt(this.level);
        // handle bool value as int
        dest.writeInt(this.material ? 1 : 0);
        dest.writeString(this.name);
        dest.writeString(this.range);
        // get the string value of the enum
        dest.writeString(this.school);
        // handle bool value as int
        dest.writeInt(this.somatic ? 1 : 0);
        // handle bool value as int
        dest.writeInt(this.verbal ? 1 : 0);
        dest.writeString(this.source);
    }

    // Needed to pass a Spell object between two Activities (via an Intent)
    public static final Parcelable.Creator<Spell> CREATOR = new Parcelable.Creator<Spell>() {
        public Spell createFromParcel(Parcel src) {
            final Spell spell = new Spell();
            spell.setCastTime(src.readString());
            spell.setComponents(src.readString());
            // convert back to bool from int
            spell.setConcentration(src.readInt() != 0);
            spell.setDescription(src.readString());
            spell.setDuration(src.readString());
            spell.setLevel(src.readInt());
            spell.setMaterial(src.readInt() != 0);
            spell.setName(src.readString());
            spell.setRange(src.readString());
            spell.setSchool(src.readString());
            // convert back to bool from int
            spell.setSomatic(src.readInt() != 0);
            // convert back to bool from int
            spell.setVerbal(src.readInt() != 0);
            spell.setSource(src.readString());

            return spell;
        }

        public Spell[] newArray(int size){
            return new Spell[size];
        }
    };
}