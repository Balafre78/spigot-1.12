/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ public abstract class EntityMinecartContainer
/*     */   extends EntityMinecartAbstract
/*     */   implements ITileInventory, ILootable
/*     */ {
/*     */   private NonNullList<ItemStack> items;
/*     */   private boolean b;
/*     */   private MinecraftKey c;
/*     */   private long d;
/*  22 */   public List<HumanEntity> transaction = new ArrayList<>();
/*  23 */   private int maxStack = 64;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  26 */     return this.items;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  30 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  34 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  38 */     return this.transaction;
/*     */   }
/*     */   
/*     */   public InventoryHolder getOwner() {
/*  42 */     CraftEntity craftEntity = getBukkitEntity();
/*  43 */     if (craftEntity instanceof InventoryHolder) return (InventoryHolder)craftEntity; 
/*  44 */     return null;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int size) {
/*  48 */     this.maxStack = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/*  53 */     return getBukkitEntity().getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartContainer(World world) {
/*  58 */     super(world);
/*  59 */     this.items = NonNullList.a(36, ItemStack.a);
/*  60 */     this.b = true;
/*     */   }
/*     */   
/*     */   public EntityMinecartContainer(World world, double d0, double d1, double d2) {
/*  64 */     super(world, d0, d1, d2);
/*  65 */     this.items = NonNullList.a(36, ItemStack.a);
/*  66 */     this.b = true;
/*     */   }
/*     */   
/*     */   public void a(DamageSource damagesource) {
/*  70 */     super.a(damagesource);
/*  71 */     if (this.world.getGameRules().getBoolean("doEntityDrops")) {
/*  72 */       InventoryUtils.dropEntity(this.world, this, this);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean x_() {
/*     */     ItemStack itemstack;
/*  78 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  83 */       if (!iterator.hasNext()) {
/*  84 */         return true;
/*     */       }
/*     */       
/*  87 */       itemstack = iterator.next();
/*  88 */     } while (itemstack.isEmpty());
/*     */     
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack getItem(int i) {
/*  94 */     f((EntityHuman)null);
/*  95 */     return this.items.get(i);
/*     */   }
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/*  99 */     f((EntityHuman)null);
/* 100 */     return ContainerUtil.a(this.items, i, j);
/*     */   }
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/* 104 */     f((EntityHuman)null);
/* 105 */     ItemStack itemstack = this.items.get(i);
/*     */     
/* 107 */     if (itemstack.isEmpty()) {
/* 108 */       return ItemStack.a;
/*     */     }
/* 110 */     this.items.set(i, ItemStack.a);
/* 111 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 116 */     f((EntityHuman)null);
/* 117 */     this.items.set(i, itemstack);
/* 118 */     if (!itemstack.isEmpty() && itemstack.getCount() > getMaxStackSize()) {
/* 119 */       itemstack.setCount(getMaxStackSize());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {}
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 127 */     return this.dead ? false : ((entityhuman.h(this) <= 64.0D));
/*     */   }
/*     */   
/*     */   public void startOpen(EntityHuman entityhuman) {}
/*     */   
/*     */   public void closeContainer(EntityHuman entityhuman) {}
/*     */   
/*     */   public boolean b(int i, ItemStack itemstack) {
/* 135 */     return true;
/*     */   }
/*     */   
/*     */   public int getMaxStackSize() {
/* 139 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity b(int i) {
/* 144 */     this.b = false;
/* 145 */     return super.b(i);
/*     */   }
/*     */   
/*     */   public void die() {
/* 149 */     if (this.b) {
/* 150 */       InventoryUtils.dropEntity(this.world, this, this);
/*     */     }
/*     */     
/* 153 */     super.die();
/*     */   }
/*     */   
/*     */   public void b(boolean flag) {
/* 157 */     this.b = flag;
/*     */   }
/*     */   
/*     */   public static void b(DataConverterManager dataconvertermanager, Class<?> oclass) {
/* 161 */     EntityMinecartAbstract.a(dataconvertermanager, oclass);
/* 162 */     dataconvertermanager.a(DataConverterTypes.ENTITY, new DataInspectorItemList(oclass, new String[] { "Items" }));
/*     */   }
/*     */   
/*     */   protected void b(NBTTagCompound nbttagcompound) {
/* 166 */     super.b(nbttagcompound);
/* 167 */     if (this.c != null) {
/* 168 */       nbttagcompound.setString("LootTable", this.c.toString());
/* 169 */       if (this.d != 0L) {
/* 170 */         nbttagcompound.setLong("LootTableSeed", this.d);
/*     */       }
/*     */     } else {
/* 173 */       ContainerUtil.a(nbttagcompound, this.items);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NBTTagCompound nbttagcompound) {
/* 179 */     super.a(nbttagcompound);
/* 180 */     this.items = NonNullList.a(getSize(), ItemStack.a);
/* 181 */     if (nbttagcompound.hasKeyOfType("LootTable", 8)) {
/* 182 */       this.c = new MinecraftKey(nbttagcompound.getString("LootTable"));
/* 183 */       this.d = nbttagcompound.getLong("LootTableSeed");
/*     */     } else {
/* 185 */       ContainerUtil.b(nbttagcompound, this.items);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(EntityHuman entityhuman, EnumHand enumhand) {
/* 191 */     if (!this.world.isClientSide) {
/* 192 */       entityhuman.openContainer(this);
/*     */     }
/*     */     
/* 195 */     return true;
/*     */   }
/*     */   
/*     */   protected void r() {
/* 199 */     float f = 0.98F;
/*     */     
/* 201 */     if (this.c == null) {
/* 202 */       int i = 15 - Container.b(this);
/*     */       
/* 204 */       f += i * 0.001F;
/*     */     } 
/*     */     
/* 207 */     this.motX *= f;
/* 208 */     this.motY *= 0.0D;
/* 209 */     this.motZ *= f;
/*     */   }
/*     */   
/*     */   public int getProperty(int i) {
/* 213 */     return 0;
/*     */   }
/*     */   
/*     */   public void setProperty(int i, int j) {}
/*     */   
/*     */   public int h() {
/* 219 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean isLocked() {
/* 223 */     return false;
/*     */   }
/*     */   
/*     */   public void setLock(ChestLock chestlock) {}
/*     */   
/*     */   public ChestLock getLock() {
/* 229 */     return ChestLock.a;
/*     */   }
/*     */   
/*     */   public void f(@Nullable EntityHuman entityhuman) {
/* 233 */     if (this.c != null) {
/* 234 */       Random random; LootTable loottable = this.world.getLootTableRegistry().a(this.c);
/*     */       
/* 236 */       this.c = null;
/*     */ 
/*     */       
/* 239 */       if (this.d == 0L) {
/* 240 */         random = new Random();
/*     */       } else {
/* 242 */         random = new Random(this.d);
/*     */       } 
/*     */       
/* 245 */       LootTableInfo.a loottableinfo_a = new LootTableInfo.a((WorldServer)this.world);
/*     */       
/* 247 */       if (entityhuman != null) {
/* 248 */         loottableinfo_a.a(entityhuman.du());
/*     */       }
/*     */       
/* 251 */       loottable.a(this, random, loottableinfo_a.a());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 257 */     f((EntityHuman)null);
/* 258 */     this.items.clear();
/*     */   }
/*     */   
/*     */   public void a(MinecraftKey minecraftkey, long i) {
/* 262 */     this.c = minecraftkey;
/* 263 */     this.d = i;
/*     */   }
/*     */   
/*     */   public MinecraftKey b() {
/* 267 */     return this.c;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityMinecartContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */