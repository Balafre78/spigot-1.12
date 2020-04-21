/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterHanging
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/* 11 */     return 111;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 16 */     String str = paramNBTTagCompound.getString("id");
/*    */     
/* 18 */     boolean bool1 = "Painting".equals(str);
/* 19 */     boolean bool2 = "ItemFrame".equals(str);
/*    */     
/* 21 */     if ((bool1 || bool2) && !paramNBTTagCompound.hasKeyOfType("Facing", 99)) {
/*    */       EnumDirection enumDirection;
/* 23 */       if (paramNBTTagCompound.hasKeyOfType("Direction", 99)) {
/* 24 */         enumDirection = EnumDirection.fromType2(paramNBTTagCompound.getByte("Direction"));
/*    */         
/* 26 */         paramNBTTagCompound.setInt("TileX", paramNBTTagCompound.getInt("TileX") + enumDirection.getAdjacentX());
/* 27 */         paramNBTTagCompound.setInt("TileY", paramNBTTagCompound.getInt("TileY") + enumDirection.getAdjacentY());
/* 28 */         paramNBTTagCompound.setInt("TileZ", paramNBTTagCompound.getInt("TileZ") + enumDirection.getAdjacentZ());
/*    */         
/* 30 */         paramNBTTagCompound.remove("Direction");
/*    */         
/* 32 */         if (bool2 && paramNBTTagCompound.hasKeyOfType("ItemRotation", 99)) {
/* 33 */           paramNBTTagCompound.setByte("ItemRotation", (byte)(paramNBTTagCompound.getByte("ItemRotation") * 2));
/*    */         }
/*    */       } else {
/* 36 */         enumDirection = EnumDirection.fromType2(paramNBTTagCompound.getByte("Dir"));
/* 37 */         paramNBTTagCompound.remove("Dir");
/*    */       } 
/* 39 */       paramNBTTagCompound.setByte("Facing", (byte)enumDirection.get2DRotationValue());
/*    */     } 
/*    */     
/* 42 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterHanging.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */