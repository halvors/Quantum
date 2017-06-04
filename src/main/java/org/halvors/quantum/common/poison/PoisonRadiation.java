package org.halvors.quantum.common.poison;

import net.minecraft.entity.EntityLivingBase;
import org.halvors.quantum.common.damage.CustomDamageSource;
import org.halvors.quantum.common.potion.CustomPotionEffect;
import org.halvors.quantum.common.potion.PotionRadiation;
import org.halvors.quantum.common.transform.vector.Vector3;

public class PoisonRadiation extends Poison {
    public static final Poison poison = new PoisonRadiation("radiation");
    public static final CustomDamageSource damageSource = new CustomDamageSource("radiation").setDamageBypassesArmor();

    public PoisonRadiation(String name) {
        super(name);
    }

    @Override
    public boolean isEntityProtected(Vector3 emitPosition, EntityLivingBase entity, int amplifier) {
        return emitPosition != null && getAntiPoisonBlockCount(entity.worldObj, emitPosition, new Vector3(entity)) <= amplifier && super.isEntityProtected(emitPosition, entity, amplifier);
    }

    @Override
    protected void doPoisonEntity(Vector3 emitPosition, EntityLivingBase entity, int amplifier) {
        // TODO: Add option to disable poisoning?
        entity.addPotionEffect(new CustomPotionEffect(PotionRadiation.potion.getId(), 300 * (amplifier + 1), amplifier, null));
    }
}
