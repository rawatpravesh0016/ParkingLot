import java.util.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
class Main {
  public static void main(String[] args) throws IOException {
      FilesUtil file = new FilesUtil();
      ParkingLot parkingLot = new ParkingLot();
      List<String> lines = file.readTextFileByLines("input.txt");

      for(String line : lines){
        StringTokenizer st = new StringTokenizer(line," ");
        List<String> tokens = new ArrayList<String>();
        while (st.hasMoreTokens()) {  
         tokens.add(st.nextToken());  
        }  

        if(tokens.get(0).equals("Create_parking_lot")){
          parkingLot.createParkingLot(tokens.get(1));
        }
        else if(tokens.get(0).equals("Park")){
          parkingLot.park(tokens.get(1),Integer.parseInt(tokens.get(3)));
        }
        else if(tokens.get(0).equals("Vehicle_registration_number_for_driver_of_age")){
          parkingLot.getRegistrationNumbersFromAge(Integer.parseInt(tokens.get(1)));
        }
        else if(tokens.get(0).equals("Leave")){
          parkingLot.leave(tokens.get(1));
        }
        else if(tokens.get(0).equals("Slot_numbers_for_driver_of_age")){
          parkingLot.getSlotNumbersFromAge(Integer.parseInt(tokens.get(1)));
        }
        else if(tokens.get(0).equals("Slot_number_for_car_with_number")){
          parkingLot.getSlotNumberFromRegNo(tokens.get(1));
        }
      }
  }
}
