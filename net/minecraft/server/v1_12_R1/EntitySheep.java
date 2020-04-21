/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Sheep;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.SheepRegrowWoolEvent;
/*     */ import org.bukkit.event.player.PlayerShearEntityEvent;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class EntitySheep extends EntityAnimal {
/*  16 */   private static final DataWatcherObject<Byte> bx = DataWatcher.a((Class)EntitySheep.class, DataWatcherRegistry.a);
/*  17 */   private final InventoryCrafting container = new InventoryCrafting(new Container() {
/*     */         public boolean a(EntityHuman entityhuman) {
/*  19 */           return false;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public InventoryView getBukkitView() {
/*  25 */           return null;
/*     */         }
/*     */       }, 
/*  28 */       2, 1);
/*  29 */   private static final Map<EnumColor, float[]> bz = Maps.newEnumMap(EnumColor.class);
/*     */   private int bB;
/*     */   private PathfinderGoalEatTile bC;
/*     */   
/*     */   private static float[] c(EnumColor enumcolor) {
/*  34 */     float[] afloat = enumcolor.f();
/*     */ 
/*     */     
/*  37 */     return new float[] { afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F };
/*     */   }
/*     */   
/*     */   public EntitySheep(World world) {
/*  41 */     super(world);
/*  42 */     setSize(0.9F, 1.3F);
/*  43 */     this.container.setItem(0, new ItemStack(Items.DYE));
/*  44 */     this.container.setItem(1, new ItemStack(Items.DYE));
/*  45 */     this.container.resultInventory = new InventoryCraftResult();
/*     */   }
/*     */   
/*     */   protected void r() {
/*  49 */     this.bC = new PathfinderGoalEatTile(this);
/*  50 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  51 */     this.goalSelector.a(1, new PathfinderGoalPanic(this, 1.25D));
/*  52 */     this.goalSelector.a(2, new PathfinderGoalBreed(this, 1.0D));
/*  53 */     this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.1D, Items.WHEAT, false));
/*  54 */     this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 1.1D));
/*  55 */     this.goalSelector.a(5, this.bC);
/*  56 */     this.goalSelector.a(6, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  57 */     this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  58 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*     */   }
/*     */   
/*     */   protected void M() {
/*  62 */     this.bB = this.bC.f();
/*  63 */     super.M();
/*     */   }
/*     */   
/*     */   public void n() {
/*  67 */     if (this.world.isClientSide) {
/*  68 */       this.bB = Math.max(0, this.bB - 1);
/*     */     }
/*     */     
/*  71 */     super.n();
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  75 */     super.initAttributes();
/*  76 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(8.0D);
/*  77 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.23000000417232513D);
/*     */   }
/*     */   
/*     */   protected void i() {
/*  81 */     super.i();
/*  82 */     this.datawatcher.register(bx, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/*  87 */     if (isSheared()) {
/*  88 */       return LootTables.P;
/*     */     }
/*  90 */     switch (getColor()) {
/*     */       
/*     */       default:
/*  93 */         return LootTables.Q;
/*     */       
/*     */       case ORANGE:
/*  96 */         return LootTables.R;
/*     */       
/*     */       case MAGENTA:
/*  99 */         return LootTables.S;
/*     */       
/*     */       case LIGHT_BLUE:
/* 102 */         return LootTables.T;
/*     */       
/*     */       case YELLOW:
/* 105 */         return LootTables.U;
/*     */       
/*     */       case LIME:
/* 108 */         return LootTables.V;
/*     */       
/*     */       case PINK:
/* 111 */         return LootTables.W;
/*     */       
/*     */       case GRAY:
/* 114 */         return LootTables.X;
/*     */       
/*     */       case SILVER:
/* 117 */         return LootTables.Y;
/*     */       
/*     */       case CYAN:
/* 120 */         return LootTables.Z;
/*     */       
/*     */       case PURPLE:
/* 123 */         return LootTables.aa;
/*     */       
/*     */       case BLUE:
/* 126 */         return LootTables.ab;
/*     */       
/*     */       case BROWN:
/* 129 */         return LootTables.ac;
/*     */       
/*     */       case GREEN:
/* 132 */         return LootTables.ad;
/*     */       
/*     */       case RED:
/* 135 */         return LootTables.ae;
/*     */       case null:
/*     */         break;
/* 138 */     }  return LootTables.af;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/* 144 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 146 */     if (itemstack.getItem() == Items.SHEARS && !isSheared() && !isBaby()) {
/* 147 */       if (!this.world.isClientSide) {
/*     */         
/* 149 */         PlayerShearEntityEvent event = new PlayerShearEntityEvent((Player)entityhuman.getBukkitEntity(), (Entity)getBukkitEntity());
/* 150 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 152 */         if (event.isCancelled()) {
/* 153 */           return false;
/*     */         }
/*     */ 
/*     */         
/* 157 */         setSheared(true);
/* 158 */         int i = 1 + this.random.nextInt(3);
/*     */         
/* 160 */         for (int j = 0; j < i; j++) {
/* 161 */           this.forceDrops = true;
/* 162 */           EntityItem entityitem = a(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, getColor().getColorIndex()), 1.0F);
/* 163 */           this.forceDrops = false;
/*     */           
/* 165 */           entityitem.motY += (this.random.nextFloat() * 0.05F);
/* 166 */           entityitem.motX += ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
/* 167 */           entityitem.motZ += ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
/*     */         } 
/*     */       } 
/*     */       
/* 171 */       itemstack.damage(1, entityhuman);
/* 172 */       a(SoundEffects.gv, 1.0F, 1.0F);
/*     */     } 
/*     */     
/* 175 */     return super.a(entityhuman, enumhand);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 179 */     EntityInsentient.a(dataconvertermanager, EntitySheep.class);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 183 */     super.b(nbttagcompound);
/* 184 */     nbttagcompound.setBoolean("Sheared", isSheared());
/* 185 */     nbttagcompound.setByte("Color", (byte)getColor().getColorIndex());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 189 */     super.a(nbttagcompound);
/* 190 */     setSheared(nbttagcompound.getBoolean("Sheared"));
/* 191 */     setColor(EnumColor.fromColorIndex(nbttagcompound.getByte("Color")));
/*     */   }
/*     */   
/*     */   protected SoundEffect F() {
/* 195 */     return SoundEffects.gs;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/* 199 */     return SoundEffects.gu;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/* 203 */     return SoundEffects.gt;
/*     */   }
/*     */   
/*     */   protected void a(BlockPosition blockposition, Block block) {
/* 207 */     a(SoundEffects.gw, 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   public EnumColor getColor() {
/* 211 */     return EnumColor.fromColorIndex(((Byte)this.datawatcher.<Byte>get(bx)).byteValue() & 0xF);
/*     */   }
/*     */   
/*     */   public void setColor(EnumColor enumcolor) {
/* 215 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(bx)).byteValue();
/*     */     
/* 217 */     this.datawatcher.set(bx, Byte.valueOf((byte)(b0 & 0xF0 | enumcolor.getColorIndex() & 0xF)));
/*     */   }
/*     */   
/*     */   public boolean isSheared() {
/* 221 */     return ((((Byte)this.datawatcher.<Byte>get(bx)).byteValue() & 0x10) != 0);
/*     */   }
/*     */   
/*     */   public void setSheared(boolean flag) {
/* 225 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(bx)).byteValue();
/*     */     
/* 227 */     if (flag) {
/* 228 */       this.datawatcher.set(bx, Byte.valueOf((byte)(b0 | 0x10)));
/*     */     } else {
/* 230 */       this.datawatcher.set(bx, Byte.valueOf((byte)(b0 & 0xFFFFFFEF)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumColor a(Random random) {
/* 236 */     int i = random.nextInt(100);
/*     */     
/* 238 */     return (i < 5) ? EnumColor.BLACK : ((i < 10) ? EnumColor.GRAY : ((i < 15) ? EnumColor.SILVER : ((i < 18) ? EnumColor.BROWN : ((random.nextInt(500) == 0) ? EnumColor.PINK : EnumColor.WHITE))));
/*     */   }
/*     */   
/*     */   public EntitySheep b(EntityAgeable entityageable) {
/* 242 */     EntitySheep entitysheep = (EntitySheep)entityageable;
/* 243 */     EntitySheep entitysheep1 = new EntitySheep(this.world);
/*     */     
/* 245 */     entitysheep1.setColor(a(this, entitysheep));
/* 246 */     return entitysheep1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void A() {
/* 251 */     SheepRegrowWoolEvent event = new SheepRegrowWoolEvent((Sheep)getBukkitEntity());
/* 252 */     this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */     
/* 254 */     if (event.isCancelled())
/*     */       return; 
/* 256 */     setSheared(false);
/* 257 */     if (isBaby()) {
/* 258 */       setAge(60);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/* 265 */     groupdataentity = super.prepare(difficultydamagescaler, groupdataentity);
/* 266 */     setColor(a(this.world.random));
/* 267 */     return groupdataentity;
/*     */   }
/*     */   
/*     */   private EnumColor a(EntityAnimal entityanimal, EntityAnimal entityanimal1) {
/* 271 */     int k, i = ((EntitySheep)entityanimal).getColor().getInvColorIndex();
/* 272 */     int j = ((EntitySheep)entityanimal1).getColor().getInvColorIndex();
/*     */     
/* 274 */     this.container.getItem(0).setData(i);
/* 275 */     this.container.getItem(1).setData(j);
/* 276 */     ItemStack itemstack = CraftingManager.craft(this.container, ((EntitySheep)entityanimal).world);
/*     */ 
/*     */     
/* 279 */     if (itemstack.getItem() == Items.DYE) {
/* 280 */       k = itemstack.getData();
/*     */     } else {
/* 282 */       k = this.world.random.nextBoolean() ? i : j;
/*     */     } 
/*     */     
/* 285 */     return EnumColor.fromInvColorIndex(k);
/*     */   }
/*     */   
/*     */   public float getHeadHeight() {
/* 289 */     return 0.95F * this.length;
/*     */   }
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable entityageable) {
/* 293 */     return b(entityageable);
/*     */   }
/*     */   
/*     */   static {
/* 297 */     EnumColor[] aenumcolor = EnumColor.values();
/* 298 */     int i = aenumcolor.length;
/*     */     
/* 300 */     for (int j = 0; j < i; j++) {
/* 301 */       EnumColor enumcolor = aenumcolor[j];
/*     */       
/* 303 */       bz.put(enumcolor, c(enumcolor));
/*     */     } 
/*     */     
/* 306 */     bz.put(EnumColor.WHITE, new float[] { 0.9019608F, 0.9019608F, 0.9019608F });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntitySheep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */