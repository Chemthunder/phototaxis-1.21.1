package silly.chemthunder.phototaxis.common.util;

import eu.midnightdust.lib.config.MidnightConfig;

public class PhototaxisConfig extends MidnightConfig {
    private static final String config = "config";
    private static final String debug = "debug";

    @Entry(category = config)
    public static boolean applyParticles = true;

    @Entry(category = debug)
    public static boolean foglampVeilLight = false;

    @Entry(category = debug)
    public static boolean showMothNames = true;
}
