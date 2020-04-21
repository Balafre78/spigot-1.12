/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventorySubcontainer
/*     */   implements IInventory
/*     */ {
/*     */   private String a;
/*     */   private final int b;
/*     */   public final NonNullList<ItemStack> items;
/*     */   private List<IInventoryListener> d;
/*     */   private boolean e;
/*  23 */   public List<HumanEntity> transaction = new ArrayList<>();
/*  24 */   private int maxStack = 64;
/*     */   protected InventoryHolder bukkitOwner;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  28 */     return this.items;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  32 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  36 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  40 */     return this.transaction;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int i) {
/*  44 */     this.maxStack = i;
/*     */   }
/*     */   
/*     */   public InventoryHolder getOwner() {
/*  48 */     return this.bukkitOwner;
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/*  53 */     return null;
/*     */   }
/*     */   
/*     */   public InventorySubcontainer(String s, boolean flag, int i) {
/*  57 */     this(s, flag, i, null);
/*     */   }
/*     */   
/*     */   public InventorySubcontainer(String s, boolean flag, int i, InventoryHolder owner) {
/*  61 */     this.bukkitOwner = owner;
/*     */     
/*  63 */     this.a = s;
/*  64 */     this.e = flag;
/*  65 */     this.b = i;
/*  66 */     this.items = NonNullList.a(i, ItemStack.a);
/*     */   }
/*     */   
/*     */   public void a(IInventoryListener iinventorylistener) {
/*  70 */     if (this.d == null) {
/*  71 */       this.d = Lists.newArrayList();
/*     */     }
/*     */     
/*  74 */     this.d.add(iinventorylistener);
/*     */   }
/*     */   
/*     */   public void b(IInventoryListener iinventorylistener) {
/*  78 */     this.d.remove(iinventorylistener);
/*     */   }
/*     */   
/*     */   public ItemStack getItem(int i) {
/*  82 */     return (i >= 0 && i < this.items.size()) ? this.items.get(i) : ItemStack.a;
/*     */   }
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/*  86 */     ItemStack itemstack = ContainerUtil.a(this.items, i, j);
/*     */     
/*  88 */     if (!itemstack.isEmpty()) {
/*  89 */       update();
/*     */     }
/*     */     
/*  92 */     return itemstack;
/*     */   }
/*     */   
/*     */   public ItemStack a(ItemStack itemstack) {
/*  96 */     ItemStack itemstack1 = itemstack.cloneItemStack();
/*     */     
/*  98 */     for (int i = 0; i < this.b; i++) {
/*  99 */       ItemStack itemstack2 = getItem(i);
/*     */       
/* 101 */       if (itemstack2.isEmpty()) {
/* 102 */         setItem(i, itemstack1);
/* 103 */         update();
/* 104 */         return ItemStack.a;
/*     */       } 
/*     */       
/* 107 */       if (ItemStack.c(itemstack2, itemstack1)) {
/* 108 */         int j = Math.min(getMaxStackSize(), itemstack2.getMaxStackSize());
/* 109 */         int k = Math.min(itemstack1.getCount(), j - itemstack2.getCount());
/*     */         
/* 111 */         if (k > 0) {
/* 112 */           itemstack2.add(k);
/* 113 */           itemstack1.subtract(k);
/* 114 */           if (itemstack1.isEmpty()) {
/* 115 */             update();
/* 116 */             return ItemStack.a;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     if (itemstack1.getCount() != itemstack.getCount()) {
/* 123 */       update();
/*     */     }
/*     */     
/* 126 */     return itemstack1;
/*     */   }
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/* 130 */     ItemStack itemstack = this.items.get(i);
/*     */     
/* 132 */     if (itemstack.isEmpty()) {
/* 133 */       return ItemStack.a;
/*     */     }
/* 135 */     this.items.set(i, ItemStack.a);
/* 136 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 141 */     this.items.set(i, itemstack);
/* 142 */     if (!itemstack.isEmpty() && itemstack.getCount() > getMaxStackSize()) {
/* 143 */       itemstack.setCount(getMaxStackSize());
/*     */     }
/*     */     
/* 146 */     update();
/*     */   }
/*     */   
/*     */   public int getSize() {
/* 150 */     return this.b;
/*     */   }
/*     */   public boolean x_() {
/*     */     ItemStack itemstack;
/* 154 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 159 */       if (!iterator.hasNext()) {
/* 160 */         return true;
/*     */       }
/*     */       
/* 163 */       itemstack = iterator.next();
/* 164 */     } while (itemstack.isEmpty());
/*     */     
/* 166 */     return false;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 170 */     return this.a;
/*     */   }
/*     */   
/*     */   public boolean hasCustomName() {
/* 174 */     return this.e;
/*     */   }
/*     */   
/*     */   public void a(String s) {
/* 178 */     this.e = true;
/* 179 */     this.a = s;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent getScoreboardDisplayName() {
/* 183 */     return hasCustomName() ? new ChatComponentText(getName()) : new ChatMessage(getName(), new Object[0]);
/*     */   }
/*     */   
/*     */   public int getMaxStackSize() {
/* 187 */     return 64;
/*     */   }
/*     */   
/*     */   public void update() {
/* 191 */     if (this.d != null) {
/* 192 */       for (int i = 0; i < this.d.size(); i++) {
/* 193 */         ((IInventoryListener)this.d.get(i)).a(this);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 200 */     return true;
/*     */   }
/*     */   
/*     */   public void startOpen(EntityHuman entityhuman) {}
/*     */   
/*     */   public void closeContainer(EntityHuman entityhuman) {}
/*     */   
/*     */   public boolean b(int i, ItemStack itemstack) {
/* 208 */     return true;
/*     */   }
/*     */   
/*     */   public int getProperty(int i) {
/* 212 */     return 0;
/*     */   }
/*     */   
/*     */   public void setProperty(int i, int j) {}
/*     */   
/*     */   public int h() {
/* 218 */     return 0;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 222 */     this.items.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\InventorySubcontainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */