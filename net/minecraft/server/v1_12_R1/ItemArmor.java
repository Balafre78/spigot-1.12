/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicates;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockDispenseEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class ItemArmor extends Item {
/*  15 */   private static final int[] n = new int[] { 13, 15, 16, 11 };
/*  16 */   private static final UUID[] o = new UUID[] { UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150") };
/*  17 */   public static final String[] a = new String[] { "minecraft:items/empty_armor_slot_boots", "minecraft:items/empty_armor_slot_leggings", "minecraft:items/empty_armor_slot_chestplate", "minecraft:items/empty_armor_slot_helmet" };
/*  18 */   public static final IDispenseBehavior b = new DispenseBehaviorItem() {
/*     */       protected ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/*  20 */         ItemStack itemstack1 = ItemArmor.a(isourceblock, itemstack);
/*     */         
/*  22 */         return itemstack1.isEmpty() ? super.b(isourceblock, itemstack) : itemstack1;
/*     */       }
/*     */     };
/*     */   public final EnumItemSlot c;
/*     */   public final int d;
/*     */   public final float e;
/*     */   public final int f;
/*     */   private final EnumArmorMaterial p;
/*     */   
/*     */   public static ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/*  32 */     BlockPosition blockposition = isourceblock.getBlockPosition().shift(isourceblock.e().<EnumDirection>get(BlockDispenser.FACING));
/*  33 */     List<EntityLiving> list = isourceblock.getWorld().a(EntityLiving.class, new AxisAlignedBB(blockposition), Predicates.and(IEntitySelector.e, new IEntitySelector.EntitySelectorEquipable(itemstack)));
/*     */     
/*  35 */     if (list.isEmpty()) {
/*  36 */       return ItemStack.a;
/*     */     }
/*  38 */     EntityLiving entityliving = list.get(0);
/*  39 */     EnumItemSlot enumitemslot = EntityInsentient.d(itemstack);
/*  40 */     ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/*     */     
/*  42 */     World world = isourceblock.getWorld();
/*  43 */     Block block = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/*  44 */     CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*     */     
/*  46 */     BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(0, 0, 0));
/*  47 */     if (!BlockDispenser.eventFired) {
/*  48 */       world.getServer().getPluginManager().callEvent((Event)event);
/*     */     }
/*     */     
/*  51 */     if (event.isCancelled()) {
/*  52 */       itemstack.add(1);
/*  53 */       return itemstack;
/*     */     } 
/*     */     
/*  56 */     if (!event.getItem().equals(craftItem)) {
/*  57 */       itemstack.add(1);
/*     */       
/*  59 */       ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/*  60 */       IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/*  61 */       if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != b) {
/*  62 */         idispensebehavior.a(isourceblock, eventStack);
/*  63 */         return itemstack;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  68 */     entityliving.setSlot(enumitemslot, itemstack1);
/*  69 */     if (entityliving instanceof EntityInsentient) {
/*  70 */       ((EntityInsentient)entityliving).a(enumitemslot, 2.0F);
/*     */     }
/*     */     
/*  73 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemArmor(EnumArmorMaterial itemarmor_enumarmormaterial, int i, EnumItemSlot enumitemslot) {
/*  78 */     this.p = itemarmor_enumarmormaterial;
/*  79 */     this.c = enumitemslot;
/*  80 */     this.f = i;
/*  81 */     this.d = itemarmor_enumarmormaterial.b(enumitemslot);
/*  82 */     setMaxDurability(itemarmor_enumarmormaterial.a(enumitemslot));
/*  83 */     this.e = itemarmor_enumarmormaterial.e();
/*  84 */     this.maxStackSize = 1;
/*  85 */     b(CreativeModeTab.j);
/*  86 */     BlockDispenser.REGISTRY.a(this, b);
/*     */   }
/*     */   
/*     */   public int c() {
/*  90 */     return this.p.a();
/*     */   }
/*     */   
/*     */   public EnumArmorMaterial d() {
/*  94 */     return this.p;
/*     */   }
/*     */   
/*     */   public boolean e_(ItemStack itemstack) {
/*  98 */     if (this.p != EnumArmorMaterial.LEATHER) {
/*  99 */       return false;
/*     */     }
/* 101 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/* 103 */     return (nbttagcompound != null && nbttagcompound.hasKeyOfType("display", 10)) ? nbttagcompound.getCompound("display").hasKeyOfType("color", 3) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int c(ItemStack itemstack) {
/* 108 */     if (this.p != EnumArmorMaterial.LEATHER) {
/* 109 */       return 16777215;
/*     */     }
/* 111 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/* 113 */     if (nbttagcompound != null) {
/* 114 */       NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");
/*     */       
/* 116 */       if (nbttagcompound1 != null && nbttagcompound1.hasKeyOfType("color", 3)) {
/* 117 */         return nbttagcompound1.getInt("color");
/*     */       }
/*     */     } 
/*     */     
/* 121 */     return 10511680;
/*     */   }
/*     */ 
/*     */   
/*     */   public void d(ItemStack itemstack) {
/* 126 */     if (this.p == EnumArmorMaterial.LEATHER) {
/* 127 */       NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */       
/* 129 */       if (nbttagcompound != null) {
/* 130 */         NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");
/*     */         
/* 132 */         if (nbttagcompound1.hasKey("color")) {
/* 133 */           nbttagcompound1.remove("color");
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(ItemStack itemstack, int i) {
/* 141 */     if (this.p != EnumArmorMaterial.LEATHER) {
/* 142 */       throw new UnsupportedOperationException("Can't dye non-leather!");
/*     */     }
/* 144 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/* 146 */     if (nbttagcompound == null) {
/* 147 */       nbttagcompound = new NBTTagCompound();
/* 148 */       itemstack.setTag(nbttagcompound);
/*     */     } 
/*     */     
/* 151 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");
/*     */     
/* 153 */     if (!nbttagcompound.hasKeyOfType("display", 10)) {
/* 154 */       nbttagcompound.set("display", nbttagcompound1);
/*     */     }
/*     */     
/* 157 */     nbttagcompound1.setInt("color", i);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(ItemStack itemstack, ItemStack itemstack1) {
/* 162 */     return (this.p.c() == itemstack1.getItem()) ? true : super.a(itemstack, itemstack1);
/*     */   }
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 166 */     ItemStack itemstack = entityhuman.b(enumhand);
/* 167 */     EnumItemSlot enumitemslot = EntityInsentient.d(itemstack);
/* 168 */     ItemStack itemstack1 = entityhuman.getEquipment(enumitemslot);
/*     */     
/* 170 */     if (itemstack1.isEmpty()) {
/* 171 */       entityhuman.setSlot(enumitemslot, itemstack.cloneItemStack());
/* 172 */       itemstack.setCount(0);
/* 173 */       return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
/*     */     } 
/* 175 */     return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap<String, AttributeModifier> a(EnumItemSlot enumitemslot) {
/* 180 */     Multimap<String, AttributeModifier> multimap = super.a(enumitemslot);
/*     */     
/* 182 */     if (enumitemslot == this.c) {
/* 183 */       multimap.put(GenericAttributes.h.getName(), new AttributeModifier(o[enumitemslot.b()], "Armor modifier", this.d, 0));
/* 184 */       multimap.put(GenericAttributes.i.getName(), new AttributeModifier(o[enumitemslot.b()], "Armor toughness", this.e, 0));
/*     */     } 
/*     */     
/* 187 */     return multimap;
/*     */   }
/*     */   
/*     */   public enum EnumArmorMaterial
/*     */   {
/* 192 */     LEATHER("leather", 5, (String)new int[] { 1, 2, 3, 1 }, 15, (int[])SoundEffects.t, 0.0F), CHAIN("chainmail", 15, (String)new int[] { 1, 4, 5, 2 }, 12, (int[])SoundEffects.n, 0.0F), IRON("iron", 15, (String)new int[] { 2, 5, 6, 2 }, 9, (int[])SoundEffects.s, 0.0F), GOLD("gold", 7, (String)new int[] { 1, 3, 5, 2 }, 25, (int[])SoundEffects.r, 0.0F), DIAMOND("diamond", 33, (String)new int[] { 3, 6, 8, 3 }, 10, (int[])SoundEffects.o, 2.0F);
/*     */     
/*     */     private final float k;
/*     */     private final SoundEffect j;
/*     */     private final int i;
/*     */     private final int[] h;
/*     */     private final int g;
/*     */     private final String f;
/*     */     
/*     */     EnumArmorMaterial(String s, int i, int[] aint, int j, SoundEffect soundeffect, float f) {
/* 202 */       this.f = s;
/* 203 */       this.g = i;
/* 204 */       this.h = aint;
/* 205 */       this.i = j;
/* 206 */       this.j = soundeffect;
/* 207 */       this.k = f;
/*     */     }
/*     */     
/*     */     public int a(EnumItemSlot enumitemslot) {
/* 211 */       return ItemArmor.n[enumitemslot.b()] * this.g;
/*     */     }
/*     */     
/*     */     public int b(EnumItemSlot enumitemslot) {
/* 215 */       return this.h[enumitemslot.b()];
/*     */     }
/*     */     
/*     */     public int a() {
/* 219 */       return this.i;
/*     */     }
/*     */     
/*     */     public SoundEffect b() {
/* 223 */       return this.j;
/*     */     }
/*     */     
/*     */     public Item c() {
/* 227 */       return (this == LEATHER) ? Items.LEATHER : ((this == CHAIN) ? Items.IRON_INGOT : ((this == GOLD) ? Items.GOLD_INGOT : ((this == IRON) ? Items.IRON_INGOT : ((this == DIAMOND) ? Items.DIAMOND : null))));
/*     */     }
/*     */     
/*     */     public float e() {
/* 231 */       return this.k;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */