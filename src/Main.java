import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1.  AQICN");
        System.out.println("2.  AirVisual");
        System.out.println("Choose source: ");
        int source = Integer.parseInt(br.readLine());
        System.out.println("Enter city name: ");
        String city = br.readLine();
        switch(source) {
            case 1: Object obj = AqicnReader.getAirQualityIndexForCity(city);
                    System.out.println(obj);
                break;
            case 2: System.out.println("Enter state's full name:");
                    String state = br.readLine();
                    System.out.println("Enter country's full name:");
                    String country = br.readLine();
                    Object obj1 = AirVisualApiReader.getAirQualityForCity(city,state,country);
                    System.out.println(obj1);
                break;
            default: System.out.println("ERROR: Invalid Code");
                break;
        }

    }

}
