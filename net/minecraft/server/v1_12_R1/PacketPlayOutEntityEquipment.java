/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutEntityEquipment
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private EnumItemSlot b;
/* 13 */   private ItemStack c = ItemStack.a;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketPlayOutEntityEquipment(int paramInt, EnumItemSlot paramEnumItemSlot, ItemStack paramItemStack) {
/* 19 */     this.a = paramInt;
/* 20 */     this.b = paramEnumItemSlot;
/* 21 */     this.c = paramItemStack.cloneItemStack();
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 26 */     this.a = paramPacketDataSerializer.g();
/* 27 */     this.b = paramPacketDataSerializer.<EnumItemSlot>a(EnumItemSlot.class);
/* 28 */     this.c = paramPacketDataSerializer.k();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 33 */     paramPacketDataSerializer.d(this.a);
/* 34 */     paramPacketDataSerializer.a(this.b);
/* 35 */     paramPacketDataSerializer.a(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 40 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */   
/*    */   public PacketPlayOutEntityEquipment() {}
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutEntityEquipment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */