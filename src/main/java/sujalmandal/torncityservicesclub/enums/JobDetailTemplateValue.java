package sujalmandal.torncityservicesclub.enums;

import lombok.Getter;

@Getter
public enum JobDetailTemplateValue {

    EMPLOYEE("EMPLOYEE", "Employee", "EMPLOYEE_FILTER", "employee"),

    HOSPITALIZE("HOSPITALIZE", "Hospitalize a player", "HOSPITALIZE_FILTER", "hospitalize"),

    PROFILE_WATCH("PROFILE_WATCH", "Profile watching", "PROFILE_WATCH_FILTER", "profile watch"),

    ITEMS_RUNNER("ITEMS_RUNNER", "Abroad item runner", "ITEMS_RUNNER_FILTER", "item runner"),

    ATTACK_FACTION("ATTACK_FACTION", "Attack a faction", "ATTACK_FACTION_FILTER", "faction attack"),

    TRAINS("TRAINS", "Trains", "TRAINS_FILTER", "train"),

    STAT_SPY("STAT_SPY", "Spy someone's stats", "STAT_SPY_FILTER", "stat spy"),

    ATTACK("ATTACK", "Attack someone", "ATTACK_FILTER", "attack"),

    DEFEND_FACTION("DEFEND_FACTION", "Defend a faction", "DEFEND_FACTION_FILTER", "faction defend"),

    BOUNTY_PLAYER("BOUNTY_PLAYER", "Bounty a player", "BOUNTY_PLAYER_FILTER", "bounty"),

    BOUNTY_REVEAL("BOUNTY_REVEAL", "Reveal bounty", "BOUNTY_REVEAL_FILTER", "bounty reveal"),

    DIRTY_BOMB_A_FACTION("DIRTY_BOMB_A_FACTION", "Nuke a faction", "DIRTY_BOMB_A_FACTION_FILTER", "dirty bomb"),

    RENT("RENT", "Rent", "RENT_FILTER", "rent"),

    MUG("MUG", "Mug someone", "MUG_FILTER", "mug");

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
