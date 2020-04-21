/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*    */ import org.bukkit.event.player.PlayerBucketFillEvent;
/*    */ 
/*    */ public class EntityCow
/*    */   extends EntityAnimal {
/*    */   public EntityCow(World world) {
/* 12 */     super(world);
/* 13 */     setSize(0.9F, 1.4F);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager dataconvertermanager) {
/* 17 */     EntityInsentient.a(dataconvertermanager, EntityCow.class);
/*    */   }
/*    */   
/*    */   protected void r() {
/* 21 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/* 22 */     this.goalSelector.a(1, new PathfinderGoalPanic(this, 2.0D));
/* 23 */     this.goalSelector.a(2, new PathfinderGoalBreed(this, 1.0D));
/* 24 */     this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.25D, Items.WHEAT, false));
/* 25 */     this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 1.25D));
/* 26 */     this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 1.0D));
/* 27 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/* 28 */     this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
/*    */   }
/*    */   
/*    */   protected void initAttributes() {
/* 32 */     super.initAttributes();
/* 33 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(10.0D);
/* 34 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.20000000298023224D);
/*    */   }
/*    */   
/*    */   protected SoundEffect F() {
/* 38 */     return SoundEffects.ar;
/*    */   }
/*    */   
/*    */   protected SoundEffect d(DamageSource damagesource) {
/* 42 */     return SoundEffects.at;
/*    */   }
/*    */   
/*    */   protected SoundEffect cf() {
/* 46 */     return SoundEffects.as;
/*    */   }
/*    */   
/*    */   protected void a(BlockPosition blockposition, Block block) {
/* 50 */     a(SoundEffects.av, 0.15F, 1.0F);
/*    */   }
/*    */   
/*    */   protected float cq() {
/* 54 */     return 0.4F;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   protected MinecraftKey J() {
/* 59 */     return LootTables.L;
/*    */   }
/*    */   
/*    */   public boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/* 63 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */     
/* 65 */     if (itemstack.getItem() == Items.BUCKET && !entityhuman.abilities.canInstantlyBuild && !isBaby()) {
/*    */       
/* 67 */       Location loc = getBukkitEntity().getLocation();
/* 68 */       PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent(entityhuman, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), null, itemstack, Items.MILK_BUCKET);
/*    */       
/* 70 */       if (event.isCancelled()) {
/* 71 */         return false;
/*    */       }
/*    */       
/* 74 */       ItemStack result = CraftItemStack.asNMSCopy(event.getItemStack());
/* 75 */       entityhuman.a(SoundEffects.au, 1.0F, 1.0F);
/* 76 */       itemstack.subtract(1);
/* 77 */       if (itemstack.isEmpty()) {
/* 78 */         entityhuman.a(enumhand, result);
/* 79 */       } else if (!entityhuman.inventory.pickup(result)) {
/* 80 */         entityhuman.drop(result, false);
/*    */       } 
/*    */ 
/*    */       
/* 84 */       return true;
/*    */     } 
/* 86 */     return super.a(entityhuman, enumhand);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityCow b(EntityAgeable entityageable) {
/* 91 */     return new EntityCow(this.world);
/*    */   }
/*    */   
/*    */   public float getHeadHeight() {
/* 95 */     return isBaby() ? this.length : 1.3F;
/*    */   }
/*    */   
/*    */   public EntityAgeable createChild(EntityAgeable entityageable) {
/* 99 */     return b(entityageable);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityCow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */