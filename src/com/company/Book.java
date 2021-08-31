package com.company;

import com.github.javafaker.Faker;

import javax.swing.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.CancellationException;

public class Book {
    private String title;
    private String autor;
    private int year;
    private double prize;
    private int count;

    public Book(){
        title=null;
        autor=null;
        year=0;
        prize=0;
        count=0;
    }

    public Book(String title, String autor, int year, double prize, int count){
        this.title = title;
        this.autor = autor;
        this.year = year;
        this.prize = prize; //jeżeli ktoś nie chce wyporzyczyć, tylko kupic na własność
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public String getAutor() { return autor; }

    public int getYear() {
        return year;
    }

    public double getPrize() {
        return prize;
    }

    public int getCount() { return count; }
    public void setCount(int i) { this.count = i;}

    @Override
    public String toString() {
        return title + " | " + autor + " | " + prize;
    }

    public List<Book> FakeBooks(){
        List<Book> listaksiazek = new ArrayList<>();
        for(int i = 0;i<50;i++){
            Random random = new Random();
            Faker faker = new Faker();
            String autor = faker.name().fullName();//losowanie autora
            String title = faker.book().title();//losowy tytuł książki
            int cena = (int) Double.parseDouble(String.valueOf(Math.pow(random.nextDouble() * 25,2)));
            while(cena < 0){
                cena = (int) Double.parseDouble(String.valueOf(Math.pow(random.nextDouble() * 25,2)));
            }
            int rok_wydania = random.nextInt(2020 - 1800) + 1800;//(max-min)+min
            int ilosc_na_stanie = random.nextInt(70);
            listaksiazek.add(new Book(title,autor,rok_wydania,cena,ilosc_na_stanie));
        }
        return listaksiazek;
    }

    public void wypisz_info_book(int index, JFrame frame, List<Book> dane){
        JOptionPane.showMessageDialog(frame,"Tytuł: " + dane.get(index).getTitle() + "\nAutor: " + dane.get(index).getAutor() +
                "\nRok wydania: " + dane.get(index).getYear() + "\nCena: " + dane.get(index).getPrize() +
                "\nIlość na magazynie: " + dane.get(index).getCount());
    }

    public void dodaj_ksiazke(DefaultListModel<String> lista, List<Book> dane, JFrame frame){
        String tytul = JOptionPane.showInputDialog(frame, "Podaj tytuł książki do dodania:");
        String autor = JOptionPane.showInputDialog(frame, "Podaj autora książki do dodania:");
        String rok_wydania = JOptionPane.showInputDialog(frame, "Podaj rok wydania książki do dodania:");
        String cena = JOptionPane.showInputDialog(frame, "Podaj cenę książki do dodania:");
        String ilosc_na_stanie = JOptionPane.showInputDialog(frame, "Podaj ilość danej książeki jaka ma zostać dodana:");
        if(tytul != null && autor != null && rok_wydania != null && cena != null && ilosc_na_stanie != null){
            int rok_wydania_int = Integer.parseInt(rok_wydania);
            int cena_int = Integer.parseInt(cena);
            int ilosc_na_stanie_int = Integer.parseInt(ilosc_na_stanie);
            Book book = new Book(tytul,autor,rok_wydania_int,cena_int,ilosc_na_stanie_int);
            dane.add(book);
            lista.addElement(book.toString());
            JOptionPane.showMessageDialog(frame,"Książka została dodana.");
        }else{
            JOptionPane.showMessageDialog(frame,"Książka nie została dodana.");
        }

    }

    public void usun_ksiazke(DefaultListModel<String> lista, List<Book> dane, JFrame frame){
        String tytul = JOptionPane.showInputDialog(frame, "Podaj tytuł książki do usunięcia:");
        String autor = JOptionPane.showInputDialog(frame, "Podaj autora książki do usunięcia:");
        boolean usun = false;
        int booknr = 0;
        for(Book book : dane){
            String tytul_ksiazki = book.getTitle();
            String autor_ksiazki = book.getAutor();
            booknr++;
            if(tytul.equals(tytul_ksiazki) && autor.equals(autor_ksiazki)){
                usun = true;
                break;
            }
        }
        if(usun){
            lista.removeElementAt(booknr - 1);
            dane.remove(booknr - 1);
            JOptionPane.showMessageDialog(frame,"Książka została pomyślnie usunięta.");
        }else{
            JOptionPane.showMessageDialog(frame,"Nie posiadamy takiej książki.");
        }
    }

    public void wyporzycz_ksiazke(List<Book> dane, JFrame frame){
        String tytul = JOptionPane.showInputDialog(frame, "Podaj tytuł książki do wyporzyczenia:");
        String autor = JOptionPane.showInputDialog(frame, "Podaj autora książki do wyporzyczenia:");
        boolean wyporzycz = false;
        for(Book book : dane){
            String tytul_ksiazki = book.getTitle();
            String autor_ksiazki = book.getAutor();
            if(tytul.equals(tytul_ksiazki) && autor.equals(autor_ksiazki)){
                int ilosc = book.getCount();
                wyporzycz = true;
                if(ilosc >= 1){
                    book.setCount(ilosc - 1);
                    JOptionPane.showMessageDialog(frame,"Książka została wyporzyczona. Dziękujemy");
                    break;
                }else{
                    JOptionPane.showMessageDialog(frame,"Książki tej nie ma aktualnie w magazynie.");
                    break;
                }
            }
        }
        if(!wyporzycz){
            JOptionPane.showMessageDialog(frame,"Nie posiadamy takiej książki");
        }
    }

    public void zwroc_ksiazke(List<Book> dane, JFrame frame){
        String tytul = JOptionPane.showInputDialog(frame, "Podaj tytuł książki do zwrotu:");
        String autor = JOptionPane.showInputDialog(frame, "Podaj autora książki do zwrotu:");
        boolean zwroc = false;
        for(Book book : dane){
            String tytul_ksiazki = book.getTitle();
            String autor_ksiazki = book.getAutor();
            if(tytul.equals(tytul_ksiazki) && autor.equals(autor_ksiazki)){
                zwroc = true;
                int ilosc = book.getCount();
                book.setCount(ilosc + 1);
                JOptionPane.showMessageDialog(frame,"Dziękujemy za skorzystanie z naszych usług.\nZapraszmy ponownie");
                break;
            }
        }
        if(!zwroc){
            JOptionPane.showMessageDialog(frame, "Nie mamy takich książek. Sprawdź w innej księgarni");
        }
    }

    public void kup_ksiazke(List<Book> dane, JFrame frame){
        String tytul = JOptionPane.showInputDialog(frame, "Podaj tytuł książki do kupnienia:");
        String autor = JOptionPane.showInputDialog(frame, "Podaj autora książki do kupienia:");
        boolean kup = false;
        for(Book book : dane){
            String tytul_ksiazki = book.getTitle();
            String autor_ksiazki = book.getAutor();
            if(tytul.equals(tytul_ksiazki) && autor.equals(autor_ksiazki)){
                kup = true;
                int ilosc = book.getCount();
                if(ilosc >= 1){
                    book.setCount(ilosc - 1);
                    JOptionPane.showMessageDialog(frame,"Za chwile powinieneś otrzymać dowód do zapłaty");
                    break;
                }else{
                    JOptionPane.showMessageDialog(frame,"Książka jest niedostępna");
                }
            }
        }
        if(!kup){
            JOptionPane.showMessageDialog(frame,"Nie posiadamy takiej książki.");
        }
    }

    public void szukaj_po_tytule(DefaultListModel<String> lista, List<Book> dane, JTextField textField){
        String tytul = textField.getText();
        lista.clear();
        for(Book book : dane){
            String tytul_ksiazki = book.getTitle();
            if(tytul.equals(tytul_ksiazki)){
                lista.addElement(book.toString());
            }
        }
    }
}
