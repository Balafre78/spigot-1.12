/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInSetCreativeSlot
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int slot;
/* 11 */   private ItemStack b = ItemStack.a;
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
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 23 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 28 */     this.slot = paramPacketDataSerializer.readShort();
/* 29 */     this.b = paramPacketDataSerializer.k();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 34 */     paramPacketDataSerializer.writeShort(this.slot);
/* 35 */     paramPacketDataSerializer.a(this.b);
/*    */   }
/*    */   
/*    */   public int a() {
/* 39 */     return this.slot;
/*    */   }
/*    */   
/*    */   public ItemStack getItemStack() {
/* 43 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInSetCreativeSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */