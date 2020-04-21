/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataPaletteHash
/*    */   implements DataPalette
/*    */ {
/*    */   private final RegistryID<IBlockData> a;
/*    */   private final DataPaletteExpandable b;
/*    */   private final int c;
/*    */   
/*    */   public DataPaletteHash(int paramInt, DataPaletteExpandable paramDataPaletteExpandable) {
/* 16 */     this.c = paramInt;
/* 17 */     this.b = paramDataPaletteExpandable;
/* 18 */     this.a = new RegistryID<>(1 << paramInt);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(IBlockData paramIBlockData) {
/* 23 */     int i = this.a.getId(paramIBlockData);
/* 24 */     if (i == -1) {
/* 25 */       i = this.a.c(paramIBlockData);
/*    */       
/* 27 */       if (i >= 1 << this.c) {
/* 28 */         i = this.b.a(this.c + 1, paramIBlockData);
/*    */       }
/*    */     } 
/* 31 */     return i;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData a(int paramInt) {
/* 37 */     return this.a.fromId(paramInt);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) {
/* 51 */     int i = this.a.b();
/* 52 */     paramPacketDataSerializer.d(i);
/*    */     
/* 54 */     for (byte b = 0; b < i; b++) {
/* 55 */       paramPacketDataSerializer.d(Block.REGISTRY_ID.getId(this.a.fromId(b)));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 61 */     int i = PacketDataSerializer.a(this.a.b());
/*    */     
/* 63 */     for (byte b = 0; b < this.a.b(); b++) {
/* 64 */       i += PacketDataSerializer.a(Block.REGISTRY_ID.getId(this.a.fromId(b)));
/*    */     }
/*    */     
/* 67 */     return i;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataPaletteHash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */