/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterSaddle
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/* 10 */     return 110;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 15 */     if ("EntityHorse".equals(paramNBTTagCompound.getString("id")) && 
/* 16 */       !paramNBTTagCompound.hasKeyOfType("SaddleItem", 10) && paramNBTTagCompound.getBoolean("Saddle")) {
/* 17 */       NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 18 */       nBTTagCompound.setString("id", "minecraft:saddle");
/* 19 */       nBTTagCompound.setByte("Count", (byte)1);
/* 20 */       nBTTagCompound.setShort("Damage", (short)0);
/*    */       
/* 22 */       paramNBTTagCompound.set("SaddleItem", nBTTagCompound);
/* 23 */       paramNBTTagCompound.remove("Saddle");
/*    */     } 
/*    */     
/* 26 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterSaddle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */