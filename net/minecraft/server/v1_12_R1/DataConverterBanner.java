/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterBanner
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/* 11 */     return 804;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 16 */     if ("minecraft:banner".equals(paramNBTTagCompound.getString("id")))
/*    */     {
/* 18 */       if (paramNBTTagCompound.hasKeyOfType("tag", 10)) {
/* 19 */         NBTTagCompound nBTTagCompound = paramNBTTagCompound.getCompound("tag");
/* 20 */         if (nBTTagCompound.hasKeyOfType("BlockEntityTag", 10)) {
/* 21 */           NBTTagCompound nBTTagCompound1 = nBTTagCompound.getCompound("BlockEntityTag");
/* 22 */           if (nBTTagCompound1.hasKeyOfType("Base", 99)) {
/*    */             
/* 24 */             paramNBTTagCompound.setShort("Damage", (short)(nBTTagCompound1.getShort("Base") & 0xF));
/*    */ 
/*    */             
/* 27 */             if (nBTTagCompound.hasKeyOfType("display", 10)) {
/* 28 */               NBTTagCompound nBTTagCompound2 = nBTTagCompound.getCompound("display");
/* 29 */               if (nBTTagCompound2.hasKeyOfType("Lore", 9)) {
/* 30 */                 NBTTagList nBTTagList = nBTTagCompound2.getList("Lore", 8);
/* 31 */                 if (nBTTagList.size() == 1 && "(+NBT)".equals(nBTTagList.getString(0))) {
/* 32 */                   return paramNBTTagCompound;
/*    */                 }
/*    */               } 
/*    */             } 
/*    */ 
/*    */             
/* 38 */             nBTTagCompound1.remove("Base");
/* 39 */             if (nBTTagCompound1.isEmpty()) {
/* 40 */               nBTTagCompound.remove("BlockEntityTag");
/*    */             }
/* 42 */             if (nBTTagCompound.isEmpty()) {
/* 43 */               paramNBTTagCompound.remove("tag");
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     }
/*    */     
/* 50 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */