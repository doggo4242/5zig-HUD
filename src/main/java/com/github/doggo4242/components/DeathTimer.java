/*
   Copyright 2021 doggo4242 Development

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.github.doggo4242.components;

import com.github.doggo4242.HUD5zig;
import com.github.doggo4242.HUDComponent;
import com.github.doggo4242.HUDComponentText;

import java.util.concurrent.TimeUnit;

public class DeathTimer extends HUDComponent {
	public static boolean dead = false;
	public static int[] deathCoords = new int[3];
	public static long deathTime = 0;
	private final static TimeUnit tUnitMs = TimeUnit.MILLISECONDS;
	private final int defVerYPos = 126;
	private final int defHorYPos = 43;

	public DeathTimer(){
		componentImages = null;
		componentText = new HUDComponentText[2];
		for(int i = 0;i<componentText.length;i++){
			componentText[i] = new HUDComponentText();
		}
	}

	@Override
	public void updateComponent() {
		if ((disabled = !(!dead && (System.currentTimeMillis() - deathTime) < tUnitMs.convert(5, TimeUnit.MINUTES)
				&& Math.abs(HUD5zig.settings.get("DeathTimerEnabled")) == 1)))//5m till despawn
		{
			return;
		}
		alignment = Math.abs(HUD5zig.settings.get("DeathTimerAlignment")) == 1;
		//get seconds without minutes
		int sec = (int) ((tUnitMs.convert(5, TimeUnit.MINUTES) -
				(System.currentTimeMillis() - deathTime)) % tUnitMs.convert(1, TimeUnit.MINUTES) /
				tUnitMs.convert(1, TimeUnit.SECONDS));
		int min = (int) ((tUnitMs.convert(5, TimeUnit.MINUTES) - (System.currentTimeMillis() - deathTime)) /
				tUnitMs.convert(1, TimeUnit.MINUTES));
		//set alignment and position
		int x = HUD5zig.settings.get("DeathTimerX");
		int y = HUD5zig.settings.get("DeathTimerY");
		x = (x == -1 || (x + 10 * componentText.length) > scrWidth) ? defXPos : x;
		if (alignment && (y == -1 || (y + 10 * componentText.length) > scrHeight)) {
			y = defVerYPos;
		} else if (!alignment && (y == -1 || y > scrHeight)) {
			y = defHorYPos;
		}
		componentText[0].text = String.format("%sItems despawn in> %s %d:%02d", red, white, min, sec);
		componentText[1].text = String.format("%sDied at> X: %s%d%s Y: %s%d%s Z: %s%d",
				red, white, deathCoords[0], red, white, deathCoords[1], red, white, deathCoords[2]);
		for (HUDComponentText text : componentText) {
			text.x = x;
			text.y = y;
			if (alignment) {
				y += defYSpacing;
			}
		}
	}
}
