/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterGuardian
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/*  9 */     return 700;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 14 */     if ("Guardian".equals(paramNBTTagCompound.getString("id"))) {
/* 15 */       if (paramNBTTagCompound.getBoolean("Elder")) {
/* 16 */         paramNBTTagCompound.setString("id", "ElderGuardian");
/*    */       }
/* 18 */       paramNBTTagCompound.remove("Elder");
/*    */     } 
/* 20 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */