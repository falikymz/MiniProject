package BiletRezervasyonApp;

public class Ticket {
    //4- mesafe(km),koltuk no,fiyat,yolculuk tipi

    public double distance ;

    public int typeNo ;

    public String seatNo;

    private double price ;

 // 5- bilet fiyatını hesaplama
    public void getTotalPrice(int age){
        double total =0;
        int seat =Integer.parseInt(this.seatNo);
        switch (this.typeNo){
            case 1:
                if (seat%3==0) {
                    total=1.2*this.distance;
                }else {
                    total=1*this.distance;

                }
                System.out.println("Toplam tutar : " +total);
                break;

            case 2 :
                if (seat%3==0){
                    total=2.4*this.distance;
                }else {
                    total=this.distance*2;
                }
                System.out.println("Toplam tutar :" +total);
                // çift yön indirimi
                total=total*0.8;
                System.out.println("Çift yön indirimli toplam tutar : " +total);
                break;

        }
        // son tutardan yaş indirimi
        if (age<12){
            total=total*0.5;
            System.out.println("12 Yaş altı indirimli toplam tutar : "+total);
        } else if (age>65) {
            total=total*0.7;
            System.out.println("65 Yaş üstü indirimli toplam tutar : "+total);
        }
        this.price=total;



    }

 // 6 - Bileti yazdıralım

    public void printTicket (Bus bus) {
        System.out.println("*".repeat(42));
        System.out.println("--- Bilet detayı --- ");
        System.out.println("Otobüs Plakası : "+bus.numberPlate);
        System.out.println("Mesafe         :"+this.distance);
        System.out.println("Yolculuk tipi  :"+(this.typeNo == 1 ? "Tek Yön": "Gidiş Dönüş"));
        System.out.println("Koltuk No      :"+this.seatNo);
        System.out.println("Toplam Tutar   :"+this.price);
        System.out.println("Keyifli yolculuklar dileriz...");

        System.out.println("*".repeat(42));
    }




}
