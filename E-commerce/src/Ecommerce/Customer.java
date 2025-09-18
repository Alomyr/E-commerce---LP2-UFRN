package Ecommerce;

public class Customer {

   private String name;
   private long CPF;


   //construtor padrao
   public Customer(){
      this.name = "user";
      this.CPF = 12345678910L;
   }
   //construtor parametrizado
   public Customer(String name, long CPF){
      this.name = name;
      this.CPF = CPF;
   }
   public String GetName(){
      return this.name;
   }
   public long GetCPF(){
      return this.CPF;
   }
   void SetName(String name){
      this.name = name;
   }
   // void SetCPF(long CPF){
   //    this.CPF = CPF;
   // }
   
}
 /*    ▪ Agregação (relação fraca)
    ▪ Pedido agrega um Cliente.
    ▪ Cliente existe independente do Pedido. */