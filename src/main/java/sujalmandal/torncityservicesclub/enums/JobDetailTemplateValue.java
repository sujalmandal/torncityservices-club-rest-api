package sujalmandal.torncityservicesclub.enums;

public enum JobDetailTemplateValue {

    EMPLOYEE("Employee", "EMPLOYEE"), HOSPITALIZE("Hospitalize a player", "HOSPITALIZE"),
    PROFILE_WATCH("Profile watching", "PROFILE_WATCH"), ITEMS_RUNNER("Abroad item runner", "ITEMS_RUNNER"),
    ATTACK_FACTION("Attack a faction", "ATTACK_FACTION"), TRAINS("Trains", "TRAINS"),
    STAT_SPY("Spy someone's stats", "STAT_SPY"), ATTACK("Attack someone", "ATTACK"),
    DEFEND_FACTION("Defend a faction", "DEFEND_FACTION"), BOUNTY_PLAYER("Bounty a player", "BOUNTY_PLAYER"),
    BOUNTY_REVEAL("Reveal bounty", "BOUNTY_REVEAL"), DIRTY_BOMB_A_FACTION("Nuke a faction", "DIRTY_BOMB_A_FACTION"),
    RENT("Rent", "RENT"), MUG("Mug someone", "MUG");

    private String label;
    private String key;

    private JobDetailTemplateValue(String label, String key) {
	this.label = label;
	this.key = key;
    }

    public String getLabel() {
	return label;
    }

    public String getKey() {
	return key;
    }

}
