package me.bobcatsss.trampolines.utils;

public enum Messages {
	
	NO_PERMISSION ("&cYou don't have permission to use this command."),
	BETWEEN_WORLDS (Utils.prefix + "&f{trampoline_id} &ccannot be made between two worlds."),
	TRAMPOLINE_TOO_BIG (Utils.prefix + "&f{trampoline_id} &ccannot be created, the selection is too big"),
	TRAMPOLINE_CREATED (Utils.prefix + "&f{trampoline_id} &7has been successfully created."),
	CREATING_TRAMPOLINE (Utils.prefix + "&7Now in the Trampoline creating stage&f, &7please select one corner of the trampoline by left clicking."),
	POS_ONE_REQUIRED (Utils.prefix + "&7Position one is required before position two can be set&f, &7please left click the first corner where you wish to create this trampoline."),
	POS_ONE_SET (Utils.prefix + "&7Position one has been set&f, &7please right click the opposite corner to set position two."),
	POS_TWO_SET (Utils.prefix + "&7Position two has been set&f."),
	TRAMPOLINE_REMOVED (Utils.prefix + "&7The trampoline &f{trampoline_id} &7has been removed."),
	INVALID_TRAMPOLINE (Utils.prefix + "&cNo trampoline found with the id of&f: {trampoline_id}"),
	NO_CREATING (Utils.prefix + "&cYou don't seem to be creating a trampoline at the moment."),
	CANCELLED_CREATION (Utils.prefix + "&7Cancelled the trampoline creation."),
	INVALID_COMMAND (Utils.prefix + "&cUnknown command&f, &7please use &f/trampoline help");
	
	private String message;
	
	public String getValue() {
		return Utils.c(message);
	}
	
	public String getValue(String replace, String replacment) {
		return Utils.c(message.replace(replace, replacment));
	}
	
	private Messages(String type) {
		this.message = type;
	}
}
