/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumDragonRespawn
/*    */ {
/* 15 */   START
/*    */   {
/*    */     public void a(WorldServer param1WorldServer, EnderDragonBattle param1EnderDragonBattle, List<EntityEnderCrystal> param1List, int param1Int, BlockPosition param1BlockPosition) {
/* 18 */       BlockPosition blockPosition = new BlockPosition(0, 128, 0);
/* 19 */       for (EntityEnderCrystal entityEnderCrystal : param1List) {
/* 20 */         entityEnderCrystal.setBeamTarget(blockPosition);
/*    */       }
/* 22 */       param1EnderDragonBattle.a(PREPARING_TO_SUMMON_PILLARS);
/*    */     }
/*    */   },
/* 25 */   PREPARING_TO_SUMMON_PILLARS
/*    */   {
/*    */     public void a(WorldServer param1WorldServer, EnderDragonBattle param1EnderDragonBattle, List<EntityEnderCrystal> param1List, int param1Int, BlockPosition param1BlockPosition) {
/* 28 */       if (param1Int < 100) {
/* 29 */         if (param1Int == 0 || param1Int == 50 || param1Int == 51 || param1Int == 52 || param1Int >= 95) {
/* 30 */           param1WorldServer.triggerEffect(3001, new BlockPosition(0, 128, 0), 0);
/*    */         }
/*    */       } else {
/* 33 */         param1EnderDragonBattle.a(SUMMONING_PILLARS);
/*    */       } 
/*    */     }
/*    */   },
/* 37 */   SUMMONING_PILLARS
/*    */   {
/*    */     public void a(WorldServer param1WorldServer, EnderDragonBattle param1EnderDragonBattle, List<EntityEnderCrystal> param1List, int param1Int, BlockPosition param1BlockPosition) {
/* 40 */       byte b = 40;
/* 41 */       boolean bool1 = (param1Int % 40 == 0) ? true : false;
/* 42 */       boolean bool2 = (param1Int % 40 == 39) ? true : false;
/* 43 */       if (bool1 || bool2) {
/* 44 */         WorldGenEnder.Spike[] arrayOfSpike = BiomeTheEndDecorator.a(param1WorldServer);
/* 45 */         int i = param1Int / 40;
/* 46 */         if (i < arrayOfSpike.length) {
/* 47 */           WorldGenEnder.Spike spike = arrayOfSpike[i];
/*    */           
/* 49 */           if (bool1) {
/* 50 */             for (EntityEnderCrystal entityEnderCrystal : param1List) {
/* 51 */               entityEnderCrystal.setBeamTarget(new BlockPosition(spike.a(), spike.d() + 1, spike.b()));
/*    */             }
/*    */           } else {
/* 54 */             byte b1 = 10;
/* 55 */             for (BlockPosition.MutableBlockPosition mutableBlockPosition : BlockPosition.b(new BlockPosition(spike
/* 56 */                   .a() - 10, spike.d() - 10, spike.b() - 10), new BlockPosition(spike
/* 57 */                   .a() + 10, spike.d() + 10, spike.b() + 10))) {
/* 58 */               param1WorldServer.setAir(mutableBlockPosition);
/*    */             }
/* 60 */             param1WorldServer.explode(null, (spike.a() + 0.5F), spike.d(), (spike.b() + 0.5F), 5.0F, true);
/*    */             
/* 62 */             WorldGenEnder worldGenEnder = new WorldGenEnder();
/* 63 */             worldGenEnder.a(spike);
/* 64 */             worldGenEnder.a(true);
/* 65 */             worldGenEnder.a(new BlockPosition(0, 128, 0));
/* 66 */             worldGenEnder.generate(param1WorldServer, new Random(), new BlockPosition(spike.a(), 45, spike.b()));
/*    */           } 
/* 68 */         } else if (bool1) {
/* 69 */           param1EnderDragonBattle.a(SUMMONING_DRAGON);
/*    */         } 
/*    */       } 
/*    */     }
/*    */   },
/* 74 */   SUMMONING_DRAGON
/*    */   {
/*    */     public void a(WorldServer param1WorldServer, EnderDragonBattle param1EnderDragonBattle, List<EntityEnderCrystal> param1List, int param1Int, BlockPosition param1BlockPosition) {
/* 77 */       if (param1Int >= 100) {
/* 78 */         param1EnderDragonBattle.a(END);
/* 79 */         param1EnderDragonBattle.f();
/* 80 */         for (EntityEnderCrystal entityEnderCrystal : param1List) {
/* 81 */           entityEnderCrystal.setBeamTarget((BlockPosition)null);
/* 82 */           param1WorldServer.explode(entityEnderCrystal, entityEnderCrystal.locX, entityEnderCrystal.locY, entityEnderCrystal.locZ, 6.0F, false);
/* 83 */           entityEnderCrystal.die();
/*    */         } 
/* 85 */       } else if (param1Int >= 80) {
/* 86 */         param1WorldServer.triggerEffect(3001, new BlockPosition(0, 128, 0), 0);
/* 87 */       } else if (param1Int == 0) {
/* 88 */         for (EntityEnderCrystal entityEnderCrystal : param1List) {
/* 89 */           entityEnderCrystal.setBeamTarget(new BlockPosition(0, 128, 0));
/*    */         }
/* 91 */       } else if (param1Int < 5) {
/* 92 */         param1WorldServer.triggerEffect(3001, new BlockPosition(0, 128, 0), 0);
/*    */       } 
/*    */     }
/*    */   },
/* 96 */   END {
/*    */     public void a(WorldServer param1WorldServer, EnderDragonBattle param1EnderDragonBattle, List<EntityEnderCrystal> param1List, int param1Int, BlockPosition param1BlockPosition) {}
/*    */   };
/*    */   
/*    */   public abstract void a(WorldServer paramWorldServer, EnderDragonBattle paramEnderDragonBattle, List<EntityEnderCrystal> paramList, int paramInt, BlockPosition paramBlockPosition);
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnumDragonRespawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */