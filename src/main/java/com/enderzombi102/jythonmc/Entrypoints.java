package com.enderzombi102.jythonmc;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.fabricmc.loader.api.metadata.CustomValue;

import static com.enderzombi102.jythonmc.Jython.LOGGER;

public class Entrypoints implements PreLaunchEntrypoint {
	@Override
	public void onPreLaunch() {
		// init the system if there's any mods that use jython
		boolean initSystem = FabricLoader.getInstance().getAllMods().stream().anyMatch( mod -> {
			CustomValue useJython = mod.getMetadata().getCustomValue("jythonmc:use");
			return useJython != null && useJython.getAsBoolean();
		});
		if ( initSystem ) {
			LOGGER.info("[JythonMC] Someone is using me! Lets start the system!");
			Jython.initSystem();
		}
	}
}
