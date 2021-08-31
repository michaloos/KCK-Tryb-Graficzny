package com.company;
import com.github.javafaker.Faker;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static jdk.nashorn.internal.objects.NativeMath.round;

public class Swing {
    public static void SwingGUI() throws IOException{
        JFrame frame = new JFrame("Księgarnia");

        Student zbudujstudentow = new Student();
        Book zbudujksiazki = new Book();
        List<Student> dane_studentow = new ArrayList<Student>(zbudujstudentow.FakeStudents());
        List<Book> ksiazki = new ArrayList<Book>(zbudujksiazki.FakeBooks());

        DefaultListModel<String> listModelStudent = new DefaultListModel<>();
        for(Student student : dane_studentow){
            String string = student.toString();
            listModelStudent.addElement(string);
        }
        final JList<String> studnetJList = new JList<String>(listModelStudent);
        studnetJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jScrollPanestudent = new JScrollPane();
        jScrollPanestudent.setViewportView(studnetJList);
        studnetJList.setLayoutOrientation(JList.VERTICAL);
        jScrollPanestudent.setBounds(450,40,250,250);
        frame.add(jScrollPanestudent);

        JLabel listastud = new JLabel("Lista Studentów:(Imie | Nazwisko | indeks)");
        listastud.setBounds(450,10,250,30);
        frame.add(listastud);

        DefaultListModel<String> listModelBook = new DefaultListModel<>();
        for(Book book : ksiazki){
            String string = book.toString();
            listModelBook.addElement(string);
        }
        final JList<String> bookJlist = new JList<String>(listModelBook);
        bookJlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jScrollPanebook = new JScrollPane();
        jScrollPanebook.setViewportView(bookJlist);
        bookJlist.setLayoutOrientation(JList.VERTICAL);
        jScrollPanebook.setBounds(720,40,250,250);
        frame.add(jScrollPanebook);

        JButton pelne_info_student = new JButton("Informacje o studencie");
        pelne_info_student.setBounds(450,300,250,30);
        pelne_info_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!studnetJList.isSelectionEmpty()) {
                    int selected = studnetJList.getSelectedIndex();
                    new Student().wypisz_info_student(selected, frame, dane_studentow);
                }
            }
        });
        frame.add(pelne_info_student);

        JButton pelne_info_book = new JButton("Informcje o książce");
        pelne_info_book.setBounds(720,300,250,30);
        pelne_info_book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bookJlist.isSelectionEmpty()) {
                    int selected = bookJlist.getSelectedIndex();
                    new Book().wypisz_info_book(selected, frame, ksiazki);
                }
            }
        });
        frame.add(pelne_info_book);

        JLabel listabook = new JLabel("Lista Książek:(Tytuł | autor | cena)");
        listabook.setBounds(720,10,250,30);
        frame.add(listabook);

        JLabel glowny_label = new JLabel(" Wybierz co chcesz zrobić.");
        glowny_label.setBounds(10,10,200,30);
        frame.add(glowny_label);

        JButton dodaj_studenta = new JButton("Dodaj studenta.");
        dodaj_studenta.setBounds(10,50,150,30);
        dodaj_studenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Student().dodaj_studenta(listModelStudent, dane_studentow, frame);
            }
        });
        frame.add(dodaj_studenta);

        JButton usun_studenta = new JButton("Usun studenta.");
        usun_studenta.setBounds(10,90,150,30);
        usun_studenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Student().usun_studenta(listModelStudent,dane_studentow,frame);
            }
        });
        frame.add(usun_studenta);

        JButton dodaj_ksiazke = new JButton("Dodaj książkę.");
        dodaj_ksiazke.setBounds(210,50,150,30);
        dodaj_ksiazke.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Book().dodaj_ksiazke(listModelBook,ksiazki,frame);
            }
        });
        frame.add(dodaj_ksiazke);

        JButton usun_ksiazke = new JButton("Usuń książkę.");
        usun_ksiazke.setBounds(210,90,150,30);
        usun_ksiazke.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Book().usun_ksiazke(listModelBook,ksiazki,frame);
            }
        });
        frame.add(usun_ksiazke);

        JButton wyporzycz_ksiazke = new JButton("Wyporzycz książkę.");
        wyporzycz_ksiazke.setBounds(10,130,150,30);
        wyporzycz_ksiazke.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Book().wyporzycz_ksiazke(ksiazki,frame);
            }
        });
        frame.add(wyporzycz_ksiazke);

        JButton zwroc_ksiazke = new JButton("Zwróć książkę.");
        zwroc_ksiazke.setBounds(10,170,150,30);
        zwroc_ksiazke.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Book().zwroc_ksiazke(ksiazki,frame);
            }
        });
        frame.add(zwroc_ksiazke);

        JButton kup_ksiazke = new JButton("Kup książkę.");
        kup_ksiazke.setBounds(210,130,150,30);
        kup_ksiazke.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Book().kup_ksiazke(ksiazki,frame);
            }
        });
        frame.add(kup_ksiazke);

        JLabel szukaj_po_tytule = new JLabel(" Wyszukaj książkę po tytule.");
        szukaj_po_tytule.setBounds(10,210,240,30);
        frame.add(szukaj_po_tytule);

        JTextField text_szukaj_po_tytule = new JTextField();
        text_szukaj_po_tytule.setBounds(250,210,150,30);
        frame.add(text_szukaj_po_tytule);

        JLabel szukaj_po_indeksie = new JLabel(" Wyszukaj studenta po numerze indeksu.");
        szukaj_po_indeksie.setBounds(10,250,240,30);
        frame.add(szukaj_po_indeksie);

        JTextField text_szukaj_po_indeksie = new JTextField();
        text_szukaj_po_indeksie.setBounds(250,250,150,30);
        frame.add(text_szukaj_po_indeksie);

        JButton button_szukaj = new JButton("Szukaj");
        button_szukaj.setBounds(190,290,150,30);
        button_szukaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!text_szukaj_po_indeksie.getText().equals("")){
                    new Student().szukaj_po_indeksie(listModelStudent,dane_studentow,text_szukaj_po_indeksie);
                }
                if(!text_szukaj_po_tytule.getText().equals("")){
                    new Book().szukaj_po_tytule(listModelBook,ksiazki,text_szukaj_po_tytule);
                }
            }
        });
        frame.add(button_szukaj);

        JButton zakoncz_szukanie = new JButton("Zakończ szukanie.");
        zakoncz_szukanie.setBounds(10,290,150,30);
        zakoncz_szukanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text_indeks = text_szukaj_po_indeksie.getText();
                String text_tytul = text_szukaj_po_tytule.getText();
                if(!text_szukaj_po_indeksie.getText().equals("")){ listModelStudent.clear(); }
                if(!text_szukaj_po_tytule.getText().equals("")){ listModelBook.clear(); }
                text_szukaj_po_tytule.setText("");
                text_szukaj_po_indeksie.setText("");
                //żeby nie ładwać listy kiedy nie było nic szukane
                if(!text_indeks.equals("")){
                    for(Student student : dane_studentow){
                        String string = student.toString();
                        listModelStudent.addElement(string);
                    }
                }
                if(!text_tytul.equals("")) {
                    for (Book book : ksiazki) {
                        String string = book.toString();
                        listModelBook.addElement(string);
                    }
                }
            }
        });
        frame.add(zakoncz_szukanie);


        JButton wyjscie = new JButton("Wyjście.");
        wyjscie.setBounds(10,330,150,30);
        wyjscie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(wyjscie);

        frame.setSize(1000,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
