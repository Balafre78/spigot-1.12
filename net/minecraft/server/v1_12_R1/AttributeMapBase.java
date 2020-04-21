/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.HashMultimap;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public abstract class AttributeMapBase
/*    */ {
/* 13 */   protected final Map<IAttribute, AttributeInstance> a = Maps.newHashMap();
/* 14 */   protected final Map<String, AttributeInstance> b = new InsensitiveStringMap<>();
/* 15 */   protected final Multimap<IAttribute, IAttribute> c = (Multimap<IAttribute, IAttribute>)HashMultimap.create();
/*    */   
/*    */   public AttributeInstance a(IAttribute paramIAttribute) {
/* 18 */     return this.a.get(paramIAttribute);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public AttributeInstance a(String paramString) {
/* 23 */     return this.b.get(paramString);
/*    */   }
/*    */   
/*    */   public AttributeInstance b(IAttribute paramIAttribute) {
/* 27 */     if (this.b.containsKey(paramIAttribute.getName())) {
/* 28 */       throw new IllegalArgumentException("Attribute is already registered!");
/*    */     }
/*    */     
/* 31 */     AttributeInstance attributeInstance = c(paramIAttribute);
/* 32 */     this.b.put(paramIAttribute.getName(), attributeInstance);
/* 33 */     this.a.put(paramIAttribute, attributeInstance);
/*    */     
/* 35 */     IAttribute iAttribute = paramIAttribute.d();
/* 36 */     while (iAttribute != null) {
/* 37 */       this.c.put(iAttribute, paramIAttribute);
/* 38 */       iAttribute = iAttribute.d();
/*    */     } 
/*    */     
/* 41 */     return attributeInstance;
/*    */   }
/*    */   
/*    */   protected abstract AttributeInstance c(IAttribute paramIAttribute);
/*    */   
/*    */   public Collection<AttributeInstance> a() {
/* 47 */     return this.b.values();
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(AttributeInstance paramAttributeInstance) {}
/*    */   
/*    */   public void a(Multimap<String, AttributeModifier> paramMultimap) {
/* 54 */     for (Map.Entry entry : paramMultimap.entries()) {
/* 55 */       AttributeInstance attributeInstance = a((String)entry.getKey());
/*    */       
/* 57 */       if (attributeInstance != null) {
/* 58 */         attributeInstance.c((AttributeModifier)entry.getValue());
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public void b(Multimap<String, AttributeModifier> paramMultimap) {
/* 64 */     for (Map.Entry entry : paramMultimap.entries()) {
/* 65 */       AttributeInstance attributeInstance = a((String)entry.getKey());
/*    */       
/* 67 */       if (attributeInstance != null) {
/* 68 */         attributeInstance.c((AttributeModifier)entry.getValue());
/* 69 */         attributeInstance.b((AttributeModifier)entry.getValue());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AttributeMapBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */