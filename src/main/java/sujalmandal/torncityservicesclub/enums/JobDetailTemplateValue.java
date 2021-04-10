package sujalmandal.torncityservicesclub.enums;

import lombok.Getter;

@Getter
public enum JobDetailTemplateValue {

    // REQUEST : I am looking for + formRequestTypeLabel
    // OFFER : I am offering to + formOfferTypeLabel
    EMPLOYEE(
	    "EMPLOYEE", "Employee/Company", "EMPLOYEE_FILTER", "employee/company", "a company to work in",
	    "hire an employee", "Enter details to search for companies to work in.",
	    "Enter details to search for workers available. "
    ),

    HOSPITALIZE(
	    "HOSPITALIZE", "Hospitalize", "HOSPITALIZE_FILTER", "hospitalization", "hospitalization services",
	    "provide hospitalization services", "", ""
    ),

    PROFILE_WATCH(
	    "PROFILE_WATCH", "Profile watch", "PROFILE_WATCH_FILTER", "profile watch", "profile watching services",
	    "watch profiles", "", ""
    ),

    ITEMS_RUNNER(
	    "ITEMS_RUNNER", "Item runners", "ITEMS_RUNNER_FILTER", "item runners", "item runners", "run items", "", ""
    ),

    ATTACK_FACTION(
	    "ATTACK_FACTION", "Attack a faction", "ATTACK_FACTION_FILTER", "faction attack",
	    "attack on faction services", "attack a faction", "", ""
    ),

    TRAINS(
	    "TRAINS", "Trains", "TRAINS_FILTER", "train", "trains", "sell trains", "", ""
    ),

    STAT_SPY(
	    "STAT_SPY", "Spy someone's stats", "STAT_SPY_FILTER", "stat spy", "stat spy services", "spy stats", "", ""
    ),

    ATTACK(
	    "ATTACK", "Attack someone", "ATTACK_FILTER", "attack", "attack services", "provide attack services", "", ""
    ),

    DEFEND_FACTION(
	    "DEFEND_FACTION", "Defend a faction", "DEFEND_FACTION_FILTER", "faction defend", "faction defend services",
	    "provide faction defend services", "", ""
    ),

    REVIVE_FACTION(
	    "REVIVE_FACTION", "Faction revive contract", "REVIVE_FACTION_FILTER", "faction revive contract",
	    "faction revive contract", "sell faction revive contract", "", ""
    ),

    BOUNTY_PLAYER(
	    "BOUNTY_PLAYER", "Bounty a player", "BOUNTY_PLAYER_FILTER", "bounty", "bounty slots", "sell bounty slots",
	    "", ""
    ),

    BOUNTY_REVEAL(
	    "BOUNTY_REVEAL", "Reveal bounty", "BOUNTY_REVEAL_FILTER", "bounty reveal", "bounty reveals",
	    "sell bounty reveals", "", ""
    ),

    DIRTY_BOMB_A_FACTION(
	    "DIRTY_BOMB_A_FACTION", "Nuke a faction", "DIRTY_BOMB_A_FACTION_FILTER", "dirty bomb",
	    "someone to bomb a faction", "bomb a faction", "", ""
    ),

    RENT(
	    "RENT", "Rent", "RENT_FILTER", "rent", "a property to rent", "rent a property", "", ""
    ),

    MUG(
	    "MUG", "Mug someone", "MUG_FILTER", "mug", "mugging services", "provide mugging services", "", ""
    );

    private String formTemplateName;
    private String formTemplateLabel;
    private String filterTemplateName;;
    private String filterTemplateLabel;
    private String formRequestTypeLabel;
    private String formOfferTypeLabel;
    private String filterRequestTypeLabel;
    private String filterOfferTypeLabel;

    private JobDetailTemplateValue(String formTemplateName, String formTemplateLabel, String filterTemplateName,
	    String filterTemplateLabel, String formRequestTypeLabel, String formOfferTypeLabel,
	    String filterRequestTypeLabel, String filterOfferTypeLabel) {
	this.formTemplateName = formTemplateName;
	this.formTemplateLabel = formTemplateLabel;
	this.filterTemplateName = filterTemplateName;
	this.filterTemplateLabel = filterTemplateLabel;
	this.formRequestTypeLabel = formRequestTypeLabel;
	this.formOfferTypeLabel = formOfferTypeLabel;
	this.filterRequestTypeLabel = filterRequestTypeLabel;
	this.filterOfferTypeLabel = filterOfferTypeLabel;
    }

}
