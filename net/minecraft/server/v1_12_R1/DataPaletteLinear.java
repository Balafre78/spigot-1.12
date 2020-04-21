/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataPaletteLinear
/*    */   implements DataPalette
/*    */ {
/*    */   private final IBlockData[] a;
/*    */   private final DataPaletteExpandable b;
/*    */   private final int c;
/*    */   private int d;
/*    */   
/*    */   public DataPaletteLinear(int paramInt, DataPaletteExpandable paramDataPaletteExpandable) {
/* 16 */     this.a = new IBlockData[1 << paramInt];
/* 17 */     this.c = paramInt;
/* 18 */     this.b = paramDataPaletteExpandable;
/*    */   }
/*    */   
/*    */   public int a(IBlockData paramIBlockData) {
/*    */     int i;
/* 23 */     for (i = 0; i < this.d; i++) {
/* 24 */       if (this.a[i] == paramIBlockData) {
/* 25 */         return i;
/*    */       }
/*    */     } 
/*    */     
/* 29 */     i = this.d;
/* 30 */     if (i < this.a.length) {
/* 31 */       this.a[i] = paramIBlockData;
/* 32 */       this.d++;
/* 33 */       return i;
/*    */     } 
/*    */     
/* 36 */     return this.b.a(this.c + 1, paramIBlockData);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData a(int paramInt) {
/* 42 */     if (paramInt >= 0 && paramInt < this.d) {
/* 43 */       return this.a[paramInt];
/*    */     }
/* 45 */     return null;
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
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) {
/* 58 */     paramPacketDataSerializer.d(this.d);
/* 59 */     for (byte b = 0; b < this.d; b++) {
/* 60 */       paramPacketDataSerializer.d(Block.REGISTRY_ID.getId(this.a[b]));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 66 */     int i = PacketDataSerializer.a(this.d);
/*    */     
/* 68 */     for (byte b = 0; b < this.d; b++) {
/* 69 */       i += PacketDataSerializer.a(Block.REGISTRY_ID.getId(this.a[b]));
/*    */     }
/*    */     
/* 72 */     return i;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataPaletteLinear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */