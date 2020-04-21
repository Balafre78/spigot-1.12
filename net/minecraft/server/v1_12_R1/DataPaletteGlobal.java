/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataPaletteGlobal
/*    */   implements DataPalette
/*    */ {
/*    */   public int a(IBlockData paramIBlockData) {
/* 12 */     int i = Block.REGISTRY_ID.getId(paramIBlockData);
/* 13 */     return (i == -1) ? 0 : i;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(int paramInt) {
/* 18 */     IBlockData iBlockData = Block.REGISTRY_ID.fromId(paramInt);
/* 19 */     return (iBlockData == null) ? Blocks.AIR.getBlockData() : iBlockData;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) {
/* 30 */     paramPacketDataSerializer.d(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 35 */     return PacketDataSerializer.a(0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataPaletteGlobal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */