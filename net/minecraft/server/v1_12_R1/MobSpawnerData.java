/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MobSpawnerData
/*    */   extends WeightedRandom.WeightedRandomChoice
/*    */ {
/*    */   private final NBTTagCompound b;
/*    */   
/*    */   public MobSpawnerData() {
/* 15 */     super(1);
/*    */     
/* 17 */     this.b = new NBTTagCompound();
/* 18 */     this.b.setString("id", "minecraft:pig");
/*    */   }
/*    */   
/*    */   public MobSpawnerData(NBTTagCompound paramNBTTagCompound) {
/* 22 */     this(paramNBTTagCompound.hasKeyOfType("Weight", 99) ? paramNBTTagCompound.getInt("Weight") : 1, paramNBTTagCompound.getCompound("Entity"));
/*    */   }
/*    */   
/*    */   public MobSpawnerData(int paramInt, NBTTagCompound paramNBTTagCompound) {
/* 26 */     super(paramInt);
/*    */     
/* 28 */     this.b = paramNBTTagCompound;
/*    */   }
/*    */   
/*    */   public NBTTagCompound a() {
/* 32 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*    */     
/* 34 */     if (!this.b.hasKeyOfType("id", 8)) {
/* 35 */       this.b.setString("id", "minecraft:pig");
/* 36 */     } else if (!this.b.getString("id").contains(":")) {
/* 37 */       this.b.setString("id", (new MinecraftKey(this.b.getString("id"))).toString());
/*    */     } 
/* 39 */     nBTTagCompound.set("Entity", this.b);
/* 40 */     nBTTagCompound.setInt("Weight", this.a);
/*    */     
/* 42 */     return nBTTagCompound;
/*    */   }
/*    */   
/*    */   public NBTTagCompound b() {
/* 46 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MobSpawnerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */