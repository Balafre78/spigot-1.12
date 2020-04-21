/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ public class CommandSpreadPlayers
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  20 */     return "spreadplayers";
/*     */   }
/*     */   
/*     */   public int a() {
/*  24 */     return 2;
/*     */   }
/*     */   
/*     */   public String getUsage(ICommandListener icommandlistener) {
/*  28 */     return "commands.spreadplayers.usage";
/*     */   }
/*     */   
/*     */   public void execute(MinecraftServer minecraftserver, ICommandListener icommandlistener, String[] astring) throws CommandException {
/*  32 */     if (astring.length < 6) {
/*  33 */       throw new ExceptionUsage("commands.spreadplayers.usage", new Object[0]);
/*     */     }
/*  35 */     byte b0 = 0;
/*  36 */     BlockPosition blockposition = icommandlistener.getChunkCoordinates();
/*  37 */     double d0 = blockposition.getX();
/*  38 */     int i = b0 + 1;
/*  39 */     double d1 = b(d0, astring[b0], true);
/*  40 */     double d2 = b(blockposition.getZ(), astring[i++], true);
/*  41 */     double d3 = a(astring[i++], 0.0D);
/*  42 */     double d4 = a(astring[i++], d3 + 1.0D);
/*  43 */     boolean flag = d(astring[i++]);
/*  44 */     ArrayList<Entity> arraylist = Lists.newArrayList();
/*     */     
/*  46 */     while (i < astring.length) {
/*  47 */       String s = astring[i++];
/*     */       
/*  49 */       if (PlayerSelector.isPattern(s)) {
/*  50 */         List<Entity> list = PlayerSelector.getPlayers(icommandlistener, s, Entity.class);
/*     */         
/*  52 */         if (list.isEmpty()) {
/*  53 */           throw new ExceptionEntityNotFound("commands.generic.selector.notFound", new Object[] { s });
/*     */         }
/*     */         
/*  56 */         arraylist.addAll(list); continue;
/*     */       } 
/*  58 */       EntityPlayer entityplayer = minecraftserver.getPlayerList().getPlayer(s);
/*     */       
/*  60 */       if (entityplayer == null) {
/*  61 */         throw new ExceptionPlayerNotFound("commands.generic.player.notFound", new Object[] { s });
/*     */       }
/*     */       
/*  64 */       arraylist.add(entityplayer);
/*     */     } 
/*     */ 
/*     */     
/*  68 */     icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ENTITIES, arraylist.size());
/*  69 */     if (arraylist.isEmpty()) {
/*  70 */       throw new ExceptionEntityNotFound("commands.spreadplayers.noop");
/*     */     }
/*  72 */     icommandlistener.sendMessage(new ChatMessage("commands.spreadplayers.spreading." + (flag ? "teams" : "players"), new Object[] { Integer.valueOf(arraylist.size()), Double.valueOf(d4), Double.valueOf(d1), Double.valueOf(d2), Double.valueOf(d3) }));
/*  73 */     a(icommandlistener, arraylist, new Location2D(d1, d2), d3, d4, ((Entity)arraylist.get(0)).world, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(ICommandListener icommandlistener, List<Entity> list, Location2D commandspreadplayers_location2d, double d0, double d1, World world, boolean flag) throws CommandException {
/*  79 */     Random random = new Random();
/*  80 */     double d2 = commandspreadplayers_location2d.a - d1;
/*  81 */     double d3 = commandspreadplayers_location2d.b - d1;
/*  82 */     double d4 = commandspreadplayers_location2d.a + d1;
/*  83 */     double d5 = commandspreadplayers_location2d.b + d1;
/*  84 */     Location2D[] acommandspreadplayers_location2d = a(random, flag ? b(list) : list.size(), d2, d3, d4, d5);
/*  85 */     int i = a(commandspreadplayers_location2d, d0, world, random, d2, d3, d4, d5, acommandspreadplayers_location2d, flag);
/*  86 */     double d6 = a(list, world, acommandspreadplayers_location2d, flag);
/*     */     
/*  88 */     a(icommandlistener, this, "commands.spreadplayers.success." + (flag ? "teams" : "players"), new Object[] { Integer.valueOf(acommandspreadplayers_location2d.length), Double.valueOf(commandspreadplayers_location2d.a), Double.valueOf(commandspreadplayers_location2d.b) });
/*  89 */     if (acommandspreadplayers_location2d.length > 1) {
/*  90 */       icommandlistener.sendMessage(new ChatMessage("commands.spreadplayers.info." + (flag ? "teams" : "players"), new Object[] { String.format("%.2f", new Object[] { Double.valueOf(d6) }), Integer.valueOf(i) }));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private int b(List<Entity> list) {
/*  96 */     HashSet<ScoreboardTeamBase> hashset = Sets.newHashSet();
/*  97 */     Iterator<Entity> iterator = list.iterator();
/*     */     
/*  99 */     while (iterator.hasNext()) {
/* 100 */       Entity entity = iterator.next();
/*     */       
/* 102 */       if (entity instanceof EntityHuman) {
/* 103 */         hashset.add(entity.aY()); continue;
/*     */       } 
/* 105 */       hashset.add(null);
/*     */     } 
/*     */ 
/*     */     
/* 109 */     return hashset.size();
/*     */   }
/*     */   
/*     */   private int a(Location2D commandspreadplayers_location2d, double d0, World world, Random random, double d1, double d2, double d3, double d4, Location2D[] acommandspreadplayers_location2d, boolean flag) throws CommandException {
/* 113 */     boolean flag1 = true;
/* 114 */     double d5 = 3.4028234663852886E38D;
/*     */     
/*     */     int i;
/*     */     
/* 118 */     for (i = 0; i < 10000 && flag1; i++) {
/* 119 */       flag1 = false;
/* 120 */       d5 = 3.4028234663852886E38D;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 125 */       for (int k = 0; k < acommandspreadplayers_location2d.length; k++) {
/* 126 */         Location2D commandspreadplayers_location2d2 = acommandspreadplayers_location2d[k];
/*     */         
/* 128 */         int j = 0;
/* 129 */         Location2D commandspreadplayers_location2d1 = new Location2D();
/*     */         
/* 131 */         for (int l = 0; l < acommandspreadplayers_location2d.length; l++) {
/* 132 */           if (k != l) {
/* 133 */             Location2D commandspreadplayers_location2d3 = acommandspreadplayers_location2d[l];
/* 134 */             double d6 = commandspreadplayers_location2d2.a(commandspreadplayers_location2d3);
/*     */             
/* 136 */             d5 = Math.min(d6, d5);
/* 137 */             if (d6 < d0) {
/* 138 */               j++;
/* 139 */               commandspreadplayers_location2d1.a += commandspreadplayers_location2d3.a - commandspreadplayers_location2d2.a;
/* 140 */               commandspreadplayers_location2d1.b += commandspreadplayers_location2d3.b - commandspreadplayers_location2d2.b;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 145 */         if (j > 0) {
/* 146 */           commandspreadplayers_location2d1.a /= j;
/* 147 */           commandspreadplayers_location2d1.b /= j;
/* 148 */           double d7 = commandspreadplayers_location2d1.b();
/*     */           
/* 150 */           if (d7 > 0.0D) {
/* 151 */             commandspreadplayers_location2d1.a();
/* 152 */             commandspreadplayers_location2d2.b(commandspreadplayers_location2d1);
/*     */           } else {
/* 154 */             commandspreadplayers_location2d2.a(random, d1, d2, d3, d4);
/*     */           } 
/*     */           
/* 157 */           flag1 = true;
/*     */         } 
/*     */         
/* 160 */         if (commandspreadplayers_location2d2.a(d1, d2, d3, d4)) {
/* 161 */           flag1 = true;
/*     */         }
/*     */       } 
/*     */       
/* 165 */       if (!flag1) {
/* 166 */         Location2D[] acommandspreadplayers_location2d1 = acommandspreadplayers_location2d;
/* 167 */         int i1 = acommandspreadplayers_location2d.length;
/*     */         
/* 169 */         for (int j = 0; j < i1; j++) {
/* 170 */           Location2D commandspreadplayers_location2d1 = acommandspreadplayers_location2d1[j];
/* 171 */           if (!commandspreadplayers_location2d1.b(world)) {
/* 172 */             commandspreadplayers_location2d1.a(random, d1, d2, d3, d4);
/* 173 */             flag1 = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 179 */     if (i >= 10000) {
/* 180 */       throw new CommandException("commands.spreadplayers.failure." + (flag ? "teams" : "players"), new Object[] { Integer.valueOf(acommandspreadplayers_location2d.length), Double.valueOf(commandspreadplayers_location2d.a), Double.valueOf(commandspreadplayers_location2d.b), String.format("%.2f", new Object[] { Double.valueOf(d5) }) });
/*     */     }
/* 182 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   private double a(List<Entity> list, World world, Location2D[] acommandspreadplayers_location2d, boolean flag) {
/* 187 */     double d0 = 0.0D;
/* 188 */     int i = 0;
/* 189 */     HashMap<ScoreboardTeamBase, Location2D> hashmap = Maps.newHashMap();
/*     */     
/* 191 */     for (int j = 0; j < list.size(); j++) {
/* 192 */       Location2D commandspreadplayers_location2d; Entity entity = list.get(j);
/*     */ 
/*     */       
/* 195 */       if (flag) {
/* 196 */         ScoreboardTeamBase scoreboardteambase = (entity instanceof EntityHuman) ? entity.aY() : null;
/*     */         
/* 198 */         if (!hashmap.containsKey(scoreboardteambase)) {
/* 199 */           hashmap.put(scoreboardteambase, acommandspreadplayers_location2d[i++]);
/*     */         }
/*     */         
/* 202 */         commandspreadplayers_location2d = hashmap.get(scoreboardteambase);
/*     */       } else {
/* 204 */         commandspreadplayers_location2d = acommandspreadplayers_location2d[i++];
/*     */       } 
/*     */       
/* 207 */       entity.enderTeleportTo((MathHelper.floor(commandspreadplayers_location2d.a) + 0.5F), commandspreadplayers_location2d.a(world), MathHelper.floor(commandspreadplayers_location2d.b) + 0.5D);
/* 208 */       double d1 = Double.MAX_VALUE;
/* 209 */       Location2D[] acommandspreadplayers_location2d1 = acommandspreadplayers_location2d;
/* 210 */       int k = acommandspreadplayers_location2d.length;
/*     */       
/* 212 */       for (int l = 0; l < k; l++) {
/* 213 */         Location2D commandspreadplayers_location2d1 = acommandspreadplayers_location2d1[l];
/*     */         
/* 215 */         if (commandspreadplayers_location2d != commandspreadplayers_location2d1) {
/* 216 */           double d2 = commandspreadplayers_location2d.a(commandspreadplayers_location2d1);
/*     */           
/* 218 */           d1 = Math.min(d2, d1);
/*     */         } 
/*     */       } 
/*     */       
/* 222 */       d0 += d1;
/*     */     } 
/*     */     
/* 225 */     d0 /= list.size();
/* 226 */     return d0;
/*     */   }
/*     */   
/*     */   private Location2D[] a(Random random, int i, double d0, double d1, double d2, double d3) {
/* 230 */     Location2D[] acommandspreadplayers_location2d = new Location2D[i];
/*     */     
/* 232 */     for (int j = 0; j < acommandspreadplayers_location2d.length; j++) {
/* 233 */       Location2D commandspreadplayers_location2d = new Location2D();
/*     */       
/* 235 */       commandspreadplayers_location2d.a(random, d0, d1, d2, d3);
/* 236 */       acommandspreadplayers_location2d[j] = commandspreadplayers_location2d;
/*     */     } 
/*     */     
/* 239 */     return acommandspreadplayers_location2d;
/*     */   }
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer minecraftserver, ICommandListener icommandlistener, String[] astring, @Nullable BlockPosition blockposition) {
/* 243 */     return (astring.length >= 1 && astring.length <= 2) ? b(astring, 0, blockposition) : Collections.<String>emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(ICommand o) {
/* 249 */     return a(o);
/*     */   }
/*     */ 
/*     */   
/*     */   static class Location2D
/*     */   {
/*     */     double a;
/*     */     double b;
/*     */     
/*     */     Location2D() {}
/*     */     
/*     */     Location2D(double d0, double d1) {
/* 261 */       this.a = d0;
/* 262 */       this.b = d1;
/*     */     }
/*     */     
/*     */     double a(Location2D commandspreadplayers_location2d) {
/* 266 */       double d0 = this.a - commandspreadplayers_location2d.a;
/* 267 */       double d1 = this.b - commandspreadplayers_location2d.b;
/*     */       
/* 269 */       return Math.sqrt(d0 * d0 + d1 * d1);
/*     */     }
/*     */     
/*     */     void a() {
/* 273 */       double d0 = b();
/*     */       
/* 275 */       this.a /= d0;
/* 276 */       this.b /= d0;
/*     */     }
/*     */     
/*     */     float b() {
/* 280 */       return MathHelper.sqrt(this.a * this.a + this.b * this.b);
/*     */     }
/*     */     
/*     */     public void b(Location2D commandspreadplayers_location2d) {
/* 284 */       this.a -= commandspreadplayers_location2d.a;
/* 285 */       this.b -= commandspreadplayers_location2d.b;
/*     */     }
/*     */     
/*     */     public boolean a(double d0, double d1, double d2, double d3) {
/* 289 */       boolean flag = false;
/*     */       
/* 291 */       if (this.a < d0) {
/* 292 */         this.a = d0;
/* 293 */         flag = true;
/* 294 */       } else if (this.a > d2) {
/* 295 */         this.a = d2;
/* 296 */         flag = true;
/*     */       } 
/*     */       
/* 299 */       if (this.b < d1) {
/* 300 */         this.b = d1;
/* 301 */         flag = true;
/* 302 */       } else if (this.b > d3) {
/* 303 */         this.b = d3;
/* 304 */         flag = true;
/*     */       } 
/*     */       
/* 307 */       return flag;
/*     */     }
/*     */     
/*     */     public int a(World world) {
/* 311 */       BlockPosition blockposition = new BlockPosition(this.a, 256.0D, this.b);
/*     */       
/*     */       do {
/* 314 */         if (blockposition.getY() <= 0) {
/* 315 */           return 257;
/*     */         }
/*     */         
/* 318 */         blockposition = blockposition.down();
/* 319 */       } while (getType(world, blockposition).getMaterial() == Material.AIR);
/*     */       
/* 321 */       return blockposition.getY() + 1;
/*     */     }
/*     */     public boolean b(World world) {
/*     */       Material material;
/* 325 */       BlockPosition blockposition = new BlockPosition(this.a, 256.0D, this.b);
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 330 */         if (blockposition.getY() <= 0) {
/* 331 */           return false;
/*     */         }
/*     */         
/* 334 */         blockposition = blockposition.down();
/* 335 */         material = getType(world, blockposition).getMaterial();
/* 336 */       } while (material == Material.AIR);
/*     */       
/* 338 */       return (!material.isLiquid() && material != Material.FIRE);
/*     */     }
/*     */     
/*     */     public void a(Random random, double d0, double d1, double d2, double d3) {
/* 342 */       this.a = MathHelper.a(random, d0, d2);
/* 343 */       this.b = MathHelper.a(random, d1, d3);
/*     */     }
/*     */ 
/*     */     
/*     */     private static IBlockData getType(World world, BlockPosition position) {
/* 348 */       ((ChunkProviderServer)world.chunkProvider).getChunkAt(position.getX() >> 4, position.getZ() >> 4);
/* 349 */       return world.getType(position);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandSpreadPlayers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */