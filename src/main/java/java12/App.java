package java12;

import jakarta.persistence.EntityManagerFactory;
import java12.congifs.HibernateConfig;
import java12.entities.Address;
import java12.entities.Company;
import java12.entities.Programmer;
import java12.entities.Project;
import java12.services.AddressImpl;
import java12.services.CompanyImpl;
import java12.services.ProgrammerImpl;
import java12.services.ProjectImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AddressImpl address = new AddressImpl();
        CompanyImpl company = new CompanyImpl();
        ProgrammerImpl programmer = new ProgrammerImpl();
        ProjectImpl project = new ProjectImpl();
        LOOP1:
        while (true) {
            System.out.println("""
                    ____________________________________
                    1.Create address
                    2.Get all addresses
                    3.Find address by id
                    4.Remove address bi id
                    5.Update Address by id
                    ____________________________________
                    6.Create Company
                    7.Get all companies
                    8.Find company by id
                    9.Remove company by id
                    10.Update company by id
                    11.Assign Company To Address
                    ____________________________________
                    12.Create programmer
                    13.Get all programmers
                    14.Find programmer by id
                    15.Remove programmer by id
                    16.Update programmer by id
                    17.Assign programmer to project
                    ____________________________________                    
                    18.Save Project 
                    19.Save Long companyId Project project
                    20.DeleteById
                    21.Find By Id
                    22.Get All Projects
                    23.Update By Id
                    24.Assign Project To Company
                    """);
            int action = new Scanner(System.in).nextInt();
            switch (action) {
                case 1 -> {
                    System.out.println(address.create(new Address(new Scanner(System.in).nextLine())));
                }
                case 2 -> {
                    System.out.println(address.getAllAddresses());
                }
                case 3 -> {
                    System.out.println(address.findAddressById(new Scanner(System.in).nextLong()));
                }
                case 4 -> {
                    System.out.println(address.removeAddress(new Scanner(System.in).nextLong()));
                }
                case 5 -> {
                    System.out.println(address.updateAddressById(new Scanner(System.in).nextLong(), new Address(new Scanner(System.in).nextLine())));
                }
                case 6 -> {
                    System.out.println(company.createCompany(new Company(new Scanner(System.in).nextLine(), new ArrayList<>())));
                }
                case 7 -> {
                    System.out.println(company.getAllCompanies());
                }
                case 8 -> {
                    System.out.println(company.findById(new Scanner(System.in).nextLong()));
                }
                case 9 -> {
                    System.out.println(company.removeById(new Scanner(System.in).nextLong()));
                }
                case 10 -> {
                    System.out.println(company.updateById(new Scanner(System.in).nextLong(), new Company(new Scanner(System.in).nextLine(), new ArrayList<>())));
                }
                case 11 -> {
                    System.out.println(company.assignCompanyToAddress(new
                            Scanner(System.in).nextLong(), new Scanner(System.in).nextLong()));
                }
                case 12 -> {
                    System.out.println("Write fullname:");
                    String name = new Scanner(System.in).nextLine();
                    System.out.println("Write email");
                    String email = new Scanner(System.in).nextLine();
                    System.out.println("Write country");
                    String country = new Scanner(System.in).nextLine();
                    Address address1 = new Address(country);
                    Programmer programmer1 = new Programmer(name, email, new ArrayList<>(), address1);
                    address1.setProgrammer(programmer1);
                    System.out.println(programmer.save(programmer1));
                }
                case 13 -> {
                    System.out.println(programmer.getAllProgrammers());
                }
                case 14 -> {
                    System.out.println(programmer.findById(new Scanner(System.in).nextLong()));
                }
                case 15 -> {
                    System.out.println(programmer.deleteById(new Scanner(System.in).nextLong()));
                }
                case 16 -> {
                    System.out.println(programmer.updateById(new Scanner(System.in).nextLong(), new Programmer(new Scanner(System.in).nextLine(), new Scanner(System.in).nextLine())));
                }
                case 17 -> {
                    System.out.println(programmer.assignProgrammerToProject(new Scanner(System.in).nextLong(), new Scanner(System.in).nextLong()));
                }
                case 18 -> {
                    System.out.println(project.save(new Project(new Scanner(System.in).nextLine(), new ArrayList<>())));
                }
                case 19 -> {
                    System.out.println("Write title ");
                    String title = new Scanner(System.in).nextLine();
                    Project project1 = new Project(title, new ArrayList<>());
                    System.out.println(project.save(new Scanner(System.in).nextLong(), project1));
                }
                case 20 -> {
                    System.out.println(project.deleteById(new Scanner(System.in).nextLong()));
                }
                case 21 -> {
                    System.out.println(project.findById(new Scanner(System.in).nextLong()));
                }
                case 22 -> {
                    System.out.println(project.getAllProjects());
                }
                case 23 -> {
                    System.out.println("Write id");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println("Write title");
                    String title = new Scanner(System.in).nextLine();
                    Project project1 = new Project(title, new ArrayList<>());
                    System.out.println(project.updateById(new Scanner(System.in).nextLong(), project1));
                    System.out.println(project.updateById(id, project1));
                }
                case 24 -> {
                    System.out.println(project.assignProjectToCompany(new Scanner(System.in).nextLong(), new Scanner(System.in).nextLong()));
                }
            }
        }
    }
}
