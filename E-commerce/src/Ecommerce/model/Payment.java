package Ecommerce.model;

public abstract class Payment {
    
   public abstract void process();
   protected double value;
   public Payment(double value){
      this.value =value;
   }

}

