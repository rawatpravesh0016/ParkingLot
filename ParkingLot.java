import java.util.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
public class ParkingLot {
    Integer MAX_SIZE = 0;
    private class Car {
        String regNo;
        Integer age;
        public Car(String regNo,Integer age) {
            this.regNo = regNo;
            this.age = age;
        }
    }
    // Available slots list
    ArrayList<Integer> availableSlotList;
    // Map of Slot, Car
    Map<Integer, Car> map1;
    // Map of RegNo, Slot
    Map<String,Integer> map2;
    // Map of age, List of RegNo
    Map<Integer, ArrayList<String>> map3;

    FilesUtil file = new FilesUtil();

    public void createParkingLot(String lotCount) throws IOException {
        try {
            this.MAX_SIZE = Integer.parseInt(lotCount);
        } catch (Exception e) {
            System.out.println("Invalid lot count");
            
        }
        this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= this.MAX_SIZE; i++) {
            availableSlotList.add(i);
        }
        //This maps age to slot numbers of vehicles
        this.map1 = new HashMap<Integer, Car>();
        //This maps registration number to slot
        this.map2 = new HashMap<String,Integer>();
        //This maps age to registration numbers
        this.map3 = new HashMap<Integer, ArrayList<String>>();
        String str = "Created parking of " + lotCount + " slots"+System.lineSeparator();
        file.writeToTextFile("output.txt",str);
    }
    public void park(String regNo,Integer age) throws IOException {
      String slot;
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            
        } else if (this.map1.size() == this.MAX_SIZE) {
            System.out.println("Sorry, parking lot is full");
            
        } else {
            Collections.sort(availableSlotList);
            slot = availableSlotList.get(0).toString();
            Car car = new Car(regNo, age);
            this.map1.put(Integer.parseInt(slot), car);
            this.map2.put(regNo, Integer.parseInt(slot));
            if (this.map3.containsKey(age)) {
                ArrayList<String> regNoList = this.map3.get(age);
                this.map3.remove(age);
                regNoList.add(regNo);
                this.map3.put(age, regNoList);
            } else {
                ArrayList<String> regNoList = new ArrayList<String>();
                regNoList.add(regNo);
                this.map3.put(age, regNoList);
            }
            String str = "Car with vehicle registration number "+regNo+ " has been parked at slot number "+slot+System.lineSeparator();
            file.writeToTextFile("output.txt",str);
            availableSlotList.remove(0);
        }
    }
    public void leave(String slotNo) throws IOException {
      Car carToLeave;
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.map1.size() > 0) {
             carToLeave = this.map1.get(Integer.parseInt(slotNo));
            if (carToLeave != null) {
                this.map1.remove(slotNo);
                this.map2.remove(carToLeave.regNo);
                ArrayList<String> regNoList = this.map3.get(carToLeave.age);
                if (regNoList.contains(carToLeave.regNo)) {
                    regNoList.remove(carToLeave.regNo);
                }
                // Add the Lot No. back to available slot list.
                this.availableSlotList.add(Integer.parseInt(slotNo));
                String str = "Slot number "+ slotNo+ " vacated, the car with vehicle registration number "+carToLeave.regNo+" left the space, the driver of the car was of age "+carToLeave.age+System.lineSeparator();
                file.writeToTextFile("output.txt",str);
            } else {
                System.out.println("Slot number " + slotNo + " is already empty");
                
            }
        } else {
            System.out.println("Parking lot is empty");
            
        }
    }
    public void getRegistrationNumbersFromAge(Integer age) throws IOException {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            
        } else if (this.map3.containsKey(age)) {
            ArrayList<String> regNoList = this.map3.get(age);
            
            for (int i=0; i < regNoList.size(); i++) {
                if (!(i==regNoList.size() - 1)){
                    String str = regNoList.get(i) + ",";
                    file.writeToTextFile("output.txt",str);
                } else {
                    String str = regNoList.get(i);
                    file.writeToTextFile("output.txt",str);
                }
            }
        } else {
            System.out.println("Not found");
            
        }
    }
    public void getSlotNumbersFromAge(Integer age) throws IOException {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.map3.containsKey(age)) {
            ArrayList<String> regNoList = this.map3.get(age);
            ArrayList<Integer> slotList = new ArrayList<Integer>();
            for (int i=0; i < regNoList.size(); i++) {
                slotList.add(Integer.valueOf(this.map2.get(regNoList.get(i))));
            }
            Collections.sort(slotList);
            String str="";
            for (int j=0; j < slotList.size(); j++) {
                if (!(j == slotList.size() - 1)) {
                     str = str + slotList.get(j).toString() + ",";
                } else {
                     str = str + slotList.get(j).toString();
                }
            }
            str=str+System.lineSeparator();
            file.writeToTextFile("output.txt",str);
            
        } else {
            System.out.println("Not found");
            
        }
    }
    public void getSlotNumberFromRegNo(String regNo) throws IOException {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            
        } else if (this.map2.containsKey(regNo)) {
            String str = this.map2.get(regNo).toString()+System.lineSeparator();
            file.writeToTextFile("output.txt",str);
        } else {
            System.out.println("Not found");
            
        }
    }
}