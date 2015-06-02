package com.kodingkingdom.educraft.powers.powers;

import org.bukkit.entity.Player;

import com.kodingkingdom.educraft.powers.Power;

public class SuperSpeedPower extends Power{
	
	public final String getName(){
		return "SuperSpeedPower";}
	
	protected final void doAction(PowerItem powerItem){
		final Player player = powerItem.getUser().getPlayer();
		if (player!=null){
			player.setFlySpeed(getRealMoveSpeed(10F, true, false));
			player.setWalkSpeed(getRealMoveSpeed(10F, false, false));
			player.sendMessage("You have activated the superspeed power!");}}
	protected final void undoAction(PowerItem powerItem){
		final Player player = powerItem.getUser().getPlayer();
		if (player!=null){
			player.setFlySpeed(getRealMoveSpeed(1F, true, false));
			player.setWalkSpeed(getRealMoveSpeed(1F, false, false));
			player.sendMessage("You have deactivated the superspeed power!");}}
	

	private float getRealMoveSpeed(final float userSpeed, final boolean isFly, final boolean isBypass){
		double maxFlySpeed=0.8, maxWalkSpeed=0.8;
		final float defaultSpeed = isFly ? 0.1f : 0.2f;
		float maxSpeed = 1f;
		if (!isBypass){
			maxSpeed = (float)(isFly ? maxFlySpeed : maxWalkSpeed);}

		if (userSpeed < 1f){
			return defaultSpeed * userSpeed;}
		else{
			float ratio = ((userSpeed - 1) / 9) * (maxSpeed - defaultSpeed);
			return ratio + defaultSpeed;}}}
