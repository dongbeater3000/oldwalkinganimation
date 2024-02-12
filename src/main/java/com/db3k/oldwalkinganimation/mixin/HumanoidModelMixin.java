package com.db3k.oldwalkinganimation.mixin;

import com.db3k.oldwalkinganimation.Config;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public class HumanoidModelMixin {

    @Shadow public ModelPart rightArm;
    @Shadow public ModelPart leftArm;
    @Shadow public ModelPart rightLeg;
    @Shadow public ModelPart leftLeg;

    @Inject(method="setupAnim", at=@At(value = "FIELD",
            target = "Lnet/minecraft/client/model/geom/ModelPart;zRot:F",
            ordinal = 1,
            shift = At.Shift.AFTER))
    private void setAngles(LivingEntity livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
        if (Config.enableMod) {
            if (livingEntity instanceof Player || Config.moreMobs) {
                this.rightArm.xRot = Mth.cos(f * 0.6662F + 3.1415927F) * 2.0F * g;
                this.leftArm.xRot = Mth.cos(f * 0.6662F) * 2.0F * g;
                this.rightArm.zRot = (Mth.cos(f * 0.2312F) + 1.0F) * 1.0F * g;
                this.leftArm.zRot = (Mth.cos(f * 0.2812F) - 1.0F) * 1.0F * g;
            }
       }
    }
}


