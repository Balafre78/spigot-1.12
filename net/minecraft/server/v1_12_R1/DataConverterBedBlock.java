/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterBedBlock
/*    */   implements IDataConverter
/*    */ {
/* 15 */   private static final Logger a = LogManager.getLogger();
/*    */ 
/*    */ 
/*    */   
/*    */   public int a() {
/* 20 */     return 1125;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 26 */     char c = 'Ơ';
/*    */     try {
/* 28 */       NBTTagCompound nBTTagCompound = paramNBTTagCompound.getCompound("Level");
/* 29 */       int i = nBTTagCompound.getInt("xPos");
/* 30 */       int j = nBTTagCompound.getInt("zPos");
/*    */       
/* 32 */       NBTTagList nBTTagList1 = nBTTagCompound.getList("TileEntities", 10);
/* 33 */       NBTTagList nBTTagList2 = nBTTagCompound.getList("Sections", 10);
/* 34 */       for (byte b = 0; b < nBTTagList2.size(); b++) {
/* 35 */         NBTTagCompound nBTTagCompound1 = nBTTagList2.get(b);
/* 36 */         byte b1 = nBTTagCompound1.getByte("Y");
/* 37 */         byte[] arrayOfByte = nBTTagCompound1.getByteArray("Blocks");
/* 38 */         for (byte b2 = 0; b2 < arrayOfByte.length; b2++) {
/* 39 */           if (416 == (arrayOfByte[b2] & 0xFF) << 4) {
/* 40 */             int k = b2 & 0xF;
/* 41 */             int m = b2 >> 8 & 0xF;
/* 42 */             int n = b2 >> 4 & 0xF;
/*    */             
/* 44 */             NBTTagCompound nBTTagCompound2 = new NBTTagCompound();
/* 45 */             nBTTagCompound2.setString("id", "bed");
/* 46 */             nBTTagCompound2.setInt("x", k + (i << 4));
/* 47 */             nBTTagCompound2.setInt("y", m + (b1 << 4));
/* 48 */             nBTTagCompound2.setInt("z", n + (j << 4));
/* 49 */             nBTTagList1.add(nBTTagCompound2);
/*    */           } 
/*    */         } 
/*    */       } 
/* 53 */     } catch (Exception exception) {
/* 54 */       a.warn("Unable to datafix Bed blocks, level format may be missing tags.");
/*    */     } 
/* 56 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterBedBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */