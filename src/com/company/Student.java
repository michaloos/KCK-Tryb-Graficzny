package com.company;

import com.github.javafaker.Faker;

import javax.swing.*;
import java.util.*;
import java.util.List;

public class Student {
    private String name;
    private String nazwisko;
    private int nr_ideksu;
    private int rok_studiow;
    private int ilosc_wyporz_ksiazek;

    public Student(){}

    public Student(String name, String nazwisko, int numer, int rok_studiow, int ilosc_wyporz_ksiazek){
        this.name = name;
        this.nazwisko = nazwisko;
        this.nr_ideksu = numer;
        this.rok_studiow = rok_studiow;
        this.ilosc_wyporz_ksiazek = ilosc_wyporz_ksiazek;
    }

    public String getName(){
        return name;
    }

    public String getNazwisko(){
        return nazwisko;
    }

    public int getNr_ideksu(){
        return nr_ideksu;
    }

    public int getRok_studiow(){
        return rok_studiow;
    }

    public int getIlosc_wyporz_ksiazek() { return ilosc_wyporz_ksiazek; }

    @Override
    public String toString() {
        return name + ' ' + nazwisko + ' ' + nr_ideksu;
    }

    public List<Student> FakeStudents(){
        List<Student> listastudentow = new ArrayList<>();
        for(int i = 0;i<50;i++){
            Random random = new Random();
            Faker faker = new Faker();
            String name = faker.name().firstName();//losowe imię
            String surname = faker.name().lastName();//losowe nazwisko
            int indesk = random.nextInt(10000);
            int ilosc_wyporz_ksiazek = random.nextInt(10);
            int rok_studiow = random.nextInt(6);
            listastudentow.add(new Student(name,surname,indesk,ilosc_wyporz_ksiazek,rok_studiow));
        }
        return listastudentow;
    }

    public void dodaj_studenta(DefaultListModel<String> lista, List<Student> dane_studentow, JFrame frame){
        String imie = JOptionPane.showInputDialog(frame, "Podaj imię studenta do dodania:");
        String nazwisko = JOptionPane.showInputDialog(frame, "Podaj nazwisko studenta do dodania:");
        String numer_indeksu = JOptionPane.showInputDialog(frame, "Podaj numer indeksu studenta do dodania:");
        String rok_studiow = JOptionPane.showInputDialog(frame, "Podaj rok studiów studenta do dodania:");
        String ilosc_wyporz = JOptionPane.showInputDialog(frame, "Podaj ilość wyporzyczonych książek\nprzez studenta do dodania:");
        if(imie != null && nazwisko != null && numer_indeksu != null && rok_studiow != null && ilosc_wyporz != null){
            int numer_indeksu_int = Integer.parseInt(numer_indeksu);
            int rok_studiow_int = Integer.parseInt(rok_studiow);
            int ilosc_wyporz_int = Integer.parseInt(ilosc_wyporz);
            Student student = new Student(imie,nazwisko,numer_indeksu_int,rok_studiow_int,ilosc_wyporz_int);
            dane_studentow.add(student);
            lista.addElement(student.toString());
            JOptionPane.showMessageDialog(frame,"Student został dodany.");
        }else{
            JOptionPane.showMessageDialog(frame,"Student nie został dodany.");
        }
    }

    public void usun_studenta(DefaultListModel<String> lista, List<Student> dane_studentow, JFrame frame){
        String numer_indeksu = JOptionPane.showInputDialog(frame, "Podaj numer indeksu studenta do usunięcia:");
        int numer_indeksu_int = Integer.parseInt(numer_indeksu);
        boolean usun = false;
        int studtnr = 0;
        for(Student student : dane_studentow){
            int numer_studenta = student.getNr_ideksu();
            studtnr++;
            if(numer_indeksu_int == numer_studenta){
                usun = true;
                break;
            }
        }
        if(usun){
            lista.removeElementAt(studtnr - 1);
            dane_studentow.remove(studtnr - 1);
            JOptionPane.showMessageDialog(frame,"Student został pomyślnie usunięty.");
        }else{
            JOptionPane.showMessageDialog(frame,"Takiego studenta nie ma na liście.");
        }
    }

    public void wypisz_info_student(int index, JFrame frame, List<Student> dane){
        JOptionPane.showMessageDialog(frame,"Imie: " + dane.get(index).getName() + "\nNazwisko: " + dane.get(index).getNazwisko() +
                "\nNumer indeksu: " + dane.get(index).getNr_ideksu() + "\nRok studiow: " + dane.get(index).getRok_studiow() +
                "\nIlość wyporzyczonych książek: " + dane.get(index).getIlosc_wyporz_ksiazek());
    }


    public void szukaj_po_indeksie(DefaultListModel<String> lista, List<Student> dane_studentow, JTextField textField){
        String stringindeks = textField.getText();
        int indeks = Integer.parseInt(stringindeks);
        lista.clear();
        for(Student student : dane_studentow){
            int student_indeks = student.getNr_ideksu();
            if(indeks == student_indeks){
                lista.addElement(student.toString());
            }
        }
    }
}
