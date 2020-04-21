/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemTool
/*    */   extends Item
/*    */ {
/*    */   private final Set<Block> e;
/* 17 */   protected float a = 4.0F;
/*    */   
/*    */   protected float b;
/*    */   protected float c;
/*    */   protected Item.EnumToolMaterial d;
/*    */   
/*    */   protected ItemTool(float paramFloat1, float paramFloat2, Item.EnumToolMaterial paramEnumToolMaterial, Set<Block> paramSet) {
/* 24 */     this.d = paramEnumToolMaterial;
/* 25 */     this.e = paramSet;
/* 26 */     this.maxStackSize = 1;
/* 27 */     setMaxDurability(paramEnumToolMaterial.a());
/* 28 */     this.a = paramEnumToolMaterial.b();
/* 29 */     this.b = paramFloat1 + paramEnumToolMaterial.c();
/* 30 */     this.c = paramFloat2;
/* 31 */     b(CreativeModeTab.i);
/*    */   }
/*    */   
/*    */   protected ItemTool(Item.EnumToolMaterial paramEnumToolMaterial, Set<Block> paramSet) {
/* 35 */     this(0.0F, 0.0F, paramEnumToolMaterial, paramSet);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDestroySpeed(ItemStack paramItemStack, IBlockData paramIBlockData) {
/* 40 */     return this.e.contains(paramIBlockData.getBlock()) ? this.a : 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving1, EntityLiving paramEntityLiving2) {
/* 45 */     paramItemStack.damage(2, paramEntityLiving2);
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack paramItemStack, World paramWorld, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EntityLiving paramEntityLiving) {
/* 52 */     if (!paramWorld.isClientSide && paramIBlockData.b(paramWorld, paramBlockPosition) != 0.0D) {
/* 53 */       paramItemStack.damage(1, paramEntityLiving);
/*    */     }
/* 55 */     return true;
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
/*    */   public int c() {
/* 69 */     return this.d.e();
/*    */   }
/*    */   
/*    */   public String h() {
/* 73 */     return this.d.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack paramItemStack1, ItemStack paramItemStack2) {
/* 78 */     if (this.d.f() == paramItemStack2.getItem()) {
/* 79 */       return true;
/*    */     }
/* 81 */     return super.a(paramItemStack1, paramItemStack2);
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<String, AttributeModifier> a(EnumItemSlot paramEnumItemSlot) {
/* 86 */     Multimap<String, AttributeModifier> multimap = super.a(paramEnumItemSlot);
/*    */ 
/*    */     
/* 89 */     if (paramEnumItemSlot == EnumItemSlot.MAINHAND) {
/* 90 */       multimap.put(GenericAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(h, "Tool modifier", this.b, 0));
/* 91 */       multimap.put(GenericAttributes.g.getName(), new AttributeModifier(i, "Tool modifier", this.c, 0));
/*    */     } 
/*    */     
/* 94 */     return multimap;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */