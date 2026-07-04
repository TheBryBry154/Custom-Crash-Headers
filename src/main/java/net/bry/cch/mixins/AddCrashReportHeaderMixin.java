package net.bry.cch.mixins;

import net.bry.cch.newheaders.NewCrashReportHeader;
import net.minecraft.CrashReport;
import net.minecraftforge.logging.CrashReportExtender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(CrashReportExtender.class)
public class AddCrashReportHeaderMixin {

    @Inject(method = "addCrashReportHeader", at = @At("HEAD"), remap = false)
    private static void addCrashReportHeader(StringBuilder stringbuilder, CrashReport crashReport, CallbackInfo ci) throws IOException {
        NewCrashReportHeader.getOrCreateYapperFile();
        NewCrashReportHeader.newCrashReportHeader(stringbuilder);
    }
}
