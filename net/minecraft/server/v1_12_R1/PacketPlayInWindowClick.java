/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInWindowClick
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   private int slot;
/*    */   private int button;
/*    */   private short d;
/* 15 */   private ItemStack item = ItemStack.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private InventoryClickType shift;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 33 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 38 */     this.a = paramPacketDataSerializer.readByte();
/* 39 */     this.slot = paramPacketDataSerializer.readShort();
/* 40 */     this.button = paramPacketDataSerializer.readByte();
/* 41 */     this.d = paramPacketDataSerializer.readShort();
/* 42 */     this.shift = paramPacketDataSerializer.<InventoryClickType>a(InventoryClickType.class);
/*    */     
/* 44 */     this.item = paramPacketDataSerializer.k();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 49 */     paramPacketDataSerializer.writeByte(this.a);
/* 50 */     paramPacketDataSerializer.writeShort(this.slot);
/* 51 */     paramPacketDataSerializer.writeByte(this.button);
/* 52 */     paramPacketDataSerializer.writeShort(this.d);
/* 53 */     paramPacketDataSerializer.a(this.shift);
/*    */     
/* 55 */     paramPacketDataSerializer.a(this.item);
/*    */   }
/*    */   
/*    */   public int a() {
/* 59 */     return this.a;
/*    */   }
/*    */   
/*    */   public int b() {
/* 63 */     return this.slot;
/*    */   }
/*    */   
/*    */   public int c() {
/* 67 */     return this.button;
/*    */   }
/*    */   
/*    */   public short d() {
/* 71 */     return this.d;
/*    */   }
/*    */   
/*    */   public ItemStack e() {
/* 75 */     return this.item;
/*    */   }
/*    */   
/*    */   public InventoryClickType f() {
/* 79 */     return this.shift;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInWindowClick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */