/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Multimap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemSword
/*     */   extends Item
/*     */ {
/*     */   private final float a;
/*     */   private final Item.EnumToolMaterial b;
/*     */   
/*     */   public ItemSword(Item.EnumToolMaterial paramEnumToolMaterial) {
/*  20 */     this.b = paramEnumToolMaterial;
/*  21 */     this.maxStackSize = 1;
/*  22 */     setMaxDurability(paramEnumToolMaterial.a());
/*  23 */     b(CreativeModeTab.j);
/*     */     
/*  25 */     this.a = 3.0F + paramEnumToolMaterial.c();
/*     */   }
/*     */   
/*     */   public float g() {
/*  29 */     return this.b.c();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDestroySpeed(ItemStack paramItemStack, IBlockData paramIBlockData) {
/*  34 */     Block block = paramIBlockData.getBlock();
/*  35 */     if (block == Blocks.WEB)
/*     */     {
/*  37 */       return 15.0F;
/*     */     }
/*     */ 
/*     */     
/*  41 */     Material material = paramIBlockData.getMaterial();
/*  42 */     if (material == Material.PLANT || material == Material.REPLACEABLE_PLANT || material == Material.CORAL || material == Material.LEAVES || material == Material.PUMPKIN) {
/*  43 */       return 1.5F;
/*     */     }
/*  45 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving1, EntityLiving paramEntityLiving2) {
/*  50 */     paramItemStack.damage(1, paramEntityLiving2);
/*  51 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(ItemStack paramItemStack, World paramWorld, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EntityLiving paramEntityLiving) {
/*  57 */     if (paramIBlockData.b(paramWorld, paramBlockPosition) != 0.0D) {
/*  58 */       paramItemStack.damage(2, paramEntityLiving);
/*     */     }
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDestroySpecialBlock(IBlockData paramIBlockData) {
/*  70 */     return (paramIBlockData.getBlock() == Blocks.WEB);
/*     */   }
/*     */ 
/*     */   
/*     */   public int c() {
/*  75 */     return this.b.e();
/*     */   }
/*     */   
/*     */   public String h() {
/*  79 */     return this.b.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(ItemStack paramItemStack1, ItemStack paramItemStack2) {
/*  84 */     if (this.b.f() == paramItemStack2.getItem()) {
/*  85 */       return true;
/*     */     }
/*  87 */     return super.a(paramItemStack1, paramItemStack2);
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap<String, AttributeModifier> a(EnumItemSlot paramEnumItemSlot) {
/*  92 */     Multimap<String, AttributeModifier> multimap = super.a(paramEnumItemSlot);
/*     */ 
/*     */     
/*  95 */     if (paramEnumItemSlot == EnumItemSlot.MAINHAND) {
/*  96 */       multimap.put(GenericAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(h, "Weapon modifier", this.a, 0));
/*  97 */       multimap.put(GenericAttributes.g.getName(), new AttributeModifier(i, "Weapon modifier", -2.4000000953674316D, 0));
/*     */     } 
/*     */     
/* 100 */     return multimap;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */