package com.enderzombi102.jython;

import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.python.core.PySystemState;

import java.util.Properties;

public final class Jython {

	private static final Logger LOGGER = LogManager.getLogger("Jython");
	private static boolean hasBeenInitialized = false;

	/**
	 * 
	 * @return true if the system is initialized
	 */
	public static boolean isSystemInitialized() {
		return hasBeenInitialized;
	}

	/**
	 * Initialize the python system, if it was already initialized, do nothing
	 * This MUST always be called ONCE when you're initializing.
	 * The initialization is a long process, so its done in another thread to keep the game from freezing
	 */
	public static void initSystem() {
		if ( hasBeenInitialized ) return;
		new Thread( () -> {
			try {
				LOGGER.info("[Jython] Initializing system...");
				Properties preProperties = System.getProperties();
				Properties postProperties = new Properties();
				preProperties.put("python.home", FabricLoader.getInstance().getGameDir().toString());
				preProperties.put(
						"python.path",
						FabricLoader.getInstance().getModContainer("jython").get().getRootPath().toString() + "\\StdLib"
				);
				PySystemState.initialize(
						preProperties,
						postProperties,
						new String[]{ "" }
				);
				hasBeenInitialized = true;
				LOGGER.info("[Jython] System initialized!");
			} catch (Exception e) {
				LOGGER.error("[Jython] An error occured while initializing system: ", e);
			}
		}).start();
	}


}
