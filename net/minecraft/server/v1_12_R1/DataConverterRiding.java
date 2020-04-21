/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterRiding
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/* 11 */     return 135;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 16 */     while (paramNBTTagCompound.hasKeyOfType("Riding", 10)) {
/* 17 */       NBTTagCompound nBTTagCompound = b(paramNBTTagCompound);
/* 18 */       a(paramNBTTagCompound, nBTTagCompound);
/* 19 */       paramNBTTagCompound = nBTTagCompound;
/*    */     } 
/*    */     
/* 22 */     return paramNBTTagCompound;
/*    */   }
/*    */   
/*    */   protected void a(NBTTagCompound paramNBTTagCompound1, NBTTagCompound paramNBTTagCompound2) {
/* 26 */     NBTTagList nBTTagList = new NBTTagList();
/* 27 */     nBTTagList.add(paramNBTTagCompound1);
/* 28 */     paramNBTTagCompound2.set("Passengers", nBTTagList);
/*    */   }
/*    */   
/*    */   protected NBTTagCompound b(NBTTagCompound paramNBTTagCompound) {
/* 32 */     NBTTagCompound nBTTagCompound = paramNBTTagCompound.getCompound("Riding");
/* 33 */     paramNBTTagCompound.remove("Riding");
/* 34 */     return nBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterRiding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */