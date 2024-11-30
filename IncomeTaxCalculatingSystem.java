

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IncomeTaxCalculatingSystem {
    
    String EmpID;
    String EmpName;
    Double GrossPay;
    Double BaseSalary;
    Double HRA; 
    Double DA;
    Double IncomeTax;

    IncomeTaxCalculatingSystem(Double GrossPay, String empName, String empID) {
        this.EmpID = empID;
        this.EmpName = empName;
    	this.GrossPay = GrossPay;
        this.BaseSalary = GrossPay / 12; 
        this.HRA = 0.0;
        this.DA = 0.0;
        this.IncomeTax = 0.0;
    }
    
    public double slabTax() { 
        if(GrossPay <= 250000) {
        	IncomeTax = 0.0;
            System.out.println("No tax");
        }else if(GrossPay <= 500000){
            IncomeTax = (GrossPay-250000) * 0.05;
        }else if(GrossPay <= 1000000){
            IncomeTax = (250000*0.05)+((GrossPay-500000)*0.2);
        }else if(GrossPay > 1000000){
            IncomeTax = ((250000*0.05)+500000*0.2)+((GrossPay-1000000)*0.3);
        }
        return IncomeTax;
      }
    
    public double calculateHRA() {
    	double actualHRA = 25000;
    	double rentPaid = BaseSalary*0.1;
    	double locationBased = BaseSalary*0.5;
    	
    	double[] TotalHRA = { actualHRA, rentPaid, locationBased};
    	Arrays.sort(TotalHRA);
    	double minAmount = TotalHRA[0];
    	
    	HRA = actualHRA-minAmount;
    	return HRA;
    	    }
    
    public double calculateDA() {
    	DA = BaseSalary*0.1;
    	return DA;
    }
    public double totalIncomeTax() {
    	double TotalIncomeTax = IncomeTax+HRA+DA;
		return TotalIncomeTax; 
    }
    
    public void display() {
        System.out.println("Basic Income Tax is: "+slabTax());
    	System.out.println("calculated HRA:" +calculateHRA());
    	System.out.println("calculated DA:" +calculateDA());
    	System.out.println("Total Income Tax:" +totalIncomeTax());
    }
   
    public static void main(String abc[]) {
        Scanner s1 = new Scanner(System.in);
        Map<String, IncomeTaxCalculatingSystem>empData = new HashMap<>();
       boolean data = true;

        while (data) {
            System.out.println("\n--- Income Tax Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Display All Employees");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = s1.nextInt();
            

            switch (choice){
                case 1:
                    System.out.print("Enter Employee ID: ");
                    String empID = s1.next();
                    System.out.print("Enter Employee Name: ");
                    String empName = s1.next();
                    System.out.print("Enter Gross Pay: ");
                    double GrossPay = s1.nextDouble();

                    IncomeTaxCalculatingSystem obj = new IncomeTaxCalculatingSystem(GrossPay,empID,empName);
                    obj.slabTax();
                    obj.calculateHRA();
                    obj.calculateDA();

                    empData.put(empID, obj);
                    System.out.println("Employee added successfully!");
                    break;

                case 2: 
                    System.out.print("Enter Employee ID to remove: ");
                    String removeID = s1.next();
                    if(empData.containsKey(removeID)){
                        empData.remove(removeID);
                        System.out.println("Employee removed successfully!");
                    }else{
                        System.out.println("Employee ID not found.");
                    }
                    break;

                case 3: 
                    if(empData.isEmpty()){
                        System.out.println("No employees in the database.");
                    }else{
                        System.out.println("\n******* Employee Details ********");
                        for(IncomeTaxCalculatingSystem x : empData.values()){
                           System.out.println("Employee Name is:" +x.EmpName);
                        System.out.println("Employee Id is:"+x.EmpID);
                        	x.display();
                            System.out.println("*********************");
                        }
                    }
                    break;

                case 4:
                    data =false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
              }
      }
      }
}

