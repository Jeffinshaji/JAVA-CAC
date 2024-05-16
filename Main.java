public class Main {
    public static void main(String[] args){
        data_cleaner file_cleaner = new data_cleaner("NFLX.csv");
        file_cleaner.print_columns();
        file_cleaner.print_dataset();
        file_cleaner.isnull_sum();
        file_cleaner.to_csv("Cleaned_NFLX.csv");
        System.out.println(file_cleaner.row_count());
    }
    
}
