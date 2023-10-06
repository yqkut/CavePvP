package org.cavepvp.suge.enchant.data.type;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.cavepvp.suge.enchant.data.CustomEnchant;
import org.cavepvp.suge.enchant.data.Tier;

import java.util.Collections;
import java.util.List;

public class GuardianAngelEnchant extends CustomEnchant {

    @Override
    public int getAmplifier() {
        return 1;
    }

    @Override
    public Tier getLevel() {
        return Tier.CAVE;
    }

    @Override
    public PotionEffectType getEffect() {
        return null;
    }

    @Override
    public List<String> getDescription() {
        return Collections.singletonList("When you fall, you take 15% less fall damage.");
    }
    @Override
    public void onEnable(Player player, int level) {
    }

    @Override
    public void onDisable(Player player, int level) {
    }
}
