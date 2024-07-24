package org.sashaiolh.iolhcrutches;


import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class ModConfig {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class Common {

        public final ForgeConfigSpec.ConfigValue<String> test;

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> forbidden_arcanus_quantum_catcher_blacklist_entities;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("general");

            test = builder
                    .comment("test")
                    .define("test", "test");

            builder.pop();


            builder.push("forbidden_arcanus");

            forbidden_arcanus_quantum_catcher_blacklist_entities = builder
                    .comment("Quantum Catcher blacklist entities (regex)")
                    .defineList("quantum_catcher_blacklist_entities",
                            List.of("npc", "iolhbosses", "botania:doppleganger"),
                            o -> o instanceof String);

            
            builder.pop();
        }
    }
}

