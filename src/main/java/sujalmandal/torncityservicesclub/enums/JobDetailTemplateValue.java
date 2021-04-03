package sujalmandal.torncityservicesclub.enums;

import lombok.Getter;

@Getter
public enum JobDetailTemplateValue {

    EMPLOYEE("EMPLOYEE", "Employee", "EMPLOYEE_FILTER", "Employee"),

    HOSPITALIZE("HOSPITALIZE", "Hospitalize a player", "HOSPITALIZE_FILTER", "Hospitalizes"),

    PROFILE_WATCH("PROFILE_WATCH", "Profile watching", "PROFILE_WATCH_FILTER", "Profile watches"),

    ITEMS_RUNNER("ITEMS_RUNNER", "Abroad item runner", "ITEMS_RUNNER_FILTER", "Item runners"),

    ATTACK_FACTION("ATTACK_FACTION", "Attack a faction", "ATTACK_FACTION_FILTER", "Faction attacks"),

    TRAINS("TRAINS", "Trains", "TRAINS_FILTER", "Trains"),

    STAT_SPY("STAT_SPY", "Spy someone's stats", "STAT_SPY_FILTER", "Stat spies"),

    ATTACK("ATTACK", "Attack someone", "ATTACK_FILTER", "Attacks"),

    DEFEND_FACTION("DEFEND_FACTION", "Defend a faction", "DEFEND_FACTION_FILTER", "Faction defends"),

    BOUNTY_PLAYER("BOUNTY_PLAYER", "Bounty a player", "BOUNTY_PLAYER_FILTER", "Bounties"),

    BOUNTY_REVEAL("BOUNTY_REVEAL", "Reveal bounty", "BOUNTY_REVEAL_FILTER", "Bounty reveals"),

    DIRTY_BOMB_A_FACTION("DIRTY_BOMB_A_FACTION", "Nuke a faction", "DIRTY_BOMB_A_FACTION_FILTER", "Dirty bombs"),

    RENT("RENT", "Rent", "RENT_FILTER", "Rents"),

    MUG("MUG", "Mug someone", "MUG_FILTER", "Mugs");

    private String formTemplateName;
    private String formTemplateLabel;
    private String filterTemplateName;;
    private String filterTemplateLabel;

    private JobDetailTemplateValue(String formTemplateName, String formTemplateLabel, String filterTemplateName,
	    String filterTemplateLabel) {
	this.formTemplateName = formTemplateName;
	this.formTemplateLabel = formTemplateLabel;
	this.filterTemplateName = filterTemplateName;
	this.filterTemplateLabel = filterTemplateLabel;
    }

}
