package Ecommerce.model;

public abstract class Pagamento {
    
   public abstract void processar();
   protected double val;
   public Pagamento(double val){
      this.val =val;
   }

}

