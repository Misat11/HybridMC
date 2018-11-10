package com.nukkitx.server;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nukkitx.server.level.manager.LevelPaletteManager;

import lombok.Getter;

public class NukkitServer {
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	
	@Getter
    private static final LevelPaletteManager paletteManager = new LevelPaletteManager();
	
	public static final String MINECRAFT_VERSION = "1.7.0";
}
