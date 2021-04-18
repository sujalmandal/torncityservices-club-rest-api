package sujalmandal.torncityservicesclub.constants;

import lombok.Getter;

@Getter
public enum TemplateValue {

    HIRE_OFFER(
	    "HIRE_OFFER", "Offer employment in your company", "Search job offers", ServiceTypeValue.OFFER,
	    MoneyFieldType.COST
    ),

    JOB_REQUEST(
	    "JOB_REQUEST", "Request for work in a company", "Search for people looking for jobs",
	    ServiceTypeValue.REQUEST, MoneyFieldType.PAY
    ),

    HOSPITALIZE_REQUEST(
	    "HOSPITALIZE_REQUEST", "Request for hospitalization of your target",
	    "Search people requesting hospitalization services", ServiceTypeValue.REQUEST, MoneyFieldType.PAY
    ),

    HOSPITALIZE_OFFER(
	    "HOSPITALIZE_OFFER", "Offer hospitalization services", "Search people offering hospitalization services",
	    ServiceTypeValue.OFFER, MoneyFieldType.COST
    ),

    PROFILE_WATCH_REQUEST(
	    "PROFILE_WATCH_REQUEST", "Request for someone to watch your profile",
	    "Search for people requesting for profile watching services", ServiceTypeValue.REQUEST, MoneyFieldType.PAY
    ),

    PROFILE_WATCH_OFFER(
	    "PROFILE_WATCH_OFFER", "Offer profile watching services",
	    "Search for people offering profile watching services", ServiceTypeValue.OFFER, MoneyFieldType.COST
    ),

    ITEMS_RUN_REQUEST(
	    "ITEMS_RUN_REQUEST", "Request for someone to run items for you",
	    "Search for people looking for item runners", ServiceTypeValue.REQUEST, MoneyFieldType.COST
    ),

    ITEMS_RUN_OFFER(
	    "ITEMS_RUN_OFFER", "Offer to run items for clients", "Search for people willing to run items",
	    ServiceTypeValue.OFFER, MoneyFieldType.PAY
    ),

    TRAINS_OFFER(
	    "TRAINS_OFFER", "Offer to sell trains", "Search for people offering to sell trains", ServiceTypeValue.OFFER,
	    MoneyFieldType.PAY
    ),

    TRAINS_REQUEST(
	    "TRAINS_REQUEST", "Offer to sell trains", "Search for people selling trains", ServiceTypeValue.REQUEST,
	    MoneyFieldType.COST
    ),

    REVIVE_CONTRACT_REQUEST(
	    "REVIVE_CONTRACT_REQUEST", "Request for a revive contract for your faction",
	    "Search for people requesting revive contracts for faction", ServiceTypeValue.REQUEST, MoneyFieldType.COST
    ),

    REVIVE_CONTRACT_OFFER(
	    "REVIVE_CONTRACT_OFFER", "Offer revive contract for factions",
	    "Search for people offering revive contracts for faction", ServiceTypeValue.OFFER, MoneyFieldType.PAY
    ),

    STAT_SPY_REQUEST(
	    "STAT_SPY_REQUEST", "Request for spying the stats of your target",
	    "Search for people willing to hire stat spies", ServiceTypeValue.REQUEST, MoneyFieldType.COST
    ),

    STAT_SPY_OFFER(
	    "STAT_SPY_OFFER", "Offer to sell stat spies", "Search for people selling stat spies",
	    ServiceTypeValue.OFFER, MoneyFieldType.PAY
    ),

    BOUNTY_REVEAL_REQUEST(
	    "BOUNTY_REVEAL_REQUEST", "Request for bounty reveal services",
	    "Search for people requesting bounty reveals", ServiceTypeValue.REQUEST, MoneyFieldType.COST
    ),

    BOUNTY_REVEAL_OFFER(
	    "BOUNTY_REVEAL_OFFER", "Offer to sell bounty reveal services", "Search for selling bounty reveals",
	    ServiceTypeValue.OFFER, MoneyFieldType.PAY
    ),

    BOUNTY_PLAYER_REQUEST(
	    "BOUNTY_PLAYER_REQUEST", "Request to buy bounty slots", "Search for people requesting to buy bounty slots",
	    ServiceTypeValue.REQUEST, MoneyFieldType.COST
    ),

    BOUNTY_PLAYER_OFFER(
	    "BOUNTY_PLAYER_OFFER", "Offer to sell bounty slots", "Search for people selling bounty slots",
	    ServiceTypeValue.OFFER, MoneyFieldType.PAY
    ),

    LOSSES_REQUEST(
	    "LOSSES_REQUEST", "Buy losses from other players", "Search for people requesting to buy losses",
	    ServiceTypeValue.REQUEST, MoneyFieldType.COST
    ),

    LOSSES_OFFER(
	    "LOSSES_OFFER", "Sell losses to other players", "Search for people selling losses",
	    ServiceTypeValue.REQUEST, MoneyFieldType.PAY
    ),

    ESCAPES_REQUEST(
	    "ESCAPES_REQUEST", "Buy losses from other players", "Search for people requesting to buy losses",
	    ServiceTypeValue.REQUEST, MoneyFieldType.COST
    ),

    ESCAPES_OFFER(
	    "ESCAPES_OFFER", "Sell losses to other players", "Search for people selling losses",
	    ServiceTypeValue.REQUEST, MoneyFieldType.PAY
    );

    private String templateName;
    private String formLabel;
    private String filterLabel;
    private ServiceTypeValue serviceType;
    private MoneyFieldType moneyFieldType;

    private TemplateValue(String templateName, String formLabel, String filterLabel, ServiceTypeValue serviceType,
	    MoneyFieldType moneyFieldType) {
	this.templateName = templateName;
	this.formLabel = formLabel;
	this.filterLabel = filterLabel;
	this.serviceType = serviceType;
	this.moneyFieldType = moneyFieldType;
    }

}