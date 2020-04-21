/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class EntityPerchable
/*    */   extends EntityTameableAnimal
/*    */ {
/*    */   private int bB;
/*    */   
/*    */   public EntityPerchable(World paramWorld) {
/* 13 */     super(paramWorld);
/*    */   }
/*    */   
/*    */   public boolean g(EntityHuman paramEntityHuman) {
/* 17 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 18 */     nBTTagCompound.setString("id", getSaveID());
/* 19 */     save(nBTTagCompound);
/*    */     
/* 21 */     if (paramEntityHuman.g(nBTTagCompound)) {
/* 22 */       this.world.kill(this);
/* 23 */       return true;
/*    */     } 
/*    */     
/* 26 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void B_() {
/* 31 */     this.bB++;
/* 32 */     super.B_();
/*    */   }
/*    */   
/*    */   public boolean dw() {
/* 36 */     return (this.bB > 100);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityPerchable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */