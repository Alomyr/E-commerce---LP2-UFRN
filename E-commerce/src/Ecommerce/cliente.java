package Ecommerce;

public class cliente {

   private String name;
   private long CPF;


   //construtor padrao
   public cliente(){
      this.name = "user";
      this.CPF = 12345678910L;
   }
   //construtor parametrizado
   public cliente(String name, long CPF){
      this.name = name;
      this.CPF = CPF;
   }
   public String Getname(){
      return this.name;
   }
   public long GetCPF(){
      return this.CPF;
   }
   void Setname(String name){
      this.name = name;
   }
   // void SetCPF(long CPF){
   //    this.CPF = CPF;
   // }
   
}
 /*    ▪ Agregação (relação fraca)
    ▪ Pedido agrega um Cliente.
    ▪ Cliente existe independente do Pedido. */