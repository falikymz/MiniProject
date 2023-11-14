package LoginPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {

    List<String>emails =new ArrayList<>();

    List<String>password =new ArrayList<>();
    //List<User> users =new ArrayList<>();
    public void register() {
        Scanner inp = new Scanner(System.in);
        System.out.println("Ad-Soyad giriniz : ");
        String name = inp.nextLine();

        // Email geçersizse tekrar girilmeli bu sebeple döngü ile çalışmalı
        String email;
        boolean isValid;
        boolean isExistEmail;
        do {
            System.out.println("E-mail giriniz :");
            email=inp.nextLine().trim();
            isValid=validateEmail(email);
            isExistEmail=this.emails.contains(email);
            if (isExistEmail){
                System.out.println("Bu e-mail ile kayıtlı kullanıcı zaten var");
                isValid=false;
            }
        }while (!isValid);


        //Password oluşturma...
        String password ;
        boolean isValidPassword;
        do {
            System.out.println("Şifrenizi oluşturunuz : ");
            password=inp.next().trim();
            isValidPassword=validatePassword(password);
        }while(!isValidPassword);

        // User oluşturalım
        User user =new User(name,email,password);

        // User bilgilerini bu listeye ekleyelim

        this.emails.add(user.email);
        this.password.add(password);

        System.out.println("Tebrikler kayıt işlemi başarıyla gerçekleşti.");
        System.out.println("Email ve şifrenizi kullanarak sisteme giriş yapabilirsiniz.");


    }


    //3-login:girilen email kayıtlı kullanıcıların emailleri arasında var mı
    //       :girilen email ile aynı indekste bulunan password ile girilen şifre eşleşiyor mu
    public void login(){
        Scanner inp=new Scanner(System.in);
        System.out.println("Email giriniz : ");
        String email=inp.nextLine().trim();

        // girilen email list de var mı ?
        boolean existsEmail=this.emails.contains(email);
        if (existsEmail){
           // kullanıcı kayıtlı şifre kontrolü yapılmalı
            boolean isWrong=true;
            while(isWrong){
                System.out.println("Şifrenizi giriniz :");
                String pwd=inp.nextLine().trim();

                // Şifre ile email index eşleşiyor mu ?
                int index =emails.indexOf(email);
                if (this.password.get(index).equals(pwd)){
                    System.out.println("Sisteme giriş başarılı \n Hoşgeldiniz ");
                    isWrong=false;
                }else {
                    System.out.println("Şifreniz yanlış,tekrar deneyiniz");
                }
            }
        }else {
            System.out.println("Sisteme kayıtlı kullanıcı bulunamadı.");
            System.out.println("Üyeyseniz bilgilerinizi kontrol ediniz , değilseniz üye olunuz ");
        }

    }

    private boolean validateEmail(String email) {
        boolean isValid;
        boolean space=email.contains(" ");
        boolean containsAt=email.contains("@");

        if (space){
            System.out.println("Email boşluk içeremez!");
            isValid=false;
        }else if(!containsAt){
            System.out.println("Email @ sembolünü içermelidir!");
            isValid=false;
        }else {//asad*?@asd.com

            String firstPart=email.split("@")[0];//asad*?
            String secondPart=email.split("@")[1];//asd.com,hotmail.com

            //harf.rakam ve -._ dışında karakter var mı?
            int notValidCharLength=firstPart.replaceAll("[a-zA-Z0-9-._]","").length();
            boolean checkStart=notValidCharLength==0;
            boolean checkEnd=secondPart.equals("gmail.com") ||
                    secondPart.equals("yahoo.com") ||
                    secondPart.equals("hotmail.com");

            if (!checkStart){
                System.out.println("Email kullanıcı adı harf, rakam ve -._ dışında karakter içeremez!");
            } else if (!checkEnd) {
                System.out.println("email gmail.com, hotmail.com veya yahoo.com ile bitmelidir!");
            }

            isValid=checkStart && checkEnd;

        }

        if (!isValid){
            System.out.println("Geçersiz email, tekrar deneyiniz!");
        }

        return isValid;



    }


    private boolean validatePassword(String password) {
        //Ja1234*
        boolean isValid;
        String upperLetters=password.replaceAll("[^A_Z]","");//J
        String lowerLetter=password.replaceAll("[^a-z]","");//as
        String digit=password.replaceAll("[\\D]","");//123
        String symbol=password.replaceAll("[\\P{Punct}]","");//*.


        boolean space=password.contains(" ");
        boolean lengthGtSix=password.length()>5;
        boolean existsUpperLetter=upperLetters.length()>0;
        boolean existsLower=lowerLetter.length()>0;
        boolean existsDigit=digit.length()>0;
        boolean existsSymbol=symbol.length()>0;



        if (space){
            System.out.println("Şifre boşluk içeremez.");
        }else if (!lengthGtSix){
            System.out.println("Şifre en az 6 karakter içermelidir!");
        } else if (!existsUpperLetter) {
            System.out.println("Şifre en az 1 tane büyük harf içermelidir!");
        } else if (!existsLower) {
            System.out.println("Şifre en az 1 tane küçük harf içermelidir!");
        } else if (!existsDigit) {
            System.out.println("Şifre en az 1 tane rakam içermelidir!");
        } else if (!existsSymbol) {
            System.out.println("Şifre en az 1 tane sembol içermelidir!");
        }

        isValid=!space && lengthGtSix && existsUpperLetter && existsLower && existsDigit && existsSymbol;//   T & T=T T & F=F

        if (!isValid){
            System.out.println("Geçersiz şifre, tekrar deneyiniz.");
        }
        return isValid;

    }


}
