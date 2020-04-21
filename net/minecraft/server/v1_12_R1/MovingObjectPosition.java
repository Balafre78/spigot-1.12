/*    */ package net.minecraft.server.v1_12_R1;public class MovingObjectPosition {
/*    */   private BlockPosition e;
/*    */   public EnumMovingObjectType type;
/*    */   public EnumDirection direction;
/*    */   public Vec3D pos;
/*    */   public Entity entity;
/*    */   
/*    */   public enum EnumMovingObjectType {
/*  9 */     MISS, BLOCK, ENTITY;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MovingObjectPosition(Vec3D paramVec3D, EnumDirection paramEnumDirection, BlockPosition paramBlockPosition) {
/* 19 */     this(EnumMovingObjectType.BLOCK, paramVec3D, paramEnumDirection, paramBlockPosition);
/*    */   }
/*    */   
/*    */   public MovingObjectPosition(Vec3D paramVec3D, EnumDirection paramEnumDirection) {
/* 23 */     this(EnumMovingObjectType.BLOCK, paramVec3D, paramEnumDirection, BlockPosition.ZERO);
/*    */   }
/*    */   
/*    */   public MovingObjectPosition(Entity paramEntity) {
/* 27 */     this(paramEntity, new Vec3D(paramEntity.locX, paramEntity.locY, paramEntity.locZ));
/*    */   }
/*    */   
/*    */   public MovingObjectPosition(EnumMovingObjectType paramEnumMovingObjectType, Vec3D paramVec3D, EnumDirection paramEnumDirection, BlockPosition paramBlockPosition) {
/* 31 */     this.type = paramEnumMovingObjectType;
/* 32 */     this.e = paramBlockPosition;
/* 33 */     this.direction = paramEnumDirection;
/* 34 */     this.pos = new Vec3D(paramVec3D.x, paramVec3D.y, paramVec3D.z);
/*    */   }
/*    */   
/*    */   public MovingObjectPosition(Entity paramEntity, Vec3D paramVec3D) {
/* 38 */     this.type = EnumMovingObjectType.ENTITY;
/* 39 */     this.entity = paramEntity;
/* 40 */     this.pos = paramVec3D;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockPosition a() {
/* 51 */     return this.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     return "HitResult{type=" + this.type + ", blockpos=" + this.e + ", f=" + this.direction + ", pos=" + this.pos + ", entity=" + this.entity + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MovingObjectPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */