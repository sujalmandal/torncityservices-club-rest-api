package sujalmandal.torncityservicesclub.enums;

import lombok.Getter;

@Getter
public enum JobDetailTemplateValue {

    EMPLOYEE("EMPLOYEE", "Employee", "EMPLOYEE_FILTER", "employee"),

    HOSPITALIZE("HOSPITALIZE", "Hospitalize a player", "HOSPITALIZE_FILTER", "hospitalizes"),

    PROFILE_WATCH("PROFILE_WATCH", "Profile watching", "PROFILE_WATCH_FILTER", "profile watches"),

    ITEMS_RUNNER("ITEMS_RUNNER", "Abroad item runner", "ITEMS_RUNNER_FILTER", "item runners"),

    ATTACK_FACTION("ATTACK_FACTION", "Attack a faction", "ATTACK_FACTION_FILTER", "faction attacks"),

    TRAINS("TRAINS", "Trains", "TRAINS_FILTER", "trains"),

    STAT_SPY("STAT_SPY", "Spy someone's stats", "STAT_SPY_FILTER", "stat spies"),

    ATTACK("ATTACK", "Attack someone", "ATTACK_FILTER", "attacks"),

    DEFEND_FACTION("DEFEND_FACTION", "Defend a faction", "DEFEND_FACTION_FILTER", "faction defends"),

    BOUNTY_PLAYER("BOUNTY_PLAYER", "Bounty a player", "BOUNTY_PLAYER_FILTER", "bounties"),

    BOUNTY_REVEAL("BOUNTY_REVEAL", "Reveal bounty", "BOUNTY_REVEAL_FILTER", "bounty reveals"),

    DIRTY_BOMB_A_FACTION("DIRTY_BOMB_A_FACTION", "Nuke a faction", "DIRTY_BOMB_A_FACTION_FILTER", "dirty bombs"),

    RENT("RENT", "Rent", "RENT_FILTER", "rents"),

    MUG("MUG", "Mug someone", "MUG_FILTER", "mugs");

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
