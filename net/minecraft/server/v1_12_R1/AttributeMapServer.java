/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Collection;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class AttributeMapServer
/*    */   extends AttributeMapBase {
/* 11 */   private final Set<AttributeInstance> e = Sets.newHashSet();
/* 12 */   protected final Map<String, AttributeInstance> d = new InsensitiveStringMap<>();
/*    */ 
/*    */   
/*    */   public AttributeModifiable e(IAttribute paramIAttribute) {
/* 16 */     return (AttributeModifiable)super.a(paramIAttribute);
/*    */   }
/*    */ 
/*    */   
/*    */   public AttributeModifiable b(String paramString) {
/* 21 */     AttributeInstance attributeInstance = super.a(paramString);
/* 22 */     if (attributeInstance == null) {
/* 23 */       attributeInstance = this.d.get(paramString);
/*    */     }
/* 25 */     return (AttributeModifiable)attributeInstance;
/*    */   }
/*    */ 
/*    */   
/*    */   public AttributeInstance b(IAttribute paramIAttribute) {
/* 30 */     AttributeInstance attributeInstance = super.b(paramIAttribute);
/*    */     
/* 32 */     if (paramIAttribute instanceof AttributeRanged && ((AttributeRanged)paramIAttribute).g() != null) {
/* 33 */       this.d.put(((AttributeRanged)paramIAttribute).g(), attributeInstance);
/*    */     }
/*    */     
/* 36 */     return attributeInstance;
/*    */   }
/*    */ 
/*    */   
/*    */   protected AttributeInstance c(IAttribute paramIAttribute) {
/* 41 */     return new AttributeModifiable(this, paramIAttribute);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(AttributeInstance paramAttributeInstance) {
/* 46 */     if (paramAttributeInstance.getAttribute().c()) {
/* 47 */       this.e.add(paramAttributeInstance);
/*    */     }
/*    */     
/* 50 */     for (IAttribute iAttribute : this.c.get(paramAttributeInstance.getAttribute())) {
/* 51 */       AttributeModifiable attributeModifiable = e(iAttribute);
/* 52 */       if (attributeModifiable != null) {
/* 53 */         attributeModifiable.f();
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public Set<AttributeInstance> getAttributes() {
/* 59 */     return this.e;
/*    */   }
/*    */   
/*    */   public Collection<AttributeInstance> c() {
/* 63 */     HashSet<AttributeInstance> hashSet = Sets.newHashSet();
/*    */     
/* 65 */     for (AttributeInstance attributeInstance : a()) {
/* 66 */       if (attributeInstance.getAttribute().c()) {
/* 67 */         hashSet.add(attributeInstance);
/*    */       }
/*    */     } 
/*    */     
/* 71 */     return hashSet;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AttributeMapServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */