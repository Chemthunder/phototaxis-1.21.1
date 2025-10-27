package silly.chemthunder.phototaxis.index;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import silly.chemthunder.phototaxis.Phototaxis;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public interface PhototaxisEnchantments {
    Map<ComponentType<?>, Identifier> ENCHANTMENT_EFFECTS = new LinkedHashMap<>();

    ComponentType<Unit> CARYATID = create("caryatid", builder -> builder.codec(Unit.CODEC));
//    ComponentType<Unit> AXIS = create("axis", builder -> builder.codec(Unit.CODEC));
//    ComponentType<Unit> ENVOY = create("envoy", builder -> builder.codec(Unit.CODEC));

    private static <T> ComponentType<T> create(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        ComponentType<T> componentType = builderOperator.apply(ComponentType.builder()).build();
        ENCHANTMENT_EFFECTS.put(componentType, Phototaxis.id(id));
        return componentType;
    }

    static void initialize() {
        ENCHANTMENT_EFFECTS.keySet().forEach(effect -> {
            Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, ENCHANTMENT_EFFECTS.get(effect), effect);
        });
    }
}
