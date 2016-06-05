package com.gforce.tome.test;

import com.gforce.tome.SpellBook.Spell;
import com.gforce.tome.SpellBook.SpellList;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * SpellBookTest.java
 *
 * A test class for the Spell, Spelllist, EnumCastTime, and EnumSchool classes
 */
public class SpellBookTest extends TestCase {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // SPELL TESTS
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void testName() throws Exception {
        Spell spA = new Spell();
        spA.setName("Fireball");
        assertEquals(null, spA.getName(), "Fireball");
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");
        assertEquals(null, spB.getName(), "Cure Wounds");
    }

    public void testLevel() throws Exception {
        Spell spA = new Spell();
        spA.setLevel(2);
        assertEquals(null, spA.getLevel(), 2);
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");
        assertEquals(null, spB.getLevel(), 1);
    }

    public void testSchool() throws Exception {
        Spell spA = new Spell();
        spA.setSchool("evocation");
        assertEquals(null, spA.getSchool(), "evocation");
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");
        assertEquals(null, spB.getSchool(), "abjuration");
    }

    public void testCastTime() throws Exception {
        Spell spA = new Spell();
        spA.setCastTime("1 bonus action");
        assertEquals(null, spA.getCastTime(), "1 bonus action");
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");
        assertEquals(null, spB.getCastTime(), "1 action");
    }

    public void testRange() throws Exception {
        Spell spA = new Spell();
        spA.setRange("60 feet");
        assertEquals(null, spA.getRange(), "60 feet");
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");
        assertEquals(null, spB.getRange(), "Touch");
    }

    public void testComponents() throws Exception {
        Spell spA = new Spell();
        spA.setVerbal(true);
        spA.setSomatic(true);
        spA.setMaterial(true);
        spA.setComponents("A firefly encased in sap");
        assertEquals(null, spA.hasVerbal(), true);
        assertEquals(null, spA.hasSomatic(), true);
        assertEquals(null, spA.hasMaterial(), true);
        assertEquals(null, spA.getComponents(), "A firefly encased in sap");
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");
        assertEquals(null, spB.hasVerbal(), true);
        assertEquals(null, spB.hasSomatic(), false);
        assertEquals(null, spB.hasMaterial(), false);
        assertEquals(null, spB.getComponents(), "");
    }

    public void testDuration() throws Exception {
        Spell spA = new Spell();
        spA.setDuration("1 round");
        assertEquals(null, spA.getDuration(), "1 round");
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");
        assertEquals(null, spB.getDuration(), "Instantaneous");
    }

    public void testConcentration() throws Exception {
        Spell spA = new Spell();
        spA.setConcentration(true);
        assertEquals(null, spA.isConcentration(), true);
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");
        assertEquals(null, spB.isConcentration(), false);
    }

    public void testDesc() throws Exception {
        Spell spA = new Spell();
        spA.setDescription("A burning fireball");
        assertEquals(null, spA.getDescription(), "A burning fireball");
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");
        assertEquals(null, spB.getDescription(), "Heal a friend");
    }

    public void testSrc() throws Exception {
        Spell spA = new Spell();
        assertEquals(null, spA.getSource(), "UNKNOWN");
        spA.setSource("Sword Coast Adventurer's Guide");
        assertEquals(null, spA.getSource(), "Sword Coast Adventurer's Guide");
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");
        assertEquals(null, spB.getSource(), "Player's Handbook");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // SPELL LIST TESTS
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void testAddSpell() throws Exception {
        Spell spA = new Spell();
        spA.setName("Fireball");
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");

        SpellList list = new SpellList();
        list.addSpell(spA);
        assertEquals(null, list.getLength(), 1);
        list.addSpell(spB);
        assertEquals(null, list.getLength(), 2);
    }

    public void testAddSpellList() throws Exception {
        Spell spA = new Spell();
        spA.setName("Fireball");
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");
        Spell spC = new Spell();
        spC.setName("Thunderwave");
        Spell spD = new Spell();
        spD.setName("Ray of Frost");

        SpellList list = new SpellList();
        list.addSpell(spA);
        list.addSpell(spB);
        SpellList altList = new SpellList();
        altList.addSpell(spC);
        altList.addSpell(spD);
        assertEquals(null, list.getLength(), 2);
        list.addSpellList(altList);
        assertEquals(null, list.getLength(), 4);
        assertEquals(null, list.getSpellByIndex(2), spC);
        assertEquals(null, list.getSpellByIndex(3), spD);
    }

    public void testGetSetList() throws Exception {
        Spell spA = new Spell();
        spA.setName("Fireball");
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");

        SpellList list = new SpellList();
        ArrayList<Spell> altList = new ArrayList<>();
        altList.add(spA);
        altList.add(spB);
        list.setSpellList(altList);
        assertEquals(null, list.getSpellList(), altList);
    }

    public void testRemove() throws Exception {
        Spell spA = new Spell();
        spA.setName("Fireball");
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");

        SpellList list = new SpellList();
        list.addSpell(spA);
        list.addSpell(spB);
        assertEquals(null, list.getLength(), 2);
        assertEquals(null, list.getSpellByIndex(0), spA);
        list.removeSpell(spA);
        assertEquals(null, list.getLength(), 1);
        assertEquals(null, list.getSpellByIndex(0), spB);
    }

    public void testRemoveList() throws Exception {
        Spell spA = new Spell();
        spA.setName("Fireball");
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");
        Spell spC = new Spell();
        spC.setName("Thunderwave");
        Spell spD = new Spell();
        spD.setName("Ray of Frost");

        SpellList list = new SpellList();
        list.addSpell(spA);
        list.addSpell(spB);
        list.addSpell(spC);
        list.addSpell(spD);
        SpellList altList = new SpellList();
        altList.addSpell(spC);
        altList.addSpell(spD);
        assertEquals(null, list.getLength(), 4);
        list.removeSpellList(altList);
        assertEquals(null, list.getLength(), 2);
    }

    public void testGetSpells() throws Exception {
        Spell spA = new Spell();
        spA.setName("Fireball");
        Spell spB = new Spell("Cure Wounds", 1, "abjuration", "1 action", "Touch",
                true, false, false, "", "Instantaneous", false, "Heal a friend", "Player's Handbook");
        Spell spC = new Spell();
        spC.setName("Thunderwave");
        Spell spD = new Spell();
        spD.setName("Ray of Frost");

        SpellList list = new SpellList();
        list.addSpell(spA);
        list.addSpell(spB);
        list.addSpell(spC);
        list.addSpell(spD);
        assertEquals(null, list.getSpellByIndex(2), spC);
        assertEquals(null, list.getSpellByName("Thunderwave"), spC);
        list.removeSpell(spC);
        assertEquals(null, list.getSpellByIndex(2), spD);
    }
}