package com.github.doggo4242;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * @TODO add custom colors, options to show/hide data in config file
 */

public class HUD{
	private final Minecraft mc = Minecraft.getInstance();

	/**
	 * Overlay triggers upon render event
	 * @param event Contains render event info
	 */
	@SubscribeEvent
	public void overlay(RenderGameOverlayEvent.Post event){
//		int width = this.mc.getMainWindow().getScaledWidth();
//		int height = this.mc.getMainWindow().getScaledHeight();
		FontRenderer renderer = mc.fontRenderer;
		String red = TextFormatting.RED.toString();
		//red = TextFormatting.fromColorIndex(15).toString();
		String white = TextFormatting.WHITE.toString();
		String gray = TextFormatting.GRAY.toString();
		int posX = (int)mc.player.lastTickPosX;//getting positions
		int posY = (int)mc.player.lastTickPosY;
		int posZ = (int)mc.player.lastTickPosZ;
		int dir = (int)mc.player.rotationYaw;//getting direction
		dir+=(dir<0)?382:22;
		dir%=360;
		dir/=45;
		//direction formatting
		//will fix intermediate directions later
		//String[] face = {"S "+gray+"+Z","SW "+gray+"-X +Z","W "+gray+"-X","NW "+gray+"-X -Z","N "+gray+"-Z","NE "+gray+"+X -Z","E "+gray+"+X","SE "+gray+"+X +Z"};
		//System.out.println(dir);
		String tx = red+"X"+white+"> "+posX;//formatting stuff
		String ty = red+"Y"+white+"> "+posY;
		String tz = red+"Z"+white+"> "+posZ;
		String tfps = red+"FPS"+white+"> "+Minecraft.debugFPS;
		String tface = red+"F"+white+"> ";
		//why the hardcoded array didn't work, i dont know. at least this does.
		switch (dir)
		{
			case 0:
				tface += "S "+gray+"+Z";
				break;
			case 1:
				tface += "SW "+gray+"-X +Z";
				break;
			case 2:
				tface += "W "+gray+"-X";
				break;
			case 3:
				tface += "NW "+gray+"-X -Z";
				break;
			case 4:
				tface += "N "+gray+"-Z";
				break;
			case 5:
				tface += "NE "+gray+"+X -Z";
				break;
			case 6:
				tface += "E "+gray+"+X";
				break;
			case 7:
				tface += "SE "+gray+"+X +Z";
				break;
			default:
				tface = "";
				break;
		}
		renderer.drawStringWithShadow(event.getMatrixStack(),tx,3,3,0xFFFFFF);//display the text
		renderer.drawStringWithShadow(event.getMatrixStack(),ty,3,13,0xFFFFFF);
		renderer.drawStringWithShadow(event.getMatrixStack(),tz,3,23,0xFFFFFF);
		renderer.drawStringWithShadow(event.getMatrixStack(),tfps,3,33,0xFFFFFF);
		renderer.drawStringWithShadow(event.getMatrixStack(),tface,3,43,0xFFFFFF);
	}

}
