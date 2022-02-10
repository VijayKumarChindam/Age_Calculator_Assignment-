import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class AgeCalculator {
    private static Age DisplayAge(Date birthDate) {
        int years = 0;
        int months = 0;
        int days = 0;

        Calendar birthDay = Calendar.getInstance();
        birthDay.setTimeInMillis(birthDate.getTime());

        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(currentTime);


        years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        int currMonth = now.get(Calendar.MONTH) + 1;
        int birthMonth = birthDay.get(Calendar.MONTH) + 1;

        months = currMonth - birthMonth;

        if (months < 0) {
            years--;
            months = 12 - birthMonth + currMonth;
            if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
                months--;
        } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
            years--;
            months = 11;
        }

        if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
            days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
        else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
            int today = now.get(Calendar.DAY_OF_MONTH);
            now.add(Calendar.MONTH, -1);
            days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
        } else {
            days = 0;
            if (months == 12) {
                years++;
                months = 0;
            }
        }
        return new Age(days, months, years);
    }

    public static void main(String[] args) throws ParseException {
        System.out.print("Enter date of birth in dd/MM/yyyy format: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] d=input.split("/");
        int birthMonth= Integer.parseInt(d[0]);
        int birthDay= Integer.parseInt(d[1]);
        int birthyear= Integer.parseInt(d[2]);

        scanner.close();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//        sdf.setLenient(false);
        Date birthDate = sdf.parse(input);
        if (birthMonth<=12 && birthDay<=31) {
            Age age = DisplayAge(birthDate);
            System.out.println(age);
        }
        else{
            System.out.println("enter the correct date format");
        }

    }
}
