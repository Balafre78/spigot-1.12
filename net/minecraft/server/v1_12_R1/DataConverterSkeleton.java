/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterSkeleton
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/*  9 */     return 701;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 14 */     String str = paramNBTTagCompound.getString("id");
/* 15 */     if ("Skeleton".equals(str)) {
/* 16 */       int i = paramNBTTagCompound.getInt("SkeletonType");
/* 17 */       if (i == 1) {
/* 18 */         paramNBTTagCompound.setString("id", "WitherSkeleton");
/* 19 */       } else if (i == 2) {
/* 20 */         paramNBTTagCompound.setString("id", "Stray");
/*    */       } 
/*    */       
/* 23 */       paramNBTTagCompound.remove("SkeletonType");
/*    */     } 
/* 25 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */