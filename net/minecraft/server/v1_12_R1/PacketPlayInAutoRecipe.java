/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ public class PacketPlayInAutoRecipe
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   private short b;
/*    */   private List<a> c;
/*    */   private List<a> d;
/*    */   
/*    */   public int a() {
/* 29 */     return this.a;
/*    */   }
/*    */   
/*    */   public short b() {
/* 33 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 38 */     this.a = paramPacketDataSerializer.readByte();
/* 39 */     this.b = paramPacketDataSerializer.readShort();
/*    */     
/* 41 */     this.c = c(paramPacketDataSerializer);
/* 42 */     this.d = c(paramPacketDataSerializer);
/*    */   }
/*    */   
/*    */   private List<a> c(PacketDataSerializer paramPacketDataSerializer) {
/* 46 */     short s = paramPacketDataSerializer.readShort();
/* 47 */     ArrayList<a> arrayList = Lists.newArrayListWithCapacity(s);
/* 48 */     for (byte b = 0; b < s; b++) {
/* 49 */       ItemStack itemStack = paramPacketDataSerializer.k();
/* 50 */       byte b1 = paramPacketDataSerializer.readByte();
/* 51 */       byte b2 = paramPacketDataSerializer.readByte();
/* 52 */       arrayList.add(new a(itemStack, b1, b2));
/*    */     } 
/* 54 */     return arrayList;
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 59 */     paramPacketDataSerializer.writeByte(this.a);
/* 60 */     paramPacketDataSerializer.writeShort(this.b);
/*    */     
/* 62 */     a(paramPacketDataSerializer, this.c);
/* 63 */     a(paramPacketDataSerializer, this.d);
/*    */   }
/*    */   
/*    */   private void a(PacketDataSerializer paramPacketDataSerializer, List<a> paramList) {
/* 67 */     paramPacketDataSerializer.writeShort(paramList.size());
/* 68 */     for (a a : paramList) {
/* 69 */       paramPacketDataSerializer.a(a.a);
/* 70 */       paramPacketDataSerializer.writeByte(a.b);
/* 71 */       paramPacketDataSerializer.writeByte(a.c);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 77 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */   
/*    */   public List<a> c() {
/* 81 */     return this.d;
/*    */   }
/*    */   
/*    */   public List<a> d() {
/* 85 */     return this.c;
/*    */   }
/*    */   
/*    */   public static class a {
/*    */     public ItemStack a;
/*    */     public int b;
/*    */     public int c;
/*    */     
/*    */     public a(ItemStack param1ItemStack, int param1Int1, int param1Int2) {
/* 94 */       this.a = param1ItemStack.cloneItemStack();
/* 95 */       this.b = param1Int1;
/* 96 */       this.c = param1Int2;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInAutoRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */