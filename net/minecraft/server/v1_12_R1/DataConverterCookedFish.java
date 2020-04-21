/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterCookedFish
/*    */   implements IDataConverter
/*    */ {
/*  9 */   private static final MinecraftKey a = new MinecraftKey("cooked_fished");
/*    */ 
/*    */   
/*    */   public int a() {
/* 13 */     return 502;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 18 */     if (paramNBTTagCompound.hasKeyOfType("id", 8) && a.equals(new MinecraftKey(paramNBTTagCompound.getString("id")))) {
/* 19 */       paramNBTTagCompound.setString("id", "minecraft:cooked_fish");
/*    */     }
/*    */     
/* 22 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterCookedFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */