/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class GenericAttributes {
/*  12 */   private static final Logger k = LogManager.getLogger();
/*     */   
/*  14 */   public static final IAttribute maxHealth = (new AttributeRanged(null, "generic.maxHealth", 20.0D, 0.0D, SpigotConfig.maxHealth)).a("Max Health").a(true);
/*  15 */   public static final IAttribute FOLLOW_RANGE = (new AttributeRanged(null, "generic.followRange", 32.0D, 0.0D, 2048.0D)).a("Follow Range");
/*  16 */   public static final IAttribute c = (new AttributeRanged(null, "generic.knockbackResistance", 0.0D, 0.0D, 1.0D)).a("Knockback Resistance");
/*  17 */   public static final IAttribute MOVEMENT_SPEED = (new AttributeRanged(null, "generic.movementSpeed", 0.699999988079071D, 0.0D, SpigotConfig.movementSpeed)).a("Movement Speed").a(true);
/*  18 */   public static final IAttribute e = (new AttributeRanged(null, "generic.flyingSpeed", 0.4000000059604645D, 0.0D, 1024.0D)).a("Flying Speed").a(true);
/*  19 */   public static final IAttribute ATTACK_DAMAGE = new AttributeRanged(null, "generic.attackDamage", 2.0D, 0.0D, SpigotConfig.attackDamage);
/*  20 */   public static final IAttribute g = (new AttributeRanged(null, "generic.attackSpeed", 4.0D, 0.0D, 1024.0D)).a(true);
/*  21 */   public static final IAttribute h = (new AttributeRanged(null, "generic.armor", 0.0D, 0.0D, 30.0D)).a(true);
/*  22 */   public static final IAttribute i = (new AttributeRanged(null, "generic.armorToughness", 0.0D, 0.0D, 20.0D)).a(true);
/*  23 */   public static final IAttribute j = (new AttributeRanged(null, "generic.luck", 0.0D, -1024.0D, 1024.0D)).a(true);
/*     */ 
/*     */   
/*     */   public static NBTTagList a(AttributeMapBase attributemapbase) {
/*  27 */     NBTTagList nbttaglist = new NBTTagList();
/*  28 */     Iterator<AttributeInstance> iterator = attributemapbase.a().iterator();
/*     */     
/*  30 */     while (iterator.hasNext()) {
/*  31 */       AttributeInstance attributeinstance = iterator.next();
/*     */       
/*  33 */       nbttaglist.add(a(attributeinstance));
/*     */     } 
/*     */     
/*  36 */     return nbttaglist;
/*     */   }
/*     */   
/*     */   private static NBTTagCompound a(AttributeInstance attributeinstance) {
/*  40 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*  41 */     IAttribute iattribute = attributeinstance.getAttribute();
/*     */     
/*  43 */     nbttagcompound.setString("Name", iattribute.getName());
/*  44 */     nbttagcompound.setDouble("Base", attributeinstance.b());
/*  45 */     Collection<AttributeModifier> collection = attributeinstance.c();
/*     */     
/*  47 */     if (collection != null && !collection.isEmpty()) {
/*  48 */       NBTTagList nbttaglist = new NBTTagList();
/*  49 */       Iterator<AttributeModifier> iterator = collection.iterator();
/*     */       
/*  51 */       while (iterator.hasNext()) {
/*  52 */         AttributeModifier attributemodifier = iterator.next();
/*     */         
/*  54 */         if (attributemodifier.e()) {
/*  55 */           nbttaglist.add(a(attributemodifier));
/*     */         }
/*     */       } 
/*     */       
/*  59 */       nbttagcompound.set("Modifiers", nbttaglist);
/*     */     } 
/*     */     
/*  62 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public static NBTTagCompound a(AttributeModifier attributemodifier) {
/*  66 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */     
/*  68 */     nbttagcompound.setString("Name", attributemodifier.b());
/*  69 */     nbttagcompound.setDouble("Amount", attributemodifier.d());
/*  70 */     nbttagcompound.setInt("Operation", attributemodifier.c());
/*  71 */     nbttagcompound.a("UUID", attributemodifier.a());
/*  72 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public static void a(AttributeMapBase attributemapbase, NBTTagList nbttaglist) {
/*  76 */     for (int i = 0; i < nbttaglist.size(); i++) {
/*  77 */       NBTTagCompound nbttagcompound = nbttaglist.get(i);
/*  78 */       AttributeInstance attributeinstance = attributemapbase.a(nbttagcompound.getString("Name"));
/*     */       
/*  80 */       if (attributeinstance == null) {
/*  81 */         k.warn("Ignoring unknown attribute '{}'", nbttagcompound.getString("Name"));
/*     */       } else {
/*  83 */         a(attributeinstance, nbttagcompound);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void a(AttributeInstance attributeinstance, NBTTagCompound nbttagcompound) {
/*  90 */     attributeinstance.setValue(nbttagcompound.getDouble("Base"));
/*  91 */     if (nbttagcompound.hasKeyOfType("Modifiers", 9)) {
/*  92 */       NBTTagList nbttaglist = nbttagcompound.getList("Modifiers", 10);
/*     */       
/*  94 */       for (int i = 0; i < nbttaglist.size(); i++) {
/*  95 */         AttributeModifier attributemodifier = a(nbttaglist.get(i));
/*     */         
/*  97 */         if (attributemodifier != null) {
/*  98 */           AttributeModifier attributemodifier1 = attributeinstance.a(attributemodifier.a());
/*     */           
/* 100 */           if (attributemodifier1 != null) {
/* 101 */             attributeinstance.c(attributemodifier1);
/*     */           }
/*     */           
/* 104 */           attributeinstance.b(attributemodifier);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static AttributeModifier a(NBTTagCompound nbttagcompound) {
/* 113 */     UUID uuid = nbttagcompound.a("UUID");
/*     */     
/*     */     try {
/* 116 */       return new AttributeModifier(uuid, nbttagcompound.getString("Name"), nbttagcompound.getDouble("Amount"), nbttagcompound.getInt("Operation"));
/* 117 */     } catch (Exception exception) {
/* 118 */       k.warn("Unable to create attribute: {}", exception.getMessage());
/* 119 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenericAttributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */